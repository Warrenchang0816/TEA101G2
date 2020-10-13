package com.spaceDetail.model;

import com.orderMaster.model.OrderMasterVO;

public class SpaceDetailVO {
	
	private String spaceDetailId;
	private String spaceId;
	private java.sql.Date spaceDetailFreeDate;
	private java.sql.Timestamp spaceDetailFreeTimeStart;
	private java.sql.Timestamp spaceDetailFreeTimeEnd;
	private Integer spaceDetailCharge;
	
	public static class Builder {

		private String spaceDetailId = "";
		private String spaceId = "";
		private java.sql.Date spaceDetailFreeDate = (new java.sql.Date(System.currentTimeMillis()));
		private java.sql.Timestamp spaceDetailFreeTimeStart = (new java.sql.Timestamp(System.currentTimeMillis()));
		private java.sql.Timestamp spaceDetailFreeTimeEnd = (new java.sql.Timestamp(System.currentTimeMillis()));
		private Integer spaceDetailCharge = 0;
	
		public SpaceDetailVO.Builder spaceDetailId(String spaceDetailId) {
			this.spaceDetailId = spaceDetailId;
			return this;
		}
	
		public SpaceDetailVO.Builder spaceId(String spaceId) {
			this.spaceId = spaceId;
			return this;
		}
	
		public SpaceDetailVO.Builder spaceDetailFreeDate(java.sql.Date spaceDetailFreeDate) {
			this.spaceDetailFreeDate = spaceDetailFreeDate;
			return this;
		}
	
		public SpaceDetailVO.Builder spaceDetailFreeTimeStart(java.sql.Timestamp spaceDetailFreeTimeStart) {
			this.spaceDetailFreeTimeStart = spaceDetailFreeTimeStart;
			return this;
		}
	
		public SpaceDetailVO.Builder spaceDetailFreeTimeEnd(java.sql.Timestamp spaceDetailFreeTimeEnd) {
			this.spaceDetailFreeTimeEnd = spaceDetailFreeTimeEnd;
			return this;
		}
		
		public SpaceDetailVO.Builder spaceDetailCharge(Integer spaceDetailCharge) {
			this.spaceDetailCharge = spaceDetailCharge;
			return this;
		}
	
	}

	public SpaceDetailVO() {
		super();
	}
	
	private SpaceDetailVO(SpaceDetailVO.Builder builder) {
		spaceDetailId = builder.spaceDetailId;
		spaceId = builder.spaceId;
		spaceDetailFreeDate = builder.spaceDetailFreeDate;
		spaceDetailFreeTimeStart = builder.spaceDetailFreeTimeStart;
		spaceDetailFreeTimeEnd = builder.spaceDetailFreeTimeEnd;
		spaceDetailCharge = builder.spaceDetailCharge;
		
	}
	
	public String getSpaceDetailId() {
		return spaceDetailId;
	}
	public void setSpaceDetailId(String spaceDetailId) {
		this.spaceDetailId = spaceDetailId;
	}
	public String getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	public java.sql.Date getSpaceDetailFreeDate() {
		return spaceDetailFreeDate;
	}
	public void setSpaceDetailFreeDate(java.sql.Date spaceDetailFreeDate) {
		this.spaceDetailFreeDate = spaceDetailFreeDate;
	}
	public java.sql.Timestamp getSpaceDetailFreeTimeStart() {
		return spaceDetailFreeTimeStart;
	}
	public void setSpaceDetailFreeTimeStart(java.sql.Timestamp spaceDetailFreeTimeStart) {
		this.spaceDetailFreeTimeStart = spaceDetailFreeTimeStart;
	}
	public java.sql.Timestamp getSpaceDetailFreeTimeEnd() {
		return spaceDetailFreeTimeEnd;
	}
	public void setSpaceDetailFreeTimeEnd(java.sql.Timestamp spaceDetailFreeTimeEnd) {
		this.spaceDetailFreeTimeEnd = spaceDetailFreeTimeEnd;
	}
	public Integer getSpaceDetailCharge() {
		return spaceDetailCharge;
	}
	public void setSpaceDetailCharge(Integer spaceDetailCharge) {
		this.spaceDetailCharge = spaceDetailCharge;
	}
	
	
	@Override
	public String toString() {
		return "SpaceDetailVO [spaceDetailId=" + spaceDetailId + ", spaceId=" + spaceId + ", spaceDetailFreeDate="
				+ spaceDetailFreeDate + ", spaceDetailFreeTimeStart=" + spaceDetailFreeTimeStart
				+ ", spaceDetailFreeTimeEnd=" + spaceDetailFreeTimeEnd + ", spaceDetailCharge=" + spaceDetailCharge
				+ "]";
	}
	
	

}
