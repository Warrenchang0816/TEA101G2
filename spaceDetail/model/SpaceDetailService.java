package com.spaceDetail.model;

import java.util.List;

import com.space.model.SpaceVO;

public class SpaceDetailService {
	
	private SpaceDetailDAOInterface dao;
	
	public SpaceDetailService() {
		dao = new SpaceDetailDAO();
	}
	
	
	public SpaceDetailVO addSpaceDetail(SpaceDetailVO spaceDetailVO) {
		dao.insert(spaceDetailVO);
		return spaceDetailVO;
	}
	
	
	public SpaceDetailVO updateSapceDetail(SpaceDetailVO spaceDetailVO) {		
		dao.update(spaceDetailVO);
		return spaceDetailVO;
	}
	
	public void deleteSpaceDetail(String spaceId) {
		dao.delete(spaceId);
	}
	
	
	public SpaceDetailVO selectOneSpaceDetail(String spaceDetailId) {
		return dao.selectOne(spaceDetailId);
	}
	
	public List<SpaceDetailVO> selectAllSpaceDetail() {
		return dao.selectAll();
	}

}
