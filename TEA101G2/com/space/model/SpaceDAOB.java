package com.space.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.digester.RuleSet;

public class SpaceDAOB implements SpaceDAOInterfaceB{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G2";
	String passwd = "TEA101G2";

	private static final String INSERT_STMT = 
	    "INSERT INTO SPACE VALUES ('SPACE' || lpad(SPACE_ID_SEQ.NEXTVAL, 5, '0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM SPACE order by space_Id";
	private static final String SELECT_ONE_STMT = 
	    "SELECT * FROM SPACE where space_Id = ?";
	private static final String DELETE = 
		"DELETE FROM SPACE where space_Id = ?";
	private static final String UPDATE = 
		"UPDATE SPACE set member_Id=?,emp_Id=?,space_Address=?,space_Lat=?,space_Lng=?,space_Name=?,space_Text=?,space_Type=?,space_Equment=?,space_Contain=?,space_Rule=?,space_Refund=?,space_Status=?,space_Signup_Date=?,space_Onsale_Date=?,space_Offsale_Date=?,space_Status_Emp=?,space_Status_Comm=? where space_Id = ?";
//	private static final String SELECT_ALL_BY_MEMBERID_STMT = 
//		"SELECT * FROM SPACE where member_Id = ? order by space_Id";

	@Override
	public void insert(SpaceVO spaceVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(INSERT_STMT);

			ptmt.setString(1, spaceVO.getMemberId());
			ptmt.setString(2, spaceVO.getEmpId());
			ptmt.setString(3, spaceVO.getSpaceAddress());
			ptmt.setDouble(4, spaceVO.getSpaceLat());
			ptmt.setDouble(5, spaceVO.getSpaceLng());
			ptmt.setString(6, spaceVO.getSpaceName());
			ptmt.setString(7, spaceVO.getSpaceText());
			ptmt.setString(8, spaceVO.getSpaceType());
			ptmt.setString(9, spaceVO.getSpaceEquipment());
			ptmt.setString(10, spaceVO.getSpaceContain());			
			ptmt.setString(11, spaceVO.getSpaceRule());
			ptmt.setString(12, spaceVO.getSpaceRefund());
			ptmt.setString(13, spaceVO.getSpaceStatus());
			ptmt.setDate(14, spaceVO.getSpaceSignupDate());
			ptmt.setDate(15, spaceVO.getSpaceOnsaleDate());
			ptmt.setDate(16, spaceVO.getSpaceOffsaleDate());
			ptmt.setString(17, "");
			ptmt.setString(18, "");

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
	public void delete(String spaceId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(DELETE);

			ptmt.setString(1, spaceId);

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
	public void update(SpaceVO spaceVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(UPDATE);

			ptmt.setString(1, spaceVO.getMemberId());
			ptmt.setString(2, spaceVO.getEmpId());
			ptmt.setString(3, spaceVO.getSpaceAddress());
			ptmt.setDouble(4, spaceVO.getSpaceLat());
			ptmt.setDouble(5, spaceVO.getSpaceLng());
			ptmt.setString(6, spaceVO.getSpaceName());
			ptmt.setString(7, spaceVO.getSpaceText());
			ptmt.setString(8, spaceVO.getSpaceType());
			ptmt.setString(9, spaceVO.getSpaceEquipment());
			ptmt.setString(10, spaceVO.getSpaceContain());			
			ptmt.setString(11, spaceVO.getSpaceRule());
			ptmt.setString(12, spaceVO.getSpaceRefund());
			ptmt.setString(13, spaceVO.getSpaceStatus());
			ptmt.setDate(14, spaceVO.getSpaceSignupDate());
			ptmt.setDate(15, spaceVO.getSpaceOnsaleDate());
			ptmt.setDate(16, spaceVO.getSpaceOffsaleDate());
			ptmt.setString(17, spaceVO.getSpaceStatusEmp());
			ptmt.setString(18, spaceVO.getSpaceStatusComm());
			ptmt.setString(19, spaceVO.getSpaceId());

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
	public SpaceVO selectOne(String spaceId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		SpaceVO spaceVO = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ONE_STMT);

			ptmt.setString(1, spaceId);
			
			rs = ptmt.executeQuery();
			while(rs.next()) {
				spaceVO = new SpaceVO();
				spaceVO.setSpaceId(rs.getString("space_Id"));
				spaceVO.setMemberId(rs.getString("member_Id"));
				spaceVO.setEmpId(rs.getString("Emp_Id"));
				spaceVO.setSpaceAddress(rs.getString("Space_Address"));
				spaceVO.setSpaceLat(rs.getDouble("SPACE_LAT"));
				spaceVO.setSpaceLng(rs.getDouble("SPACE_LNG"));
				spaceVO.setSpaceName(rs.getString("Space_Name"));
				spaceVO.setSpaceText(rs.getString("Space_TEXT"));
				spaceVO.setSpaceType(rs.getString("Space_Type"));
				spaceVO.setSpaceEquipment(rs.getString("Space_Equment"));
				spaceVO.setSpaceContain(rs.getString("Space_Contain"));
				spaceVO.setSpaceRule(rs.getString("Space_Rule"));
				spaceVO.setSpaceRefund(rs.getString("Space_Refund"));
				spaceVO.setSpaceStatus(rs.getString("Space_Status"));
				spaceVO.setSpaceSignupDate(rs.getDate("Space_Signup_Date"));
				spaceVO.setSpaceOnsaleDate(rs.getDate("Space_Onsale_Date"));
				spaceVO.setSpaceOffsaleDate(rs.getDate("Space_Offsale_Date"));
				spaceVO.setSpaceStatusEmp(rs.getString("Space_Status_Emp"));
				spaceVO.setSpaceStatusComm(rs.getString("Space_Status_Comm"));
				
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
		
		return spaceVO;
	}

	@Override
	public List<SpaceVO> selectAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		SpaceVO spaceVO = null;
		List<SpaceVO> list = new ArrayList<SpaceVO>();;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceVO = new SpaceVO();

				spaceVO.setSpaceId(rs.getString("space_Id"));
				spaceVO.setMemberId(rs.getString("member_Id"));
				spaceVO.setEmpId(rs.getString("Emp_Id"));
				spaceVO.setSpaceAddress(rs.getString("Space_Address"));
				spaceVO.setSpaceLat(rs.getDouble("SPACE_LAT"));
				spaceVO.setSpaceLng(rs.getDouble("SPACE_LNG"));
				spaceVO.setSpaceName(rs.getString("Space_Name"));
				spaceVO.setSpaceText(rs.getString("Space_TEXT"));
				spaceVO.setSpaceType(rs.getString("Space_Type"));
				spaceVO.setSpaceEquipment(rs.getString("Space_Equment"));
				spaceVO.setSpaceContain(rs.getString("Space_Contain"));
				spaceVO.setSpaceRule(rs.getString("Space_Rule"));
				spaceVO.setSpaceRefund(rs.getString("Space_Refund"));
				spaceVO.setSpaceStatus(rs.getString("Space_Status"));
				spaceVO.setSpaceSignupDate(rs.getDate("Space_Signup_Date"));
				spaceVO.setSpaceOnsaleDate(rs.getDate("Space_Onsale_Date"));
				spaceVO.setSpaceOffsaleDate(rs.getDate("Space_Offsale_Date"));
				spaceVO.setSpaceStatusEmp(rs.getString("Space_Status_Emp"));
				spaceVO.setSpaceStatusComm(rs.getString("Space_Status_Comm"));

				list.add(spaceVO);
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

//	@Override
//	public List<SpaceVO> selectAllByMember(String memberId) {
//		Connection con = null;
//		PreparedStatement ptmt = null;
//		ResultSet rs = null;
//		
//		SpaceVO spaceVO = null;
//		List<SpaceVO> list = new ArrayList<SpaceVO>();;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			ptmt = con.prepareStatement(SELECT_ALL_BY_MEMBERID_STMT);
//			
//			ptmt.setString(1, memberId);
//			
//			rs = ptmt.executeQuery();
//			while (rs.next()) {
//				spaceVO = new SpaceVO();
//
//				spaceVO.setSpaceId(rs.getString("space_Id"));
//				spaceVO.setMemberId(rs.getString("member_Id"));
//				spaceVO.setEmpId(rs.getString("Emp_Id"));
//				spaceVO.setSpaceAddress(rs.getString("Space_Address"));
//				spaceVO.setSpaceName(rs.getString("Space_Name"));
//				spaceVO.setSpaceType(rs.getString("Space_Type"));
//				spaceVO.setSpaceEqument(rs.getString("Space_Equment"));
//				spaceVO.setSpaceContain(rs.getString("Space_Contain"));
//				spaceVO.setSpaceRule(rs.getString("Space_Rule"));
//				spaceVO.setSpaceRefund(rs.getString("Space_Refund"));
//				spaceVO.setSpaceStatus(rs.getString("Space_Status"));
//				spaceVO.setSpaceSignupDate(rs.getDate("Space_Signup_Date"));
//				spaceVO.setSpaceOnsaleDate(rs.getDate("Space_Onsale_Date"));
//				spaceVO.setSpaceOffsaleDate(rs.getDate("Space_Offsale_Date"));
//
//				list.add(spaceVO);
//			}
//
//			}catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (ptmt != null) {
//					try {
//						ptmt.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//		return list;
//	}

}
