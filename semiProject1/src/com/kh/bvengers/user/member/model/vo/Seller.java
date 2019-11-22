package com.kh.bvengers.user.member.model.vo;

import java.io.Serializable;
import java.util.Date;

public class Seller implements Serializable {
	private String id;
	private String name;
	private Date enrollDate;
	private int sellCount;
	private String grade;
	private String product;

	public Seller() {
		// TODO Auto-generated constructor stub
	}

	public Seller(String id, String name, Date enrollDate, int sellCount, String grade, String product) {
		super();
		this.id = id;
		this.name = name;
		this.enrollDate = enrollDate;
		this.sellCount = sellCount;
		this.grade = grade;
		this.product = product;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", enrollDate=" + enrollDate + ", sellCount=" + sellCount
				+ ", grade=" + grade + ", product=" + product + "]";
	}


}
