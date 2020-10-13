package com.memberComment.model;

import java.util.List;

public class MemberCommentServiceB {
	private MemberCommentDAO_interface dao;

	public MemberCommentServiceB() {
		dao = new MemberCommentDAO();
	}

	public MemberCommentVO addMemberComment(MemberCommentVO memberCommentVO) {
		dao.insert(memberCommentVO);
		return memberCommentVO;
	}

	public MemberCommentVO updateMemberComment(MemberCommentVO memberCommentVO) {
		dao.update(memberCommentVO);
		return memberCommentVO;
	}

	public void deleteMemberComment(String memberCommentId) {
		dao.delete(memberCommentId);
	}

	public MemberCommentVO getOneMemberComment(String memberCommentId) {
		return dao.findByPrimaryKey(memberCommentId);
	}

	public List<MemberCommentVO> getAllMemberComment() {
		return dao.getAll();
	}

	public List<MemberCommentVO> getAllMemberCommentById(String memberCommentId) {
		return dao.getAllById(memberCommentId);
	}
}
