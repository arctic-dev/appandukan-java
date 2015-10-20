package com.arctic.apdu.management.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADT_RECHARGE_MAST")
public class RechargeMast implements Serializable {

	private static final long serialVersionUID = -4865994774285619549L;

	@Id @GeneratedValue
	@Column(name = "RM_ID_PK")
	private BigInteger idPk;
	@Column(name = "RM_NAME")
	private String name;
	@Column(name = "RM_PROD_CODE")
	private String prodCode;
	@Column(name = "RM_STATUS")
	private String status;
	@Column(name = "RM_PROVIDER")
	private String provider;
	@Column(name = "RM_CYBERPLAT_PA")
	private String cyberplatPa;
	@Column(name = "RM_CYBERPLAT_PR")
	private String cyberplatPr;
	@Column(name = "RM_CYBERPLAT_PS")
	private String cyberplatPs;
	@Column(name = "RM_EZYPAY_OPCODE")
	private Integer ezypayOpcode;
	@Column(name = "RM_EZYPAY_PRCODE")
	private Integer ezypayPrcode;
	@Column(name = "RM_COMMISSION")
	private Float commission;
	@Column(name = "RM_DCOMMISSION")
	private Float dcommission;
	@Column(name = "RM_SCOMMISSION")
	private Float scommission;
	@Column(name = "RM_SFCOMMISSION")
	private Float sfcommission;
	@Column(name = "RM_FCOMMISSION")
	private Float fcommission;
	@Column(name = "RM_SDCOMMISSION")
	private Float sdcommission;
	@Column(name = "RM_RLL_CODE")
	private String rllCode;
	public BigInteger getIdPk() {
		return idPk;
	}
	public void setIdPk(BigInteger idPk) {
		this.idPk = idPk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getCyberplatPa() {
		return cyberplatPa;
	}
	public void setCyberplatPa(String cyberplatPa) {
		this.cyberplatPa = cyberplatPa;
	}
	public String getCyberplatPr() {
		return cyberplatPr;
	}
	public void setCyberplatPr(String cyberplatPr) {
		this.cyberplatPr = cyberplatPr;
	}
	public String getCyberplatPs() {
		return cyberplatPs;
	}
	public void setCyberplatPs(String cyberplatPs) {
		this.cyberplatPs = cyberplatPs;
	}
	public Integer getEzypayOpcode() {
		return ezypayOpcode;
	}
	public void setEzypayOpcode(Integer ezypayOpcode) {
		this.ezypayOpcode = ezypayOpcode;
	}
	public Integer getEzypayPrcode() {
		return ezypayPrcode;
	}
	public void setEzypayPrcode(Integer ezypayPrcode) {
		this.ezypayPrcode = ezypayPrcode;
	}
	public Float getCommission() {
		return commission;
	}
	public void setCommission(Float commission) {
		this.commission = commission;
	}
	public Float getDcommission() {
		return dcommission;
	}
	public void setDcommission(Float dcommission) {
		this.dcommission = dcommission;
	}
	public Float getScommission() {
		return scommission;
	}
	public void setScommission(Float scommission) {
		this.scommission = scommission;
	}
	public Float getSfcommission() {
		return sfcommission;
	}
	public void setSfcommission(Float sfcommission) {
		this.sfcommission = sfcommission;
	}
	public Float getFcommission() {
		return fcommission;
	}
	public void setFcommission(Float fcommission) {
		this.fcommission = fcommission;
	}
	public Float getSdcommission() {
		return sdcommission;
	}
	public void setSdcommission(Float sdcommission) {
		this.sdcommission = sdcommission;
	}
	public String getRllCode() {
		return rllCode;
	}
	public void setRllCode(String rllCode) {
		this.rllCode = rllCode;
	}

	
	
}
