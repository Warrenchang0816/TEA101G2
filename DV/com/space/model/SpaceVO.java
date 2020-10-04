package com.space.model;

import java.sql.Date;

import com.emp.model.EmpVO;

public class SpaceVO {
	private String spaceId;
	private String memberId;
	private String empId;
	private String spaceAddress;
	private String spaceName;
	private String spaceType;
	private String spaceEqument;
	private String spaceContain;
	private String spaceRule;
	private String spaceRefund;
	private String spaceStatus;
	private java.sql.Date spaceSignupDate;
	private java.sql.Date spaceOnsaleDate;
	private java.sql.Date spaceOffsaleDate;
	private String spaceStatusEmp;
	private String spaceStatusComm;
	
	public static class Builder {

		private String spaceId = "";
		private String memberId = "";
		private String empId = "";
		private String spaceAddress = "";
		private String spaceName = "";
		private String spaceType = "";
		private String spaceEqument = "";
		private String spaceContain = "";
		private String spaceRule = "";
		private String spaceRefund = "";
		private String spaceStatus = "";
		private java.sql.Date spaceSignupDate = new java.sql.Date(System.currentTimeMillis());
		private java.sql.Date spaceOnsaleDate = new java.sql.Date(System.currentTimeMillis());
		private java.sql.Date spaceOffsaleDate = new java.sql.Date(System.currentTimeMillis());
		private String spaceStatusEmp = "";
		private String spaceStatusComm = "";
	
		public SpaceVO.Builder spaceId(String spaceId) {
			this.spaceId = spaceId;
			return this;
		}
	
		public SpaceVO.Builder memberId(String memberId) {
			this.memberId = memberId;
			return this;
		}
	
		public SpaceVO.Builder empId(String empId) {
			this.empId = empId;
			return this;
		}
	
		public SpaceVO.Builder spaceAddress(String spaceAddress) {
			this.spaceAddress = spaceAddress;
			return this;
		}
	
		public SpaceVO.Builder spaceName(String spaceName) {
			this.spaceName = spaceName;
			return this;
		}
		
		public SpaceVO.Builder spaceType(String spaceType) {
			this.spaceType = spaceType;
			return this;
		}
		
		public SpaceVO.Builder spaceEqument(String spaceEqument) {
			this.spaceEqument = spaceEqument;
			return this;
		}
		
		public SpaceVO.Builder spaceContain(String spaceContain) {
			this.spaceContain = spaceContain;
			return this;
		}
		
		public SpaceVO.Builder spaceRule(String spaceRule) {
			this.spaceRule = spaceRule;
			return this;
		}
		
		public SpaceVO.Builder spaceRefund(String spaceRefund) {
			this.spaceRefund = spaceRefund;
			return this;
		}
		
		public SpaceVO.Builder spaceStatus(String spaceStatus) {
			this.spaceStatus = spaceStatus;
			return this;
		}
		
		public SpaceVO.Builder spaceSignupDate(java.sql.Date spaceSignupDate) {
			this.spaceSignupDate = spaceSignupDate;
			return this;
		}
		
		public SpaceVO.Builder spaceOnsaleDate(java.sql.Date spaceOnsaleDate) {
			this.spaceOnsaleDate = spaceOnsaleDate;
			return this;
		}
		
		public SpaceVO.Builder spaceOffsaleDate(java.sql.Date spaceOffsaleDate) {
			this.spaceOffsaleDate = spaceOffsaleDate;
			return this;
		}
		
		public SpaceVO.Builder spaceStatusEmp(String spaceStatusEmp) {
			this.spaceStatusEmp = spaceStatusEmp;
			return this;
		}
		
		public SpaceVO.Builder spaceStatusComm(String spaceStatusComm) {
			this.spaceStatusComm = spaceStatusComm;
			return this;
		}
	
	}

	public SpaceVO() {
		super();
	}
	
	private SpaceVO(SpaceVO.Builder builder) {
		spaceId = builder.spaceId;
		memberId = builder.memberId;
		empId = builder.empId;
		spaceAddress = builder.spaceAddress;
		spaceType = builder.spaceType;
		spaceEqument = builder.spaceEqument;
		spaceContain = builder.spaceContain;
		spaceRule = builder.spaceRule;
		spaceRefund = builder.spaceRefund;
		spaceStatus = builder.spaceStatus;
		spaceSignupDate = builder.spaceSignupDate;
		spaceOnsaleDate = builder.spaceOnsaleDate;
		spaceOffsaleDate = builder.spaceOffsaleDate;
		spaceStatusEmp = builder.spaceStatusEmp;
		spaceStatusComm = builder.spaceStatusComm;
	}
	
	public String getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
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
	public String getSpaceAddress() {
		return spaceAddress;
	}
	public void setSpaceAddress(String spaceAddress) {
		this.spaceAddress = spaceAddress;
	}
	public String getSpaceName() {
		return spaceName;
	}
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	public String getSpaceType() {
		return spaceType;
	}
	public void setSpaceType(String spaceType) {
		this.spaceType = spaceType;
	}
	public String getSpaceEqument() {
		return spaceEqument;
	}
	public void setSpaceEqument(String spaceEqument) {
		this.spaceEqument = spaceEqument;
	}
	public String getSpaceContain() {
		return spaceContain;
	}
	public void setSpaceContain(String spaceContain) {
		this.spaceContain = spaceContain;
	}
	public String getSpaceRule() {
		return spaceRule;
	}
	public void setSpaceRule(String spaceRule) {
		this.spaceRule = spaceRule;
	}
	public String getSpaceRefund() {
		return spaceRefund;
	}
	public void setSpaceRefund(String spaceRefund) {
		this.spaceRefund = spaceRefund;
	}
	public String getSpaceStatus() {
		return spaceStatus;
	}
	public void setSpaceStatus(String spaceStatus) {
		this.spaceStatus = spaceStatus;
	}

	public java.sql.Date getSpaceSignupDate() {
		return spaceSignupDate;
	}
	public void setSpaceSignupDate(java.sql.Date spaceSignupDate) {
		this.spaceSignupDate = spaceSignupDate;
	}
	public java.sql.Date getSpaceOnsaleDate() {
		return spaceOnsaleDate;
	}
	public void setSpaceOnsaleDate(java.sql.Date spaceOnsaleDate) {
		this.spaceOnsaleDate = spaceOnsaleDate;
	}
	public java.sql.Date getSpaceOffsaleDate() {
		return spaceOffsaleDate;
	}
	public void setSpaceOffsaleDate(java.sql.Date spaceOffsaleDate) {
		this.spaceOffsaleDate = spaceOffsaleDate;
	}
	
	public String getSpaceStatusEmp() {
		return spaceStatusEmp;
	}

	public void setSpaceStatusEmp(String spaceStatusEmp) {
		this.spaceStatusEmp = spaceStatusEmp;
	}

	public String getSpaceStatusComm() {
		return spaceStatusComm;
	}

	public void setSpaceStatusComm(String spaceStatusComm) {
		this.spaceStatusComm = spaceStatusComm;
	}

	@Override
	public String toString() {
		return "SpaceVO [spaceId=" + spaceId + ", memberId=" + memberId + ", empId=" + empId + ", spaceAddress="
				+ spaceAddress + ", spaceName=" + spaceName + ", spaceType=" + spaceType + ", spaceEqument="
				+ spaceEqument + ", spaceContain=" + spaceContain + ", spaceRule=" + spaceRule + ", spaceRefund="
				+ spaceRefund + ", spaceStatus=" + spaceStatus + ", spaceSignupDate=" + spaceSignupDate
				+ ", spaceOnsaleDate=" + spaceOnsaleDate + ", spaceOffsaleDate=" + spaceOffsaleDate
				+ ", spaceStatusEmp=" + spaceStatusEmp + ", spaceStatusComm=" + spaceStatusComm + "]";
	}

}
