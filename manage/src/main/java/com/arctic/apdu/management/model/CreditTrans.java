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
@Table(name = "ADT_CREDIT_RECHARGE")
public class CreditTrans implements Serializable {

	private static final long serialVersionUID = 8756676582297042274L;
	
	@Id @GeneratedValue
	@Column(name = "CR_ID_PK")
	private BigInteger idPk;
	@Column(name = "CR_USER_ID")
	private String userId;
	@Column(name = "CR_TYPE")
	private String type;
	@Column(name = "CR_OPEN_BAL")
	private Double openBal;
	@Column(name = "CR_AMOUNT")
	private Double amount;
	@Column(name = "CR_FEE")
	private Double fee;
	@Column(name = "CR_NEW_BAL")
	private Double newBal;
	@Column(name = "CR_CREATED_BY")
	private String createdBy;
	@Column(name = "CR_CREATED_AT")
	private Date createdAt;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getOpenBal() {
		return openBal;
	}
	public void setOpenBal(Double openBal) {
		this.openBal = openBal;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Double getNewBal() {
		return newBal;
	}
	public void setNewBal(Double newBal) {
		this.newBal = newBal;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}