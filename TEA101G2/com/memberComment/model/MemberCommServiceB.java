package com.memberComment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MemberCommServiceB {
	private MemberCommentDAOInterfaceB dao;
	public MemberCommServiceB() {
		dao = new MemberCommentDAOB();
	} 
	
	public MemberCommentVO addMemberComm(MemberCommentVO memberCommVO) {
		dao.insert(memberCommVO);
		return memberCommVO;
	}
	
	
	public MemberCommentVO updateMemberComm(MemberCommentVO memberCommVO) {		
		dao.update(memberCommVO);
		return memberCommVO;
	}
	
	public void deleteMemberComm(String memberCommId) {
		dao.delete(memberCommId);
	}
	
	
	public MemberCommentVO selectOneMemberComm(String memberCommId) {
		return dao.selectOne(memberCommId);
	}
	
	public List<MemberCommentVO> selectAllMemberComm() {
		return dao.selectAll();
	}
	
	public List<MemberCommentVO> selectAllMemberCommByMember(String memberId) {
		List<MemberCommentVO> all = dao.selectAll();
		List<MemberCommentVO> allById = new ArrayList<MemberCommentVO>();
		
		allById = all.stream()
				.filter(mc -> memberId.equals(mc.getMemberAId()))
				.collect(Collectors.toList());
		
		return allById;
	}

}
