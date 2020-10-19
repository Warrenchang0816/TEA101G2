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
		List<FormListVO> all = dao.selectAll();
		List<FormListVO> allByType = new ArrayList<FormListVO>();
		
		allByType = all.stream()
				.filter(fl -> !fl.getFormListType().equals("mail"))
				.filter(fl -> !fl.getFormListType().equals("trash"))
				.filter(fl -> !fl.getFormListType().equals("message"))
				.collect(Collectors.toList());
		
		return allByType;
	}
	
	public List<FormListVO> selectAllFormListByStatus(String formListStatus) {
		List<FormListVO> all = dao.selectAll();
		List<FormListVO> allByStatus = new ArrayList<FormListVO>();
		
		allByStatus = all.stream()
				.filter(fl -> formListStatus.equals(fl.getFormListStatus()))
				.collect(Collectors.toList());
		
		return allByStatus;
	}
	
	//mail角度
	//formListSolu(寄件者)
	//empId(收件者)
	//formListStatus-'R':已讀,'M':未讀
	//formListStatus-'00':收&寄垃圾信,'10':收垃圾信,'01':寄垃圾信,'11':收&寄正常
	
	public List<FormListVO> selectAllMailByGet(String empId) {
		List<FormListVO> all = dao.selectAll();
		List<FormListVO> allByGet = new ArrayList<FormListVO>();
		
		allByGet = all.stream()
				.filter(fl -> "mail".equals(fl.getFormListType()))
				.filter(fl -> empId.equals(fl.getEmpId()))
				.collect(Collectors.toList());
		
		return allByGet;
	}
	
	public List<FormListVO> selectAllNewMailByGet(String empId) {
		List<FormListVO> all = dao.selectAll();
		List<FormListVO> allByGet = new ArrayList<FormListVO>();
		
		allByGet = all.stream()
				.filter(fl -> "mail".equals(fl.getFormListType()))
				.filter(fl -> "M".equals(fl.getFormListStatus()))
				.filter(fl -> empId.equals(fl.getEmpId()))
				.collect(Collectors.toList());
		
		return allByGet;
	}
	
	public List<FormListVO> selectAllMailBySend(String formListSolu) {
		List<FormListVO> all = dao.selectAll();
		List<FormListVO> allBySend = new ArrayList<FormListVO>();
		
		allBySend = all.stream()
				.filter(fl -> "mail".equals(fl.getFormListType()))
				.filter(fl -> formListSolu.equals(fl.getFormListSolu()))
				.collect(Collectors.toList());
		
		return allBySend;
	}
	
	public List<FormListVO> selectAllTrash(String formListStatus, String empId) {
		List<FormListVO> all = dao.selectAll();
		List<FormListVO> allByStatus = new ArrayList<FormListVO>();
		
		allByStatus = all.stream()
				.filter(fl -> empId.equals(fl.getEmpId()))
				.filter(fl -> formListStatus.equals(fl.getFormListStatus()))
				.collect(Collectors.toList());
		
		return allByStatus;
	}
	
	public void readMail(String formListId) {
		FormListVO formList = dao.selectOne(formListId);
		formList.setFormListStatus("R");
		dao.update(formList);
	}
	
	//message角度
	//formListSolu(收件會員)
	//formListStatus-'R':已讀,'M':未讀
	
	public List<FormListVO> selectAllMessagesByGet(String formListSolu) {
		List<FormListVO> all = dao.selectAll();
		List<FormListVO> allByGet = new ArrayList<FormListVO>();
		
		allByGet = all.stream()
				.filter(fl -> "message".equals(fl.getFormListType()))
				.filter(fl -> formListSolu.equals(fl.getFormListSolu()))
				.collect(Collectors.toList());
		
		return allByGet;
	}
	
	public List<FormListVO> selectAllNewMessagesByGet(String formListSolu) {
		List<FormListVO> all = dao.selectAll();
		List<FormListVO> allByGet = new ArrayList<FormListVO>();
		
		allByGet = all.stream()
				.filter(fl -> "message".equals(fl.getFormListType()))
				.filter(fl -> "M".equals(fl.getFormListStatus()))
				.filter(fl -> formListSolu.equals(fl.getFormListSolu()))
				.collect(Collectors.toList());
		
		return allByGet;
	}

}
