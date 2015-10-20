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
@Table(name = "ADT_RECHARGE_DETAILS")
public class RechargeDetails implements Serializable {

	private static final long serialVersionUID = -4865994654285619549L;

	@Id @GeneratedValue
	@Column(name = "RD_ID_PK")
	private BigInteger idPk;
	@Column(name = "RD_PROD_CODE")
	private String prodCode;
	@Column(name = "RD_SERVICE_PROVIDER")
	private String serviceProvider;
	@Column(name = "RD_NUMBER")
	private BigInteger number;
	@Column(name = "RD_AMOUNT")
	private Double amount;
	@Column(name = "RD_SFCOMMISSION")
	private Double sfcommission;
	@Column(name = "RD_COMMISSION")
	private Double commission;
	@Column(name = "RD_DCOMMISSION")
	private Double dcommission;
	@Column(name = "RD_CREATED_AT")
	private Date createdAt;
	@Column(name = "RD_CREATED_BY")
	private String createdBy;
	@Column(name = "RD_CREATED_TYPE")
	private String createdType;
	@Column(name = "RD_RESULT")
	private String result;
	@Column(name = "RD_TRANS_ID")
	private String transId;
	@Column(name = "RD_AUTH_CODE")
	private String authCode;
	@Column(name = "RD_CLIENT_IP")
	private String clientIp;
	@Column(name = "RD_PROVIDER")
	private String provider;
	@Column(name = "RD_REFUND_STATUS")
	private String refundStatus;
	@Column(name = "RD_REFUND_AT")
	private Date refundAt;

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
	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	public BigInteger getNumber() {
		return number;
	}
	public void setNumber(BigInteger number) {
		this.number = number;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getSfcommission() {
		return sfcommission;
	}
	public void setSfcommission(Double sfcommission) {
		this.sfcommission = sfcommission;
	}
	public Double getCommission() {
		return commission;
	}
	public void setCommission(Double commission) {
		this.commission = commission;
	}
	public Double getDcommission() {
		return dcommission;
	}
	public void setDcommission(Double dcommission) {
		this.dcommission = dcommission;
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
	public String getCreatedType() {
		return createdType;
	}
	public void setCreatedType(String createdType) {
		this.createdType = createdType;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
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
	
}