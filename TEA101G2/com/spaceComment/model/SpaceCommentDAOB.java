package com.spaceComment.model;

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

import com.spaceDetail.model.SpaceDetailVO;

public class SpaceCommentDAOB implements SpaceCommentDAOInterfaceB{
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
	    "INSERT INTO SPACE_COMMENT VALUES ('SCOMMENT' || lpad(SPACE_COMMENT_ID_SEQ.NEXTVAL, 5, '0'),?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM SPACE_COMMENT order by SPACE_COMMENT_ID";
	private static final String SELECT_ONE_STMT = 
		"SELECT * FROM SPACE_COMMENT where SPACE_COMMENT_ID = ?";
	private static final String DELETE = 
		"DELETE FROM SPACE_COMMENT where SPACE_COMMENT_ID = ?";
	private static final String UPDATE = 
		"UPDATE SPACE_COMMENT set SPACE_ID=?,MEMBER_ID=?,SPACE_COMMENT_CONTENT=?,SPACE_COMMENT_LEVEL=?,SPACE_COMMENT_DATE=?,SPACE_COMMENT_STATUS=?,SPACE_COMMENT_STATUS_EMP=?,SPACE_COMMENT_STATUS_COMM=? where SPACE_COMMENT_ID = ?";


	@Override
	public void insert(SpaceCommentVO spaceCommVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, spaceCommVO.getSpaceId());
			ptmt.setString(2, spaceCommVO.getMemberId());
			ptmt.setString(3, spaceCommVO.getSpaceCommentContent());
			ptmt.setDouble(4, spaceCommVO.getSpaceCommentLevel());
			ptmt.setDate(5, spaceCommVO.getSpaceCommentDate());
			ptmt.setString(6, spaceCommVO.getSpaceCommStatus());
			ptmt.setString(7, spaceCommVO.getSpaceCommStatusEmp());
			ptmt.setString(8, spaceCommVO.getSpaceCommStatusComm());

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
	public void delete(String spaceCommId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, spaceCommId);
			
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
	public void update(SpaceCommentVO spaceCommVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, spaceCommVO.getSpaceId());
			ptmt.setString(2, spaceCommVO.getMemberId());
			ptmt.setString(3, spaceCommVO.getSpaceCommentContent());
			ptmt.setDouble(4, spaceCommVO.getSpaceCommentLevel());
			ptmt.setDate(5, spaceCommVO.getSpaceCommentDate());
			ptmt.setString(6, spaceCommVO.getSpaceCommStatus());
			ptmt.setString(7, spaceCommVO.getSpaceCommStatusEmp());
			ptmt.setString(8, spaceCommVO.getSpaceCommStatusComm());
			ptmt.setString(9, spaceCommVO.getSpaceCommentId());
			
			
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
	public SpaceCommentVO selectOne(String spaceCommId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		SpaceCommentVO spaceCommVO = new SpaceCommentVO();
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, spaceCommId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceCommVO.setSpaceCommentId(rs.getString("SPACE_COMMENT_ID"));
				spaceCommVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceCommVO.setMemberId(rs.getString("MEMBER_ID"));
				spaceCommVO.setSpaceCommentContent(rs.getString("SPACE_COMMENT_CONTENT"));
				spaceCommVO.setSpaceCommentLevel(rs.getDouble("SPACE_COMMENT_LEVEL"));
				spaceCommVO.setSpaceCommentDate(rs.getDate("SPACE_COMMENT_DATE"));
				spaceCommVO.setSpaceCommStatus(rs.getString("SPACE_COMMENT_STATUS"));
				spaceCommVO.setSpaceCommStatusEmp(rs.getString("SPACE_COMMENT_STATUS_EMP"));
				spaceCommVO.setSpaceCommStatusComm(rs.getString("SPACE_COMMENT_STATUS_COMM"));
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
		return spaceCommVO;
	}

	@Override
	public List<SpaceCommentVO> selectAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		SpaceCommentVO spaceCommVO = null;
		List<SpaceCommentVO> list = new ArrayList<SpaceCommentVO>();;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceCommVO = new SpaceCommentVO();
				spaceCommVO.setSpaceCommentId(rs.getString("SPACE_COMMENT_ID"));
				spaceCommVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceCommVO.setMemberId(rs.getString("MEMBER_ID"));
				spaceCommVO.setSpaceCommentContent(rs.getString("SPACE_COMMENT_CONTENT"));
				spaceCommVO.setSpaceCommentLevel(rs.getDouble("SPACE_COMMENT_LEVEL"));
				spaceCommVO.setSpaceCommentDate(rs.getDate("SPACE_COMMENT_DATE"));
				spaceCommVO.setSpaceCommStatus(rs.getString("SPACE_COMMENT_STATUS"));
				spaceCommVO.setSpaceCommStatusEmp(rs.getString("SPACE_COMMENT_STATUS_EMP"));
				spaceCommVO.setSpaceCommStatusComm(rs.getString("SPACE_COMMENT_STATUS_COMM"));
				list.add(spaceCommVO);
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
