package com.spaceComment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpaceCommServiceB {
	
	private SpaceCommentDAOInterfaceB dao;
	public SpaceCommServiceB() {
		dao = new SpaceCommentDAOB();
	} 
	
	public SpaceCommentVO addSpaceComm(SpaceCommentVO spaceCommVO) {
		dao.insert(spaceCommVO);
		return spaceCommVO;
	}
	
	
	public SpaceCommentVO updateSpaceComm(SpaceCommentVO spaceCommVO) {		
		dao.update(spaceCommVO);
		return spaceCommVO;
	}
	
	public void deleteSpaceComm(String spaceCommId) {
		dao.delete(spaceCommId);
	}
	
	
	public SpaceCommentVO selectOneSpaceComm(String spaceCommId) {
		return dao.selectOne(spaceCommId);
	}
	
	public List<SpaceCommentVO> selectAllSpaceComm() {
		return dao.selectAll();
	}
	
	public List<SpaceCommentVO> selectAllSpaceCommById(String spaceCommId) {
		List<SpaceCommentVO> all = dao.selectAll();
		List<SpaceCommentVO> allById = new ArrayList<SpaceCommentVO>();
		
		allById = all.stream()
				.filter(sc -> spaceCommId.equals(sc.getSpaceId()))
				.collect(Collectors.toList());
		
		return allById;
	}
	
	public List<SpaceCommentVO> selectAllSpaceCommBySpace(String spaceId) {
		List<SpaceCommentVO> all = dao.selectAll();
		List<SpaceCommentVO> allBySpace = new ArrayList<SpaceCommentVO>();
		
		allBySpace = all.stream()
				.filter(sc -> spaceId.equals(sc.getSpaceId()))
				.collect(Collectors.toList());
		
		return allBySpace;
	}

}
