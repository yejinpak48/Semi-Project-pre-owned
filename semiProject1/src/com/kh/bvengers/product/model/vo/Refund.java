package com.kh.bvengers.product.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Refund implements Serializable{
	private String ono;
	private Date rDate;
	private String mno;
	private String cStatus;	//검수상태
	private String rStatus; //환불상태
	private String pCode;	//상품코드
	private String pno;		//결제번호

	public Refund() {}

	public Refund(String ono, Date rDate, String mno, String cStatus, String rStatus, String pCode, String pno) {
		super();
		this.ono = ono;
		this.rDate = rDate;
		this.mno = mno;
		this.cStatus = cStatus;
		this.rStatus = rStatus;
		this.pCode = pCode;
		this.pno = pno;
	}

	public String getOno() {
		return ono;
	}

	public void setOno(String ono) {
		this.ono = ono;
	}

	public Date getrDate() {
		return rDate;
	}

	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getcStatus() {
		return cStatus;
	}

	public void setcStatus(String cStatus) {
		this.cStatus = cStatus;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}

	@Override
	public String toString() {
		return "Refund [ono=" + ono + ", rDate=" + rDate + ", mno=" + mno + ", cStatus=" + cStatus + ", rStatus="
				+ rStatus + ", pCode=" + pCode + ", pno=" + pno + "]";
	}




}
