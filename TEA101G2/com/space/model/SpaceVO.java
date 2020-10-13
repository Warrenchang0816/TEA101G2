package com.space.model;

import java.sql.Date;

import com.emp.model.EmpVO;

public class SpaceVO {
	private String spaceId;
	private String memberId;
	private String empId;
	private String spaceAddress;
	private Double spaceLng;
	private Double spaceLat;
	private String spaceName;
	private String spaceText;
	private String spaceType;
	private String spaceEquipment;
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
		private Double spaceLng = 0.0;
		private Double spaceLat = 0.0;
		private String spaceName = "";
		private String spaceText = "";
		private String spaceType = "";
		private String spaceEquipment = "";
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
		
		public SpaceVO.Builder spaceLng(Double spaceLng) {
			this.spaceLng = spaceLng;
			return this;
		}
		
		public SpaceVO.Builder spaceLat(Double spaceLat) {
			this.spaceLat = spaceLat;
			return this;
		}
	
		public SpaceVO.Builder spaceName(String spaceName) {
			this.spaceName = spaceName;
			return this;
		}
		
		public SpaceVO.Builder spaceText(String spaceText) {
			this.spaceText = spaceText;
			return this;
		}
		
		public SpaceVO.Builder spaceType(String spaceType) {
			this.spaceType = spaceType;
			return this;
		}
		
		public SpaceVO.Builder spaceEquipment(String spaceEquipment) {
			this.spaceEquipment = spaceEquipment;
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
		spaceText = builder.spaceText;
		spaceLat = builder.spaceLat;
		spaceLng = builder.spaceLng;
		spaceType = builder.spaceType;
		spaceEquipment = builder.spaceEquipment;
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
	
	public Double getSpaceLng() {
		return spaceLng;
	}

	public void setSpaceLng(Double spaceLng) {
		this.spaceLng = spaceLng;
	}

	public Double getSpaceLat() {
		return spaceLat;
	}

	public void setSpaceLat(Double spaceLat) {
		this.spaceLat = spaceLat;
	}

	public String getSpaceName() {
		return spaceName;
	}
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	
	public String getSpaceText() {
		return spaceText;
	}

	public void setSpaceText(String spaceText) {
		this.spaceText = spaceText;
	}

	public String getSpaceType() {
		return spaceType;
	}
	public void setSpaceType(String spaceType) {
		this.spaceType = spaceType;
	}
	public String getSpaceEquipment() {
		return spaceEquipment;
	}
	public void setSpaceEquipment(String spaceEquipment) {
		this.spaceEquipment = spaceEquipment;
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
				+ spaceAddress + ", spaceLng=" + spaceLng + ", spaceLat=" + spaceLat + ", spaceName=" + spaceName
				+ ", spaceText=" + spaceText + ", spaceType=" + spaceType + ", spaceEquipment=" + spaceEquipment
				+ ", spaceContain=" + spaceContain + ", spaceRule=" + spaceRule + ", spaceRefund=" + spaceRefund
				+ ", spaceStatus=" + spaceStatus + ", spaceSignupDate=" + spaceSignupDate + ", spaceOnsaleDate="
				+ spaceOnsaleDate + ", spaceOffsaleDate=" + spaceOffsaleDate + ", spaceStatusEmp=" + spaceStatusEmp
				+ ", spaceStatusComm=" + spaceStatusComm + "]";
	}

}
