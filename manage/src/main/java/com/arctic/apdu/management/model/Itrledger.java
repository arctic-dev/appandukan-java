package com.arctic.apdu.management.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adt_ledger_report")
public class Itrledger {

	@Id
	@GeneratedValue
	@Column(name = "lr_id_pk")
	private BigInteger ilridpkdPk;
	@Column(name = "lr_date")
	private Date lrdate;
	@Column(name = "lr_trans_type")
	private String lrtranstype;
	@Column(name = "lr_comment")
	private String lrcomment;
	@Column(name = "lr_debit_amount")
	private Double lrdebitamount;
	@Column(name = "lr_credit_amount")
	private Double lrcreditamount;
	@Column(name = "lr_post_balance")
	private Double lrpostbalance;
	@Column(name = "lr_created_by")
	private String lrcreatedby;
	@Column(name = "lr_prod_code")
	private String lrprodcode;
	public BigInteger getIlridpkdPk() {
		return ilridpkdPk;
	}
	public void setIlridpkdPk(BigInteger ilridpkdPk) {
		this.ilridpkdPk = ilridpkdPk;
	}
	public Date getLrdate() {
		return lrdate;
	}
	public void setLrdate(Date lrdate) {
		this.lrdate = lrdate;
	}
	public String getLrtranstype() {
		return lrtranstype;
	}
	public void setLrtranstype(String lrtranstype) {
		this.lrtranstype = lrtranstype;
	}
	public String getLrcomment() {
		return lrcomment;
	}
	public void setLrcomment(String lrcomment) {
		this.lrcomment = lrcomment;
	}
	public Double getLrdebitamount() {
		return lrdebitamount;
	}
	public void setLrdebitamount(Double lrdebitamount) {
		this.lrdebitamount = lrdebitamount;
	}
	public Double getLrcreditamount() {
		return lrcreditamount;
	}
	public void setLrcreditamount(Double lrcreditamount) {
		this.lrcreditamount = lrcreditamount;
	}
	public Double getLrpostbalance() {
		return lrpostbalance;
	}
	public void setLrpostbalance(Double lrpostbalance) {
		this.lrpostbalance = lrpostbalance;
	}
	public String getLrcreatedby() {
		return lrcreatedby;
	}
	public void setLrcreatedby(String lrcreatedby) {
		this.lrcreatedby = lrcreatedby;
	}
	public String getLrprodcode() {
		return lrprodcode;
	}
	public void setLrprodcode(String lrprodcode) {
		this.lrprodcode = lrprodcode;
	}



}
