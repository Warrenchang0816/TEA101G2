package com.spaceComment.model;

import java.util.List;

public class SpaceCommentService {
	
	private SpaceCommentDAO_interface dao;
	
	public SpaceCommentService() {
		dao = new SpaceCommentDAO();
	}
	
	public SpaceCommentVO addSpaceComment(SpaceCommentVO spaceCommentVO) {
		dao.insert(spaceCommentVO);
		return spaceCommentVO;
	}
	
	public SpaceCommentVO updateSpaceComment(SpaceCommentVO spaceCommentVO) {
		dao.update(spaceCommentVO);
		return spaceCommentVO;
	}
	
	public void deleteSpaceComment(String spaceCommentId) {
		dao.delete(spaceCommentId);
	}
	
	public SpaceCommentVO selectOneSpaceComment(String spaceCommentId) {
		return dao.selectOne(spaceCommentId);
	}
	
	public List<SpaceCommentVO> getAll(){
		return dao.getAll();
	}
}
