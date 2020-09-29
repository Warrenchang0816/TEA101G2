package com.memberComm.model;

public class MemberCommVO {
	private String memberCommId;
	private String memberAId;
	private String memberBId;
	private String Comm;
	private Integer CommLevel;
	private java.sql.Date CommDate;
	
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
