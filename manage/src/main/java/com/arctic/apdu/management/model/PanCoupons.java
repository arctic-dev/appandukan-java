package com.arctic.apdu.management.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADT_PAN_COUPONS")
public class PanCoupons implements Serializable {
 
    private static final long serialVersionUID = -7788619154548951712L;
  
    @Id
    @Column(name = "PC_COUPON_NO")
    private BigInteger couponNo;
    @Column(name = "PC_STATE")
    private String state;
    
	public BigInteger getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(BigInteger couponNo) {
		this.couponNo = couponNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    
}
