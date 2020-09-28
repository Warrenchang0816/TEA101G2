package com.emp.model;

import java.util.List;


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
	
	public void deleteEmp(String empId) {
		dao.delete(empId);
	}
	
	
	public EmpVO selectOneEmp(String empId) {
		return dao.selectOne(empId);
	}
	
	public List<EmpVO> selectAllEmp() {
		return dao.selectAll();
	}

}
