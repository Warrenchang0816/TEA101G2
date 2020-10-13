package com.emp.model;

import java.util.Arrays;

import com.spaceDetail.model.SpaceDetailVO;

public class EmpVO {
	
	private String empId;
	private String empAccount;
	private String empPassword;
	private String empName;
	private String empNickname;
	private String empEmail;
	private byte[] empPhoto;
	private String empPhone;
	private String empAddress;
	private java.sql.Date empBirth;
	private String empSex;
	private String empCountry;
	private java.sql.Date empHireDate;
	private String empJob;
	private Integer empAuth;
	private String empStatus;
	private String empOnline;
	
	public static class Builder {

		private String empId = "";
		private String empAccount = "";
		private String empPassword = "";
		private String empName = "";
		private String empNickname = "";
		private String empEmail = "";
		private byte[] empPhoto = null;
		private String empPhone = "";
		private String empAddress = "";
		private java.sql.Date empBirth = (new java.sql.Date(System.currentTimeMillis()));
		private String empSex = "";
		private String empCountry = "";
		private java.sql.Date empHireDate = (new java.sql.Date(System.currentTimeMillis()));
		private String empJob = "";
		private Integer empAuth = 0;
		private String empStatus = "";
		private String empOnline = "";
	
		public EmpVO.Builder empId(String empId) {
			this.empId = empId;
			return this;
		}
	
		public EmpVO.Builder empAccount(String empAccount) {
			this.empAccount = empAccount;
			return this;
		}
	
		public EmpVO.Builder empPassword(String empPassword) {
			this.empPassword = empPassword;
			return this;
		}
	
		public EmpVO.Builder empName(String empName) {
			this.empName = empName;
			return this;
		}
	
		public EmpVO.Builder empNickname(String empNickname) {
			this.empNickname = empNickname;
			return this;
		}
		
		public EmpVO.Builder empEmail(String empEmail) {
			this.empEmail = empEmail;
			return this;
		}
		
		public EmpVO.Builder empPhoto(byte[] empPhoto) {
			this.empPhoto = empPhoto;
			return this;
		}
		
		public EmpVO.Builder empPhone(String empPhone) {
			this.empPhone = empPhone;
			return this;
		}
		
		public EmpVO.Builder empAddress(String empAddress) {
			this.empAddress = empAddress;
			return this;
		}
		
		public EmpVO.Builder empBirth(java.sql.Date empBirth) {
			this.empBirth = empBirth;
			return this;
		}
		
		public EmpVO.Builder empSex(String empSex) {
			this.empSex = empSex;
			return this;
		}
		
		public EmpVO.Builder empCountry(String empCountry) {
			this.empCountry = empCountry;
			return this;
		}
		
		public EmpVO.Builder empHireDate(java.sql.Date empHireDate) {
			this.empHireDate = empHireDate;
			return this;
		}
		
		public EmpVO.Builder empJob(String empJob) {
			this.empJob = empJob;
			return this;
		}
		
		public EmpVO.Builder empAuth(Integer empAuth) {
			this.empAuth = empAuth;
			return this;
		}
		
		public EmpVO.Builder empStatus(String empStatus) {
			this.empStatus = empStatus;
			return this;
		}
		
		public EmpVO.Builder empOnline(String empOnline) {
			this.empOnline = empOnline;
			return this;
		}
	}

	public EmpVO() {
		super();
	}
	
	private EmpVO(EmpVO.Builder builder) {
		empId = builder.empId;
		empAccount = builder.empAccount;
		empPassword = builder.empPassword;
		empName = builder.empName;
		empNickname = builder.empNickname;
		empEmail = builder.empEmail;
		empPhoto = builder.empPhoto;
		empPhone = builder.empPhone;
		empAddress = builder.empAddress;
		empBirth = builder.empBirth;
		empSex = builder.empSex;
		empCountry = builder.empCountry;
		empHireDate = builder.empHireDate;
		empJob = builder.empJob;
		empAuth = builder.empAuth;
		empStatus = builder.empStatus;
		empOnline = builder.empOnline;
		
	}
	
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
	
	public String getEmpOnline() {
		return empOnline;
	}

	public void setEmpOnline(String empOnline) {
		this.empOnline = empOnline;
	}

	@Override
	public String toString() {
		return "EmpVO [empId=" + empId + ", empAccount=" + empAccount + ", empPassword=" + empPassword + ", empName="
				+ empName + ", empNickname=" + empNickname + ", empEmail=" + empEmail + ", empPhoto="
				+ Arrays.toString(empPhoto) + ", empPhone=" + empPhone + ", empAddress=" + empAddress + ", empBirth="
				+ empBirth + ", empSex=" + empSex + ", empCountry=" + empCountry + ", empHireDate=" + empHireDate
				+ ", empJob=" + empJob + ", empAuth=" + empAuth + ", empStatus=" + empStatus + ", empOnline="
				+ empOnline + "]";
	}

}
