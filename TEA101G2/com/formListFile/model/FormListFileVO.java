package com.formListFile.model;

import java.util.Arrays;

import com.emp.model.EmpVO;

public class FormListFileVO {
	
	private String formListFileId;
	private String formListId;
	private byte[] formListFile;
	
	public static class Builder {

		private String formListFileId = "";
		private String formListId = "";
		private byte[] formListFile = null;
	
		public FormListFileVO.Builder formListFileId(String formListFileId) {
			this.formListFileId = formListFileId;
			return this;
		}
	
		public FormListFileVO.Builder formListId(String formListId) {
			this.formListId = formListId;
			return this;
		}
	
		public FormListFileVO.Builder formListFile(byte[] formListFile) {
			this.formListFile = formListFile;
			return this;
		}
	
	}

	public FormListFileVO() {
		super();
	}
	
	private FormListFileVO(FormListFileVO.Builder builder) {
		formListFileId = builder.formListFileId;
		formListId = builder.formListId;
		formListFile = builder.formListFile;
	}
	
	public String getFormListFileId() {
		return formListFileId;
	}

	public void setFormListFileId(String formListFileId) {
		this.formListFileId = formListFileId;
	}

	public String getFormListId() {
		return formListId;
	}

	public void setFormListId(String formListId) {
		this.formListId = formListId;
	}

	public byte[] getFormListFile() {
		return formListFile;
	}

	public void setFormListFile(byte[] formListFile) {
		this.formListFile = formListFile;
	}

	@Override
	public String toString() {
		return "FormListFileVO [formListFileId=" + formListFileId + ", formListId=" + formListId + ", formListFile="
				+ Arrays.toString(formListFile) + "]";
	}
	
}
