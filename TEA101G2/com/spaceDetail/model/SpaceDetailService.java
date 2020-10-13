package com.spaceDetail.model;

import java.util.List;

public class SpaceDetailService {
	
	private SpaceDetailDAO_interface dao;
	
	public SpaceDetailService() {
		dao = new SpaceDetailDAO();
	}
	
	public SpaceDetailVO addSpaceDetail(SpaceDetailVO spaceDetailVO) {
		dao.insert(spaceDetailVO);
		return spaceDetailVO;
	}
	
	public SpaceDetailVO updateSpaceDetail(SpaceDetailVO spaceDetailVO) {
		dao.update(spaceDetailVO);
		return spaceDetailVO;
	}
	
	public void deleteSpaceDetail(String spaceDetailId) {
		dao.delete(spaceDetailId);
	}
	
	public SpaceDetailVO selectOneSpaceDetail(String spaceDetailId) {
		return dao.selectOne(spaceDetailId);
	}
	//誠實業者必備顯示最低價格
	public SpaceDetailVO selectOneLowest(String spaceId) {
		return dao.selectOneLowest(spaceId);
	}
	
	public List<SpaceDetailVO> getAll(){
		return dao.getAll();
	}
	//根據場地ID滾出list
	public List<SpaceDetailVO> getSpaceIdList(String spaceId){
		return dao.getSpaceIdList(spaceId);
	}
}
