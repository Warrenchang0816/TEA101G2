package com.formListFile.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class FormListFileDAO implements FormListFileDAOInterface{
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "TEA101G2";
//	String passwd = "TEA101G2";
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TEA101G2");
		}
		catch(NamingException e){
			e.printStackTrace();
		}
	}
	

	private static final String INSERT_STMT = 
	    "INSERT INTO FORM_LIST_FILE VALUES ('FLF' || lpad(FORM_LIST_FILE_ID_SEQ.NEXTVAL, 7, '0'),?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM FORM_LIST_FILE order by FORM_LIST_FILE_ID";
	private static final String SELECT_ONE_STMT = 
		"SELECT * FROM FORM_LIST_FILE where FORM_LIST_FILE_ID = ?";
	private static final String DELETE = 
		"DELETE FROM FORM_LIST_FILE where FORM_LIST_FILE_ID = ?";
	private static final String UPDATE = 
		"UPDATE FORM_LIST_FILE set FORM_LIST_ID=?,FORM_LIST_FILE=? where FORM_LIST_FILE_ID = ?";


	@Override
	public void insert(FormListFileVO formListFileVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, formListFileVO.getFormListId());
			ptmt.setBytes(2, formListFileVO.getFormListFile());

			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (ptmt != null) {
				try {
					ptmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void insertFromFormList(FormListFileVO formListFileVO, Connection con) {
		PreparedStatement ptmt = null;
		
		try {
			System.out.println("insertFromFormList");
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, formListFileVO.getFormListId());
			ptmt.setBytes(2, formListFileVO.getFormListFile());

			ptmt.executeUpdate();
			System.out.println("insertFromFormListYAYA");
		} catch (Exception e) {
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back due to FormListFile error");
					con.rollback();
				} catch (SQLException se) {
					e.printStackTrace(System.err);
				}
			}
		}finally {
			if (ptmt != null) {
				try {
					ptmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
		}
	}


	@Override
	public void delete(String formListFileId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, formListFileId);
			
			ptmt.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (ptmt != null) {
					try {
						ptmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
	}

	@Override
	public void update(FormListFileVO formListFileVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, formListFileVO.getFormListId());
			ptmt.setBytes(2, formListFileVO.getFormListFile());
			ptmt.setString(3, formListFileVO.getFormListFileId());
			
			ptmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (ptmt != null) {
				try {
					ptmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public FormListFileVO selectOne(String formListFileId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		FormListFileVO formListFileVO = new FormListFileVO();
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, formListFileId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				formListFileVO.setFormListFileId(rs.getString("FORM_LIST_FILE_ID"));
				formListFileVO.setFormListId(rs.getString("FORM_LIST_ID"));
				formListFileVO.setFormListFile(rs.getBytes("FORM_LIST_FILE"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ptmt != null) {
				try {
					ptmt.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return formListFileVO;
	}

	@Override
	public List<FormListFileVO> selectAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		FormListFileVO formListFileVO = null;
		List<FormListFileVO> list = new ArrayList<FormListFileVO>();;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				formListFileVO = new FormListFileVO();
				formListFileVO.setFormListFileId(rs.getString("FORM_LIST_FILE_ID"));
				formListFileVO.setFormListId(rs.getString("FORM_LIST_ID"));
				formListFileVO.setFormListFile(rs.getBytes("FORM_LIST_FILE"));
				
				list.add(formListFileVO);
			}

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (ptmt != null) {
					try {
						ptmt.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
		return list;
	}


}
