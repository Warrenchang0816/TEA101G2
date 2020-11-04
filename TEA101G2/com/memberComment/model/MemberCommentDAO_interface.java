package com.memberComment.model;

import java.util.List;

public interface MemberCommentDAO_interface {
	public void insert(MemberCommentVO memberCommentVO);
	public void update(MemberCommentVO memberCommentVO);
	public void delete(String memberCommentId);
	public MemberCommentVO findByPrimaryKey(String memberCommentId);
	public List<MemberCommentVO> getAll();
	public List<MemberCommentVO> getAllById(String memberCommentId);
	public Double getMemberRating(String memberId);
}
