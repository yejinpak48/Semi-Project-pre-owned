package com.kh.bvengers.board.model.vo;

import java.io.Serializable;

public class PowerLink implements Serializable {
	private String fileName;
	private String postsId;
	private String fileSrc;
	private String title;
	private String contents;
	private int price;

	public PowerLink() {
	}

	public PowerLink(String fileName, String postsId, String fileSrc, String title, String contents, int price) {
		super();
		this.fileName = fileName;
		this.postsId = postsId;
		this.fileSrc = fileSrc;
		this.title = title;
		this.contents = contents;
		this.price = price;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPostsId() {
		return postsId;
	}

	public void setPostsId(String postsId) {
		this.postsId = postsId;
	}

	public String getFileSrc() {
		return fileSrc;
	}

	public void setFileSrc(String fileSrc) {
		this.fileSrc = fileSrc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PowerLink [fileName=" + fileName + ", postsId=" + postsId + ", fileSrc=" + fileSrc + ", title=" + title
				+ ", contents=" + contents + ", price=" + price + "]";
	}

}
