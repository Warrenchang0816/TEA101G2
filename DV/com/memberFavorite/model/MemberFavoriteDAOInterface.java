package com.memberFavorite.model;

import java.util.List;

public interface MemberFavoriteDAOInterface {
	public void insert(MemberFavoriteVO memberFavoriteVO);
	public void delete(String memberFavoriteId);
	public void update(MemberFavoriteVO memberFavoriteVO);
	public MemberFavoriteVO selectOne(String memberFavoriteId);
	public List<MemberFavoriteVO> selectAll();

}
