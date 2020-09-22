package com.spaceComm.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spaceDetail.model.SpaceDetailVO;

public class SpaceCommDAO implements SpaceCommDAOInterface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G2";
	String passwd = "TEA101G2";

	private static final String INSERT_STMT = 
	    "INSERT INTO SPACE_COMMENT VALUES ('SCOMMENT' || lpad(SPACE_COMMENT_ID_SEQ.NEXTVAL, 5, '0'),?,?,?,?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM SPACE_COMMENT order by SPACE_COMMENT_ID";
	private static final String SELECT_ONE_STMT = 
		"SELECT * FROM SPACE_COMMENT where SPACE_COMMENT_ID = ?";
	private static final String DELETE = 
		"DELETE FROM SPACE_COMMENT where SPACE_COMMENT_ID = ?";
	private static final String UPDATE = 
		"UPDATE SPACE_COMMENT set SPACE_ID=?,MEMBER_ID=?,SPACE_COMMENT_CONTENT=?,SPACE_COMMENT_LEVEL=?,SPACE_COMMENT_DATE=? where SPACE_COMMENT_ID = ?";


	@Override
	public void insert(SpaceCommVO spaceCommVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, spaceCommVO.getSpaceId());
			ptmt.setString(2, spaceCommVO.getMemberId());
			ptmt.setString(3, spaceCommVO.getComm());
			ptmt.setInt(4, spaceCommVO.getCommLevel());
			ptmt.setDate(5, spaceCommVO.getCommDate());

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
	public void delete(String spaceCommId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, spaceCommId);
			
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
	public void update(SpaceCommVO spaceCommVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, spaceCommVO.getSpaceId());
			ptmt.setString(2, spaceCommVO.getMemberId());
			ptmt.setString(3, spaceCommVO.getComm());
			ptmt.setInt(4, spaceCommVO.getCommLevel());
			ptmt.setDate(5, spaceCommVO.getCommDate());
			ptmt.setString(6, spaceCommVO.getSpaceCommId());
			
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
	public SpaceCommVO selectOne(String spaceCommId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		SpaceCommVO spaceCommVO = new SpaceCommVO();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, spaceCommId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceCommVO.setSpaceCommId(rs.getString("SPACE_COMMENT_ID"));
				spaceCommVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceCommVO.setMemberId(rs.getString("MEMBER_ID"));
				spaceCommVO.setComm(rs.getString("SPACE_COMMENT_CONTENT"));
				spaceCommVO.setCommLevel(rs.getInt("SPACE_COMMENT_LEVEL"));
				spaceCommVO.setCommDate(rs.getDate("SPACE_COMMENT_DATE"));
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
		return spaceCommVO;
	}

	@Override
	public List<SpaceCommVO> selectAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		SpaceCommVO spaceCommVO = null;
		List<SpaceCommVO> list = new ArrayList<SpaceCommVO>();;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceCommVO = new SpaceCommVO();
				spaceCommVO.setSpaceCommId(rs.getString("SPACE_COMMENT_ID"));
				spaceCommVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceCommVO.setMemberId(rs.getString("MEMBER_ID"));
				spaceCommVO.setComm(rs.getString("SPACE_COMMENT_CONTENT"));
				spaceCommVO.setCommLevel(rs.getInt("SPACE_COMMENT_LEVEL"));
				spaceCommVO.setCommDate(rs.getDate("SPACE_COMMENT_DATE"));
				list.add(spaceCommVO);
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
