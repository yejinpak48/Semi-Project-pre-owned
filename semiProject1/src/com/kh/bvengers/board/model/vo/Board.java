package com.kh.bvengers.board.model.vo;

import java.io.Serializable;
import java.sql.Date;



public class Board  implements Serializable{
	private int postsId;
	private String postsTitle;
	private int memberNo;
	private int postsViews;
	private int recommendCount;
	private String open;
	private String notice;
	private String postsDelete;
	private String boardCode;
	private String contents;
	private String writer;
	private String postsCode;
	private String memberName;
	private Date createDate;
	private String MemberId;
	private String count;
	
	
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Board(int postsId, String postsTitle, int memberNo, int postsViews, int recommendCount, String open,
			String notice, String postsDelete, String boardCode, String contents, String writer, String postsCode,
			String memberName, Date createDate, String memberId, String count) {
		super();
		this.postsId = postsId;
		this.postsTitle = postsTitle;
		this.memberNo = memberNo;
		this.postsViews = postsViews;
		this.recommendCount = recommendCount;
		this.open = open;
		this.notice = notice;
		this.postsDelete = postsDelete;
		this.boardCode = boardCode;
		this.contents = contents;
		this.writer = writer;
		this.postsCode = postsCode;
		this.memberName = memberName;
		this.createDate = createDate;
		MemberId = memberId;
		this.count = count;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getMemberId() {
		return MemberId;
	}
	public void setMemberId(String memberId) {
		MemberId = memberId;
	}
	public Date getCreateDate() {
		return createDate;
	}





	public void setCreateDate(Date date) {
		this.createDate = date;
	}





	public String getMemberName() {
		return memberName;
	}



	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}



	public int getPostsId() {
		return postsId;
	}

	public void setPostsId(int postsId) {
		this.postsId = postsId;
	}

	public String getPostsTitle() {
		return postsTitle;
	}

	public void setPostsTitle(String postsTitle) {
		this.postsTitle = postsTitle;
	}

	public String getPostsCode() {
		return postsCode;
	}

	public void setPostsCode(String postsCode) {
		this.postsCode = postsCode;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getPostsViews() {
		return postsViews;
	}

	public void setPostsViews(int postsViews) {
		this.postsViews = postsViews;
	}

	public int getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getPostsDelete() {
		return postsDelete;
	}

	public void setPostsDelete(String postsDelete) {
		this.postsDelete = postsDelete;
	}

	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}






	@Override
	public String toString() {
		return "Board [postsId=" + postsId + ", postsTitle=" + postsTitle + ", memberNo=" + memberNo + ", postsViews="
				+ postsViews + ", recommendCount=" + recommendCount + ", open=" + open + ", notice=" + notice
				+ ", postsDelete=" + postsDelete + ", boardCode=" + boardCode + ", contents=" + contents + ", writer="
				+ writer + ", postsCode=" + postsCode + ", memberName=" + memberName + ", createDate=" + createDate
				+ ", MemberId=" + MemberId + ", count=" + count + "]";
	}












	

	


}

