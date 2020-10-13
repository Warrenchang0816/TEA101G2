package com.spaceDetail.model;

import java.util.List;

public interface SpaceDetailDAOInterfaceB {
	public void insert(SpaceDetailVO spaceDetailVO);

	public void delete(String spaceDetailId);

	public void update(SpaceDetailVO spaceDetailVO);

	public SpaceDetailVO selectOne(String spaceDetailId);

	public List<SpaceDetailVO> selectAll();
//	public List<SpaceDetailVO> selectAllBySpace(String spaceId);

}
