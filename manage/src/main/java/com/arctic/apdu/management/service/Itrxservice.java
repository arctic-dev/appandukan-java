package com.arctic.apdu.management.service;

import java.util.Map;

import org.json.JSONException;

public interface Itrxservice {

	public Map<String, Object> createitrxform(String json) throws JSONException;

	public Map<String, Object> getitrxform() throws JSONException;

	public Map<String, Object> updateitrxreceipt(String json) throws JSONException;
	
	public Map<String, Object> getitrcreatedby(String json) throws JSONException;
	
	

}
