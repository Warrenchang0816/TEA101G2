package com.space.model;

import java.util.List;

public interface SpaceDAOInterface {
	public void insert(SpaceVO spaceVO);
	public void delete(String spaceId);
	public void update(SpaceVO spaceVO);
	public SpaceVO selectOne(String spaceId);
//	public SpaceVO selectOneBySpaceId(String spaceId);
//	public SpaceVO selectOneBySpaceName(String spaceName);
	public List<SpaceVO> selectAll();

}
