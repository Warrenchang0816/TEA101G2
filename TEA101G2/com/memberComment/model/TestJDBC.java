package com.memberComment.model;

import java.util.List;

public class TestJDBC {
	public static void main(String[] args) {
	
		MemberCommentDAO_interface dao = new MemberCommentDAO();
		
		//All
//		List<MemberCommVO> list = dao.selectAll();
//		for(MemberCommVO memberCommVO : list) {
//			System.out.println(memberCommVO.getComm());
//		}		
		
		//All
//		MemberCommentService mcs = new MemberCommentService();
//		List<MemberCommentVO> list = mcs.getAllMemberCommentById("MEM00001");
//		for(MemberCommentVO memberCommVO : list) {
//			System.out.println(memberCommVO.getMemberCommentContent());
//			System.out.println(memberCommVO.getMemberCommStatusEmp());
//			System.out.println("----------------------");
//		}		
		
		//One
//		MemberCommentVO one = dao.findByPrimaryKey("MCOMMENT00001");
//		System.out.println(one.getMemberAId());
//		System.out.println(one.getMemberCommStatusEmp());
		
		//insert
//		MemberCommentVO add = new MemberCommentVO();
//		add.setMemberAId("MEM00001");
//		add.setMemberBId("MEM00002");
//		add.setMemberCommentContent("QQQQQQQQQQQQQ");
//		add.setMemberCommentLevel(4.2);
//		add.setMemberCommentDate(java.sql.Date.valueOf("2020-09-01"));
//		add.setMemberCommStatus("T");
//		add.setMemberCommStatusEmp("T");
//		add.setMemberCommStatusComm("T");
//		dao.insert(add);
//		System.out.println("FUCK");
		
		//delete
//		dao.delete("MCOMMENT00006");
//		System.out.println("FUCK");
		
		//update
//		MemberCommentVO update = new MemberCommentVO();
//		update.setMemberCommentId("MCOMMENT00005");
//		update.setMemberAId("MEM00002");
//		update.setMemberBId("MEM00001");
//		update.setMemberCommentContent("ccccccccccccccccccccccc");
//		update.setMemberCommentLevel(4.5);
//		update.setMemberCommentDate(java.sql.Date.valueOf("2020-09-15"));
//		update.setMemberCommStatus("A");
//		update.setMemberCommStatusEmp("C");
//		update.setMemberCommStatusComm("C");
//		dao.update(update);
//		System.out.println("FUCK");
//		
	}

}
