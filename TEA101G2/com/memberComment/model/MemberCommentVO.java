package com.memberComment.model;

import java.sql.Date;

import com.emp.model.EmpVO;

public class MemberCommentVO {
	private String memberCommentId;
	private String memberAId;
	private String memberBId;
	private String memberCommentContent;
	private Double memberCommentLevel;
	private java.sql.Date memberCommentDate;
	private String memberCommStatus;
	private String memberCommStatusEmp;
	private String memberCommStatusComm;
	
	public static class Builder {

		private String memberCommentId = "";
		private String memberAId = "";
		private String memberBId = "";
		private String memberCommentContent = "";
		private Double memberCommentLevel = 0.0;
		private java.sql.Date memberCommentDate = (new java.sql.Date(System.currentTimeMillis()));
		private String memberCommStatus = "";
		private String memberCommStatusEmp = "";
		private String memberCommStatusComm = "";
	
		public MemberCommentVO.Builder memberCommentId(String memberCommentId) {
			this.memberCommentId = memberCommentId;
			return this;
		}
	
		public MemberCommentVO.Builder memberAId(String memberAId) {
			this.memberAId = memberAId;
			return this;
		}
	
		public MemberCommentVO.Builder memberBId(String memberBId) {
			this.memberBId = memberBId;
			return this;
		}
	
		public MemberCommentVO.Builder memberCommentContent(String memberCommentContent) {
			this.memberCommentContent = memberCommentContent;
			return this;
		}
	
		public MemberCommentVO.Builder memberCommentLevel(Double memberCommentLevel) {
			this.memberCommentLevel = memberCommentLevel;
			return this;
		}
		
		public MemberCommentVO.Builder memberCommentDate(java.sql.Date memberCommentDate) {
			this.memberCommentDate = memberCommentDate;
			return this;
		}
		
		public MemberCommentVO.Builder memberCommStatus(String memberCommStatus) {
			this.memberCommStatus = memberCommStatus;
			return this;
		}
		
		public MemberCommentVO.Builder memberCommStatusEmp(String memberCommStatusEmp) {
			this.memberCommStatusEmp = memberCommStatusEmp;
			return this;
		}
		
		public MemberCommentVO.Builder memberCommStatusComm(String memberCommStatusComm) {
			this.memberCommStatusComm = memberCommStatusComm;
			return this;
		}
	
	}

	public MemberCommentVO() {
		super();
	}
	
	private MemberCommentVO(MemberCommentVO.Builder builder) {
		memberCommentId = builder.memberCommentId;
		memberAId = builder.memberAId;
		memberBId = builder.memberBId;
		memberCommentContent = builder.memberCommentContent;
		memberCommentLevel = builder.memberCommentLevel;
		memberCommentDate = builder.memberCommentDate;
		memberCommStatus = builder.memberCommStatus;
		memberCommStatusEmp = builder.memberCommStatusEmp;
		memberCommStatusComm = builder.memberCommStatusComm;
	}
	
	
	public String getMemberCommentId() {
		return memberCommentId;
	}

	public void setMemberCommentId(String memberCommentId) {
		this.memberCommentId = memberCommentId;
	}

	public String getMemberAId() {
		return memberAId;
	}
	public void setMemberAId(String memberAId) {
		this.memberAId = memberAId;
	}
	public String getMemberBId() {
		return memberBId;
	}
	public void setMemberBId(String memberBId) {
		this.memberBId = memberBId;
	}
	
	
	public String getMemberCommentContent() {
		return memberCommentContent;
	}

	public void setMemberCommentContent(String memberCommentContent) {
		this.memberCommentContent = memberCommentContent;
	}

	public Double getMemberCommentLevel() {
		return memberCommentLevel;
	}

	public void setMemberCommentLevel(Double memberCommentLevel) {
		this.memberCommentLevel = memberCommentLevel;
	}

	public java.sql.Date getMemberCommentDate() {
		return memberCommentDate;
	}

	public void setMemberCommentDate(java.sql.Date memberCommentDate) {
		this.memberCommentDate = memberCommentDate;
	}

	public String getMemberCommStatus() {
		return memberCommStatus;
	}

	public void setMemberCommStatus(String memberCommStatus) {
		this.memberCommStatus = memberCommStatus;
	}

	public String getMemberCommStatusEmp() {
		return memberCommStatusEmp;
	}

	public void setMemberCommStatusEmp(String memberCommStatusEmp) {
		this.memberCommStatusEmp = memberCommStatusEmp;
	}

	public String getMemberCommStatusComm() {
		return memberCommStatusComm;
	}

	public void setMemberCommStatusComm(String memberCommStatusComm) {
		this.memberCommStatusComm = memberCommStatusComm;
	}

	@Override
	public String toString() {
		return "MemberCommentVO [memberCommentId=" + memberCommentId + ", memberAId=" + memberAId + ", memberBId=" + memberBId
				+ ", memberCommentContent=" + memberCommentContent + ", memberCommentLevel=" + memberCommentLevel
				+ ", memberCommentDate=" + memberCommentDate + ", memberCommStatus=" + memberCommStatus
				+ ", memberCommStatusEmp=" + memberCommStatusEmp + ", memberCommStatusComm=" + memberCommStatusComm
				+ "]";
	}

	

}
