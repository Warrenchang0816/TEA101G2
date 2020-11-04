package com.formListFile.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TestJDBC {
	public static void main(String[] args) {
		
		FormListFileDAOInterface dao = new FormListFileDAO();
		
		ByteArrayOutputStream baos = null;
		try {
//			File file = new File("C:\\Users\\CJ01007\\Desktop\\TEA101G2\\BlobTest1.jpg");
			File file = new File("C:\\Users\\CJ01007\\Desktop\\TEA101G2\\BlobTest2.jpg");
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
//		List<FormListFileVO> list = dao.selectAll();
//		for(FormListFileVO spacePhotoVO : list) {
//			System.out.println(spacePhotoVO.getFormListFile());
//			System.out.println(spacePhotoVO.getFormListId());
//		}		
//		System.out.println("FUCK");
		
		//One
//		FormListFileVO one = dao.selectOne("FLF0000002");
//		System.out.println(one.getFormListFile());
//		System.out.println(one.getFormListId());
//		System.out.println("FUCK");
		
		//insert
//		FormListFileVO add = new FormListFileVO();
//		add.setFormListId("FORMS0000079");
//		add.setFormListFile(baos.toByteArray());
//		dao.insert(add);
//		System.out.println("FUCK");
		
		//delete
//		dao.delete("10");
//		System.out.println("FUCK");
		
		//update
		FormListFileVO update = new FormListFileVO();
		update.setFormListFileId("FLF0000003");
		update.setFormListId("FORMS0000076");
		update.setFormListFile(baos.toByteArray());
		dao.update(update);
		System.out.println("FUCK");
		
	}
	

}
