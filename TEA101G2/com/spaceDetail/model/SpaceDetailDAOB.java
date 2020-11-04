package com.spaceDetail.model;

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

import com.space.model.SpaceVO;

public class SpaceDetailDAOB implements SpaceDetailDAOInterfaceB{
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
	    "INSERT INTO SPACE_DETAIL VALUES ('SD' || lpad(SPACE_DETAIL_ID_SEQ.NEXTVAL, 5, '0'),?,?,?,?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM SPACE_DETAIL order by SPACE_DETAIL_ID";
	private static final String SELECT_ONE_STMT = 
		"SELECT * FROM SPACE_DETAIL where SPACE_DETAIL_ID = ?";
	private static final String DELETE = 
		"DELETE FROM SPACE_DETAIL where SPACE_DETAIL_ID = ?";
	private static final String UPDATE = 
		"UPDATE SPACE_DETAIL set SPACE_ID=?,SPACE_DETAIL_FREEDATE=?,SPACE_DETAIL_FREETIME_START=?,SPACE_DETAIL_FREETIME_END=?,SPACE_DETAIL_CHARGE=? where SPACE_DETAIL_ID = ?";
//	private static final String SELECT_ALL_BY_SPACEID_STMT = 
//			"SELECT * FROM SPACE_DETAIL where SPACE_ID = ? order by SPACE_DETAIL_ID";


	@Override
	public void insert(SpaceDetailVO spaceDetailVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, spaceDetailVO.getSpaceId());
			ptmt.setDate(2, spaceDetailVO.getSpaceDetailFreeDate());
			ptmt.setTimestamp(3, spaceDetailVO.getSpaceDetailFreeTimeStart());
			ptmt.setTimestamp(4, spaceDetailVO.getSpaceDetailFreeTimeEnd());
			ptmt.setInt(5, spaceDetailVO.getSpaceDetailCharge());

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
	public void delete(String spaceDetailId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, spaceDetailId);
			
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
	public void update(SpaceDetailVO spaceDetailVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, spaceDetailVO.getSpaceId());
			ptmt.setDate(2, spaceDetailVO.getSpaceDetailFreeDate());
			ptmt.setTimestamp(3, spaceDetailVO.getSpaceDetailFreeTimeStart());
			ptmt.setTimestamp(4, spaceDetailVO.getSpaceDetailFreeTimeEnd());
			ptmt.setInt(5, spaceDetailVO.getSpaceDetailCharge());
			ptmt.setString(6, spaceDetailVO.getSpaceDetailId());

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
	public SpaceDetailVO selectOne(String spaceDetailId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		SpaceDetailVO spaceDetailVO = new SpaceDetailVO();
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, spaceDetailId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceDetailVO.setSpaceDetailId(rs.getString("SPACE_DETAIL_ID"));
				spaceDetailVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceDetailVO.setSpaceDetailFreeDate(rs.getDate("SPACE_DETAIL_FREEDATE"));
				spaceDetailVO.setSpaceDetailFreeTimeStart(rs.getTimestamp("SPACE_DETAIL_FREETIME_START"));
				spaceDetailVO.setSpaceDetailFreeTimeEnd(rs.getTimestamp("SPACE_DETAIL_FREETIME_END"));
				spaceDetailVO.setSpaceDetailCharge(rs.getInt("SPACE_DETAIL_CHARGE"));
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
		
		return spaceDetailVO;
	}

	@Override
	public List<SpaceDetailVO> selectAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		SpaceDetailVO spaceDetailVO = null;
		List<SpaceDetailVO> list = new ArrayList<SpaceDetailVO>();;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceDetailVO = new SpaceDetailVO();
				spaceDetailVO.setSpaceDetailId(rs.getString("SPACE_DETAIL_ID"));
				spaceDetailVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceDetailVO.setSpaceDetailFreeDate(rs.getDate("SPACE_DETAIL_FREEDATE"));
				spaceDetailVO.setSpaceDetailFreeTimeStart(rs.getTimestamp("SPACE_DETAIL_FREETIME_START"));
				spaceDetailVO.setSpaceDetailFreeTimeEnd(rs.getTimestamp("SPACE_DETAIL_FREETIME_END"));
				spaceDetailVO.setSpaceDetailCharge(rs.getInt("SPACE_DETAIL_CHARGE"));
				list.add(spaceDetailVO);
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

//	@Override
//	public List<SpaceDetailVO> selectAllBySpace(String spaceId) {
//		Connection con = null;
//		PreparedStatement ptmt = null;
//		ResultSet rs = null;
//		
//		SpaceDetailVO spaceDetailVO = null;
//		List<SpaceDetailVO> list = new ArrayList<SpaceDetailVO>();;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			ptmt = con.prepareStatement(SELECT_ALL_BY_SPACEID_STMT);
//			
//			ptmt.setString(1, spaceId);
//			
//			rs = ptmt.executeQuery();
//			while (rs.next()) {
//				spaceDetailVO = new SpaceDetailVO();
//				spaceDetailVO.setSpaceDetailId(rs.getString("SPACE_DETAIL_ID"));
//				spaceDetailVO.setSpaceId(rs.getString("SPACE_ID"));
//				spaceDetailVO.setSpaceDetailFreeDate(rs.getDate("SPACE_DETAIL_FREEDATE"));
//				spaceDetailVO.setSpaceDetailFreeTimeStart(rs.getTimestamp("SPACE_DETAIL_FREETIME_START"));
//				spaceDetailVO.setSpaceDetailFreeTimeEnd(rs.getTimestamp("SPACE_DETAIL_FREETIME_END"));
//				spaceDetailVO.setSpaceDetailCharge(rs.getInt("SPACE_DETAIL_CHARGE"));
//				list.add(spaceDetailVO);
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
