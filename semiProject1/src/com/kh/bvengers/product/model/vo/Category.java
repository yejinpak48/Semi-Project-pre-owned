package com.kh.bvengers.product.model.vo;

public class Category implements java.io.Serializable{
	private String categoryCode;
	private String categoryDiv;
	private String categoryHilevel;
	private int vategoryLevel;
	
	public Category() {}

	public Category(String categoryCode, String categoryDiv, String categoryHilevel, int vategoryLevel) {
		super();
		this.categoryCode = categoryCode;
		this.categoryDiv = categoryDiv;
		this.categoryHilevel = categoryHilevel;
		this.vategoryLevel = vategoryLevel;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryDiv() {
		return categoryDiv;
	}

	public void setCategoryDiv(String categoryDiv) {
		this.categoryDiv = categoryDiv;
	}

	public String getCategoryHilevel() {
		return categoryHilevel;
	}

	public void setCategoryHilevel(String categoryHilevel) {
		this.categoryHilevel = categoryHilevel;
	}

	public int getVategoryLevel() {
		return vategoryLevel;
	}

	public void setVategoryLevel(int vategoryLevel) {
		this.vategoryLevel = vategoryLevel;
	}

	@Override
	public String toString() {
		return "Category [categoryCode=" + categoryCode + ", categoryDiv=" + categoryDiv + ", categoryHilevel="
				+ categoryHilevel + ", vategoryLevel=" + vategoryLevel + "]";
	};
	
	
}