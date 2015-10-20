package com.arctic.apdu.management.service;

import java.util.Map;

public interface IPan49A {

	public Map<String, Object> createPan49A(String json);
	public Map<String, Object> fetchPan49A(String userId);
	public Map<String, Object> refundPan49A(String json);

}
