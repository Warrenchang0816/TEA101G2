package com.spaceComm.model;

import java.util.List;


public interface SpaceCommDAOInterface {
	public void insert(SpaceCommVO spaceCommVO);
	public void delete(String spaceCommId);
	public void update(SpaceCommVO spaceCommVO);
	public SpaceCommVO selectOne(String spaceCommId);
	public List<SpaceCommVO> selectAll();

}
