package com.arctic.apdu.management.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arctic.apdu.management.dao.UserDetailsDAO;
import com.arctic.apdu.management.dao.UserFinanceDAO;
import com.arctic.apdu.management.dao.UserProductAccessDAO;
import com.arctic.apdu.management.model.CreditTrans;
import com.arctic.apdu.management.model.UserDetails;
import com.arctic.apdu.management.model.UserFinance;
import com.arctic.apdu.management.model.UserProductAccess;
import com.arctic.apdu.management.service.IUserDetails;
import com.arctic.apdu.management.utils.CoreConstants;
import com.arctic.apdu.management.utils.CoreUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class UserDetailsImpl implements IUserDetails {

	private static final Logger logger = Logger.getLogger(UserDetailsImpl.class);

	@Autowired
	private UserDetailsDAO userDetailsDAO;

	@Autowired
	private UserProductAccessDAO userProductAccessDAO;

	@Autowired
	private UserFinanceDAO userFinanceDAO;

	@Transactional
	@Override
	public Map<String, Object> checkUniqueId(String userId) {
		Map<String, Object> resultMap = null;
		resultMap = new HashMap<String, Object>();
		try {
			uniqueIdCheck(URLDecoder.decode(userId, "UTF-8"), resultMap, null);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Unsupported Encoding Exception");
		}
		return resultMap;
	}

	@Transactional
	@Override
	public Map<String, Object> checkUniqueEmail(String input) {
		Map<String, Object> resultMap = null;
		Map<String, Object> inputMap = null;
		try {
			inputMap = new HashMap<String, Object>();
			resultMap = new HashMap<String, Object>();
			inputMap = CoreUtils.jsonToMap(URLDecoder.decode(input, "UTF-8"));
			uniqueEmailCheck(inputMap.get(CoreConstants.USER_EMAIL).toString(), resultMap, null);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Unsupported Encoding Exception");
		} catch (JsonParseException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JsonParseException");
		} catch (JsonMappingException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JsonMappingException");
		} catch (IOException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "IOException");
		}
		return resultMap;
	}

	@Transactional
	@Override
	public Map<String, Object> checkUniqueMobile(String userMobile) {
		Map<String, Object> resultMap = null;
		resultMap = new HashMap<String, Object>();
		try {
			uniqueMobileCheck(URLDecoder.decode(userMobile, "UTF-8"), resultMap, null);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Unsupported Encoding Exception");
		}
		return resultMap;
	}

	@Transactional
	@Override
	public Map<String, Object> addUserDetails(String input) {
		UserDetails ud = null;
		Map<String, Object> inputMap = null;
		Map<String, Object> resultMap = null;
		try {
			inputMap = new HashMap<String, Object>();
			resultMap = new HashMap<String, Object>();
			inputMap = CoreUtils.jsonToMap(URLDecoder.decode(input, "UTF-8"));
			ud = new UserDetails();
			resultMap = checkUniqueUser(inputMap, resultMap, null);
			if ((Boolean) resultMap.get(CoreConstants.USER_IS_UNIQUE)) {
				buildUserDetails(ud, inputMap);
				this.userDetailsDAO.addUserDetails(ud);
				logger.info("Converted HashMap Implementation --->" + inputMap);
				updateUserProductAccess(input);
				createUserFinance(ud, inputMap);
				resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
				resultMap.put(CoreConstants.MESSAGE, "UserCreated Successfully");
				resultMap.put(CoreConstants.USER_ID_PK, ud.getIdPk().toString());
			} else {
				resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			}
		} catch (JsonParseException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JsonParseException");
		} catch (JsonMappingException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JsonMappingException");
		} catch (IOException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "IOException");
		} catch (JSONException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JSONException");
		}
		return resultMap;
	}

	@Transactional
	@Override
	public Map<String, Object> userLogin(String input) {
		UserDetails ud = null;
		Map<String, Object> inputMap = null;
		Map<String, Object> resultMap = null;
		try {
			inputMap = new HashMap<String, Object>();
			resultMap = new HashMap<String, Object>();
			inputMap = CoreUtils.jsonToMap(URLDecoder.decode(input, "UTF-8"));
			ud = new UserDetails();
			ud = userDetailsDAO.userDetailsByUserId(inputMap.get(CoreConstants.USER_ID).toString(), null);
			if (null != ud && "A".equals(ud.getUserStatus())) {
				String[] hashedInKey = CoreUtils
						.getSaltedHash(inputMap.get(CoreConstants.USER_KEY).toString(), ud.getUserSlug()).split(":");
				if (ud.getUserKey().equals(hashedInKey[1])) {
					buildUserDetailsResult(ud, resultMap);
					resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
					resultMap.put(CoreConstants.MESSAGE, "User Logged-in Successfully");
				} else {
					resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
					resultMap.put(CoreConstants.MESSAGE, "Invalid Login Credentials");
				}
			} else {
				resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
				resultMap.put(CoreConstants.MESSAGE, "Inactive account or Account does not exist.");
			}

		} catch (JsonParseException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JsonParseException");
		} catch (JsonMappingException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JsonMappingException");
		} catch (IOException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "IOException");
		}
		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, Object> updateUserDetails(String input) {
		UserDetails ud = null;
		Map<String, Object> inputMap = null;
		Map<String, Object> resultMap = null;
		try {
			inputMap = new HashMap<String, Object>();
			resultMap = new HashMap<String, Object>();
			inputMap = CoreUtils.jsonToMap(URLDecoder.decode(input, "UTF-8"));
			String idPkString = inputMap.get(CoreConstants.USER_ID_PK).toString();
			BigInteger idPk = BigInteger.valueOf(Long.valueOf(idPkString));
			ud = userDetailsDAO.userDetailsByIdPk(idPk);
			resultMap = checkUniqueUser(inputMap, resultMap, idPk);
			if ((Boolean) resultMap.get(CoreConstants.USER_IS_UNIQUE)) {
				buildUserDetails(ud, inputMap);
				this.userDetailsDAO.saveOrUpdate(ud);
				if (null != inputMap.get(CoreConstants.PRODUCTS)) {
					updateUserProductAccess(input);
				}
				resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
				resultMap.put(CoreConstants.MESSAGE, "User updated Successfully");
				resultMap.put(CoreConstants.USER_ID_PK, ud.getIdPk().toString());
			} else {
				resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			}
		} catch (JsonParseException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JsonParseException");
		} catch (JsonMappingException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JsonMappingException");
		} catch (IOException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "IOException");
		} catch (JSONException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JSONException");
		}
		return resultMap;
	}

	// @Override
	// @Transactional
	// public Map<String, Object> forgotPassword(String json) {
	//
	// return resultMap;
	// }
	//
	// @Override
	// @Transactional
	// public Map<String, Object> changePassword(String json) {
	//
	// return resultMap;
	// }
	//
	// @Override
	// @Transactional
	// public Map<String, Object> resendOTP(String json) {
	//
	// return resultMap;
	// }

	@Override
	@Transactional
	public Map<String, Object> getAllSubUsers(BigInteger id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<UserDetails> userDetailsList = userDetailsDAO.getAllSubUsers(id);
		if (null != userDetailsList && !userDetailsList.isEmpty()) {
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			for (UserDetails ud : userDetailsList) {
				Map<String, Object> userMap = new HashMap<String, Object>();
				buildUserDetailsResult(ud, userMap);
				resultList.add(userMap);
			}
			resultMap.put(CoreConstants.SUB_USERS, resultList);
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "User details returned successfully");
		} else {
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "User not found");
		}
		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, Object> userDetailsByIdPk(BigInteger id) {
		Map<String, Object> resultMap = null;
		resultMap = new HashMap<String, Object>();
		UserDetails ud = userDetailsDAO.userDetailsByIdPk(id);
		if (null != ud) {
			buildUserDetailsResult(ud, resultMap);
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "User details returned successfully");
		} else {
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "User not found");
		}
		return resultMap;
	}

	@Transactional
	@Override
	public Map<String, Object> addUserBal(String json) {
		Double openBal = 0.0;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JSONObject input = new JSONObject(json);
			CreditTrans cr = new CreditTrans();
			UserFinance ufin = userFinanceDAO.getUserFinance(input.getString(CoreConstants.USER_ID));

			if (null != ufin) {
				openBal = ufin.getMainBal();
			}
			cr.setUserId(input.getString(CoreConstants.USER_ID));
			cr.setType("CR");
			cr.setOpenBal(openBal);
			cr.setAmount(input.getDouble(CoreConstants.AMOUNT));
			cr.setFee(input.getDouble(CoreConstants.AMOUNT) * input.getDouble(CoreConstants.FEE_PERC) * 0.01);
			cr.setNewBal(openBal + cr.getAmount() - cr.getFee());
			cr.setCreatedBy(input.getString(CoreConstants.PARENT_ID));
			cr.setCreatedAt(new Date());
			this.userDetailsDAO.saveOrUpdate(cr);
			resultMap = updateUserFinance(ufin, cr, input);
		} catch (JSONException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JSONException");
		}
		return resultMap;
	}

	@Transactional
	@Override
	public Map<String, Object> deductUserBal(String json) {
		// TODO Auto-generated method stub
		return null;
	}

	private Map<String, Object> checkUniqueUser(Map<String, Object> inputMap, Map<String, Object> resultMap,
			BigInteger idPk) {
		Boolean isUnique = false;
		resultMap.put(CoreConstants.USER_IS_UNIQUE, isUnique);
		// if (null != inputMap.get(CoreConstants.USER_ID_PK)) {
		// resultMap.put(CoreConstants.MESSAGE, "Incorrect Service Request");
		// return resultMap;
		// }
		if ((Boolean) uniqueIdCheck(inputMap.get(CoreConstants.USER_ID).toString(), resultMap, idPk)
				.get(CoreConstants.USER_IS_UNIQUE)) {
			if ((Boolean) uniqueEmailCheck(inputMap.get(CoreConstants.USER_EMAIL).toString(), resultMap, idPk)
					.get(CoreConstants.USER_IS_UNIQUE)) {
				if ((Boolean) uniqueMobileCheck(inputMap.get(CoreConstants.USER_MOBILE).toString(), resultMap, idPk)
						.get(CoreConstants.USER_IS_UNIQUE)) {
					isUnique = true;
					resultMap.put(CoreConstants.USER_IS_UNIQUE, isUnique);
					resultMap.put(CoreConstants.MESSAGE, "User is Unique");
				}
			}
		}
		return resultMap;
	}

	private Map<String, Object> uniqueIdCheck(String userId, Map<String, Object> resultMap, BigInteger idPk) {
		Boolean isUnique = false;
		resultMap.put(CoreConstants.USER_IS_UNIQUE, isUnique);
		if (null == userId || null != userDetailsDAO.userDetailsByUserId(userId, idPk)) {
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "User ID Already exists");
		} else {
			isUnique = true;
			resultMap.put(CoreConstants.USER_IS_UNIQUE, isUnique);
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "User ID is available");
		}
		return resultMap;
	}

	private Map<String, Object> uniqueEmailCheck(String userEmail, Map<String, Object> resultMap, BigInteger idPk) {
		Boolean isUnique = false;
		resultMap.put(CoreConstants.USER_IS_UNIQUE, isUnique);
		if (null == userEmail || null != userDetailsDAO.userDetailsByEmail(userEmail, idPk)) {
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Email ID Already exists");
		} else {
			isUnique = true;
			resultMap.put(CoreConstants.USER_IS_UNIQUE, isUnique);
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "Email is allowed");
		}
		return resultMap;
	}

	private Map<String, Object> uniqueMobileCheck(String userMobile, Map<String, Object> resultMap, BigInteger idPk) {
		Boolean isUnique = false;
		resultMap.put(CoreConstants.USER_IS_UNIQUE, isUnique);
		if (null == userMobile || null != userDetailsDAO.userDetailsByMobile(userMobile, idPk)) {
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Mobile Number Already exists");
		} else {
			isUnique = true;
			resultMap.put(CoreConstants.USER_IS_UNIQUE, isUnique);
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "Mobile Number is allowed");
		}
		return resultMap;
	}

	@Transactional
	private void updateUserProductAccess(String inputMap) throws JSONException {
		logger.info(inputMap);
		JSONObject input = new JSONObject(inputMap);
		JSONArray products = input.getJSONArray(CoreConstants.PRODUCTS);
		String userId = input.getString(CoreConstants.USER_ID);
		logger.info(userId.toString());
		for (int i = 0; i < products.length(); i++) {
			JSONObject json = products.getJSONObject(i);
			UserProductAccess upa = this.userProductAccessDAO.getUserProductAccess(userId,
					json.getString(CoreConstants.PROD_CODE));
			if (null != upa) {
				upa.setAccessStatus(json.getString(CoreConstants.PROD_STATUS));
				upa.setEditedAt(new Date());
				upa.setEditedBy(input.getString(CoreConstants.CURRENT_USER_ID));
			} else {
				upa = new UserProductAccess();
				upa.setUserId(userId);
				upa.setProdCode(json.getString(CoreConstants.PROD_CODE));
				upa.setAccessStatus(json.getString(CoreConstants.PROD_STATUS));
				upa.setCreatedBy(input.getString(CoreConstants.CURRENT_USER_ID));
				upa.setCreatedAt(new Date());
			}
			this.userProductAccessDAO.saveUserProductAccess(upa);
		}
	}

	@Transactional
	private void createUserFinance(UserDetails ud, Map<String, Object> inputMap) {
		UserFinance ufin = new UserFinance();
		ufin.setUserId(ud.getUserId());
		ufin.setUseridPk(ud.getIdPk());
		ufin.setFeePerc(0.0);
		ufin.setCommEarned(0.0);
		ufin.setTotalComm(0.0);
		ufin.setTotalUsed(0.0);
		ufin.setMainBal(0.0);
		ufin.setTotalCredited(0.0);
		ufin.setEditedAt(new Date());
		ufin.setEditedBy(inputMap.get(CoreConstants.PARENT_ID).toString());
		this.userDetailsDAO.saveOrUpdate(ufin);

	}

	@Transactional
	private Map<String, Object> updateUserFinance(UserFinance ufin, CreditTrans cr, JSONObject input)
			throws JSONException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Double totalCredited = 0.0;
		if (null == ufin) {
			ufin = new UserFinance();
			ufin.setUserId(input.getString(CoreConstants.USER_ID));
			ufin.setUseridPk(BigInteger.valueOf(input.getInt(CoreConstants.USER_ID_PK)));
			ufin.setFeePerc(input.getDouble(CoreConstants.FEE_PERC));
			ufin.setCommEarned(0.0);
			ufin.setTotalComm(0.0);
			ufin.setTotalUsed(0.0);
		} else {
			totalCredited = ufin.getTotalCredited();
		}
		ufin.setMainBal(cr.getNewBal());
		ufin.setTotalCredited(totalCredited + cr.getNewBal() - cr.getOpenBal());
		ufin.setEditedAt(new Date());
		ufin.setEditedBy(input.getString(CoreConstants.PARENT_ID));
		this.userDetailsDAO.saveOrUpdate(ufin);
		resultMap.put(CoreConstants.MAIN_BALANCE, ufin.getMainBal());
		resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
		resultMap.put(CoreConstants.MESSAGE, "Amount added to user successfully.");
		return resultMap;
	}

	@Override
	public Map<String, Object> forgotPassword(String json) {
		// TODO Auto-generated method stub
		return null;
	}

	private void buildUserDetails(UserDetails ud, Map<String, Object> inputMap) {
		ud.setUserId(inputMap.get(CoreConstants.USER_ID).toString());
		ud.setUserName(inputMap.get(CoreConstants.USER_NAME).toString());
		if (null != inputMap.get(CoreConstants.USER_KEY)) {
			String[] saltedPassword = CoreUtils.getSaltedHash(inputMap.get(CoreConstants.USER_KEY).toString(), null)
					.split(":");
			ud.setUserKey(saltedPassword[1]);
			ud.setUserSlug(saltedPassword[0]);
		}
		ud.setUserMobile(inputMap.get(CoreConstants.USER_MOBILE).toString());
		ud.setUserEmail(inputMap.get(CoreConstants.USER_EMAIL).toString());
		ud.setUserAddress1(inputMap.get(CoreConstants.USER_ADDRESS1).toString());
		ud.setUserAddress2(inputMap.get(CoreConstants.USER_ADDRESS2).toString());
		ud.setUserCity(inputMap.get(CoreConstants.USER_CITY).toString());
		ud.setUserState(inputMap.get(CoreConstants.USER_STATE).toString());
		ud.setUserPincode(Integer.valueOf(inputMap.get(CoreConstants.USER_PINCODE).toString()));
		ud.setUserStatus(inputMap.get(CoreConstants.USER_STATUS).toString());
		ud.setUserType(inputMap.get(CoreConstants.USER_TYPE).toString());
		ud.setParentId(inputMap.get(CoreConstants.PARENT_ID_PK).toString());
		if (null != inputMap.get(CoreConstants.USER_ID_PK)) {
			ud.setEditedAt(new Date());
			ud.setEditedBy(inputMap.get(CoreConstants.CURRENT_USER_ID).toString());
		} else {
			ud.setCreatedAt(new Date());
			ud.setCreatedBy(inputMap.get(CoreConstants.CURRENT_USER_ID).toString());
		}
	}

	private Map<String, Object> buildUserDetailsResult(UserDetails ud, Map<String, Object> resultMap) {
		resultMap.put(CoreConstants.USER_ID_PK, ud.getIdPk());
		resultMap.put(CoreConstants.USER_ID, ud.getUserId());
		resultMap.put(CoreConstants.USER_NAME, ud.getUserName());
		resultMap.put(CoreConstants.USER_EMAIL, ud.getUserEmail());
		resultMap.put(CoreConstants.USER_MOBILE, ud.getUserMobile());
		resultMap.put(CoreConstants.USER_TYPE, ud.getUserType());
		resultMap.put(CoreConstants.PARENT_ID_PK, ud.getParentId());
		resultMap.put(CoreConstants.USER_ADDRESS1, ud.getUserAddress1());
		resultMap.put(CoreConstants.USER_ADDRESS2, ud.getUserAddress2());
		resultMap.put(CoreConstants.USER_CITY, ud.getUserCity());
		resultMap.put(CoreConstants.USER_STATE, ud.getUserState());
		resultMap.put(CoreConstants.USER_PINCODE, ud.getUserPincode());
		resultMap.put(CoreConstants.USER_STATUS, ud.getUserStatus());
		resultMap.put(CoreConstants.USER_TYPE, ud.getUserType());
		if (!ud.getUserType().equals("SA")) {
			UserFinance ufin = userFinanceDAO.getUserFinance(resultMap.get(CoreConstants.USER_ID).toString());
			resultMap.put(CoreConstants.CURRENT_BAL, ufin.getMainBal());
			BigInteger parentIdPk = BigInteger.valueOf(Long.valueOf(ud.getParentId()));
			UserDetails parentUd = userDetailsDAO.userDetailsByIdPk(parentIdPk);
			resultMap.put(CoreConstants.PARENT_ID, parentUd.getUserId());
		}
		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, Object> getSubUserFinance(BigInteger id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<UserFinance> userDetailsList = userFinanceDAO.getSubUserFinance(id);
		if (null != userDetailsList && !userDetailsList.isEmpty()) {
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			for (UserFinance ufin : userDetailsList) {
				Map<String, Object> userMap = new HashMap<String, Object>();
				buildUserFinance(ufin, userMap);
				resultList.add(userMap);
			}
			resultMap.put(CoreConstants.SUB_USERS, resultList);
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "User Finance returned successfully");
		} else {
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "User not found");
		}
		return resultMap;
	}

	private void buildUserFinance(UserFinance ufin, Map<String, Object> userMap) {
		userMap.put(CoreConstants.USER_ID, ufin.getUserId());
		userMap.put(CoreConstants.USER_ID_PK, ufin.getUseridPk());
		userMap.put(CoreConstants.CURRENT_BAL, ufin.getMainBal());
		userMap.put(CoreConstants.TOTAL_CREDITED, ufin.getTotalCredited());
		userMap.put(CoreConstants.TOTAL_USED, ufin.getTotalUsed());
		userMap.put(CoreConstants.TOTAL_COMM_EARNED, ufin.getTotalComm());
	}

	@Override
	@Transactional
	public Map<String, Object> additrxreg(String json) throws JSONException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject input = new JSONObject(json);
		String userId = input.getString(CoreConstants.USER_ID);
		String prodcode = input.getString(CoreConstants.PROD_CODE);
		UserFinance ufinobj = userFinanceDAO.getUserFinance(userId);

		Double itrxamount = 99d;
		UserProductAccess userDetailsList = this.userProductAccessDAO.getUserProductAccess(userId, prodcode);
		System.out.println("userDetailsList.size()==0------>" + userDetailsList);
		if ((userDetailsList == null)) {
			if (ufinobj.getMainBal() > itrxamount) {
				UserProductAccess upaobj = new UserProductAccess();
				upaobj.setProdCode(prodcode);
				upaobj.setUserId(userId);
				upaobj.setAccessStatus("1");
				upaobj.setCreatedAt(new Date());
				upaobj.setCreatedBy(userId);
				upaobj.setEditedAt(new Date());
				upaobj.setEditedBy(userId);
				userProductAccessDAO.saveUserProductAccess(upaobj);

				UserFinance ufin = userFinanceDAO.getUserFinance(userId);
				Double newBal = ufin.getMainBal() - itrxamount;
				ufin.setMainBal(newBal);
				Double totlusd = ufin.getTotalUsed() + itrxamount;
				ufin.setTotalUsed(totlusd);
				ufin.setEditedAt(new Date());
				ufin.setEditedBy(userId);
				userFinanceDAO.saveOrUpdate(ufin);

				resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
				resultMap.put(CoreConstants.MESSAGE, "Product added successfully");

			} else {
				resultMap.put(CoreConstants.STATUS, CoreConstants.ERROR);
				resultMap.put(CoreConstants.MESSAGE, "Insufficient Balance");

			}

		} else {

			resultMap.put(CoreConstants.STATUS, CoreConstants.ERROR);
			resultMap.put(CoreConstants.MESSAGE, "User already exist");
		}

		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, Object> getuserprdaccess(String json) throws JSONException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject input = new JSONObject(json);
		String prodcode = input.getString(CoreConstants.PROD_CODE);
		List<UserProductAccess> usdl = userProductAccessDAO.getUsrprodaccess(prodcode);
		Map<String, Object> userProdMap = null;
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (UserProductAccess ufin : usdl) {
			userProdMap = new HashMap<String, Object>();
			UserDetails ud = userDetailsDAO.userDetailsByUserId(ufin.getUserId(), null);
			buildITVResult(ud, ufin, userProdMap);
			resultList.add(userProdMap);
		}
		if(!resultList.isEmpty()) {
			resultMap.put(CoreConstants.IT_UPLOAD, resultList);
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "Uploaded IT user lists returned for verification");
		} else {
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "No user available for verification");
		}
		return resultMap;
	}
	
	private Map<String, Object> buildITVResult(UserDetails ud, UserProductAccess upa, Map<String, Object> resultMap) {
		resultMap.put(CoreConstants.USER_ID_PK, ud.getIdPk());
		resultMap.put(CoreConstants.USER_ID, ud.getUserId());
		resultMap.put(CoreConstants.USER_NAME, ud.getUserName());
		resultMap.put(CoreConstants.USER_IMAGE, null != ud.getUserlink() ? ud.getUserlink() : null);
		resultMap.put(CoreConstants.USER_TYPE, ud.getUserType());
		resultMap.put(CoreConstants.PROD_STATUS, upa.getAccessStatus());
		return resultMap;
	}

}