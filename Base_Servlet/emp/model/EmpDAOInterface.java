package com.emp.model;

import java.util.List;


public interface EmpDAOInterface {
	public void insert(EmpVO empVO);
	public void delete(String empId);
	public void update(EmpVO empVO);
	public EmpVO selectOne(String empId);
	public List<EmpVO> selectAll();

}
