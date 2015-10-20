package com.arctic.apdu.management.dao;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.arctic.apdu.management.model.RechargeDetails;

public interface RechargeDetailsDAO {
	
	public void saveOrUpdate(Object obj);
	public List<RechargeDetails> completedRecharge(JSONObject input) throws JSONException;

}
