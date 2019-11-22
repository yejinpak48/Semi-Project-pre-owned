package com.kh.bvengers.user.member.model.vo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;

public class Member implements Serializable {
	private String memberNo;
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String email;
	private String address;
	private String phone;
	private Date enrollDate;
	private Date retireDate;
	private String retire;
	private String memberDiv;
	private String accountHolder;
	private String bankCode;
	private String accountNo;
	private String gradeCode;
	private int sellCount;

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String memberNo, String memberId, String memberPassword, String memberName, String email,
			String address, String phone, Date enrollDate, Date retireDate, String retire, String memberDiv,
			String accountHolder, String bankCode, String accountNo, String gradeCode, int sellCount) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.enrollDate = enrollDate;
		this.retireDate = retireDate;
		this.retire = retire;
		this.memberDiv = memberDiv;
		this.accountHolder = accountHolder;
		this.bankCode = bankCode;
		this.accountNo = accountNo;
		this.gradeCode = gradeCode;
		this.sellCount = sellCount;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getRetireDate() {
		return retireDate;
	}

	public void setRetireDate(Date retireDate) {
		this.retireDate = retireDate;
	}

	public String getRetire() {
		return retire;
	}

	public void setRetire(String retire) {
		this.retire = retire;
	}

	public String getMemberDiv() {
		return memberDiv;
	}

	public void setMemberDiv(String memberDiv) {
		this.memberDiv = memberDiv;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPassword=" + memberPassword
				+ ", memberName=" + memberName + ", email=" + email + ", address=" + address + ", phone=" + phone
				+ ", enrollDate=" + enrollDate + ", retireDate=" + retireDate + ", retire=" + retire + ", memberDiv="
				+ memberDiv + ", accountHolder=" + accountHolder + ", bankCode=" + bankCode + ", accountNo=" + accountNo
				+ ", gradeCode=" + gradeCode + ", sellCount=" + sellCount + "]";
	}

	

}
