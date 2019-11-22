package com.kh.bvengers.board.model.vo;

import java.io.Serializable;

public class Count implements Serializable{
	private String count;
	private int postsId;


	public Count() {}

	public Count(String count, int postsId) {
		super();
		this.count = count;
		this.postsId = postsId;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public int getPostsId() {
		return postsId;
	}

	public void setPostsId(int postsId) {
		this.postsId = postsId;
	}

	@Override
	public String toString() {
		return "Count [count=" + count + ", postsId=" + postsId + "]";
	}



}
