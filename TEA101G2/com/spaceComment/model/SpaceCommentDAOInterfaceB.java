package com.spaceComment.model;

import java.util.List;


public interface SpaceCommentDAOInterfaceB {
	public void insert(SpaceCommentVO spaceCommVO);
	public void delete(String spaceCommId);
	public void update(SpaceCommentVO spaceCommVO);
	public SpaceCommentVO selectOne(String spaceCommId);
	public List<SpaceCommentVO> selectAll();

}
