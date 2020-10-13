package com.space.model;

import java.util.List;

public class SpaceService {
	
	private SpaceDAO_interface dao;
	
	public SpaceService() {
		dao = new SpaceDAO();
	}
	
	public SpaceVO addSpace(SpaceVO spaceVO) {
		dao.insert(spaceVO);
		return spaceVO;
	}
	
	public SpaceVO updateSpace(SpaceVO spaceVO) {
		dao.update(spaceVO);
		return spaceVO;
	}
	
	public void deleteSpace(String spaceId) {
		dao.delete(spaceId);
	}
	
	public SpaceVO selectOneSpace(String spaceId) {
		return dao.selectOne(spaceId);
	}
	
	public List<SpaceVO> getAll(){
		return dao.getAll();
	}
	
	//TEST
	public List<SpaceVO> searchSpace(String spaceAddress, String spaceType, String spaceContain){
		return dao.searchSpace(spaceAddress, spaceType, spaceContain);
	}
}
