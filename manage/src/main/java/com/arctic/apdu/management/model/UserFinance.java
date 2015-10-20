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
@Table(name = "ADT_USER_FINANCE")
public class UserFinance implements Serializable {

	private static final long serialVersionUID = 1845676582297042274L;
	
	@Id @GeneratedValue
	@Column(name = "UFIN_ID_PK")
	private BigInteger idPk;
	@Column(name = "UFIN_USER_ID_PK_FK")
	private BigInteger useridPk;
	@Column(name = "UFIN_USER_ID")
	private String userId;
	@Column(name = "UFIN_MAIN_BALANCE")
	private Double mainBal;
	@Column(name = "UFIN_COMM_EARNED")
	private Double commEarned;
	@Column(name = "UFIN_TOTAL_CREDITED")
	private Double totalCredited;
	@Column(name = "UFIN_TOTAL_USED")
	private Double totalUsed;
	@Column(name = "UFIN_TOTAL_COMM")
	private Double totalComm;
	@Column(name = "UFIN_FEE_PERC")
	private Double feePerc;
	@Column(name = "UFIN_EDITED_AT")
	private Date editedAt;
	@Column(name = "UFIN_EDITED_BY")
	private String editedBy;
	
	public BigInteger getIdPk() {
		return idPk;
	}
	public void setIdPk(BigInteger idPk) {
		this.idPk = idPk;
	}
	public BigInteger getUseridPk() {
		return useridPk;
	}
	public void setUseridPk(BigInteger useridPk) {
		this.useridPk = useridPk;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getMainBal() {
		return mainBal;
	}
	public void setMainBal(Double mainBal) {
		this.mainBal = mainBal;
	}
	public Double getCommEarned() {
		return commEarned;
	}
	public void setCommEarned(Double commEarned) {
		this.commEarned = commEarned;
	}
	public Double getTotalCredited() {
		return totalCredited;
	}
	public void setTotalCredited(Double totalCredited) {
		this.totalCredited = totalCredited;
	}
	public Double getTotalUsed() {
		return totalUsed;
	}
	public void setTotalUsed(Double totalUsed) {
		this.totalUsed = totalUsed;
	}
	public Double getTotalComm() {
		return totalComm;
	}
	public void setTotalComm(Double totalComm) {
		this.totalComm = totalComm;
	}
	public Double getFeePerc() {
		return feePerc;
	}
	public void setFeePerc(Double feePerc) {
		this.feePerc = feePerc;
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