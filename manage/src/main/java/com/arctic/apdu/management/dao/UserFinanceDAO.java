package com.arctic.apdu.management.dao;

import java.math.BigInteger;
import java.util.List;

import com.arctic.apdu.management.model.UserFinance;

public interface UserFinanceDAO {
	
	public UserFinance getUserFinance(String userId);
	public void saveOrUpdate(Object obj);
	public List<UserFinance> getSubUserFinance(BigInteger id);

}
