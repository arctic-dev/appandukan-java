package com.arctic.apdu.management.service.impl;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arctic.apdu.management.dao.UserFinanceDAO;
import com.arctic.apdu.management.model.UserFinance;
import com.arctic.apdu.management.service.IUserFinance;
import com.arctic.apdu.management.utils.CoreConstants;

@Service
public class UserFinanceImpl implements IUserFinance {
	
	private static final Logger logger = Logger.getLogger(UserFinanceImpl.class);

	@Autowired
	private UserFinanceDAO userFinanceDAO;
	

	@Override
	@Transactional
	public void updateUserFinance(JSONObject input, Map<String, Object> resultMap) {
		try {
			Double amount = 106.0;
			if(null != input.getString(CoreConstants.AMOUNT)) {
				amount = input.getDouble(CoreConstants.AMOUNT);
			}
			UserFinance ufin = userFinanceDAO.getUserFinance(input.getString(CoreConstants.CURRENT_USER_ID));
			Double newBal = ufin.getMainBal()-amount;
			ufin.setMainBal(newBal);
			Double newUsed = ufin.getMainBal()+amount;
			ufin.setTotalUsed(newUsed);
			ufin.setEditedAt(new Date());
			ufin.setEditedBy(input.getString(CoreConstants.CURRENT_USER_ID));
			userFinanceDAO.saveOrUpdate(ufin);
		} catch (JSONException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JSONException");
			return ;
		}
		
	}


	@Override
	@Transactional
	public void revertUserFinance(JSONObject input, Map<String, Object> resultMap) {
		try {
			Double amount = 106.0;
			if(null != input.getString(CoreConstants.AMOUNT)) {
				amount = input.getDouble(CoreConstants.AMOUNT);
			}
			UserFinance ufin = userFinanceDAO.getUserFinance(input.getString(CoreConstants.CURRENT_USER_ID));
			ufin.setMainBal(ufin.getMainBal()+amount);
			ufin.setTotalUsed(ufin.getTotalUsed()-amount);
			ufin.setEditedAt(new Date());
			ufin.setEditedBy(input.getString(CoreConstants.CURRENT_USER_ID));
			userFinanceDAO.saveOrUpdate(ufin);
		} catch (JSONException e) {
			logger.error(e.toString());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "JSONException");
			return ;
		}		
	}

}
