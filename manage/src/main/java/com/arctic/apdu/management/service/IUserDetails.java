package com.arctic.apdu.management.service;

import java.math.BigInteger;
import java.util.Map;

import org.json.JSONException;

public interface IUserDetails {
	
	public Map<String, Object> checkUniqueId(String userId);
	public Map<String, Object> checkUniqueEmail(String userEmail);
	public Map<String, Object> checkUniqueMobile(String userMobile);
	public Map<String, Object> addUserDetails(String input);
	public Map<String, Object> updateUserDetails(String input);
	public Map<String, Object> userLogin(String input);
	
	
	public Map<String, Object> getAllSubUsers(BigInteger id);
	public Map<String, Object> userDetailsByIdPk(BigInteger id);
	public Map<String, Object> addUserBal(String json);
	public Map<String, Object> deductUserBal(String json);
	public Map<String, Object> forgotPassword(String json);
	public Map<String, Object> getSubUserFinance(BigInteger id);
	
	
	
	public Map<String, Object> additrxreg(String json) throws JSONException;
	public Map<String, Object> getuserprdaccess(String json ) throws JSONException;
		
}
