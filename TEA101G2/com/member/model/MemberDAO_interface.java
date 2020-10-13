package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public void delete(String memberId);
	public MemberVO findByPrimaryKey(String memberId);
	public MemberVO findByAccount(String Account);
	public List<MemberVO> getAll();
	public MemberVO LoginAuthenticate(String account, String password);
}
