package com.kh.bvengers.product.model.vo;

import java.io.Serializable;

public class Payment implements Serializable{

	private String memberNo;
	private String productCode;
	private int productDtpay;
	private int payMoney;
	private String subDeliverySite;
	private String deliverySite;
	private String recieverName;
	private String recieverPhone;
	private String mail;
	private String deliverySiteCode;
	private String orderNo;
	private String receipt;
	
	public Payment() {}

	

	public Payment(String memberNo, String productCode, int productDtpay, int payMoney, String subDeliverySite,
			String deliverySite, String recieverName, String recieverPhone, String mail, String deliverySiteCode,
			String orderNo, String receipt) {
		super();
		this.memberNo = memberNo;
		this.productCode = productCode;
		this.productDtpay = productDtpay;
		this.payMoney = payMoney;
		this.subDeliverySite = subDeliverySite;
		this.deliverySite = deliverySite;
		this.recieverName = recieverName;
		this.recieverPhone = recieverPhone;
		this.mail = mail;
		this.deliverySiteCode = deliverySiteCode;
		this.orderNo = orderNo;
		this.receipt = receipt;
	}



	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getProductDtpay() {
		return productDtpay;
	}

	public void setProductDtpay(int productDtpay) {
		this.productDtpay = productDtpay;
	}

	public int getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(int payMoney) {
		this.payMoney = payMoney;
	}

	public String getSubDeliverySite() {
		return subDeliverySite;
	}

	public void setSubDeliverySite(String subDeliverySite) {
		this.subDeliverySite = subDeliverySite;
	}

	public String getDeliverySite() {
		return deliverySite;
	}

	public void setDeliverySite(String deliverySite) {
		this.deliverySite = deliverySite;
	}

	public String getRecieverName() {
		return recieverName;
	}

	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}

	public String getRecieverPhone() {
		return recieverPhone;
	}

	public void setRecieverPhone(String recieverPhone) {
		this.recieverPhone = recieverPhone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDeliverySiteCode() {
		return deliverySiteCode;
	}

	public void setDeliverySiteCode(String deliverySiteCode) {
		this.deliverySiteCode = deliverySiteCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}



	public String getReceipt() {
		return receipt;
	}



	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}



	@Override
	public String toString() {
		return "Payment [memberNo=" + memberNo + ", productCode=" + productCode + ", productDtpay=" + productDtpay
				+ ", payMoney=" + payMoney + ", subDeliverySite=" + subDeliverySite + ", deliverySite=" + deliverySite
				+ ", recieverName=" + recieverName + ", recieverPhone=" + recieverPhone + ", mail=" + mail
				+ ", deliverySiteCode=" + deliverySiteCode + ", orderNo=" + orderNo + ", receipt=" + receipt + "]";
	}

	


}
