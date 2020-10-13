package com.memberComment.model;

import java.util.List;

public interface MemberCommentDAOInterfaceB {
	public void insert(MemberCommentVO memberCommVO);
	public void delete(String memberCommId);
	public void update(MemberCommentVO memberCommVO);
	public MemberCommentVO selectOne(String memberCommId);
	public List<MemberCommentVO> selectAll();

}
