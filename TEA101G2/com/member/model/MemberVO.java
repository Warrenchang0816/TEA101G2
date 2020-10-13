package com.member.model;

import java.util.Arrays;

import com.emp.model.EmpVO;

public class MemberVO {
	
	private String memberId;
	private String memberAccount;
	private String memberPassword;
	private String memberName;
	private String memberNickName;
	private String memberEmail;
	private byte[] memberPhoto;
	private String memberPhone;
	private String memberAddress;
	private java.sql.Date memberBirth;
	private String memberSex;
	private String memberCountry;
	private java.sql.Date memberSignupDate;
	private Integer memberAuth;
	private String memberStatus;
	private String memberStatusEmp;
	private String memberStatusComm;
	private String memberOnline;
	
	public static class Builder {

		private String memberId = "";
		private String memberAccount = "";
		private String memberPassword = "";
		private String memberName = "";
		private String memberNickName = "";
		private String memberEmail = "";
		private byte[] memberPhoto = null;
		private String memberPhone = "";
		private String memberAddress = "";
		private java.sql.Date memberBirth = (new java.sql.Date(System.currentTimeMillis()));
		private String memberSex = "";
		private String memberCountry = "";
		private java.sql.Date memberSignupDate = (new java.sql.Date(System.currentTimeMillis()));
		private Integer memberAuth = 0;
		private String memberStatus = "";
		private String memberStatusEmp = "";
		private String memberStatusComm = "";
		private String memberOnline = "";
	
		public MemberVO.Builder memberId(String memberId) {
			this.memberId = memberId;
			return this;
		}
	
		public MemberVO.Builder memberAccount(String memberAccount) {
			this.memberAccount = memberAccount;
			return this;
		}
	
		public MemberVO.Builder memberPassword(String memberPassword) {
			this.memberPassword = memberPassword;
			return this;
		}
	
		public MemberVO.Builder memberName(String memberName) {
			this.memberName = memberName;
			return this;
		}
	
		public MemberVO.Builder memberNickName(String memberNickName) {
			this.memberNickName = memberNickName;
			return this;
		}
		
		public MemberVO.Builder memberEmail(String memberEmail) {
			this.memberEmail = memberEmail;
			return this;
		}
		
		public MemberVO.Builder memberPhoto(byte[] memberPhoto) {
			this.memberPhoto = memberPhoto;
			return this;
		}
		
		public MemberVO.Builder memberPhone(String memberPhone) {
			this.memberPhone = memberPhone;
			return this;
		}
		
		public MemberVO.Builder memberAddress(String memberAddress) {
			this.memberAddress = memberAddress;
			return this;
		}
		
		public MemberVO.Builder memberBirth(java.sql.Date memberBirth) {
			this.memberBirth = memberBirth;
			return this;
		}
		
		public MemberVO.Builder memberSex(String memberSex) {
			this.memberSex = memberSex;
			return this;
		}
		
		public MemberVO.Builder memberCountry(String memberCountry) {
			this.memberCountry = memberCountry;
			return this;
		}
		
		public MemberVO.Builder memberSignupDate(java.sql.Date memberSignupDate) {
			this.memberSignupDate = memberSignupDate;
			return this;
		}
		
		public MemberVO.Builder memberAuth(Integer memberAuth) {
			this.memberAuth = memberAuth;
			return this;
		}
		
		public MemberVO.Builder memberStatus(String memberStatus) {
			this.memberStatus = memberStatus;
			return this;
		}
		
		public MemberVO.Builder memberStatusEmp(String memberStatusEmp) {
			this.memberStatusEmp = memberStatusEmp;
			return this;
		}
		
		public MemberVO.Builder memberStatusComm(String memberStatusComm) {
			this.memberStatusComm = memberStatusComm;
			return this;
		}
		
		public MemberVO.Builder memberOnline(String memberOnline) {
			this.memberOnline = memberOnline;
			return this;
		}
	}

	public MemberVO() {
		super();
	}
	
	private MemberVO(MemberVO.Builder builder) {
		memberId = builder.memberId;
		memberAccount = builder.memberAccount;
		memberPassword = builder.memberPassword;
		memberName = builder.memberName;
		memberNickName = builder.memberNickName;
		memberEmail = builder.memberEmail;
		memberPhoto = builder.memberPhoto;
		memberPhone = builder.memberPhone;
		memberAddress = builder.memberAddress;
		memberBirth = builder.memberBirth;
		memberSex = builder.memberSex;
		memberCountry = builder.memberCountry;
		memberSignupDate = builder.memberSignupDate;
		memberAuth = builder.memberAuth;
		memberStatus = builder.memberStatus;
		memberStatusEmp = builder.memberStatusEmp;
		memberStatusComm = builder.memberStatusComm;
		memberOnline = builder.memberOnline;
	}
	
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
	public String getMemberNickName() {
		return memberNickName;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
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
	public String getMemberStatusEmp() {
		return memberStatusEmp;
	}

	public void setMemberStatusEmp(String memberStatusEmp) {
		this.memberStatusEmp = memberStatusEmp;
	}

	public String getMemberStatusComm() {
		return memberStatusComm;
	}

	public void setMemberStatusComm(String memberStatusComm) {
		this.memberStatusComm = memberStatusComm;
	}
	
	public String getMemberOnline() {
		return memberOnline;
	}

	public void setMemberOnline(String memberOnline) {
		this.memberOnline = memberOnline;
	}

	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", memberAccount=" + memberAccount + ", memberPassword="
				+ memberPassword + ", memberName=" + memberName + ", memberNickName=" + memberNickName
				+ ", memberEmail=" + memberEmail + ", memberPhoto=" + Arrays.toString(memberPhoto) + ", memberPhone="
				+ memberPhone + ", memberAddress=" + memberAddress + ", memberBirth=" + memberBirth + ", memberSex="
				+ memberSex + ", memberCountry=" + memberCountry + ", memberSignupDate=" + memberSignupDate
				+ ", memberAuth=" + memberAuth + ", memberStatus=" + memberStatus + ", memberStatusEmp="
				+ memberStatusEmp + ", memberStatusComm=" + memberStatusComm + ", memberOnline=" + memberOnline + "]";
	}


}
