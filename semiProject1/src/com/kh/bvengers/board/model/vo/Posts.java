package com.kh.bvengers.board.model.vo;

public class Posts implements java.io.Serializable{
	private int postsId;
	private String postsTitle;
	private int memberNo;
	private int postsViews;
	private int recommendCount;
	private String open;
	private String notice;
	private String postsDelete;
	private String boardCode;
	private String writer;
	
	
	public Posts() {}


	public Posts(int postsId, String postsTitle, int memberNo, int postsViews, int recommendCount, String open,
			String notice, String postsDelete, String boardCode, String writer) {
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
		this.writer = writer;
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
	


	public String getWriter() {
		return writer;
	}



	public void setWriter(String writer) {
		this.writer = writer;
	}





	@Override
	public String toString() {
		return "Posts [postsId=" + postsId + ", postsTitle=" + postsTitle + ", memberNo=" + memberNo + ", postsViews="
				+ postsViews + ", recommendCount=" + recommendCount + ", open=" + open + ", notice=" + notice
				+ ", postsDelete=" + postsDelete + ", boardCode=" + boardCode + ", writer=" + writer + "]";
	}
	
}
