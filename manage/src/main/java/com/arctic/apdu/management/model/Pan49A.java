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
@Table(name = "ADT_PAN_49A")
public class Pan49A implements Serializable {

	private static final long serialVersionUID = 3993907902577235426L;
	
	@Id @GeneratedValue
	@Column(name = "PAN_ID_PK")
	private BigInteger idPk;
	@Column(name = "PAN_COUPON_NO")
	private BigInteger couponNo;
	@Column(name = "PAN_TITLE")
	private String title;
	@Column(name = "PAN_FIRST_NAME")
	private String firstName;
	@Column(name = "PAN_MIDDLE_NAME")
	private String middleName;
	@Column(name = "PAN_LAST_NAME")
	private String lastName;
	@Column(name = "PAN_NAME_ABBRV")
	private String nameAbbrv;
	@Column(name = "PAN_DOB")
	private String dob;
	@Column(name = "PAN_FATHER_FNAME")
	private String fatherFname;
	@Column(name = "PAN_FATHER_MNAME")
	private String fatherMname;
	@Column(name = "PAN_FATHER_LNAME")
	private String fatherLname;
	@Column(name = "PAN_COUNTRY_CODE")
	private String countryCode;
	@Column(name = "PAN_AREA_CODE")
	private String areaCode;
	@Column(name = "PAN_CONTACT_NO")
	private String contactNo;
	@Column(name = "PAN_EMAIL_ID")
	private String emailId;
	@Column(name = "PAN_CREATED_AT")
	private Date createdAt;
	@Column(name = "PAN_CREATED_BY")
	private String createdBy;
	@Column(name = "PAN_REFUND_STATUS")
	private String refundStatus;
	@Column(name = "PAN_REFUND_AT")
	private Date refundAt;
	@Column(name = "PAN_REFUND_BY")
	private String refundBy;
	public BigInteger getIdPk() {
		return idPk;
	}
	public void setIdPk(BigInteger idPk) {
		this.idPk = idPk;
	}
	public BigInteger getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(BigInteger couponNo) {
		this.couponNo = couponNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNameAbbrv() {
		return nameAbbrv;
	}
	public void setNameAbbrv(String nameAbbrv) {
		this.nameAbbrv = nameAbbrv;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getFatherFname() {
		return fatherFname;
	}
	public void setFatherFname(String fatherFname) {
		this.fatherFname = fatherFname;
	}
	public String getFatherMname() {
		return fatherMname;
	}
	public void setFatherMname(String fatherMname) {
		this.fatherMname = fatherMname;
	}
	public String getFatherLname() {
		return fatherLname;
	}
	public void setFatherLname(String fatherLname) {
		this.fatherLname = fatherLname;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	public Date getRefundAt() {
		return refundAt;
	}
	public void setRefundAt(Date refundAt) {
		this.refundAt = refundAt;
	}
	public String getRefundBy() {
		return refundBy;
	}
	public void setRefundBy(String refundBy) {
		this.refundBy = refundBy;
	}
	
}
