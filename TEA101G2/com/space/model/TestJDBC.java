package com.space.model;

import java.util.List;

public class TestJDBC {
	public static void main(String[] args) {
	
		SpaceDAOInterfaceB dao = new SpaceDAOB();
		
		
//		SpaceServiceB ss = new SpaceServiceB();
//		List<SpaceVO> list = ss.selectAllNewSpace("F");
//		for(SpaceVO spaceVO : list) {
//		System.out.println(spaceVO.getSpaceId());
//		}
		
		String s = "就是爽";
		String[] sSplit = s.split(":");
		for(String ss : sSplit) {
			System.out.println(ss);
		}
//		List<SpaceVO> list = dao.selectAll();
//		for(SpaceVO spaceVO : list) {
//		System.out.println(spaceVO.getSpaceId());
//		}
//		System.out.println("FUCK");
		
//		for(SpaceVO spaceVO : list) {
//			System.out.println(spaceVO.getEmpId());
//		}		
		
		//selectone
//		SpaceVO one = dao.selectOne("SPACE00001");
//		System.out.println(one.getSpaceId());
		
		//add
//		SpaceVO add = new SpaceVO();
//		add.setMemberId("20");
//		add.setEmpId("10");
//		add.setSpaceAdress("桃園市");
//		add.setSpaceName("場地四");
//		add.setSpaceType("聚會");
//		add.setSpaceEqument("桌子");
//		add.setSpaceContain("20");
//		add.setSpaceRule("HAPPY");
//		add.setSpaceRefund("NEVER");
//		add.setSpaceStatus("Y");
//		add.setSpaceSignupDate(java.sql.Date.valueOf("2020-09-01"));
//		add.setSpaceOnsaleDate(java.sql.Date.valueOf("2020-09-02"));
//		add.setSpaceOffsaleDate(java.sql.Date.valueOf("2020-09-03"));
//		dao.insert(add);
//		System.out.println("FUCK");
		
		//selectall
//		SpaceVO selectOne = new SpaceVO();
//		selectOne = dao.selectOne("20");
//		System.out.println(selectOne.getMemberId());
//		System.out.println(selectOne.getSpaceRule());
		
		//AllByMember
//		SpaceService ss = new SpaceService();
//		List<SpaceVO> all = ss.selectAllSpaceByMember("MEM00003");
//		for(SpaceVO spaceVO : all) {
//			System.out.println(spaceVO.getSpaceId());
////			System.out.println(spaceVO.getEmpId());
//		}
		
		//delete
//		dao.delete("50");
		
		//update
//		SpaceVO update = new SpaceVO();
//		update.setSpaceId("10");
//		update.setMemberId("20");
//		update.setEmpId("10");
//		update.setSpaceAdress("桃園市");
//		update.setSpaceName("場地四");
//		update.setSpaceType("聚會");
//		update.setSpaceEqument("桌子");
//		update.setSpaceContain("20");
//		update.setSpaceRule("HAPPY");
//		update.setSpaceRefund("NEVER");
//		update.setSpaceStatus("Y");
//		update.setSpaceSignupDate(java.sql.Date.valueOf("2020-09-01"));
//		update.setSpaceOnsaleDate(java.sql.Date.valueOf("2020-09-02"));
//		update.setSpaceOffsaleDate(java.sql.Date.valueOf("2020-09-03"));
//		dao.update(update);
//		System.out.println("FUCK");
		
	}

}
