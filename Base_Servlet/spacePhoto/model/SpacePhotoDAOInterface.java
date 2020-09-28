package com.spacePhoto.model;

import java.util.List;

public interface SpacePhotoDAOInterface {
	public void insert(SpacePhotoVO spacePhotoVO);
	public void delete(String spacePhotoId);
	public void update(SpacePhotoVO spacePhotoVO);
	public SpacePhotoVO selectOne(String spacePhotoId);
	public List<SpacePhotoVO> selectAll();

}
