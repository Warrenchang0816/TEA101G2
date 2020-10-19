package com.formList.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TestJDBC {
	public static void main(String[] args) {
		
		FormListDAOInterface dao = new FormListDAO();
		
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
//		List<FormListVO> list = dao.selectAll();
//		for(FormListVO formListVO : list) {
//			System.out.println(formListVO.getMembrId());
//			System.out.println(formListVO.getFormListTitle());
//			System.out.println(formListVO.getFormListContext());
//		}		
//		System.out.println("FUCK");
		
		//One
//		FormListVO one = dao.selectOne("5");
//		System.out.println(one.getMemberId());
//		System.out.println(one.getFormListTitle());
//		System.out.println(one.getFormListContext());
		
		//insert
//		FormListVO add = new FormListVO();
//		add.setMemberId("MEM00002");
//		add.setEmpId("EMP00002");
//		add.setFormListCreateDate(java.sql.Date.valueOf("2020-09-06"));
//		add.setFormListType("檢舉");
//		add.setFormListTitle("長相問題");
//		add.setFormListContext("太醜");
//		add.setFormListFile(baos.toByteArray());
//		dao.insert(add);
//		System.out.println("FUCK");
		
		//delete
//		dao.delete("10");
//		System.out.println("FUCK");
		
		//update
//		FormListVO update = new FormListVO();
//		update.setFormListId("10");
//		update.setMemberId("20");
//		update.setEmpId("10");
//		update.setFormListCreateDate(java.sql.Date.valueOf("2020-09-06"));
//		update.setFormListType("檢舉");
//		update.setFormListTitle("長相問題");
//		update.setFormListContext("太醜");
//		update.setFormListFile(baos.toByteArray());
//		dao.insert(add);
//		System.out.println("FUCK");
		
//		FormListService fls = new FormListService();
//		List<FormListVO> newMessage = fls.selectAllNewMessagesByGet("MEM00003");
//		for(FormListVO fl : newMessage) {
//			System.out.println(fl.getFormListTitle());
//		}
		
	}
	

}
