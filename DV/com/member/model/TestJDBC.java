package com.member.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TestJDBC {
	public static void main(String[] args) {
	
		MemberDAOInterface dao = new MemberDAO();
		
		ByteArrayOutputStream baos = null;
		try {
			File file = new File("C:\\Users\\CJ01007\\Desktop\\TEA101G2\\BlobTest1.jpg");
//			File file = new File("C:\\Users\\CJ01007\\Desktop\\TEA101G2\\BlobTest2.jpg");
			FileInputStream fis = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int i;
			while ((i = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, i);
			}
			baos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//All
//		List<MemberVO> list = dao.selectAll();
//		for(MemberVO memberVO : list) {
//			System.out.println(memberVO.getMemberId());
//			System.out.println(memberVO.getMemberName());
//			System.out.println(memberVO.getMemberPhoto());
//		}		
//		System.out.println("FUCK");
		
		//One
//		MemberVO one = dao.selectOne("MEM00006");
//		System.out.println(one.getMemberId());
//		System.out.println(one.getMemberName());
//		System.out.println(one.getMemberPhoto());
//		System.out.println("FUCK");
		
		//insert
//		MemberVO add = new MemberVO();
//		add.setMemberAccount("MEMBER_ACCOUNT");
//		add.setMemberPassword("MEMBER_PASSWORD");
//		add.setMemberName("MEMBER_NAME");
//		add.setMemberNickname("MEMBER_NICKNAME");
//		add.setMemberEmail("MEMBER_EMAIL");
//		add.setMemberPhoto(baos.toByteArray());
//		add.setMemberPhone("MEMBER_PHONE");
//		add.setMemberAddress("MEMBER_ADRESS");
//		add.setMemberBirth(java.sql.Date.valueOf("2020-09-01"));
//		add.setMemberSex("M");
//		add.setMemberCountry("MEMBER_COUNTRY");
//		add.setMemberSignupDate(java.sql.Date.valueOf("2020-09-01"));
//		add.setMemberAuth(5);
//		add.setMemberStatus("Y");
//		dao.insert(add);
//		System.out.println("FUCK");
		
		//delete
//		dao.delete("10");
//		System.out.println("FUCK");
		
		//update
//		MemberVO update = new MemberVO();
//		update.setMemberId("MEM00006");
//		update.setMemberAccount("MEMBER_ACCOUNT");
//		update.setMemberPassword("MEMBER_PASSWORD");
//		update.setMemberName("MEMBER_NAME");
//		update.setMemberNickname("MEMBER_NICKNAME");
//		update.setMemberEmail("MEMBER_EMAIL");
//		update.setMemberPhoto(baos.toByteArray());
//		update.setMemberPhone("MEMBER_PHONE");
//		update.setMemberAddress("MEMBER_ADDRESS");
//		update.setMemberBirth(java.sql.Date.valueOf("2020-09-01"));
//		update.setMemberSex("M");
//		update.setMemberCountry("MEMBER_COUNTRY");
//		update.setMemberSignupDate(java.sql.Date.valueOf("2020-09-01"));
//		update.setMemberAuth(5);
//		update.setMemberStatus("Y");
//		dao.update(update);
//		System.out.println("FUCK");
		
	}
	

}
