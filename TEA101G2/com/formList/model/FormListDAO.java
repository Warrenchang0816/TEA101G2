package com.formList.model;

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

import com.formListFile.model.FormListFileDAO;
import com.formListFile.model.FormListFileVO;


public class FormListDAO implements FormListDAOInterface{
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "TEA101G2";
//	String passwd = "123456";
	
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
	    "INSERT INTO FORM_LIST (FORM_LIST_ID, MEMBER_ID, EMP_ID, FORM_CREATE_DATE, FORM_LIST_TYPE, FORM_TITLE, FORM_CONTENT, FORM_STATUS, FORM_SOLU) VALUES ('FORMS' || lpad(FORM_LIST_ID_SEQ.NEXTVAL, 7, '0'),?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM FORM_LIST order by FORM_LIST_ID";
	private static final String SELECT_ONE_STMT = 
		"SELECT * FROM FORM_LIST where FORM_LIST_ID = ?";
	private static final String DELETE = 
		"DELETE FROM FORM_LIST where FORM_LIST_ID = ?";
	private static final String UPDATE = 
		"UPDATE FORM_LIST set MEMBER_ID=?,EMP_ID=?,FORM_CREATE_DATE=?,FORM_LIST_TYPE=?,FORM_TITLE=?,FORM_CONTENT=?,FORM_STATUS=?,FORM_SOLU=?,FORM_SOLU_DATE=? where FORM_LIST_ID = ?";

	@Override
	public void insert(FormListVO supplyListVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		String formListId = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			con.setAutoCommit(false);
			String cols[] = {"FORM_LIST_ID"};
			ptmt = con.prepareStatement(INSERT_STMT, cols);
			
			ptmt.setString(1, supplyListVO.getMemberId());
			ptmt.setString(2, supplyListVO.getEmpId());
			ptmt.setDate(3, supplyListVO.getFormListCreateDate());
			ptmt.setString(4, supplyListVO.getFormListType());
			ptmt.setString(5, supplyListVO.getFormListTitle());
			ptmt.setString(6, supplyListVO.getFormListContext());
			ptmt.setString(7, supplyListVO.getFormListStatus());
			if(supplyListVO.getFormListSolu() == null) ptmt.setString(8, "");
			else ptmt.setString(8, supplyListVO.getFormListSolu());
			
			ptmt.executeUpdate();
			con.commit();
			
			ResultSet rs = ptmt.getGeneratedKeys();
			if(rs.next()) {
				formListId = rs.getString(1);
			}else {
				System.out.println("未取得formList自增主鍵值");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
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
	public void insertWithFormListFile(FormListVO formListVO, List<FormListFileVO> FormListFileList) {
		Connection con = null;
		PreparedStatement ptmt = null;
		String formListId = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			con.setAutoCommit(false);
			String cols[] = {"FORM_LIST_ID"};
			ptmt = con.prepareStatement(INSERT_STMT, cols);
			
			ptmt.setString(1, formListVO.getMemberId());
			ptmt.setString(2, formListVO.getEmpId());
			ptmt.setDate(3, formListVO.getFormListCreateDate());
			ptmt.setString(4, formListVO.getFormListType());
			ptmt.setString(5, formListVO.getFormListTitle());
			ptmt.setString(6, formListVO.getFormListContext());
			ptmt.setString(7, formListVO.getFormListStatus());
			if(formListVO.getFormListSolu() == null) ptmt.setString(8, "");
			else ptmt.setString(8, formListVO.getFormListSolu());
			
			ptmt.executeUpdate();
			
			ResultSet rs = ptmt.getGeneratedKeys();
			if(rs.next()) {
				formListId = rs.getString(1);
				System.out.println("formList自增主鍵值="+ formListId + "剛新增成功的訂單編號");
			}else {
				System.out.println("未取得formList自增主鍵值");
			}

			rs.close();
			
			if(!formListVO.getFormListType().equals("message")) {
				FormListFileDAO dao = new FormListFileDAO();
				for(FormListFileVO addFormListFile : FormListFileList) {
					addFormListFile.setFormListId(formListId);
					dao.insertFromFormList(addFormListFile, con);
				}
			}
			
			con.commit();
			
		} catch (Exception e) {
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back due to FormList error");
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
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, supplyListId);
			
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
	public void update(FormListVO supplyListVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, supplyListVO.getMemberId());
			ptmt.setString(2, supplyListVO.getEmpId());
			ptmt.setDate(3, supplyListVO.getFormListCreateDate());
			ptmt.setString(4, supplyListVO.getFormListType());
			ptmt.setString(5, supplyListVO.getFormListTitle());
			ptmt.setString(6, supplyListVO.getFormListContext());
			ptmt.setString(7, supplyListVO.getFormListStatus());
			ptmt.setString(8, supplyListVO.getFormListSolu());
			ptmt.setDate(9, supplyListVO.getFormListSoluDate());
			ptmt.setString(10, supplyListVO.getFormListId());
			

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
	public FormListVO selectOne(String supplyListId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		FormListVO supplyListVO = new FormListVO();
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, supplyListId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				supplyListVO.setFormListId(rs.getString("FORM_LIST_ID"));
				supplyListVO.setMemberId(rs.getString("MEMBER_ID"));
				supplyListVO.setEmpId(rs.getString("EMP_ID"));
				supplyListVO.setFormListCreateDate(rs.getDate("FORM_CREATE_DATE"));
				supplyListVO.setFormListType(rs.getString("FORM_LIST_TYPE"));
				supplyListVO.setFormListTitle(rs.getString("FORM_TITLE"));
				supplyListVO.setFormListContext(rs.getString("FORM_CONTENT"));
				supplyListVO.setFormListStatus(rs.getString("FORM_STATUS"));
				if(rs.getString("FORM_SOLU") != null) {
					supplyListVO.setFormListSolu(rs.getString("FORM_SOLU"));
				}else {
					supplyListVO.setFormListSolu("");
				}
				if(rs.getDate("FORM_SOLU_DATE") != null) {
					supplyListVO.setFormListSoluDate(rs.getDate("FORM_SOLU_DATE"));
				}else {
					supplyListVO.setFormListSoluDate(new java.sql.Date(System.currentTimeMillis()));
				}
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
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				supplyListVO = new FormListVO();
				supplyListVO.setFormListId(rs.getString("FORM_LIST_ID"));
				supplyListVO.setMemberId(rs.getString("MEMBER_ID"));
				supplyListVO.setEmpId(rs.getString("EMP_ID"));
				supplyListVO.setFormListCreateDate(rs.getDate("FORM_CREATE_DATE"));
				supplyListVO.setFormListType(rs.getString("FORM_LIST_TYPE"));
				supplyListVO.setFormListTitle(rs.getString("FORM_TITLE"));
				supplyListVO.setFormListContext(rs.getString("FORM_CONTENT"));
				supplyListVO.setFormListStatus(rs.getString("FORM_STATUS"));
				supplyListVO.setFormListSolu(rs.getString("FORM_SOLU"));
				supplyListVO.setFormListSoluDate(rs.getDate("FORM_SOLU_DATE"));
				
				list.add(supplyListVO);
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
