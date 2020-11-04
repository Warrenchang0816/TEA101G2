package com.formListFile.model;

import java.sql.Connection;
import java.util.List;

public interface FormListFileDAOInterface {
	public void insert(FormListFileVO formListFileVO);
	public void insertFromFormList(FormListFileVO formListFileVO, Connection con);
	public void delete(String formListFileId);
	public void update(FormListFileVO formListFileVO);
	public FormListFileVO selectOne(String formListFileId);
	public List<FormListFileVO> selectAll();

}
