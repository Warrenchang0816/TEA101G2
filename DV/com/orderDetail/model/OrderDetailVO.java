package com.orderDetail.model;

public class OrderDetailVO {
	private String orderDetailId;
	private String orderMasterId;
	private String spaceDetailId;
	private java.sql.Timestamp rentStartTime;
	private java.sql.Timestamp rentEndTime;
	
	public String getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public String getOrderMasterId() {
		return orderMasterId;
	}
	public void setOrderMasterId(String orderMasterId) {
		this.orderMasterId = orderMasterId;
	}
	public String getSpaceDetailId() {
		return spaceDetailId;
	}
	public void setSpaceDetailId(String spaceDetailId) {
		this.spaceDetailId = spaceDetailId;
	}
	public java.sql.Timestamp getRentStartTime() {
		return rentStartTime;
	}
	public void setRentStartTime(java.sql.Timestamp rentStartTime) {
		this.rentStartTime = rentStartTime;
	}
	public java.sql.Timestamp getRentEndTime() {
		return rentEndTime;
	}
	public void setRentEndTime(java.sql.Timestamp rentEndTime) {
		this.rentEndTime = rentEndTime;
	}
	
	
	@Override
	public String toString() {
		return "OrderDetailVO [orderDetailId=" + orderDetailId + ", orderMasterId=" + orderMasterId + ", spaceDetailId="
				+ spaceDetailId + ", rentStartTime=" + rentStartTime + ", rentEndTime=" + rentEndTime + "]";
	}
	
	
}
