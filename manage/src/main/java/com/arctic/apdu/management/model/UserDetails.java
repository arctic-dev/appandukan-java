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
@Table(name = "ADT_USER_DETAILS")
public class UserDetails implements Serializable {
 
    private static final long serialVersionUID = -7788619177798333712L;
    
    @Id @GeneratedValue
    @Column(name = "UD_ID_PK")
    private BigInteger idPk;
    @Column(name = "UD_USER_ID")
    private String userId;
    @Column(name = "UD_USER_KEY")
    private String userKey;
    @Column(name = "UD_USER_NAME")
    private String userName;
    @Column(name = "UD_USER_TYPE")
    private String userType;
    @Column(name = "UD_PARENT_ID")
    private String parentId;
    @Column(name = "UD_USER_EMAIL")
    private String userEmail;
    @Column(name = "UD_USER_MOBILE")
    private String userMobile;
    @Column(name = "UD_USER_ADDRESS1")
    private String userAddress1;
    @Column(name = "UD_USER_ADDRESS2")
    private String userAddress2;
    @Column(name = "UD_USER_CITY")
    private String userCity;
    @Column(name = "UD_USER_STATE")
    private String userState;
    @Column(name = "UD_USER_PINCODE")
    private Integer userPincode;
    @Column(name = "UD_USER_STATUS")
    private String userStatus;
    @Column(name = "UD_USER_SLUG")
    private String userSlug;
    @Column(name = "UD_CREATED_AT")
    private Date createdAt;
    @Column(name = "UD_CREATED_BY")
    private String createdBy;
    @Column(name = "UD_EDITED_AT")
    private Date editedAt;
    @Column(name = "UD_EDITED_BY")
    private String editedBy;
	@Column(name = "UD_USER_LINK")
	private String userlink;
	

	public String getUserlink() {
		return userlink;
	}

	public void setUserlink(String userlink) {
		this.userlink = userlink;
	}
    
    
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
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserAddress1() {
		return userAddress1;
	}
	public void setUserAddress1(String userAddress1) {
		this.userAddress1 = userAddress1;
	}
	public String getUserAddress2() {
		return userAddress2;
	}
	public void setUserAddress2(String userAddress2) {
		this.userAddress2 = userAddress2;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public Integer getUserPincode() {
		return userPincode;
	}
	public void setUserPincode(Integer userPincode) {
		this.userPincode = userPincode;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserSlug() {
		return userSlug;
	}
	public void setUserSlug(String userSlug) {
		this.userSlug = userSlug;
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
	public Date getEditedAt() {
		return editedAt;
	}
	public void setEditedAt(Date editedAt) {
		this.editedAt = editedAt;
	}
	public String getEditedBy() {
		return editedBy;
	}
	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
	}
    
}