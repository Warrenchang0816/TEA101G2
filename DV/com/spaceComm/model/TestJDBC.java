package com.spaceComm.model;

import java.util.List;

public class TestJDBC {
	public static void main(String[] args) {
	
		SpaceCommDAOInterface dao = new SpaceCommDAO();
		List<SpaceCommVO> list = dao.selectAll();
		
//		for(SpaceCommVO spaceCommVO : list) {
//			System.out.println(spaceCommVO.getSpaceId());
//		}		
		
		//selectone
//		SpaceCommVO one = dao.selectOne("SCOMMENT00001");
//		System.out.println(one.getSpaceId());
		
		//add
//		SpaceCommVO add = new SpaceCommVO();
//		add.setSpaceId("SPACE00003");
//		add.setMemberId("MEM00001");
//		add.setComm("YOYOOYOYOYO");
//		add.setCommLevel(3);
//		add.setCommDate(java.sql.Date.valueOf("2020-09-01"));
//		dao.insert(add);
//		System.out.println("FUCK");
		
		//selectall
//		SpaceCommVO selectOne = new SpaceCommVO();
//		selectOne = dao.selectOne("20");
//		System.out.println(selectOne.getMemberId());
//		System.out.println(selectOne.getCommLevel());
		
		//delete
//		dao.delete("50");
		
		//update
		SpaceCommVO update = new SpaceCommVO();
		update.setSpaceCommId("SCOMMENT00005");
		update.setSpaceId("SPACE00003");
		update.setMemberId("MEM00002");
		update.setComm("YAYAYYAYA");
		update.setCommLevel(3);
		update.setCommDate(java.sql.Date.valueOf("2020-09-01"));
		dao.update(update);
		System.out.println("FUCK");
		
	}

}
