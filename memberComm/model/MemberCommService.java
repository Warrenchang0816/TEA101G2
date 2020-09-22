package com.memberComm.model;

import java.util.List;

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

}
