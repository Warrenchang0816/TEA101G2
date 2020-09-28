package com.member.model;

import java.util.Arrays;

public class MemberVO {
	
	private String memberId;
	private String memberAccount;
	private String memberPassword;
	private String memberName;
	private String memberNickname;
	private String memberEmail;
	private byte[] memberPhoto;
//	private String memberPhotoBase64;
	private String memberPhone;
	private String memberAddress;
	private java.sql.Date memberBirth;
	private String memberSex;
	private String memberCountry;
	private java.sql.Date memberSignupDate;
	private Integer memberAuth;
	private String memberStatus;
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberAccount() {
		return memberAccount;
	}
	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
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
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public byte[] getMemberPhoto() {
		return memberPhoto;
	}
	public void setMemberPhoto(byte[] memberPhoto) {
		this.memberPhoto = memberPhoto;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public java.sql.Date getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(java.sql.Date memberBirth) {
		this.memberBirth = memberBirth;
	}
	public String getMemberSex() {
		return memberSex;
	}
	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}
	public String getMemberCountry() {
		return memberCountry;
	}
	public void setMemberCountry(String memberCountry) {
		this.memberCountry = memberCountry;
	}
	public java.sql.Date getMemberSignupDate() {
		return memberSignupDate;
	}
	public void setMemberSignupDate(java.sql.Date memberSignupDate) {
		this.memberSignupDate = memberSignupDate;
	}
	public Integer getMemberAuth() {
		return memberAuth;
	}
	public void setMemberAuth(Integer memberAuth) {
		this.memberAuth = memberAuth;
	}
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	
	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", memberAccount=" + memberAccount + ", memberPassword="
				+ memberPassword + ", memberName=" + memberName + ", memberNickname=" + memberNickname
				+ ", memberEmail=" + memberEmail + ", memberPhoto=" + Arrays.toString(memberPhoto)
				+ ", memberPhone=" + memberPhone + ", memberAddress="
				+ memberAddress + ", memberBirth=" + memberBirth + ", memberSex=" + memberSex + ", memberCountry="
				+ memberCountry + ", memberSignupDate=" + memberSignupDate + ", memberAuth=" + memberAuth
				+ ", memberStatus=" + memberStatus + "]";
	}
	
	

}
