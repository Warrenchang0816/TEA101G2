package com.memberComm.model;

import java.util.List;

public class TestJDBC {
	public static void main(String[] args) {
	
		MemberCommDAOInterface dao = new MemberCommDAO();
		
		//All
		List<MemberCommVO> list = dao.selectAll();
		for(MemberCommVO memberCommVO : list) {
			System.out.println(memberCommVO.getComm());
		}		
		
		//One
//		MemberCommVO one = dao.selectOne("20");
//		System.out.println(one.getComm());
		
		//insert
//		MemberCommVO add = new MemberCommVO();
//		add.setMemberAId("10");
//		add.setMemberBId("20");
//		add.setComm("機掰機掰機掰機掰機掰");
//		add.setCommLevel(2);
//		add.setCommDate(java.sql.Date.valueOf("2020-09-01"));
//		dao.insert(add);
//		System.out.println("FUCK");
		
		//delete
//		dao.delete("10");
//		System.out.println("FUCK");
		
		//update
//		MemberCommVO update = new MemberCommVO();
//		update.setMemberCommlId("20");
//		update.setMemberAId("20");
//		update.setMemberBId("20");
//		update.setComm("機歪機歪機歪機歪機歪機歪");
//		update.setCommLevel(2);
//		update.setCommDate(java.sql.Date.valueOf("2020-09-11"));
//		dao.update(update);
//		System.out.println("FUCK");
//		
	}

}
