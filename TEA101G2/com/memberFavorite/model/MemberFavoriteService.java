package com.memberFavorite.model;

import java.util.List;

public class MemberFavoriteService {
	private MemberFavoriteDAO_interface dao;

	public MemberFavoriteService() {
		dao = new MemberFavoriteDAO();
	}

	public MemberFavoriteVO addMemberFavorite(MemberFavoriteVO memberFavoriteVO) {
		dao.insert(memberFavoriteVO);
		return memberFavoriteVO;
	}

	public MemberFavoriteVO updateMemberFavorite(MemberFavoriteVO memberFavoriteVO) {
		dao.update(memberFavoriteVO);
		return memberFavoriteVO;
	}

	public void deleteMemberFavorite(String memberFavoriteId) {
		dao.delete(memberFavoriteId);
	}

	public MemberFavoriteVO getOneMemberFavorite(String memberFavoriteId) {
		return dao.findByPrimaryKey(memberFavoriteId);
	}

	public List<MemberFavoriteVO> getAllMemberFavorite() {
		return dao.getAll();
	}

	public List<MemberFavoriteVO> getAllMemberFavoriteById(String memberFavoriteId) {
		return dao.getAllById(memberFavoriteId);
	}
	
	public MemberFavoriteVO getMemberFavoriteStatus(String memberId, String spaceId) {
		return dao.getMemberFavoriteStatus(memberId, spaceId);
	}
}
