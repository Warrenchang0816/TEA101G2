package com.spaceComment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spaceDetail.model.SpaceDetailVO;

public class SpaceCommentDAO implements SpaceCommentDAO_interface {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G2";
	String passwd = "TEA101G2";
	
	private static final String INSERT_STMT =
			"INSERT INTO SPACE_COMMENT VALUES (SPACE_COMMENT_ID_SEQ.NEXTVAL,?,?,?,?,?)";
	private static final String SELECT_ALL_STMT =
			"SELECT * FROM SPACE_COMMENT order by SPACE_COMMENT_ID";
	private static final String SELECT_ONE_STMT =
			"SELECT * FROM SPACE_COMMENT where SPACE_COMMENT_ID = ?";
	private static final String DELETE =
			"DELETE FROM SPACE_COMMENT where SPACE_COMMENT_ID = ?";
	private static final String UPDATE =
			"UPDATE SPACE_COMMENT set SPACE_ID=?,MEMBER_ID=?,SPACE_COMMENT_CONTENT=?,SPACE_COMMENT_LEVEL=?,SPACE_COMMENT_DATE=? where SPACE_COMMENT_ID=?";
	
	@Override
	public void insert(SpaceCommentVO spaceCommentVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, spaceCommentVO.getSpaceId());
			ptmt.setString(2, spaceCommentVO.getMemberId());
			ptmt.setString(3, spaceCommentVO.getSpaceCommentContent());
			ptmt.setDouble(4, spaceCommentVO.getSpaceCommentLevel());
			ptmt.setDate(5, spaceCommentVO.getSpaceCommentDate());
				
			ptmt.executeUpdate();
			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(ptmt != null) {
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
	public void delete(String spaceCommentId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, spaceCommentId);
			
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
	public void update(SpaceCommentVO spaceCommentVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, spaceCommentVO.getSpaceId());
			ptmt.setString(2, spaceCommentVO.getMemberId());
			ptmt.setString(3, spaceCommentVO.getSpaceCommentContent());
			ptmt.setDouble(4, spaceCommentVO.getSpaceCommentLevel());
			ptmt.setDate(5, spaceCommentVO.getSpaceCommentDate());
			ptmt.setString(6, spaceCommentVO.getSpaceCommentId());

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
	public SpaceCommentVO selectOne(String spaceCommentId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		SpaceCommentVO spaceCommentVO = new SpaceCommentVO();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, spaceCommentId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceCommentVO.setSpaceCommentId(rs.getString("SPACE_COMMENT_ID"));
				spaceCommentVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceCommentVO.setMemberId(rs.getString("MEMBER_ID"));
				spaceCommentVO.setSpaceCommentContent(rs.getString("SPACE_COMMENT_CONTENT"));
				spaceCommentVO.setSpaceCommentLevel(rs.getDouble("SPACE_COMMENT_LEVEL"));
				spaceCommentVO.setSpaceCommentDate(rs.getDate("SPACE_COMMENT_DATE"));
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
		return spaceCommentVO;
	}

	@Override
	public List<SpaceCommentVO> getAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		SpaceCommentVO spaceCommentVO = null;
		List<SpaceCommentVO> list = new ArrayList<SpaceCommentVO>();;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceCommentVO = new SpaceCommentVO();
				spaceCommentVO.setSpaceCommentId(rs.getString("SPACE_COMMENT_ID"));
				spaceCommentVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceCommentVO.setMemberId(rs.getString("MEMBER_ID"));
				spaceCommentVO.setSpaceCommentContent(rs.getString("SPACE_COMMENT_CONTENT"));
				spaceCommentVO.setSpaceCommentLevel(rs.getDouble("SPACE_COMMENT_LEVEL"));
				spaceCommentVO.setSpaceCommentDate(rs.getDate("SPACE_COMMENT_DATE"));
				
				list.add(spaceCommentVO);
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
