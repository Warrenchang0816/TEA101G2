package com.member.model;

import java.util.List;


public interface MemberDAOInterfaceB {
	public void insert(MemberVO memberVO);
	public void delete(String memberId);
	public void update(MemberVO memberVO);
	public MemberVO selectOne(String memberId);
	public List<MemberVO> selectAll();

}
