package com.memberComm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MemberCommService {
	private MemberCommDAOInterface dao;
	public MemberCommService() {
		dao = new MemberCommDAO();
	} 
	
	public MemberCommVO addMemberComm(MemberCommVO memberCommVO) {
		dao.insert(memberCommVO);
		return memberCommVO;
	}
	
	
	public MemberCommVO updateMemberComm(MemberCommVO memberCommVO) {		
		dao.update(memberCommVO);
		return memberCommVO;
	}
	
	public void deleteMemberComm(String memberCommId) {
		dao.delete(memberCommId);
	}
	
	
	public MemberCommVO selectOneMemberComm(String memberCommId) {
		return dao.selectOne(memberCommId);
	}
	
	public List<MemberCommVO> selectAllMemberComm() {
		return dao.selectAll();
	}
	
	public List<MemberCommVO> selectAllMemberCommByMember(String memberId) {
		List<MemberCommVO> all = dao.selectAll();
		List<MemberCommVO> allById = new ArrayList<MemberCommVO>();
		
		allById = all.stream()
				.filter(mc -> memberId.equals(mc.getMemberAId()))
				.collect(Collectors.toList());
		
		return allById;
	}

}
