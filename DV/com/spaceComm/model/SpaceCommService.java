package com.spaceComm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<SpaceCommVO> selectAllSpaceCommById(String spaceCommId) {
		List<SpaceCommVO> all = dao.selectAll();
		List<SpaceCommVO> allById = new ArrayList<SpaceCommVO>();
		
		allById = all.stream()
				.filter(sc -> spaceCommId.equals(sc.getSpaceId()))
				.collect(Collectors.toList());
		
		return allById;
	}
	
	public List<SpaceCommVO> selectAllSpaceCommBySpace(String spaceId) {
		List<SpaceCommVO> all = dao.selectAll();
		List<SpaceCommVO> allBySpace = new ArrayList<SpaceCommVO>();
		
		allBySpace = all.stream()
				.filter(sc -> spaceId.equals(sc.getSpaceId()))
				.collect(Collectors.toList());
		
		return allBySpace;
	}

}
