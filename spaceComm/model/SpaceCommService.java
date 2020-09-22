package com.spaceComm.model;

import java.util.List;

import com.spaceDetail.model.SpaceDetailVO;

public class SpaceCommService {
	
	private SpaceCommDAOInterface dao;
	public SpaceCommService() {
		dao = new SpaceCommDAO();
	} 
	
	public SpaceCommVO addSpaceComm(SpaceCommVO spaceCommVO) {
		dao.insert(spaceCommVO);
		return spaceCommVO;
	}
	
	
	public SpaceCommVO updateSpaceComm(SpaceCommVO spaceCommVO) {		
		dao.update(spaceCommVO);
		return spaceCommVO;
	}
	
	public void deleteSpaceComm(String spaceCommId) {
		dao.delete(spaceCommId);
	}
	
	
	public SpaceCommVO selectOneSpaceComm(String spaceCommId) {
		return dao.selectOne(spaceCommId);
	}
	
	public List<SpaceCommVO> selectAllSpaceComm() {
		return dao.selectAll();
	}

}
