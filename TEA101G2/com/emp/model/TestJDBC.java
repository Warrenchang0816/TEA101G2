package com.emp.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.Part;

public class TestJDBC {
	public static void main(String[] args) {
	
		EmpDAOInterface dao = new EmpDAO();
		
//		ByteArrayOutputStream baos = null;
//		try {
////			File file = new File("C:\\Users\\CJ01007\\Desktop\\TEA101G2\\BlobTest1.jpg");
//			File file = new File("C:\\Users\\CJ01007\\Desktop\\TEA101G2\\BlobTest2.jpg");
//			FileInputStream fis = new FileInputStream(file);
//			baos = new ByteArrayOutputStream();
//			byte[] buffer = new byte[8192];
//			int i;
//			while ((i = fis.read(buffer)) != -1) {
//				baos.write(buffer, 0, i);
//			}
//			baos.close();
//			fis.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		//All
		List<EmpVO> list = dao.selectAll();
		for(EmpVO empVO : list) {
			System.out.println(empVO.getEmpId());
			System.out.println(empVO.getEmpName());
			System.out.println(empVO.getEmpPhoto());
		}		
		System.out.println("FUCK");
		
		//One
//		EmpVO one = dao.selectOne("20");
//		System.out.println(one.getEmpId());
//		System.out.println(one.getEmpName());
//		System.out.println(one.getEmpPhoto());
//		System.out.println(one.getEmpPhotoBase64());
//		System.out.println("FUCK");
		
		//login
//		EmpService es = new EmpService();
//		if(es.isEmpLogin("rutruw217d", "eleif74w8")) {
//			System.out.println("FUCKLogin");
//		}else {
//			System.out.println("NOTLogin");
//		}
			
		//login
//		EmpService es = new EmpService();
//		EmpVO emp = es.selectAllEmpByAccount("rutruw217d");
//		System.out.println(emp.getEmpPassword());
		
		//empAccount
//		EmpService es = new EmpService();
//		if(es.isEmpAccountLogin("123213")) {
//			System.out.println("FUCKLogin");
//		}else {
//			System.out.println("NOTLogin");
//		}
		
		//insert
//		EmpVO add = new EmpVO();
//		add.setEmpAccount("EMP_ACCOUNT");
//		add.setEmpPassword("EMP_PASSWORD");
//		add.setEmpName("EMP_NAME");
//		add.setEmpNickname("EMP_NICKNAME");
//		add.setEmpEmail("EMP_EMAIL");
//		add.setEmpPhoto(baos.toByteArray());
//		add.setEmpPhone("EMP_PHONE");
//		add.setEmpAdress("EMP_ADRESS");
//		add.setEmpBirth(java.sql.Date.valueOf("2020-09-01"));
//		add.setEmpSex("M");
//		add.setEmpCountry("EMP_COUNTRY");
//		add.setEmpHireDate(java.sql.Date.valueOf("2020-09-01"));
//		add.setEmpJob("JOB");
//		add.setEmpAuth(2);
//		add.setEmpStatus("Y");
//		dao.insert(add);
//		System.out.println("FUCK");
		
		//delete
//		dao.delete("10");
//		System.out.println("FUCK");
		
		//update
//		EmpVO update = new EmpVO();
//		update.setEmpId("20");
//		update.setEmpAccount("EMP_ACCOUNT");
//		update.setEmpPassword("EMP_PASSWORD");
//		update.setEmpName("EMP_NAME");
//		update.setEmpNickname("EMP_NICKNAME");
//		update.setEmpEmail("EMP_EMAIL");
//		update.setEmpPhoto(baos.toByteArray());
//		update.setEmpPhone("EMP_PHONE");
//		update.setEmpAddress("EMP_ADRESS");
//		update.setEmpBirth(java.sql.Date.valueOf("2020-09-01"));
//		update.setEmpSex("M");
//		update.setEmpCountry("EMP_COUNTRY");
//		update.setEmpHireDate(java.sql.Date.valueOf("2020-09-01"));
//		update.setEmpJob("JOB");
//		update.setEmpAuth(2);
//		update.setEmpStatus("Y");
//		dao.update(update);
//		System.out.println("FUCK");
		
//		TestJDBC test = new TestJDBC();
//		String allname = "A;B;C;D;";
//		String allname2 = "A";
		
//		if(allname.contentEquals("A")) {
//			System.out.println("FUCK");
//		}else {
//			System.out.println("SH\nIT");
//		}
//		if(allname.contains("A;")) {
//			System.out.println("FUCK");
//		}else {
//			System.out.println("SHIT");
//		}
//		
//		if(allname.contains(";")) {
//			String[] nameArray = allname.split(";");
//			for(String namename: nameArray) {
//				System.out.println(namename);
//			}
//		}else {
//			System.out.println(allname2);
//		}
		
//		System.out.println(allname.indexOf(";", 2));
	}
	
	

}
