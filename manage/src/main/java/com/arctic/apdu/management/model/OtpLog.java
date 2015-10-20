package com.arctic.apdu.management.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADT_OTP_LOG")
public class OtpLog implements Serializable {
 
    private static final long serialVersionUID = -7788619177548333712L;
    
    @Id @GeneratedValue
    @Column(name = "OL_ID_PK")
    private BigInteger idPk;
    @Column(name = "OL_USER_ID")
    private String userId;
    @Column(name = "OL_PASSWORD")
    private String password;
    @Column(name = "OL_TIME_SENT")
    private Date timeSent;
    @Column(name = "OL_OTP_EXPIRED")
    private String otpExpired;
    
	public BigInteger getIdPk() {
		return idPk;
	}
	public void setIdPk(BigInteger idPk) {
		this.idPk = idPk;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getTimeSent() {
		return timeSent;
	}
	public void setTimeSent(Date timeSent) {
		this.timeSent = timeSent;
	}
	public String getOtpExpired() {
		return otpExpired;
	}
	public void setOtpExpired(String otpExpired) {
		this.otpExpired = otpExpired;
	}
	
}