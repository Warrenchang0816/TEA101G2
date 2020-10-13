package com.spacePhoto.model;

import java.util.Arrays;

import com.emp.model.EmpVO;

public class SpacePhotoVO {
	
	private String spacePhotoId;
	private String spaceId;
	private byte[] spacePhoto;
	
	public static class Builder {

		private String spacePhotoId = "";
		private String spaceId = "";
		private byte[] spacePhoto = null;
	
		public SpacePhotoVO.Builder spacePhotoId(String spacePhotoId) {
			this.spacePhotoId = spacePhotoId;
			return this;
		}
	
		public SpacePhotoVO.Builder spaceId(String spaceId) {
			this.spaceId = spaceId;
			return this;
		}
	
		public SpacePhotoVO.Builder spacePhoto(byte[] spacePhoto) {
			this.spacePhoto = spacePhoto;
			return this;
		}
	
	}

	public SpacePhotoVO() {
		super();
	}
	
	private SpacePhotoVO(SpacePhotoVO.Builder builder) {
		spacePhotoId = builder.spacePhotoId;
		spaceId = builder.spaceId;
		spacePhoto = builder.spacePhoto;
	}
	
	public String getSpacePhotoId() {
		return spacePhotoId;
	}
	public void setSpacePhotoId(String spacePhotoId) {
		this.spacePhotoId = spacePhotoId;
	}
	public String getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	public byte[] getSpacePhoto() {
		return spacePhoto;
	}
	public void setSpacePhoto(byte[] spacePhoto) {
		this.spacePhoto = spacePhoto;
	}
	
	@Override
	public String toString() {
		return "SpacePhotoVO [spacePhotoId=" + spacePhotoId + ", spaceId=" + spaceId + ", spacePhoto="
				+ Arrays.toString(spacePhoto) + "]";
	}
	
	

}
