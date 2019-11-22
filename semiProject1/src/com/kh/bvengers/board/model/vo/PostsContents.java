package com.kh.bvengers.board.model.vo;

public class PostsContents implements java.io.Serializable{
	private int postsId;
	private String contents;
	
	public PostsContents() {}

	public PostsContents(int postsId, String contents) {
		super();
		this.postsId = postsId;
		this.contents = contents;
	}

	public int getPostsId() {
		return postsId;
	}

	public void setPostsId(int postsId) {
		this.postsId = postsId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "PostsContents [postsId=" + postsId + ", contents=" + contents + "]";
	}
	
	
	
}
