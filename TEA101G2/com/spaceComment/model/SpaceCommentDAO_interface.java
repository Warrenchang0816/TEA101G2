package com.spaceComment.model;

import java.util.List;

public interface SpaceCommentDAO_interface {
	public void insert(SpaceCommentVO spaceCommentVO);
	public void delete(String spaceCommentId);
	public void update(SpaceCommentVO spaceCommentVO);
	public SpaceCommentVO selectOne(String spaceCommentId);
	public List<SpaceCommentVO> getAll();
}
