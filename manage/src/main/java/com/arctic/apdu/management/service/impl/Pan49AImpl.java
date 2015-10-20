package com.arctic.apdu.management.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arctic.apdu.management.dao.Pan49ADAO;
import com.arctic.apdu.management.dao.UserFinanceDAO;
import com.arctic.apdu.management.model.Pan49A;
import com.arctic.apdu.management.model.PanCoupons;
import com.arctic.apdu.management.model.UserFinance;
import com.arctic.apdu.management.service.IPan49A;
import com.arctic.apdu.management.service.IUserFinance;
import com.arctic.apdu.management.utils.CoreConstants;

@Service
public class Pan49AImpl implements IPan49A {

	private static final Logger logger = Logger.getLogger(Pan49AImpl.class);

	@Autowired
	private Pan49ADAO pan49ADAO;

	@Autowired
	private UserFinanceDAO userFinanceDAO;
	
	@Autowired
	private IUserFinance userFinance;
	
	
	@Override
	@Transactional
	public Map<String, Object> createPan49A(String json) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JSONObject input = new JSONObject(json);
			resultMap = checkPanEligibility(input);
			if ("0".equals(resultMap.get(CoreConstants.FLAG))) {
				return resultMap;
			}
			Pan49A pan = buildPan49A(input, resultMap);
			this.pan49ADAO.saveOrUpdate(pan);
			userFinance.updateUserFinance(input, resultMap);
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "PAN Application received successfully");
		} catch (JSONException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JSONException");
		}
		return resultMap;
	}

	@Transactional
	private Map<String, Object> checkPanEligibility(JSONObject input) throws JSONException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(CoreConstants.FLAG, "1");
		UserFinance ufin = this.userFinanceDAO.getUserFinance(input.getString(CoreConstants.CURRENT_USER_ID));
		Double mainBalance = ufin.getMainBal();
		if (mainBalance < 106.0) {
			resultMap.put(CoreConstants.FLAG, "0");
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Insufficient Balance. Minimum Balance Required - INR 106.00");
			return resultMap;
		}
		Pan49A pan = this.pan49ADAO.getPan49AByMobile(input.getString(CoreConstants.CONTACT_NO));
		if (null != pan && null != pan.getRefundStatus() && pan.getRefundStatus().equals(CoreConstants.REFUNDED)) {
			pan.setContactNo(null);
			pan49ADAO.saveOrUpdate(pan);
			resultMap.put(CoreConstants.FLAG, "0");
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Contact Number Already exists");
			return resultMap;
		}
		PanCoupons coupon = this.pan49ADAO.getPanCoupon();
		if (null != coupon) {
			resultMap.put(CoreConstants.COUPON_NO, coupon.getCouponNo());
			pan49ADAO.deletePanCoupon(coupon);
		} else {
			resultMap.put(CoreConstants.FLAG, "0");
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Coupon not available");
		}
		return resultMap;
	}

	private Pan49A buildPan49A(JSONObject input, Map<String, Object> resultMap) throws JSONException {
		Pan49A pan = new Pan49A();
		pan.setAreaCode(input.getString(CoreConstants.AREA_CODE));
		pan.setCouponNo((BigInteger) resultMap.get(CoreConstants.COUPON_NO));
		pan.setTitle(input.getString(CoreConstants.TITLE));
		pan.setFirstName(input.getString(CoreConstants.FIRST_NAME));
		pan.setMiddleName(input.getString(CoreConstants.MIDDLE_NAME));
		pan.setLastName(input.getString(CoreConstants.LAST_NAME));
		pan.setNameAbbrv(input.getString(CoreConstants.NAME_ABBRV));
		pan.setDob(input.getString(CoreConstants.DOB));
		pan.setFirstName(input.getString(CoreConstants.FIRST_NAME));
		pan.setMiddleName(input.getString(CoreConstants.MIDDLE_NAME));
		pan.setLastName(input.getString(CoreConstants.LAST_NAME));
		pan.setFatherFname(input.getString(CoreConstants.F_FNAME));
		pan.setFatherMname(input.getString(CoreConstants.F_MNAME));
		pan.setFatherLname(input.getString(CoreConstants.F_LNAME));
		pan.setCountryCode(input.getString(CoreConstants.COUNTRY_CODE));
		pan.setAreaCode(input.getString(CoreConstants.AREA_CODE));
		pan.setContactNo(input.getString(CoreConstants.CONTACT_NO));
		pan.setEmailId(input.getString(CoreConstants.EMAIL_ID));
		pan.setCreatedAt(new Date());
		pan.setCreatedBy(input.getString(CoreConstants.CURRENT_USER_ID));
		return pan;
	}

	@Override
	@Transactional
	public Map<String, Object> fetchPan49A(String userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Pan49A> pan49AList = pan49ADAO.fetchPan49A(userId);
		if (null != pan49AList && !pan49AList.isEmpty()) {
			resultMap.put(CoreConstants.PAN_FORMS, pan49AList);
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "User PAN forms returned successfully");
		} else {
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "PAN applications not found");
		}
		return resultMap;
	}
	
	@Override
	@Transactional
	public Map<String, Object> refundPan49A(String json) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JSONObject input = new JSONObject(json);
			Pan49A pan = pan49ADAO.getPanByCoupon(input.getString(CoreConstants.COUPON_NO));
			if (null != pan && pan.getCreatedBy().equals(input.getString(CoreConstants.CURRENT_USER_ID))) {
				pan.setRefundStatus(CoreConstants.REFUNDED);
				pan.setRefundAt(new Date());
				pan.setRefundBy(input.getString(CoreConstants.CURRENT_USER_ID));
				userFinance.revertUserFinance(input, resultMap);
				resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
				resultMap.put(CoreConstants.MESSAGE, "User PAN forms returned successfully");
			} else {
				resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
				resultMap.put(CoreConstants.MESSAGE, "PAN fee not refunded");
			}
		} catch (JSONException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JSONException");
		}
			return resultMap;
		}

}
