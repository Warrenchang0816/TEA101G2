package com.spaceDetail.model;

public class SpaceDetailVO {
	
	private String spaceDetailId;
	private String spaceId;
	private java.sql.Date spaceDetailFreeDate;
//	private java.sql.Date spaceDetailFreeTime;
	private java.sql.Timestamp spaceDetailFreeTimeStart;
	private java.sql.Timestamp spaceDetailFreeTimeEnd;
	private Integer spaceDetailCharge;
	
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
