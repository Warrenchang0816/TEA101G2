package com.spacePhoto.model;

import java.util.List;



public class SpacePhotoService {
	
	private SpacePhotoDAOInterface dao;
	
	public SpacePhotoService() {
		dao = new SpacePhotoDAO();
	}
	
	
	public SpacePhotoVO addSpacePhoto(SpacePhotoVO spacePhotoVO) {
		dao.insert(spacePhotoVO);
		return spacePhotoVO;
	}
	
	
	public SpacePhotoVO updateSpacePhoto(SpacePhotoVO spacePhotoVO) {		
		dao.update(spacePhotoVO);
		return spacePhotoVO;
	}
	
	public void deleteSpacePhoto(String spacePhotoId) {
		dao.delete(spacePhotoId);
	}
	
	
	public SpacePhotoVO selectOneSpacePhoto(String spacePhotoId) {
		return dao.selectOne(spacePhotoId);
	}
	
	public List<SpacePhotoVO> selectAllSpacePhoto() {
		return dao.selectAll();
	}


}
