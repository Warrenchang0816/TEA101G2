package com.emp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class EmpService {
	private EmpDAOInterface dao;
	
	public EmpService() {
		dao = new EmpDAO();
	}
	
	
	public EmpVO addEmp(EmpVO empVO) {
		dao.insert(empVO);
		return empVO;
	}
	
	
	public EmpVO updateEmp(EmpVO empVO) {		
		dao.update(empVO);
		return empVO;
	}
	
	public void updateEmpOnline(EmpVO empVO, String empOnline) {		
		EmpVO updateEmpOnline = dao.selectOne(empVO.getEmpId());
		updateEmpOnline.setEmpOnline(empOnline);
		dao.update(updateEmpOnline);
	}
	
	public void deleteEmp(String empId) {
		dao.delete(empId);
	}
	
	
	public EmpVO selectOneEmp(String empId) {
		return dao.selectOne(empId);
	}
	
	public List<EmpVO> selectAllEmp() {
		return dao.selectAll();
	}
	
	public boolean isEmpAccountLogin(String empAccount) {
		List<EmpVO> all = dao.selectAll();
		
		if(all.stream().anyMatch(e -> empAccount.equals(e.getEmpAccount()))) {
			return true;
		}
		return false;
	}
	
	public boolean isEmpPasswordLogin(String empPassword) {
		List<EmpVO> all = dao.selectAll();
		
		if(all.stream().anyMatch(e -> empPassword.equals(e.getEmpPassword()))) {
			return true;
		}
		return false;
	}
	
	public EmpVO selectAllEmpByAccount(String empAccount) {
		List<EmpVO> all = dao.selectAll();
		EmpVO emp = new EmpVO();
		
		Optional<EmpVO> oemp = all.stream()
			.filter(e -> empAccount.equals(e.getEmpAccount()))
			.findFirst();
		emp = oemp.get();
		
		return emp;
	}
	
	public String selectEmpNameById(String empId) {
		List<EmpVO> all = dao.selectAll();
		EmpVO emp = new EmpVO();
		
		Optional<EmpVO> oemp = all.stream()
			.filter(e -> empId.equals(e.getEmpId()))
			.findFirst();
		emp = oemp.get();
		
		return emp.getEmpName();
	}
	
	public Map<String, String> selectAllEmpIdName() {
		List<EmpVO> all = dao.selectAll();
		
		return all.stream().collect(Collectors.toMap(EmpVO::getEmpId, EmpVO::getEmpName));
	}
	
	public Map<String, String> selectAllEmpIdNameR() {
		List<EmpVO> all = dao.selectAll();
		
		return all.stream().collect(Collectors.toMap(EmpVO::getEmpName, EmpVO::getEmpId));
	}
	
	public List<EmpVO> selectAllEmpOnline() {
		List<EmpVO> all = dao.selectAll();
		List<EmpVO> allEmpOnline = all.stream()
					.filter(e -> e.getEmpOnline().equals("Y"))
					.collect(Collectors.toList());
		
		return allEmpOnline;
	}


}
