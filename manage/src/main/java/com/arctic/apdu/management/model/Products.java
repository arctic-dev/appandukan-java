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
@Table(name = "ADT_PRODUCTS")
public class Products implements Serializable {

	private static final long serialVersionUID = 1352699266113035613L;
	 
	@Id @GeneratedValue
	@Column(name = "PROD_ID_PK")
	private BigInteger idPk;
	@Column(name = "PROD_SHORT_NAME")
	private String shortName;
	@Column(name = "PROD_FULL_NAME")
	private String fullName;
	@Column(name = "PROD_CODE")
	private String code;
	@Column(name = "PROD_CATG_CODE")
	private String catgCode;
	@Column(name = "PROD_STATUS")
	private String status;
	@Column(name = "PROD_EDITED_AT")
	private Date editedAt;
	@Column(name = "PROD_EDITED_BY")
	private String editedBy;
	
	public BigInteger getIdPk() {
		return idPk;
	}
	public void setIdPk(BigInteger idPk) {
		this.idPk = idPk;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCatgCode() {
		return catgCode;
	}
	public void setCatgCode(String catgCode) {
		this.catgCode = catgCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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