package com.formList.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FormListDAO implements FormListDAOInterface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G2";
	String passwd = "TEA101G2";
	

	private static final String INSERT_STMT = 
	    "INSERT INTO FORM_LIST VALUES ('FORMS' || lpad(FORM_LIST_ID_SEQ.NEXTVAL, 7, '0'),?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM FORM_LIST order by FORM_LIST_ID";
	private static final String SELECT_ONE_STMT = 
		"SELECT * FROM FORM_LIST where FORM_LIST_ID = ?";
	private static final String DELETE = 
		"DELETE FROM FORM_LIST where FORM_LIST_ID = ?";
	private static final String UPDATE = 
		"UPDATE FORM_LIST set MEMBER_ID=?,EMP_ID=?,FORM_CREATEDATE=?,FORM_LIST_TYPE=?,FORM_TITLE=?,FORM_CONTENT=?,FORM_FILE=? where FORM_LIST_ID = ?";

	@Override
	public void insert(FormListVO supplyListVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, supplyListVO.getMembrId());
			ptmt.setString(2, supplyListVO.getEmpId());
			ptmt.setDate(3, supplyListVO.getFormListCreateDate());
			ptmt.setString(4, supplyListVO.getFormListType());
			ptmt.setString(5, supplyListVO.getFormListTitle());
			ptmt.setString(6, supplyListVO.getFormListContext());
			ptmt.setBytes(7, supplyListVO.getFormListFile());

			ptmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public void delete(String supplyListId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, supplyListId);
			
			ptmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
				e.printStackTrace();
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
	public void update(FormListVO supplyListVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, supplyListVO.getMembrId());
			ptmt.setString(2, supplyListVO.getEmpId());
			ptmt.setDate(3, supplyListVO.getFormListCreateDate());
			ptmt.setString(4, supplyListVO.getFormListType());
			ptmt.setString(5, supplyListVO.getFormListTitle());
			ptmt.setString(6, supplyListVO.getFormListContext());
			ptmt.setBytes(7, supplyListVO.getFormListFile());
			ptmt.setString(8, supplyListVO.getFormListId());
			

			ptmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public FormListVO selectOne(String supplyListId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		FormListVO supplyListVO = new FormListVO();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, supplyListId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				supplyListVO.setFormListId(rs.getString("FORM_LIST_ID"));
				supplyListVO.setMembrId(rs.getString("MEMBER_ID"));
				supplyListVO.setEmpId(rs.getString("EMP_ID"));
				supplyListVO.setFormListCreateDate(rs.getDate("FORM_CREATEDATE"));
				supplyListVO.setFormListType(rs.getString("FORM_LIST_TYPE"));
				supplyListVO.setFormListTitle(rs.getString("FORM_TITLE"));
				supplyListVO.setFormListContext(rs.getString("FORM_CONTENT"));
				supplyListVO.setFormListFile(rs.getBytes("FORM_FILE"));
				
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		return supplyListVO;
	}

	@Override
	public List<FormListVO> selectAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		FormListVO supplyListVO = null;
		List<FormListVO> list = new ArrayList<FormListVO>();;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				supplyListVO = new FormListVO();
				supplyListVO.setFormListId(rs.getString("FORM_LIST_ID"));
				supplyListVO.setMembrId(rs.getString("MEMBER_ID"));
				supplyListVO.setEmpId(rs.getString("EMP_ID"));
				supplyListVO.setFormListCreateDate(rs.getDate("FORM_CREATEDATE"));
				supplyListVO.setFormListType(rs.getString("FORM_LIST_TYPE"));
				supplyListVO.setFormListTitle(rs.getString("FORM_TITLE"));
				supplyListVO.setFormListContext(rs.getString("FORM_CONTENT"));
				supplyListVO.setFormListFile(rs.getBytes("FORM_FILE"));
				
				list.add(supplyListVO);
			}

			}catch (ClassNotFoundException e) {
				e.printStackTrace();
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
