package com.kh.bvengers.product.model.vo;

import java.sql.Date;

public class ProductCheck implements java.io.Serializable {
	private int checkNo;
	private int checkStatus;
	private Date checkDate;
	private int completeStatus;
	private int productCode;
	private String reason;

	public ProductCheck() {}

	public ProductCheck(int checkNo, int checkStatus, Date checkDate, int completeStatus, int productCode,
			String reason) {
		super();
		this.checkNo = checkNo;
		this.checkStatus = checkStatus;
		this.checkDate = checkDate;
		this.completeStatus = completeStatus;
		this.productCode = productCode;
		this.reason = reason;
	}

	public int getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(int checkNo) {
		this.checkNo = checkNo;
	}

	public int getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public int getCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(int completeStatus) {
		this.completeStatus = completeStatus;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "ProductCheck [checkNo=" + checkNo + ", checkStatus=" + checkStatus + ", checkDate=" + checkDate
				+ ", completeStatus=" + completeStatus + ", productCode=" + productCode + ", reason=" + reason + "]";
	};

}



/*CHECK_NO
CHECK_STATUS
CHECK_DATE
COMPLETE_STATUS
PRODUCT_CODE
REASON
*/