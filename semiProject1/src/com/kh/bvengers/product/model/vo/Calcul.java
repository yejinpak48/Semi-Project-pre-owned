package com.kh.bvengers.product.model.vo;

public class Calcul implements java.io.Serializable{
	private String memberNo;
	private String adjustNo;
	private String adjustDate;
	private String adjustDiv;
	private String price;
	private String payDtno;
	private String receipt;
	public Calcul() {}

	

	public Calcul(String memberNo, String adjustNo, String adjustDate, String adjustDiv, String price, String payDtno,
			String receipt) {
		super();
		this.memberNo = memberNo;
		this.adjustNo = adjustNo;
		this.adjustDate = adjustDate;
		this.adjustDiv = adjustDiv;
		this.price = price;
		this.payDtno = payDtno;
		this.receipt = receipt;
	}



	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getAdjustNo() {
		return adjustNo;
	}

	public void setAdjustNo(String adjustNo) {
		this.adjustNo = adjustNo;
	}

	public String getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(String adjustDate) {
		this.adjustDate = adjustDate;
	}

	public String getAdjustDiv() {
		return adjustDiv;
	}

	public void setAdjustDiv(String adjustDiv) {
		this.adjustDiv = adjustDiv;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPayDtno() {
		return payDtno;
	}

	public void setPayDtno(String payDtno) {
		this.payDtno = payDtno;
	}
	
	
	public String getReceipt() {
		return receipt;
	}



	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}



	@Override
	public String toString() {
		return "Calcul [memberNo=" + memberNo + ", adjustNo=" + adjustNo + ", adjustDate=" + adjustDate + ", adjustDiv="
				+ adjustDiv + ", price=" + price + ", payDtno=" + payDtno + ", receipt=" + receipt + "]";
	}



	
	
}
