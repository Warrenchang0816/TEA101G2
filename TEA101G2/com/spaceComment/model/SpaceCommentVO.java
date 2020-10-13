package com.spaceComment.model;

import java.sql.Date;

import com.emp.model.EmpVO;

public class SpaceCommentVO {
	
	private String spaceCommentId;
	private String spaceId;
	private String memberId;
	private String spaceCommentContent;
	private Double spaceCommentLevel;
	private java.sql.Date spaceCommentDate;
	private String spaceCommStatus;
	private String spaceCommStatusEmp;
	private String spaceCommStatusComm;
	
	public static class Builder {

		private String spaceCommentId = "";
		private String spaceId = "";
		private String memberId = "";
		private String spaceCommentContent = "";
		private Double spaceCommentLevel = 0.0;
		private java.sql.Date spaceCommentDate = new java.sql.Date(System.currentTimeMillis());
		private String spaceCommStatus = "";
		private String spaceCommStatusEmp = "";
		private String spaceCommStatusComm = "";
	
		public SpaceCommentVO.Builder spaceCommentId(String spaceCommentId) {
			this.spaceCommentId = spaceCommentId;
			return this;
		}
	
		public SpaceCommentVO.Builder spaceId(String spaceId) {
			this.spaceId = spaceId;
			return this;
		}
	
		public SpaceCommentVO.Builder memberId(String memberId) {
			this.memberId = memberId;
			return this;
		}
	
		public SpaceCommentVO.Builder spaceCommentContent(String spaceCommentContent) {
			this.spaceCommentContent = spaceCommentContent;
			return this;
		}
	
		public SpaceCommentVO.Builder spaceCommentLevel(Double spaceCommentLevel) {
			this.spaceCommentLevel = spaceCommentLevel;
			return this;
		}
		
		public SpaceCommentVO.Builder spaceCommentDate(java.sql.Date spaceCommentDate) {
			this.spaceCommentDate = spaceCommentDate;
			return this;
		}
		
		public SpaceCommentVO.Builder spaceCommStatus(String spaceCommStatus) {
			this.spaceCommStatus = spaceCommStatus;
			return this;
		}
		
		public SpaceCommentVO.Builder spaceCommStatusEmp(String spaceCommStatusEmp) {
			this.spaceCommStatusEmp = spaceCommStatusEmp;
			return this;
		}
		
		public SpaceCommentVO.Builder spaceCommStatusComm(String spaceCommStatusComm) {
			this.spaceCommStatusComm = spaceCommStatusComm;
			return this;
		}
		
	}

	public SpaceCommentVO() {
		super();
	}
	
	private SpaceCommentVO(SpaceCommentVO.Builder builder) {
		spaceCommentId = builder.spaceCommentId;
		spaceId = builder.spaceId;
		memberId = builder.memberId;
		spaceCommentContent = builder.spaceCommentContent;
		spaceCommentLevel = builder.spaceCommentLevel;
		spaceCommentDate = builder.spaceCommentDate;
		spaceCommStatus = builder.spaceCommStatus;
		spaceCommStatusEmp = builder.spaceCommStatusEmp;
		spaceCommStatusComm = builder.spaceCommStatusComm;
	}
	
	
	public String getSpaceCommentId() {
		return spaceCommentId;
	}
	public void setSpaceCommentId(String spaceCommentId) {
		this.spaceCommentId = spaceCommentId;
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

	public String getSpaceCommentContent() {
		return spaceCommentContent;
	}

	public void setSpaceCommentContent(String spaceCommentContent) {
		this.spaceCommentContent = spaceCommentContent;
	}

	public Double getSpaceCommentLevel() {
		return spaceCommentLevel;
	}

	public void setSpaceCommentLevel(Double spaceCommentLevel) {
		this.spaceCommentLevel = spaceCommentLevel;
	}

	public java.sql.Date getSpaceCommentDate() {
		return spaceCommentDate;
	}

	public void setSpaceCommentDate(java.sql.Date spaceCommentDate) {
		this.spaceCommentDate = spaceCommentDate;
	}

	public String getSpaceCommStatus() {
		return spaceCommStatus;
	}

	public void setSpaceCommStatus(String spaceCommStatus) {
		this.spaceCommStatus = spaceCommStatus;
	}

	public String getSpaceCommStatusEmp() {
		return spaceCommStatusEmp;
	}

	public void setSpaceCommStatusEmp(String spaceCommStatusEmp) {
		this.spaceCommStatusEmp = spaceCommStatusEmp;
	}

	public String getSpaceCommStatusComm() {
		return spaceCommStatusComm;
	}

	public void setSpaceCommStatusComm(String spaceCommStatusComm) {
		this.spaceCommStatusComm = spaceCommStatusComm;
	}

	@Override
	public String toString() {
		return "SpaceCommentVO [spaceCommentId=" + spaceCommentId + ", spaceId=" + spaceId + ", memberId=" + memberId
				+ ", spaceCommentContent=" + spaceCommentContent + ", spaceCommentLevel=" + spaceCommentLevel
				+ ", spaceCommentDate=" + spaceCommentDate + ", spaceCommStatus=" + spaceCommStatus
				+ ", spaceCommStatusEmp=" + spaceCommStatusEmp + ", spaceCommStatusComm=" + spaceCommStatusComm + "]";
	}

}
