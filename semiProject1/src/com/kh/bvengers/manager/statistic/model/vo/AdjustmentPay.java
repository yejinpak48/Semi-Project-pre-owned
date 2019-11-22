package com.kh.bvengers.manager.statistic.model.vo;

public class AdjustmentPay implements java.io.Serializable{
	private String adjustNo;
	private String adjusetDate;
	private String price;
	private String payDtno;
	private String sumPayMoney;
	private int rowNum;
	
	public AdjustmentPay() {}

	public AdjustmentPay(String adjustNo, String adjusetDate, String price, String payDtno, String sumPayMoney,
			int rowNum) {
		super();
		this.adjustNo = adjustNo;
		this.adjusetDate = adjusetDate;
		this.price = price;
		this.payDtno = payDtno;
		this.sumPayMoney = sumPayMoney;
		this.rowNum = rowNum;
	}

	public String getAdjustNo() {
		return adjustNo;
	}

	public void setAdjustNo(String adjustNo) {
		this.adjustNo = adjustNo;
	}

	public String getAdjusetDate() {
		return adjusetDate;
	}

	public void setAdjusetDate(String adjusetDate) {
		this.adjusetDate = adjusetDate;
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

	public String getSumPayMoney() {
		return sumPayMoney;
	}

	public void setSumPayMoney(String sumPayMoney) {
		this.sumPayMoney = sumPayMoney;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	@Override
	public String toString() {
		return "AdjustmentPay [adjustNo=" + adjustNo + ", adjusetDate=" + adjusetDate + ", price=" + price
				+ ", payDtno=" + payDtno + ", sumPayMoney=" + sumPayMoney + ", rowNum=" + rowNum + "]";
	}
	
	
}
