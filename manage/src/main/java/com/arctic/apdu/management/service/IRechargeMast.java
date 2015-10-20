package com.arctic.apdu.management.service;

import java.util.Map;

public interface IRechargeMast {

	Map<String, Object> getProviders(String type);
	Map<String, Object> makeNewRecharge(String json);
	Map<String, Object> completedRecharge(String json);

}
