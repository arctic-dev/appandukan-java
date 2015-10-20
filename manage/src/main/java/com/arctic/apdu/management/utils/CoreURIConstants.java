package com.arctic.apdu.management.utils;

public class CoreURIConstants {

	public static final String GET_USER_INFO = "/user/{idPk}";
	public static final String GET_SUB_USERS = "/user/subusers/{idPk}";
	public static final String CREATE_USER = "/user/create";
	public static final String UPDATE_USER = "/user/update";

	public static final String LOGIN_USER = "/user/login";

	public static final String CHECK_UNIQUE_ID = "/user/check/id/{userId}";
	public static final String CHECK_UNIQUE_MOBILE = "/user/check/mobile/{userMobile}";
	public static final String CHECK_UNIQUE_EMAIL = "/user/check/email";

	public static final String GET_AVAIL_PRODS = "/products/available/{userId}";
	public static final String USER_ADD_BAL = "/user/balance/add";
	public static final String USER_DEDUCT_BAL = "/user/balance/deduct/";
	public static final String SUB_USER_FINANCE = "/user/finance/subuser/{idPk}";

	public static final String PAN_49A_CREATE = "/pan49a/create/";
	public static final String PAN_49A_FETCH = "/pan49a/getForm/{userId}";
	public static final String PAN_49A_REFUND = "/pan49a/refund/{couponNumber}";

	public static final String GET_PROVIDER_LIST = "/recharge/provider/{prodCode}";
	public static final String NEW_RECHARGE = "/recharge/new/";
	public static final String COMPLETED_RECHARGE = "/recharge/completed/";

	public static final String ITRX_REGTN = "/itrx/create/";
	public static final String GET_USER_PRD_ACCESS = "/user/prodaccess";
	public static final String ITRX_FORM_REGTN = "/itrx/formregtn";
	public static final String GET_ITRX_LIST = "/itrx/list";
	public static final String UPDATE_ITR_RECEIPT = "/itrx/receipt";
	public static final String GET_ITRX_LISTFILTER_CREATED = "/itrx/createdby";
	

}
