package com.space.model;

import java.util.List;

public interface SpaceDAO_interface {
		public void insert(SpaceVO spaceVO);
		public void delete(String spaceId);
		public void update(SpaceVO spaceVO);
		public SpaceVO selectOne(String spaceId);
		public List<SpaceVO> getAll();
		public List<SpaceVO> searchSpace(String subQuery, String spaceAddress, String spaceType, String spaceContain);
		public void updateSpaceStatus(SpaceVO spaceVO);
		public List<SpaceVO> selectAllByMemId(String memberId);
}
