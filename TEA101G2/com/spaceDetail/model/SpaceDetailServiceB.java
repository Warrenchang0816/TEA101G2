package com.spaceDetail.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.space.model.SpaceVO;

public class SpaceDetailServiceB {
	
	private SpaceDetailDAOInterfaceB dao;
	
	public SpaceDetailServiceB() {
		dao = new SpaceDetailDAOB();
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
	
//	public List<SpaceDetailVO> selectAllSpaceDetailBySpace(String spaceId) {
//		return dao.selectAllBySpace(spaceId);
//	}
	
	public List<SpaceDetailVO> selectAllSpaceDetailBySpace(String spaceId) {
		List<SpaceDetailVO> all = dao.selectAll();
		List<SpaceDetailVO> allBySpace = new ArrayList<>();
		
		allBySpace = all.stream()
				.filter(sd -> spaceId.equals(sd.getSpaceId()))
//				.filter(sd -> sd.getSpaceId().equals(spaceId))
				.collect(Collectors.toList());
		
		return allBySpace;
	}

}
