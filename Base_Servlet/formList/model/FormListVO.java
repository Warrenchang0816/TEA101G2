package com.formList.model;

import java.util.Arrays;

public class FormListVO {

	private String formListId;
	private String memberId;
	private String empId;
	private java.sql.Date formListCreateDate;
	private String formListType;
	private String formListTitle;
	private String formListContext;
	private byte[] formListFile;
	private String formListStatus;
	private String formListSolu;
	private java.sql.Date formListSoluDate;
	
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
	public byte[] getFormListFile() {
		return formListFile;
	}
	public void setFormListFile(byte[] formListFile) {
		this.formListFile = formListFile;
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
				+ formListTitle + ", formListContext=" + formListContext + ", formListFile="
				+ Arrays.toString(formListFile) + ", formListStatus=" + formListStatus + ", formListSolu="
				+ formListSolu + ", formListSoluDate=" + formListSoluDate + "]";
	}
}
