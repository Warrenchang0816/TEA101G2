package com.space.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpaceServiceB {
	
	private SpaceDAOInterfaceB dao;
	
	public SpaceServiceB(){
		dao = new SpaceDAOB();
	}
	
	public SpaceVO addSpace(SpaceVO spaceVO) {
		dao.insert(spaceVO);
		return spaceVO;
	}
	
	
	public SpaceVO updateSapce(SpaceVO spaceVO) {
		dao.update(spaceVO);
		return spaceVO;
	}
	
	public void deleteSpace(String spaceId) {
		dao.delete(spaceId);
	}
	
	
	public SpaceVO selectOneSpace(String spaceId) {
		return dao.selectOne(spaceId);
	}
	
	public List<SpaceVO> selectAllSpace() {
		return dao.selectAll();
	}
	
//	public List<SpaceVO> selectAllSpaceByMember(String memberId) {
//		return dao.selectAllByMember(memberId);
//	}
	
	public List<SpaceVO> selectAllSpaceByMember(String memberId) {
		List<SpaceVO> all = dao.selectAll();
		List<SpaceVO> allByMember = new ArrayList<SpaceVO>();
		
		allByMember = all.stream()
				.filter(s -> memberId.equals(s.getMemberId()))
				.collect(Collectors.toList());
		
		return allByMember;
	}
	
	public List<SpaceVO> selectAllSpaceByStatus(String spaceStatus) {
		List<SpaceVO> all = dao.selectAll();
		List<SpaceVO> allByStatus = new ArrayList<SpaceVO>();
		
		allByStatus = all.stream()
				.filter(s -> spaceStatus.equals(s.getSpaceStatus()))
				.collect(Collectors.toList());
		
		return allByStatus;
	}
	
	public List<SpaceVO> selectAllNewSpace(String spaceStatus) {
		List<SpaceVO> all = dao.selectAll();
		List<SpaceVO> allNew = new ArrayList<SpaceVO>();
		
		allNew = all.stream()
				.filter(s -> spaceStatus.equals(s.getSpaceStatus()))
				.filter(s -> (s.getSpaceStatusEmp() == null))
				.collect(Collectors.toList());
		
		return allNew;
	}
	

}
