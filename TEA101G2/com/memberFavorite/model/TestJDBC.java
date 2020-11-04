package com.memberFavorite.model;

import java.util.List;

public class TestJDBC {
	public static void main(String[] args) {
	
		MemberFavoriteDAO_interface dao = new MemberFavoriteDAO();
		
		//All
//		List<MemberFavoriteVO> list = dao.selectAll();
//		for(MemberFavoriteVO memberFavoriteVO : list) {
//			System.out.println(memberFavoriteVO.getMemberFavoriteId());
//		}		
		
		//One
//		MemberFavoriteVO one = dao.selectOne("10");
//		System.out.println(one.getMemberFavoriteId());
		
		//insert
//		MemberFavoriteVO add = new MemberFavoriteVO();
//		add.setSpaceId("10");
//		add.setMemberId("10");
//		dao.insert(add);
//		System.out.println("FUCK");
		
		//delete
//		dao.delete("10");
//		System.out.println("FUCK");
		
		//update
//		MemberFavoriteVO update = new MemberFavoriteVO();
//		update.setMemberFavoriteId("20");
//		update.setSpaceId("10");
//		update.setMemberId("20");
//		dao.update(update);
//		System.out.println("FUCK");
		
		MemberFavoriteVO memf = dao.getMemberFavoriteStatus("MEM00001", "SPACE00001");
		if(memf != null) {
			System.out.println(memf.getMemberFavoriteId());
		} else {
			System.out.println("fail");
		}
		
	}

}
