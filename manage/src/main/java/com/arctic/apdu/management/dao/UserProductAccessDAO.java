package com.arctic.apdu.management.dao;

import java.util.List;

import com.arctic.apdu.management.model.UserProductAccess;

public interface UserProductAccessDAO {

	public UserProductAccess getUserProductAccess(String userId, String prodCode);
	public void saveUserProductAccess(UserProductAccess upa);
	public void addProductAccess(UserProductAccess upa);
	
	public List<UserProductAccess> getUsrprodaccess(String prodCode);
	
}
