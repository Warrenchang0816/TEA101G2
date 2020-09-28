package com.memberFavorite.model;

import java.util.List;

public class MemberFavoriteService {
	private MemberFavoriteDAOInterface dao;
	
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
	
	
	public MemberFavoriteVO selectOneMemberFavorite(String memberFavoriteId) {
		return dao.selectOne(memberFavoriteId);
	}
	
	public List<MemberFavoriteVO> selectAllMemberFavorite() {
		return dao.selectAll();
	}

}
