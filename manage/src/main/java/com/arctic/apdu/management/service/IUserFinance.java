package com.arctic.apdu.management.service;

import java.util.Map;

import org.json.JSONObject;

public interface IUserFinance {

	public void updateUserFinance(JSONObject input, Map<String, Object> resultMap);
	public void revertUserFinance(JSONObject input, Map<String, Object> resultMap);

}
