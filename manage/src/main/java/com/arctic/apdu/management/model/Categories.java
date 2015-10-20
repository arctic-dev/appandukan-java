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
@Table(name = "ADT_CATEGORIES")
public class Categories implements Serializable {
    
	private static final long serialVersionUID = 1222717904390449993L;
	
	@Id @GeneratedValue
    @Column(name = "CATG_ID_PK")
    private BigInteger idPk;
    @Column(name = "CATG_SHORT_NAME")
    private String shortName;
    @Column(name = "CATG_FULL_NAME")
    private String fullName;
    @Column(name = "CATG_CODE")
    private String code;
    @Column(name = "CATG_EDITED_BY")
    private String editedBy;
    @Column(name = "CATG_EDITED_AT")
    private Date editedAt;
    
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
	public String getEditedBy() {
		return editedBy;
	}
	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
	}
	public Date getEditedAt() {
		return editedAt;
	}
	public void setEditedAt(Date editedAt) {
		this.editedAt = editedAt;
	}
    
}