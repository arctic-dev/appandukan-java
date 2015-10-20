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
@Table(name = "ADT_USER_PRODUCT_ACCESS")
public class UserProductAccess implements Serializable {

	private static final long serialVersionUID = -5215021183990680776L;

	@Id
	@GeneratedValue
	@Column(name = "UPA_ID_PK")
	private BigInteger idPk;
	@Column(name = "UPA_PROD_CODE")
	private String prodCode;
	@Column(name = "UPA_UD_USER_ID")
	private String userId;
	@Column(name = "UPA_ACCESS_STATUS")
	private String accessStatus;
	@Column(name = "UPA_CREATED_AT")
	private Date createdAt;
	@Column(name = "UPA_CREATED_BY")
	private String createdBy;
	@Column(name = "UPA_EDITED_AT")
	private Date editedAt;
	@Column(name = "UPA_EDITED_BY")
	private String editedBy;
 

	public BigInteger getIdPk() {
		return idPk;
	}

	public void setIdPk(BigInteger idPk) {
		this.idPk = idPk;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessStatus() {
		return accessStatus;
	}

	public void setAccessStatus(String accessStatus) {
		this.accessStatus = accessStatus;
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