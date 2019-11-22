package com.kh.bvengers.board.model.vo;

public class Calculate implements java.io.Serializable{
	private String orderNo;
	private String sellerNo;
	private String sellerId;
	private String buyerNo;
	private String buyerId;
	private String postsTitle;
	private String orderDate;
	private String calculateDate;
	private String deliveryStatus;
	private String deliveryNo;
	private String releaseDate;
	private String dateResult;
	
	public Calculate() {}

	public Calculate(String orderNo, String sellerNo, String sellerId, String buyerNo, String buyerId,
			String postsTitle, String orderDate, String calculateDate, String deliveryStatus, String deliveryNo,
			String releaseDate, String dateResult) {
		super();
		this.orderNo = orderNo;
		this.sellerNo = sellerNo;
		this.sellerId = sellerId;
		this.buyerNo = buyerNo;
		this.buyerId = buyerId;
		this.postsTitle = postsTitle;
		this.orderDate = orderDate;
		this.calculateDate = calculateDate;
		this.deliveryStatus = deliveryStatus;
		this.deliveryNo = deliveryNo;
		this.releaseDate = releaseDate;
		this.dateResult = dateResult;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSellerNo() {
		return sellerNo;
	}

	public void setSellerNo(String sellerNo) {
		this.sellerNo = sellerNo;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getBuyerNo() {
		return buyerNo;
	}

	public void setBuyerNo(String buyerNo) {
		this.buyerNo = buyerNo;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getPostsTitle() {
		return postsTitle;
	}

	public void setPostsTitle(String postsTitle) {
		this.postsTitle = postsTitle;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getCalculateDate() {
		return calculateDate;
	}

	public void setCalculateDate(String calculateDate) {
		this.calculateDate = calculateDate;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDateResult() {
		return dateResult;
	}

	public void setDateResult(String dateResult) {
		this.dateResult = dateResult;
	}

	@Override
	public String toString() {
		return "Calculate [orderNo=" + orderNo + ", sellerNo=" + sellerNo + ", sellerId=" + sellerId + ", buyerNo="
				+ buyerNo + ", buyerId=" + buyerId + ", postsTitle=" + postsTitle + ", orderDate=" + orderDate
				+ ", calculateDate=" + calculateDate + ", deliveryStatus=" + deliveryStatus + ", deliveryNo="
				+ deliveryNo + ", releaseDate=" + releaseDate + ", dateResult=" + dateResult + "]";
	}
	
	
}