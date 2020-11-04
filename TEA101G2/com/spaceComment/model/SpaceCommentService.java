package com.spaceComment.model;

import java.util.List;
import com.member.model.*;

public class SpaceCommentService {
	
	private SpaceCommentDAO_interface dao;
	private MemberDAO_interface memdao;
	
	public SpaceCommentService() {
		dao = new SpaceCommentDAO();
		memdao = new MemberDAO();
	}
	
	public SpaceCommentVO addSpaceComment(SpaceCommentVO spaceCommentVO) {
		dao.insert(spaceCommentVO);
		return spaceCommentVO;
	}
	
	public SpaceCommentVO updateSpaceComment(SpaceCommentVO spaceCommentVO) {
		dao.update(spaceCommentVO);
		return spaceCommentVO;
	}
	
	public void deleteSpaceComment(String spaceCommentId) {
		dao.delete(spaceCommentId);
	}
	
	public SpaceCommentVO selectOneSpaceComment(String spaceCommentId) {
		return dao.selectOne(spaceCommentId);
	}
	
	public List<SpaceCommentVO> getAll(){
		return dao.getAll();
	}
	
	public List<SpaceCommentVO> getAllCommBySpace(String spaceId){
		return dao.getAllCommBySpace(spaceId);
	}
	
	//根據MemberId顯示NickName
	public String showNickname(String memberId) {
		return memdao.showNickname(memberId);
	}
	
	public Double getSpaceRating(String spaceId) {
		return dao.getSpaceRating(spaceId);
	}
	
	public int getSpaceCommentCount(String spaceId) {
		return dao.getSpaceCommentCount(spaceId);
	}
}
