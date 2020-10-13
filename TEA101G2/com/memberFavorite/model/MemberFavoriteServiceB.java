package com.memberFavorite.model;

import java.util.List;

public class MemberFavoriteServiceB {
	private MemberFavoriteDAOInterfaceB dao;
	
	public MemberFavoriteServiceB() {
		dao = new MemberFavoriteDAOB();
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
	
	
	public MemberFavoriteVO selectOneMemberFavorite(String memberFavoriteId) {
		return dao.selectOne(memberFavoriteId);
	}
	
	public List<MemberFavoriteVO> selectAllMemberFavorite() {
		return dao.selectAll();
	}

}
