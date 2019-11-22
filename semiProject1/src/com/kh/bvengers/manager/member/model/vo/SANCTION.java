package com.kh.bvengers.manager.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class SANCTION implements Serializable{
	private String sanctionNo;
	private String memberNo;
	private String reason;
	private Date sanctionDate;
	private String sanctionDiv;
	private String sanctionStatus;
	private int stopTerm;
	
	public SANCTION() {
		super();
	}

	public SANCTION(String sanctionNo, String memberNo, String reason, Date sanctionDate, String sanctionDiv,
			String sanctionStatus, int stopTerm) {
		super();
		this.sanctionNo = sanctionNo;
		this.memberNo = memberNo;
		this.reason = reason;
		this.sanctionDate = sanctionDate;
		this.sanctionDiv = sanctionDiv;
		this.sanctionStatus = sanctionStatus;
		this.stopTerm = stopTerm;
	}

	public String getSanctionNo() {
		return sanctionNo;
	}

	public void setSanctionNo(String sanctionNo) {
		this.sanctionNo = sanctionNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getSanctionDate() {
		return sanctionDate;
	}

	public void setSanctionDate(Date sanctionDate) {
		this.sanctionDate = sanctionDate;
	}

	public String getSanctionDiv() {
		return sanctionDiv;
	}

	public void setSanctionDiv(String sanctionDiv) {
		this.sanctionDiv = sanctionDiv;
	}

	public String getSanctionStatus() {
		return sanctionStatus;
	}

	public void setSanctionStatus(String sanctionStatus) {
		this.sanctionStatus = sanctionStatus;
	}

	public int getStopTerm() {
		return stopTerm;
	}

	public void setStopTerm(int stopTerm) {
		this.stopTerm = stopTerm;
	}

	@Override
	public String toString() {
		return "SANCTION [sanctionNo=" + sanctionNo + ", memberNo=" + memberNo + ", reason=" + reason
				+ ", sanctionDate=" + sanctionDate + ", sanctionDiv=" + sanctionDiv + ", sanctionStatus="
				+ sanctionStatus + ", stopTerm=" + stopTerm + "]";
	}
	
	
	
}
