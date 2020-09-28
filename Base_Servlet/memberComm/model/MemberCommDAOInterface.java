package com.memberComm.model;

import java.util.List;

public interface MemberCommDAOInterface {
	public void insert(MemberCommVO memberCommVO);
	public void delete(String memberCommId);
	public void update(MemberCommVO memberCommVO);
	public MemberCommVO selectOne(String memberCommId);
	public List<MemberCommVO> selectAll();

}
