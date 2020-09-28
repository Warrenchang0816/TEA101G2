package com.orderMaster.model;

public class OrderMasterVO {
	private String orderMasterId;
	private String memberId;
	private java.sql.Date orderCreatDate;
	private Integer orderAmount;
	private String orderStatus;
	
	public String getOrderMasterId() {
		return orderMasterId;
	}

	public void setOrderMasterId(String orderMasterId) {
		this.orderMasterId = orderMasterId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public java.sql.Date getOrderCreatDate() {
		return orderCreatDate;
	}

	public void setOrderCreatDate(java.sql.Date orderCreatDate) {
		this.orderCreatDate = orderCreatDate;
	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "OrderMasterVO [orderMasterId=" + orderMasterId + ", memberId=" + memberId + ", orderCreatDate="
				+ orderCreatDate + ", orderAmount=" + orderAmount + ", orderStatus=" + orderStatus + "]";
	}
	
}
