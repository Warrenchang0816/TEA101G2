package com.formList.model;

import java.util.Arrays;

import com.emp.model.EmpVO;

public class FormListVO {

	private String formListId;
	private String memberId;
	private String empId;
	private java.sql.Date formListCreateDate;
	private String formListType;
	private String formListTitle;
	private String formListContext;
	private String formListStatus;
	private String formListSolu;
	private java.sql.Date formListSoluDate;
	
	public static class Builder {

		private String formListId;
		private String memberId;
		private String empId;
		private java.sql.Date formListCreateDate;
		private String formListType;
		private String formListTitle;
		private String formListContext;
		private String formListStatus;
		private String formListSolu;
		private java.sql.Date formListSoluDate;
	
		public FormListVO.Builder formListId(String formListId) {
			this.formListId = formListId;
			return this;
		}
	
		public FormListVO.Builder memberId(String memberId) {
			this.memberId = memberId;
			return this;
		}
	
		public FormListVO.Builder empId(String empId) {
			this.empId = empId;
			return this;
		}
	
		public FormListVO.Builder formListCreateDate(java.sql.Date formListCreateDate) {
			this.formListCreateDate = formListCreateDate;
			return this;
		}
		
		public FormListVO.Builder formListType(String formListType) {
			this.formListType = formListType;
			return this;
		}
		
		public FormListVO.Builder formListTitle(String formListTitle) {
			this.formListTitle = formListTitle;
			return this;
		}
		
		public FormListVO.Builder formListContext(String formListContext) {
			this.formListContext = formListContext;
			return this;
		}
		
		public FormListVO.Builder formListStatus(String formListStatus) {
			this.formListStatus = formListStatus;
			return this;
		}
		
		public FormListVO.Builder formListSolu(String formListSolu) {
			this.formListSolu = formListSolu;
			return this;
		}
		
		public FormListVO.Builder formListSoluDate(java.sql.Date formListSoluDate) {
			this.formListSoluDate = formListSoluDate;
			return this;
		}
		
	}

	public FormListVO() {
		super();
	}
	
	private FormListVO(FormListVO.Builder builder) {
		formListId = builder.formListId;
		memberId = builder.memberId;
		empId = builder.empId;
		formListCreateDate = builder.formListCreateDate;
		formListType = builder.formListType;
		formListTitle = builder.formListTitle;
		formListContext = builder.formListContext;
		formListStatus = builder.formListStatus;
		formListSolu = builder.formListSolu;
		formListSoluDate = builder.formListSoluDate;
		
	}
	
	public String getFormListId() {
		return formListId;
	}
	public void setFormListId(String formListId) {
		this.formListId = formListId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public java.sql.Date getFormListCreateDate() {
		return formListCreateDate;
	}
	public void setFormListCreateDate(java.sql.Date formListCreateDate) {
		this.formListCreateDate = formListCreateDate;
	}
	public String getFormListType() {
		return formListType;
	}
	public void setFormListType(String formListType) {
		this.formListType = formListType;
	}
	public String getFormListTitle() {
		return formListTitle;
	}
	public void setFormListTitle(String formListTitle) {
		this.formListTitle = formListTitle;
	}
	public String getFormListContext() {
		return formListContext;
	}
	public void setFormListContext(String formListContext) {
		this.formListContext = formListContext;
	}
	public String getFormListStatus() {
		return formListStatus;
	}
	public void setFormListStatus(String formListStatus) {
		this.formListStatus = formListStatus;
	}
	public String getFormListSolu() {
		return formListSolu;
	}
	public void setFormListSolu(String formListSolu) {
		this.formListSolu = formListSolu;
	}
	public java.sql.Date getFormListSoluDate() {
		return formListSoluDate;
	}
	public void setFormListSoluDate(java.sql.Date formListSoluDate) {
		this.formListSoluDate = formListSoluDate;
	}

	@Override
	public String toString() {
		return "FormListVO [formListId=" + formListId + ", memberId=" + memberId + ", empId=" + empId
				+ ", formListCreateDate=" + formListCreateDate + ", formListType=" + formListType + ", formListTitle="
				+ formListTitle + ", formListContext=" + formListContext + ", formListStatus=" + formListStatus
				+ ", formListSolu=" + formListSolu + ", formListSoluDate=" + formListSoluDate + "]";
	}
	

}
