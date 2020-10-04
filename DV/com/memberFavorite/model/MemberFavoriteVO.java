package com.memberFavorite.model;

import com.emp.model.EmpVO;

public class MemberFavoriteVO {
	private String memberFavoriteId;
	private String memberId;
	private String spaceId;
	
	public static class Builder {

		private String memberFavoriteId = "";
		private String memberId = "";
		private String spaceId = "";
	
		public MemberFavoriteVO.Builder memberFavoriteId(String memberFavoriteId) {
			this.memberFavoriteId = memberFavoriteId;
			return this;
		}
	
		public MemberFavoriteVO.Builder memberId(String memberId) {
			this.memberId = memberId;
			return this;
		}
	
		public MemberFavoriteVO.Builder spaceId(String spaceId) {
			this.spaceId = spaceId;
			return this;
		}
	
	}

	public MemberFavoriteVO() {
		super();
	}
	
	private MemberFavoriteVO(MemberFavoriteVO.Builder builder) {
		memberFavoriteId = builder.memberFavoriteId;
		memberId = builder.memberId;
		spaceId = builder.spaceId;
	}
	
	public String getMemberFavoriteId() {
		return memberFavoriteId;
	}
	public void setMemberFavoriteId(String memberFavoriteId) {
		this.memberFavoriteId = memberFavoriteId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	
	@Override
	public String toString() {
		return "MemberFavoriteVO [memberFavoriteId=" + memberFavoriteId + ", memberId=" + memberId + ", spaceId="
				+ spaceId + "]";
	}

	
}
