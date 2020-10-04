package com.formList.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FormListService {
private FormListDAOInterface dao;
	
	public FormListService() {
		dao = new FormListDAO();
	}
	
	
	public FormListVO addFormList(FormListVO formListVO) {
		dao.insert(formListVO);
		return formListVO;
	}
	
	
	public FormListVO updateFormList(FormListVO formListVO) {		
		dao.update(formListVO);
		return formListVO;
	}
	
	public void deleteFormList(String formListId) {
		dao.delete(formListId);
	}
	
	
	public FormListVO selectOneFormList(String formListId) {
		return dao.selectOne(formListId);
	}
	
	public List<FormListVO> selectAllFormList() {
		return dao.selectAll();
	}
	
	public List<FormListVO> selectAllFormListByStatus(String formListStatus) {
		List<FormListVO> all = dao.selectAll();
		List<FormListVO> allByStatus = new ArrayList<FormListVO>();
		
		allByStatus = all.stream()
				.filter(fl -> formListStatus.equals(fl.getFormListStatus()))
				.collect(Collectors.toList());
		
		return allByStatus;
	}

}
