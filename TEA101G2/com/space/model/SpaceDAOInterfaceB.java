package com.space.model;

import java.util.List;

public interface SpaceDAOInterfaceB {
	public void insert(SpaceVO spaceVO);
	public void delete(String spaceId);
	public void update(SpaceVO spaceVO);
	public SpaceVO selectOne(String spaceId);
	public List<SpaceVO> selectAll();
//	public List<SpaceVO> selectAllByMember(String memberId);

}
