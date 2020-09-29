package com.spacePhoto.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TestJDBC {
	public static void main(String[] args) {
		
		SpacePhotoDAOInterface dao = new SpacePhotoDAO();
		
		ByteArrayOutputStream baos = null;
		try {
			File file = new File("C:\\Users\\user\\Desktop\\TEA101G2\\BlobTest1.jpg");
//			File file = new File("C:\\Users\\user\\Desktop\\TEA101G2\\BlobTest2.jpg");
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
//		List<SpacePhotoVO> list = dao.selectAll();
//		for(SpacePhotoVO spacePhotoVO : list) {
//			System.out.println(spacePhotoVO.getSpacePhoto());
//			System.out.println(spacePhotoVO.getSpacePhotoBase64());
//		}		
//		System.out.println("FUCK");
		
		//One
//		SpacePhotoVO one = dao.selectOne("20");
//		System.out.println(one.getSpacePhoto());
//		System.out.println(one.getSpacePhotoBase64());
		
		//insert
//		SpacePhotoVO add = new SpacePhotoVO();
//		add.setSpaceId("20");
//		add.setSpacePhoto(baos.toByteArray());
//		dao.insert(add);
//		System.out.println("FUCK");
		
		//delete
//		dao.delete("10");
//		System.out.println("FUCK");
		
		//update
		SpacePhotoVO update = new SpacePhotoVO();
		update.setSpacePhotoId("120");
		update.setSpaceId("20");
		update.setSpacePhoto(baos.toByteArray());
		dao.update(update);
		System.out.println("FUCK");
		
	}
	

}
