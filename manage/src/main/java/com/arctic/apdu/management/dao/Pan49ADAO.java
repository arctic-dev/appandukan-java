package com.arctic.apdu.management.dao;

import java.util.List;

import com.arctic.apdu.management.model.Pan49A;
import com.arctic.apdu.management.model.PanCoupons;

public interface Pan49ADAO {
	
	public Pan49A getPan49AByMobile(String contactNo);
	public PanCoupons getPanCoupon();
	public void saveOrUpdate(Object obj);
	public List<Pan49A> fetchPan49A(String userId);
	public Pan49A getPanByCoupon(String couponNumber);
	public void deletePanCoupon(PanCoupons coupon);
	

}
