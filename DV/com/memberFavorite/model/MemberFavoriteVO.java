package com.memberFavorite.model;

public class MemberFavoriteVO {
	private String memberFavoriteId;
	private String memberId;
	private String spaceId;
	
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
