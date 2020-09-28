package com.member.model;

import java.util.List;

public class MemberService {
	private MemberDAOInterface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	
	public MemberVO addMember(MemberVO memberVO) {
		dao.insert(memberVO);
		return memberVO;
	}
	
	
	public MemberVO updateMember(MemberVO memberVO) {		
		dao.update(memberVO);
		return memberVO;
	}
	
	public void deleteMember(String MemberId) {
		dao.delete(MemberId);
	}
	
	
	public MemberVO selectOneMember(String MemberId) {
		return dao.selectOne(MemberId);
	}
	
	public List<MemberVO> selectAllMember() {
		return dao.selectAll();
	}

}
