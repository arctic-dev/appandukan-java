package com.arctic.apdu.management.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.arctic.apdu.management.model.UserDetails;

public interface UserDetailsDAO {
		 
	public void addUserDetails(UserDetails ud);
	public void updateUserDetails(Map<String, Object> inMap);
	public List<UserDetails> getAllSubUsers(BigInteger parentId);
	public UserDetails userDetailsByIdPk(BigInteger id);
	public UserDetails userDetailsByUserId(String userId, BigInteger idPk);
	public UserDetails userDetailsByMobile(String userMobile, BigInteger idPk);
	public UserDetails userDetailsByEmail(String userEmail, BigInteger idPk);
	void saveOrUpdate(Object obj);
	
}
