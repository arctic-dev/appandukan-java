package com.arctic.apdu.management.utils;

public class CoreQueryConstants {
	
	public static final String USER_BY_USER_ID = "from com.arctic.apdu.management.model.UserDetails ud where UPPER(ud.userId) = UPPER(?0)";
	public static final String USER_BY_MOBILE = "from com.arctic.apdu.management.model.UserDetails ud where UPPER(ud.userMobile) = UPPER(?0)";
	public static final String USER_BY_EMAIL = "from com.arctic.apdu.management.model.UserDetails ud where UPPER(ud.userEmail) = UPPER(?0)";
	public static final String ADD_ID_PK = " AND ud.idPk <> ?1";
	public static final String SUB_USER_BY_PARENT = "from com.arctic.apdu.management.model.UserDetails ud where ud.parentId = ?0";
	
	public static final String GET_AVAIL_PROD = "from com.arctic.apdu.management.model.Products prod"
			+ " where prod.code IN (SELECT upa.prodCode FROM com.arctic.apdu.management.model.UserProductAccess upa WHERE UPPER(upa.userId) = UPPER(?0) and upa.accessStatus = '1')";
	public static final String ACCESS_BY_USER_AND_PROD = "from com.arctic.apdu.management.model.UserProductAccess upa where UPPER(upa.userId) = UPPER(?0) and upa.prodCode = ?1";
	
	public static final String GET_USER_AND_PROD = "from com.arctic.apdu.management.model.UserProductAccess upa where upa.prodCode = ?0";

	
	public static final String GET_CATG_NAME = "from com.arctic.apdu.management.model.Categories catg where catg.code in (?0)";
	
	public static final String USER_FIN_BY_USER_ID = "from com.arctic.apdu.management.model.UserFinance ufin where UPPER(ufin.userId) = UPPER(?0)";
	
	public static final String PAN_BY_CONTACT_NO = "from com.arctic.apdu.management.model.Pan49A pan where pan.contactNo = ?0";
	public static final String PAN_BY_USER = "from com.arctic.apdu.management.model.Pan49A pan where pan.createdBy = ?0";
	public static final String PAN_BY_COUPON = "from com.arctic.apdu.management.model.Pan49A pan where UPPER(pan.couponNo) = UPPER(?0)";
	
	public static final String PROVIDERS_BY_PROD = "select rm.name from com.arctic.apdu.management.model.RechargeMast rm where rm.prodCode = ?0";
	public static final String PROVIDERS_BY_NAME = "from com.arctic.apdu.management.model.RechargeMast rm where rm.name = ?0";
	public static final String SUB_USER_FINANCE = "from com.arctic.apdu.management.model.UserFinance ufin "
			+ "where ufin.userId in (select ud.userId from com.arctic.apdu.management.model.UserDetails ud where ud.parentId = ?0)";
	
	public static final String RECHARGE_BY_USER = "from com.arctic.apdu.management.model.RechargeDetails rd where rd.createdBy = ?0 and rd.prodCode = ?1";
	
	public static final String GET_ITRX_LIST = "from com.arctic.apdu.management.model.Itrx";
	public static final String GET_ITRX_FILTER_CREATEDBY = "from com.arctic.apdu.management.model.Itrx itr where itr.itrcreatedby = ?0";
	
	
	public static final String GET_ITRX_FILTER_LIST = "from com.arctic.apdu.management.model.Itrx itr where itr.itridpk = ?0";

	
}
