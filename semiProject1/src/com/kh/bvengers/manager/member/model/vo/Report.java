package com.kh.bvengers.manager.member.model.vo;

import java.sql.Date;

public class Report {
	private String reportNo;
	private String reporter;
	private String memberDest;
	private String postsId;
	private Date reportDate;
	private String reportComments;
	
	public Report() {
		super();
	}

	public Report(String reportNo, String reporter, String memberDest, String postsId, Date reportDate,
			String reportComments) {
		super();
		this.reportNo = reportNo;
		this.reporter = reporter;
		this.memberDest = memberDest;
		this.postsId = postsId;
		this.reportDate = reportDate;
		this.reportComments = reportComments;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getMemberDest() {
		return memberDest;
	}

	public void setMemberDest(String memberDest) {
		this.memberDest = memberDest;
	}

	public String getPostsId() {
		return postsId;
	}

	public void setPostsId(String postsId) {
		this.postsId = postsId;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportComments() {
		return reportComments;
	}

	public void setReportComments(String reportComments) {
		this.reportComments = reportComments;
	}

	@Override
	public String toString() {
		return "Report [reportNo=" + reportNo + ", reporter=" + reporter + ", memberDest=" + memberDest + ", postsId="
				+ postsId + ", reportDate=" + reportDate + ", reportComments=" + reportComments + "]";
	}
	
	
}
