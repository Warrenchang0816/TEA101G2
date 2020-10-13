package com.orderMaster.model;

public class OrderMasterVO {
	private String orderMasterId;
	private String memberId;
	private java.sql.Date orderCreateDate;
	private Integer orderAmount;
	private String orderStatus;
	private String orderStatusEmp;
	private String orderStatusComm;
	
	public static class Builder {

		private String orderMasterId = "";
		private String memberId = "";
		private java.sql.Date orderCreatDate = (new java.sql.Date(System.currentTimeMillis()));
		private Integer orderAmount = 0;
		private String orderStatus = "";
		private String orderStatusEmp = "";
		private String orderStatusComm = "";
	
		public OrderMasterVO.Builder orderMasterId(String orderMasterId) {
			this.orderMasterId = orderMasterId;
			return this;
		}
	
		public OrderMasterVO.Builder memberId(String memberId) {
			this.memberId = memberId;
			return this;
		}
	
		public OrderMasterVO.Builder orderCreatDate(java.sql.Date orderCreatDate) {
			this.orderCreatDate = orderCreatDate;
			return this;
		}
	
		public OrderMasterVO.Builder orderAmount(Integer orderAmount) {
			this.orderAmount = orderAmount;
			return this;
		}
	
		public OrderMasterVO.Builder orderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
			return this;
		}
		
		public OrderMasterVO.Builder orderStatusEmp(String orderStatusEmp) {
			this.orderStatusEmp = orderStatusEmp;
			return this;
		}
		
		public OrderMasterVO.Builder orderStatusComm(String orderStatusComm) {
			this.orderStatusComm = orderStatusComm;
			return this;
		}
	
	}

	public OrderMasterVO() {
		super();
	}
	
	private OrderMasterVO(OrderMasterVO.Builder builder) {
		orderMasterId = builder.orderMasterId;
		memberId = builder.memberId;
		orderCreateDate = builder.orderCreatDate;
		orderAmount = builder.orderAmount;
		orderStatus = builder.orderStatus;
		orderStatusEmp = builder.orderStatusEmp;
		orderStatusComm = builder.orderStatusComm;
	}
	
	
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

	public java.sql.Date getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(java.sql.Date orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
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

	public String getOrderStatusEmp() {
		return orderStatusEmp;
	}

	public void setOrderStatusEmp(String orderStatusEmp) {
		this.orderStatusEmp = orderStatusEmp;
	}

	public String getOrderStatusComm() {
		return orderStatusComm;
	}

	public void setOrderStatusComm(String orderStatusComm) {
		this.orderStatusComm = orderStatusComm;
	}

	@Override
	public String toString() {
		return "OrderMasterVO [orderMasterId=" + orderMasterId + ", memberId=" + memberId + ", orderCreateDate="
				+ orderCreateDate + ", orderAmount=" + orderAmount + ", orderStatus=" + orderStatus + ", orderStatusEmp="
				+ orderStatusEmp + ", orderStatusComm=" + orderStatusComm + "]";
	}

	
}
