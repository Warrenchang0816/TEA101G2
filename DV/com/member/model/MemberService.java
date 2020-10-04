package com.member.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<MemberVO> selectAllMemberByStatus(String memberStatus) {
		List<MemberVO> all = dao.selectAll();
		List<MemberVO> allByStatus = new ArrayList<MemberVO>();
		
		allByStatus = all.stream()
				.filter(m -> memberStatus.equals(m.getMemberStatus()))
				.collect(Collectors.toList());
		
		return allByStatus;
	}

}
