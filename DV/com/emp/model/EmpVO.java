package com.emp.model;

import java.util.Arrays;

public class EmpVO {
	
	private String empId;
	private String empAccount;
	private String empPassword;
	private String empName;
	private String empNickname;
	private String empEmail;
	private byte[] empPhoto;
//	private String empPhotoBase64;
	private String empPhone;
	private String empAddress;
	private java.sql.Date empBirth;
	private String empSex;
	private String empCountry;
	private java.sql.Date empHireDate;
	private String empJob;
	private Integer empAuth;
	private String empStatus;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpAccount() {
		return empAccount;
	}
	public void setEmpAccount(String empAccount) {
		this.empAccount = empAccount;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpNickname() {
		return empNickname;
	}
	public void setEmpNickname(String empNickname) {
		this.empNickname = empNickname;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public byte[] getEmpPhoto() {
		return empPhoto;
	}
	public void setEmpPhoto(byte[] empPhoto) {
		this.empPhoto = empPhoto;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public java.sql.Date getEmpBirth() {
		return empBirth;
	}
	public void setEmpBirth(java.sql.Date empBirth) {
		this.empBirth = empBirth;
	}
	public String getEmpSex() {
		return empSex;
	}
	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}
	public String getEmpCountry() {
		return empCountry;
	}
	public void setEmpCountry(String empCountry) {
		this.empCountry = empCountry;
	}
	public java.sql.Date getEmpHireDate() {
		return empHireDate;
	}
	public void setEmpHireDate(java.sql.Date empHireDate) {
		this.empHireDate = empHireDate;
	}
	public String getEmpJob() {
		return empJob;
	}
	public void setEmpJob(String empJob) {
		this.empJob = empJob;
	}
	public Integer getEmpAuth() {
		return empAuth;
	}
	public void setEmpAuth(Integer empAuth) {
		this.empAuth = empAuth;
	}
	public String getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
	
	@Override
	public String toString() {
		return "EmpVO [empId=" + empId + ", empAccount=" + empAccount + ", empPassword=" + empPassword + ", empName="
				+ empName + ", empNickname=" + empNickname + ", empEmail=" + empEmail + ", empPhoto="
				+ Arrays.toString(empPhoto) + ", empPhone=" + empPhone
				+ ", empAddress=" + empAddress + ", empBirth=" + empBirth + ", empSex=" + empSex + ", empCountry="
				+ empCountry + ", empHireDate=" + empHireDate + ", empJob=" + empJob + ", empAuth=" + empAuth
				+ ", empStatus=" + empStatus + "]";
	}
	
	
	
}
