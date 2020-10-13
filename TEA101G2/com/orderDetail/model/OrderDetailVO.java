package com.orderDetail.model;

import com.emp.model.EmpVO;

public class OrderDetailVO {
	private String orderDetailId;
	private String orderMasterId;
	private String spaceDetailId;
	private java.sql.Timestamp rentStartTime;
	private java.sql.Timestamp rentEndTime;
	
	public static class Builder {

		private String orderDetailId = "";
		private String orderMasterId = "";
		private String spaceDetailId = "";
		private java.sql.Timestamp rentStartTime = new java.sql.Timestamp(System.currentTimeMillis());
		private java.sql.Timestamp rentEndTime = new java.sql.Timestamp(System.currentTimeMillis());
	
		public OrderDetailVO.Builder orderDetailId(String orderDetailId) {
			this.orderDetailId = orderDetailId;
			return this;
		}
	
		public OrderDetailVO.Builder orderMasterId(String orderMasterId) {
			this.orderMasterId = orderMasterId;
			return this;
		}
	
		public OrderDetailVO.Builder spaceDetailId(String spaceDetailId) {
			this.spaceDetailId = spaceDetailId;
			return this;
		}
	
		public OrderDetailVO.Builder rentStartTime(java.sql.Timestamp rentStartTime) {
			this.rentStartTime = rentStartTime;
			return this;
		}
	
		public OrderDetailVO.Builder rentEndTime(java.sql.Timestamp rentEndTime) {
			this.rentEndTime = rentEndTime;
			return this;
		}
		
	}

	public OrderDetailVO() {
		super();
	}
	
	private OrderDetailVO(OrderDetailVO.Builder builder) {
		orderDetailId = builder.orderDetailId;
		orderMasterId = builder.orderMasterId;
		spaceDetailId = builder.spaceDetailId;
		rentStartTime = builder.rentStartTime;
		rentEndTime = builder.rentEndTime;
	}
	
	
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
