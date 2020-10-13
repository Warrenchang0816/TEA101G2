package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class EmpDAO implements EmpDAOInterface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G2";
	String passwd = "TEA101G2";
	

	private static final String INSERT_STMT = 
	    "INSERT INTO EMP VALUES ('EMP' || lpad(EMP_ID_SEQ.NEXTVAL, 5, '0' ),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM EMP order by EMP_ID";
	private static final String SELECT_ONE_STMT = 
		"SELECT * FROM EMP where EMP_ID = ?";
	private static final String DELETE = 
		"DELETE FROM EMP where EMP_ID = ?";
	private static final String UPDATE = 
		"UPDATE EMP set EMP_ACCOUNT=?,EMP_PASSWORD=?,EMP_NAME=?,EMP_NICKNAME=?,EMP_EMAIL=?,EMP_PHOTO=?,EMP_PHONE=?,EMP_ADDRESS=?,EMP_BIRTH=?,EMP_SEX=?,EMP_COUNTRY=?,EMP_HIRE_DATE=?,EMP_JOB=?,EMP_AUTH=?,EMP_STATUS=?,EMP_ONLINE=? where EMP_ID = ?";

	@Override
	public void insert(EmpVO empVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, empVO.getEmpAccount());
			ptmt.setString(2, empVO.getEmpPassword());
			ptmt.setString(3, empVO.getEmpName());
			ptmt.setString(4, empVO.getEmpNickname());
			ptmt.setString(5, empVO.getEmpEmail());
			ptmt.setBytes(6, empVO.getEmpPhoto());
			ptmt.setString(7, empVO.getEmpPhone());
			ptmt.setString(8, empVO.getEmpAddress());
			ptmt.setDate(9, empVO.getEmpBirth());
			ptmt.setString(10, empVO.getEmpSex());
			ptmt.setString(11, empVO.getEmpCountry());
			ptmt.setDate(12, empVO.getEmpHireDate());
			ptmt.setString(13, empVO.getEmpJob());
			ptmt.setInt(14, empVO.getEmpAuth());
			ptmt.setString(15, empVO.getEmpStatus());
			ptmt.setString(16, "N");

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
	public void delete(String empId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, empId);
			
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
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, empVO.getEmpAccount());
			ptmt.setString(2, empVO.getEmpPassword());
			ptmt.setString(3, empVO.getEmpName());
			ptmt.setString(4, empVO.getEmpNickname());
			ptmt.setString(5, empVO.getEmpEmail());
			ptmt.setBytes(6, empVO.getEmpPhoto());
			ptmt.setString(7, empVO.getEmpPhone());
			ptmt.setString(8, empVO.getEmpAddress());
			ptmt.setDate(9, empVO.getEmpBirth());
			ptmt.setString(10, empVO.getEmpSex());
			ptmt.setString(11, empVO.getEmpCountry());
			ptmt.setDate(12, empVO.getEmpHireDate());
			ptmt.setString(13, empVO.getEmpJob());
			ptmt.setInt(14, empVO.getEmpAuth());
			ptmt.setString(15, empVO.getEmpStatus());
			ptmt.setString(16, empVO.getEmpOnline());
			ptmt.setString(17, empVO.getEmpId());

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
	public EmpVO selectOne(String empId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		EmpVO empVO = new EmpVO();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, empId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				empVO.setEmpId(rs.getString("EMP_ID"));
				empVO.setEmpAccount(rs.getString("EMP_ACCOUNT"));
				empVO.setEmpPassword(rs.getString("EMP_PASSWORD"));
				empVO.setEmpName(rs.getString("EMP_NAME"));
				empVO.setEmpNickname(rs.getString("EMP_NICKNAME"));
				empVO.setEmpEmail(rs.getString("EMP_EMAIL"));
				empVO.setEmpPhoto(rs.getBytes("EMP_PHOTO"));
				empVO.setEmpPhone(rs.getString("EMP_PHONE"));
				empVO.setEmpAddress(rs.getString("EMP_ADDRESS"));
				empVO.setEmpBirth(rs.getDate("EMP_BIRTH"));
				empVO.setEmpSex(rs.getString("EMP_SEX"));
				empVO.setEmpCountry(rs.getString("EMP_COUNTRY"));
				empVO.setEmpHireDate(rs.getDate("EMP_HIRE_DATE"));
				empVO.setEmpJob(rs.getString("EMP_JOB"));
				empVO.setEmpAuth(rs.getInt("EMP_AUTH"));
				empVO.setEmpStatus(rs.getString("EMP_STATUS"));
				empVO.setEmpOnline(rs.getString("EMP_ONLINE"));
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
		return empVO;
	}

	@Override
	public List<EmpVO> selectAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		EmpVO empVO = null;
		List<EmpVO> list = new ArrayList<EmpVO>();;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmpId(rs.getString("EMP_ID"));
				empVO.setEmpAccount(rs.getString("EMP_ACCOUNT"));
				empVO.setEmpPassword(rs.getString("EMP_PASSWORD"));
				empVO.setEmpName(rs.getString("EMP_NAME"));
				empVO.setEmpNickname(rs.getString("EMP_NICKNAME"));
				empVO.setEmpEmail(rs.getString("EMP_EMAIL"));
				empVO.setEmpPhoto(rs.getBytes("EMP_PHOTO"));
				empVO.setEmpPhone(rs.getString("EMP_PHONE"));
				empVO.setEmpAddress(rs.getString("EMP_ADDRESS"));
				empVO.setEmpBirth(rs.getDate("EMP_BIRTH"));
				empVO.setEmpSex(rs.getString("EMP_SEX"));
				empVO.setEmpCountry(rs.getString("EMP_COUNTRY"));
				empVO.setEmpHireDate(rs.getDate("EMP_HIRE_DATE"));
				empVO.setEmpJob(rs.getString("EMP_JOB"));
				empVO.setEmpAuth(rs.getInt("EMP_AUTH"));
				empVO.setEmpStatus(rs.getString("EMP_STATUS"));
				empVO.setEmpOnline(rs.getString("EMP_ONLINE"));
				
				list.add(empVO);
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
