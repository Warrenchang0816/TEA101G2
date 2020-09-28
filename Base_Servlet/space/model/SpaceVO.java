package com.space.model;

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
	
	@Override
	public String toString() {
		return "SpaceVO [spaceId=" + spaceId + ", memberId=" + memberId + ", empId=" + empId + ", spaceAddress="
				+ spaceAddress + ", spaceName=" + spaceName + ", spaceType=" + spaceType + ", spaceEqument="
				+ spaceEqument + ", spaceContain=" + spaceContain + ", spaceRule=" + spaceRule + ", spaceRefund="
				+ spaceRefund + ", spaceStatus=" + spaceStatus + ", spaceSignupDate=" + spaceSignupDate
				+ ", spaceOnsaleDate=" + spaceOnsaleDate + ", spaceOffsaleDate=" + spaceOffsaleDate + "]";
	}
	
	
	
	
}
