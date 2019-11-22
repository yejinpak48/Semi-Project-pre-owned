package com.kh.bvengers.board.model.vo;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

	private String commentNo;
	private String memberId;
	private Date commentDate;
	private int recommendCount;
	private String commentContents;
	private String postsId;

	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(String commentNo, String memberId, Date commentDate, int recommendCount, String commentContents,
			String postsId) {
		super();
		this.commentNo = commentNo;
		this.memberId = memberId;
		this.commentDate = commentDate;
		this.recommendCount = recommendCount;
		this.commentContents = commentContents;
		this.postsId = postsId;
	}

	public String getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(String commentNo) {
		this.commentNo = commentNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public int getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}

	public String getCommentContents() {
		return commentContents;
	}

	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}

	public String getPostsId() {
		return postsId;
	}

	public void setPostsId(String string) {
		this.postsId = string;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", memberId=" + memberId + ", commentDate=" + commentDate
				+ ", recommendCount=" + recommendCount + ", commentContents=" + commentContents + ", postsId=" + postsId
				+ "]";
	}

}
