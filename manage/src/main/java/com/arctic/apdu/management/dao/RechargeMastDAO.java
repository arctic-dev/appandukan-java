package com.arctic.apdu.management.dao;

import java.util.List;

import com.arctic.apdu.management.model.RechargeMast;

public interface RechargeMastDAO {
	
	public List<String> getProviders(String prodCode);
	public RechargeMast getRechargePlan(String provider);

}
