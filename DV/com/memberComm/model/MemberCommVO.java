package com.memberComm.model;

import java.sql.Date;

import com.emp.model.EmpVO;

public class MemberCommVO {
	private String memberCommId;
	private String memberAId;
	private String memberBId;
	private String Comm;
	private Integer CommLevel;
	private java.sql.Date CommDate;
	
	public static class Builder {

		private String memberCommId = "";
		private String memberAId = "";
		private String memberBId = "";
		private String Comm = "";
		private Integer CommLevel = 0;
		private java.sql.Date CommDate = (new java.sql.Date(System.currentTimeMillis()));
	
		public MemberCommVO.Builder memberCommId(String memberCommId) {
			this.memberCommId = memberCommId;
			return this;
		}
	
		public MemberCommVO.Builder memberAId(String memberAId) {
			this.memberAId = memberAId;
			return this;
		}
	
		public MemberCommVO.Builder memberBId(String memberBId) {
			this.memberBId = memberBId;
			return this;
		}
	
		public MemberCommVO.Builder Comm(String Comm) {
			this.Comm = Comm;
			return this;
		}
	
		public MemberCommVO.Builder CommLevel(Integer CommLevel) {
			this.CommLevel = CommLevel;
			return this;
		}
		
		public MemberCommVO.Builder CommDate(java.sql.Date CommDate) {
			this.CommDate = CommDate;
			return this;
		}
	
	}

	public MemberCommVO() {
		super();
	}
	
	private MemberCommVO(MemberCommVO.Builder builder) {
		memberCommId = builder.memberCommId;
		memberAId = builder.memberAId;
		memberBId = builder.memberBId;
		Comm = builder.Comm;
		CommLevel = builder.CommLevel;
		CommDate = builder.CommDate;
	}
	
	public String getMemberCommId() {
		return memberCommId;
	}
	public void setMemberCommId(String memberCommId) {
		this.memberCommId = memberCommId;
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
	public String getComm() {
		return Comm;
	}
	public void setComm(String comm) {
		Comm = comm;
	}
	public Integer getCommLevel() {
		return CommLevel;
	}
	public void setCommLevel(Integer commLevel) {
		CommLevel = commLevel;
	}
	public java.sql.Date getCommDate() {
		return CommDate;
	}
	public void setCommDate(java.sql.Date commDate) {
		CommDate = commDate;
	}
	
	@Override
	public String toString() {
		return "MemberCommVO [memberCommId=" + memberCommId + ", memberAId=" + memberAId + ", memberBId=" + memberBId
				+ ", Comm=" + Comm + ", CommLevel=" + CommLevel + ", CommDate=" + CommDate + "]";
	}

	
}
