package com.formList.model;

import java.util.Arrays;

public class FormListVO {

	private String formListId;
	private String membrId;
	private String empId;
	private java.sql.Date formListCreateDate;
	private String formListType;
	private String formListTitle;
	private String formListContext;
	private byte[] formListFile;
	
	public String getFormListId() {
		return formListId;
	}
	public void setFormListId(String formListId) {
		this.formListId = formListId;
	}
	public String getMembrId() {
		return membrId;
	}
	public void setMembrId(String membrId) {
		this.membrId = membrId;
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
	
	@Override
	public String toString() {
		return "FormListVO [formListId=" + formListId + ", membrId=" + membrId + ", empId=" + empId
				+ ", formListCreateDate=" + formListCreateDate + ", formListType=" + formListType + ", formListTitle="
				+ formListTitle + ", formListContext=" + formListContext + ", formListFile="
				+ Arrays.toString(formListFile) + "]";
	}
	
}
