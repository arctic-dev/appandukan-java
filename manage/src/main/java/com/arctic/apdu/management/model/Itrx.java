package com.arctic.apdu.management.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adt_itr_forms")
public class Itrx {

	private static final long serialVersionUID = 8756676582297042274L;

	@Id
	@GeneratedValue
	@Column(name = "itr_id_pk")
	private BigInteger itridpk;	
	@Column(name = "itr_pan")
	private String itrpan;
	@Column(name = "itr_name")
	private String itrname;
	@Column(name = "itr_bankstatement")
	private String itrbankstatement;
	@Column(name = "itr_form16")
	private String itrform;
	@Column(name = "itr_tdscertificate")
	private String itrtdscertificate;
	@Column(name = "itr_addrproof")
	private String itraddrproof;
	@Column(name = "itr_previtr")
	private String itrprevitr;
	@Column(name = "itr_bankname")
	private String itrbankname;
	@Column(name = "itr_bankacctype")
	private String itrbankacctype;
	@Column(name = "itr_bankaccno")
	private BigInteger itrbankaccno;
	@Column(name = "itr_bankifsc")
	private String itrbankifsc;
	@Column(name = "itr_fyear")
	private String itrfyear;
	@Column(name = "itr_mobileno")
	private String itrmobileno;
	@Column(name = "itr_email")
	private String itremail;
	@Column(name = "itr_createdby")
	private String itrcreatedby;
	@Column(name = "itr_createdat")
	private Date itrcreatedat;
	@Column(name = "itr_status")
	private Long itrstatus;
	@Column(name = "itr_clientip")
	private String itrclientip;
	@Column(name = "itr_receipt")
	private String itrreceipt;

	public String getItrreceipt() {
		return itrreceipt;
	}

	public void setItrreceipt(String itrreceipt) {
		this.itrreceipt = itrreceipt;
	}

	/*
	 * public Long getItridpk() { return itridpk; }
	 * 
	 * public void setItridpk(Long itridpk) { this.itridpk = itridpk; }
	 */

	public String getItrpan() {
		return itrpan;
	}

	public BigInteger getItridpk() {
		return itridpk;
	}

	public void setItridpk(BigInteger itridpk) {
		this.itridpk = itridpk;
	}

	public void setItrpan(String itrpan) {
		this.itrpan = itrpan;
	}

	public String getItrname() {
		return itrname;
	}

	public void setItrname(String itrname) {
		this.itrname = itrname;
	}

	public String getItrbankstatement() {
		return itrbankstatement;
	}

	public void setItrbankstatement(String itrbankstatement) {
		this.itrbankstatement = itrbankstatement;
	}

	public String getItrform() {
		return itrform;
	}

	public void setItrform(String itrform) {
		this.itrform = itrform;
	}

	public String getItrtdscertificate() {
		return itrtdscertificate;
	}

	public void setItrtdscertificate(String itrtdscertificate) {
		this.itrtdscertificate = itrtdscertificate;
	}

	public String getItraddrproof() {
		return itraddrproof;
	}

	public void setItraddrproof(String itraddrproof) {
		this.itraddrproof = itraddrproof;
	}

	public String getItrprevitr() {
		return itrprevitr;
	}

	public void setItrprevitr(String itrprevitr) {
		this.itrprevitr = itrprevitr;
	}

	public String getItrbankname() {
		return itrbankname;
	}

	public void setItrbankname(String itrbankname) {
		this.itrbankname = itrbankname;
	}

	public String getItrbankacctype() {
		return itrbankacctype;
	}

	public void setItrbankacctype(String itrbankacctype) {
		this.itrbankacctype = itrbankacctype;
	}

	public BigInteger getItrbankaccno() {
		return itrbankaccno;
	}

	public void setItrbankaccno(BigInteger itrbankaccno) {
		this.itrbankaccno = itrbankaccno;
	}

	public String getItrbankifsc() {
		return itrbankifsc;
	}

	public void setItrbankifsc(String itrbankifsc) {
		this.itrbankifsc = itrbankifsc;
	}

	public String getItrfyear() {
		return itrfyear;
	}

	public void setItrfyear(String itrfyear) {
		this.itrfyear = itrfyear;
	}

	public String getItrmobileno() {
		return itrmobileno;
	}

	public void setItrmobileno(String itrmobileno) {
		this.itrmobileno = itrmobileno;
	}

	public String getItremail() {
		return itremail;
	}

	public void setItremail(String itremail) {
		this.itremail = itremail;
	}

	public String getItrcreatedby() {
		return itrcreatedby;
	}

	public void setItrcreatedby(String itrcreatedby) {
		this.itrcreatedby = itrcreatedby;
	}

	public Date getItrcreatedat() {
		return itrcreatedat;
	}

	public void setItrcreatedat(Date itrcreatedat) {
		this.itrcreatedat = itrcreatedat;
	}

	public Long getItrstatus() {
		return itrstatus;
	}

	public void setItrstatus(Long itrstatus) {
		this.itrstatus = itrstatus;
	}

	public String getItrclientip() {
		return itrclientip;
	}

	public void setItrclientip(String itrclientip) {
		this.itrclientip = itrclientip;
	}

}
