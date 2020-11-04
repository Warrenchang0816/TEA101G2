package com.formListFile.model;

import java.util.List;
import java.util.stream.Collectors;



public class FormListFileService {
	
	private FormListFileDAOInterface dao;
	
	public FormListFileService() {
		dao = new FormListFileDAO();
	}
	
	
	public FormListFileVO addFormListFile(FormListFileVO formListFileVO) {
		dao.insert(formListFileVO);
		return formListFileVO;
	}
	
	
	public FormListFileVO updateFormListFile(FormListFileVO formListFileVO) {		
		dao.update(formListFileVO);
		return formListFileVO;
	}
	
	public void deleteFormListFile(String formListFileId) {
		dao.delete(formListFileId);
	}
	
	
	public FormListFileVO selectOneFormListFile(String formListFileId) {
		return dao.selectOne(formListFileId);
	}
	
	public List<FormListFileVO> selectAllFormListFile() {
		return dao.selectAll();
	}
	
	public List<FormListFileVO> selectAllFormListFileByFormList(String formListId) {
		List<FormListFileVO> all = dao.selectAll();
		List<FormListFileVO> allByFormList = all.stream().filter(fl -> fl.getFormListId().equals(formListId)).collect(Collectors.toList());
		return allByFormList;
	}

}
