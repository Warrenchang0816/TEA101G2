package com.formList.model;

import java.util.List;


public interface FormListDAOInterface {
	public void insert(FormListVO formListVO);
	public void delete(String formListId);
	public void update(FormListVO formListVO);
	public FormListVO selectOne(String formListId);
	public List<FormListVO> selectAll();

}
