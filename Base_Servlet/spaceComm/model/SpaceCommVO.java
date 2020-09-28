package com.spaceComm.model;

public class SpaceCommVO {
	
	private String spaceCommId;
	private String spaceId;
	private String memberId;
	private String comm;
	private Integer commLevel;
	private java.sql.Date commDate;
	
	
	public String getSpaceCommId() {
		return spaceCommId;
	}
	public void setSpaceCommId(String spaceCommId) {
		this.spaceCommId = spaceCommId;
	}
	public String getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	public Integer getCommLevel() {
		return commLevel;
	}
	public void setCommLevel(Integer commLevel) {
		this.commLevel = commLevel;
	}
	public java.sql.Date getCommDate() {
		return commDate;
	}
	public void setCommDate(java.sql.Date commDate) {
		this.commDate = commDate;
	}
	
	@Override
	public String toString() {
		return "SpaceCommVO [spaceCommlId=" + spaceCommId + ", spaceId=" + spaceId + ", memberId=" + memberId
				+ ", Comm=" + comm + ", CommLevel=" + commLevel + ", CommDate=" + commDate + "]";
	}
	
	
}
