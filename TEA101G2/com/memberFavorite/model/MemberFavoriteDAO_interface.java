package com.memberFavorite.model;

import java.util.List;

public interface MemberFavoriteDAO_interface {
	public void insert(MemberFavoriteVO memberFavoriteVO);
	public void update(MemberFavoriteVO memberFavoriteVO);
	public void delete(String memberFavoriteId);
	public MemberFavoriteVO findByPrimaryKey(String memberFavoriteId);
	public List<MemberFavoriteVO> getAll();
	public List<MemberFavoriteVO> getAllById(String memberFavoriteId);
	public MemberFavoriteVO getMemberFavoriteStatus(String memberId, String spaceId);
}
