package com.spacePhoto.model;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class SpacePhotoService {
	
	private SpacePhotoDAO_interface dao;
	
	public SpacePhotoService() {
		dao = new SpacePhotoDAO();
	}
	
	public SpacePhotoVO addSpacePhoto(SpacePhotoVO spacePhotoVO) {
		dao.insert(spacePhotoVO);
		return spacePhotoVO;
	}
	
	public SpacePhotoVO updateSpacePhoto(SpacePhotoVO spacePhotoVO) {
		dao.update(spacePhotoVO);
		return spacePhotoVO;
	}
	
	public void deleteSpacePhoto(String spacePhotoId) {
		dao.delete(spacePhotoId);
	}
	
	public SpacePhotoVO selectOneSpacePhoto(String spacePhotoId) {
		return dao.selectOne(spacePhotoId);
	}
	
	public List<SpacePhotoVO> getAll(){
		return dao.getAll();
	}
	//取相同spaceId的SpacePhoto
	public List<SpacePhotoVO> getAllPhoto(String spaceId){
		return dao.getAllPhoto(spaceId);
	}
	
	//取SpaceId預設圖
	public List<SpacePhotoVO> getDefaultPhoto(String spaceId){
		return dao.getDefaultPhoto(spaceId);
	}
	
	public String getDefaultFirstPhoto(String spaceId){
		List<SpacePhotoVO> all = dao.getAll();
		List<SpacePhotoVO> allBySpace = new ArrayList<SpacePhotoVO>();
		SpacePhotoVO first = new SpacePhotoVO(); 
		
		allBySpace = all.stream()
			.filter(sp -> sp.getSpaceId().equals(spaceId))
			.collect(Collectors.toList());
		
		Optional<SpacePhotoVO> sp = allBySpace.stream().findFirst();
		first = sp.get();
		
		Base64.Encoder encode = Base64.getEncoder();
		String spacePhoto = encode.encodeToString(first.getSpacePhoto());
		
		return spacePhoto;
	}
}
