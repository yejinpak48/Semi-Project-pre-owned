package com.kh.bvengers.board.model.vo;

import java.io.Serializable;

public class Attachment implements Serializable {
	private String fileNo;
	private String orginFileName;
	private String newFileName;
	private String fileSrc;
	private String saveDate;
	private String fileDiv;
	private String postsId;
	private String productCode;

	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attachment(String fileNo, String orginFileName, String newFileName, String fileSrc, String saveDate,
			String fileDiv, String postsId, String productCode) {
		super();
		this.fileNo = fileNo;
		this.orginFileName = orginFileName;
		this.newFileName = newFileName;
		this.fileSrc = fileSrc;
		this.saveDate = saveDate;
		this.fileDiv = fileDiv;
		this.postsId = postsId;
		this.productCode = productCode;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getOrginFileName() {
		return orginFileName;
	}

	public void setOrginFileName(String orginFileName) {
		this.orginFileName = orginFileName;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getFileSrc() {
		return fileSrc;
	}

	public void setFileSrc(String fileSrc) {
		this.fileSrc = fileSrc;
	}

	public String getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(String saveDate) {
		this.saveDate = saveDate;
	}

	public String getFileDiv() {
		return fileDiv;
	}

	public void setFileDiv(String fileDiv) {
		this.fileDiv = fileDiv;
	}

	public String getPostsId() {
		return postsId;
	}

	public void setPostsId(String postsId) {
		this.postsId = postsId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", orginFileName=" + orginFileName + ", newFileName=" + newFileName
				+ ", fileSrc=" + fileSrc + ", saveDate=" + saveDate + ", fileDiv=" + fileDiv + ", postsId=" + postsId
				+ ", productCode=" + productCode + "]";
	}
	
	
}
