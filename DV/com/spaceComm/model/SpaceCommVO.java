package com.spaceComm.model;

import java.sql.Date;

import com.emp.model.EmpVO;

public class SpaceCommVO {
	
	private String spaceCommId;
	private String spaceId;
	private String memberId;
	private String comm;
	private Integer commLevel;
	private java.sql.Date commDate;
	
	public static class Builder {

		private String spaceCommId = "";
		private String spaceId = "";
		private String memberId = "";
		private String comm = "";
		private Integer commLevel = 0;
		private java.sql.Date commDate = new java.sql.Date(System.currentTimeMillis());
	
		public SpaceCommVO.Builder spaceCommId(String spaceCommId) {
			this.spaceCommId = spaceCommId;
			return this;
		}
	
		public SpaceCommVO.Builder spaceId(String spaceId) {
			this.spaceId = spaceId;
			return this;
		}
	
		public SpaceCommVO.Builder memberId(String memberId) {
			this.memberId = memberId;
			return this;
		}
	
		public SpaceCommVO.Builder comm(String comm) {
			this.comm = comm;
			return this;
		}
	
		public SpaceCommVO.Builder commLevel(Integer commLevel) {
			this.commLevel = commLevel;
			return this;
		}
		
		public SpaceCommVO.Builder commDate(java.sql.Date commDate) {
			this.commDate = commDate;
			return this;
		}
		
	}

	public SpaceCommVO() {
		super();
	}
	
	private SpaceCommVO(SpaceCommVO.Builder builder) {
		spaceCommId = builder.spaceCommId;
		spaceId = builder.spaceId;
		memberId = builder.memberId;
		comm = builder.comm;
		commLevel = builder.commLevel;
		commDate = builder.commDate;
	}
	
	
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
