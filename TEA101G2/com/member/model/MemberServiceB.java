package com.member.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberServiceB {
	private MemberDAOInterfaceB daoB;
	
	public MemberServiceB() {
		daoB = new MemberDAOB();
	}
	
	
	public MemberVO addMember(MemberVO memberVO) {
		daoB.insert(memberVO);
		return memberVO;
	}
	
	
	public MemberVO updateMember(MemberVO memberVO) {		
		daoB.update(memberVO);
		return memberVO;
	}
	
	public void deleteMember(String MemberId) {
		daoB.delete(MemberId);
	}
	
	
	public MemberVO selectOneMember(String MemberId) {
		return daoB.selectOne(MemberId);
	}
	
	public List<MemberVO> selectAllMember() {
		return daoB.selectAll();
	}
	
	public List<MemberVO> selectAllMemberByStatus(String memberStatus) {
		List<MemberVO> all = daoB.selectAll();
		List<MemberVO> allByStatus = new ArrayList<MemberVO>();
		
		allByStatus = all.stream()
				.filter(m -> memberStatus.equals(m.getMemberStatus()))
				.collect(Collectors.toList());
		
		return allByStatus;
	}

}
