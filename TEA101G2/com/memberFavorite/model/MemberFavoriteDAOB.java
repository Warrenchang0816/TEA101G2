package com.memberFavorite.model;

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

public class MemberFavoriteDAOB implements MemberFavoriteDAOInterfaceB{
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
	    "INSERT INTO MEMBER_FAVORITE VALUES ('FAVORITE' || lpad(MEMBER_FAVORITE_ID_SEQ.NEXTVAL, 5, '0' ),?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM MEMBER_FAVORITE order by MEMBER_FAVORITE_ID";
	private static final String SELECT_ONE_STMT = 
		"SELECT * FROM MEMBER_FAVORITE where MEMBER_FAVORITE_ID = ?";
	private static final String DELETE = 
		"DELETE FROM MEMBER_FAVORITE where MEMBER_FAVORITE_ID = ?";
	private static final String UPDATE = 
		"UPDATE MEMBER_FAVORITE set MEMBER_ID=?,SPACE_ID=? where MEMBER_FAVORITE_ID = ?";


	@Override
	public void insert(MemberFavoriteVO memberFavoriteVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, memberFavoriteVO.getMemberId());
			ptmt.setString(2, memberFavoriteVO.getSpaceId());

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
	public void delete(String memberFavoriteId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, memberFavoriteId);
			
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
	public void update(MemberFavoriteVO memberFavoriteVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, memberFavoriteVO.getMemberId());
			ptmt.setString(2, memberFavoriteVO.getSpaceId());
			ptmt.setString(3, memberFavoriteVO.getMemberFavoriteId());

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
	public MemberFavoriteVO selectOne(String memberFavoriteId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		MemberFavoriteVO memberFavoriteVO = new MemberFavoriteVO();
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, memberFavoriteId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				memberFavoriteVO.setMemberFavoriteId(rs.getString("MEMBER_FAVORITE_ID"));
				memberFavoriteVO.setMemberId(rs.getString("MEMBER_ID"));
				memberFavoriteVO.setSpaceId(rs.getString("SPACE_ID"));
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
		return memberFavoriteVO;
	}

	@Override
	public List<MemberFavoriteVO> selectAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		MemberFavoriteVO memberFavoriteVO = null;
		List<MemberFavoriteVO> list = new ArrayList<MemberFavoriteVO>();;
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				memberFavoriteVO = new MemberFavoriteVO();
				memberFavoriteVO.setMemberFavoriteId(rs.getString("MEMBER_FAVORITE_ID"));
				memberFavoriteVO.setMemberId(rs.getString("MEMBER_ID"));
				memberFavoriteVO.setSpaceId(rs.getString("SPACE_ID"));
				list.add(memberFavoriteVO);
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
