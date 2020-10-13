package com.memberComment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberCommentDAOB implements MemberCommentDAOInterfaceB{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G2";
	String passwd = "TEA101G2";

	private static final String INSERT_STMT = 
	    "INSERT INTO MEMBER_COMMENT VALUES ('MCOMMENT' || lpad(MEMBER_COMMENT_ID_SEQ.NEXTVAL, 5, '0'),?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM MEMBER_COMMENT order by MEMBER_COMMENT_ID";
	private static final String SELECT_ONE_STMT = 
		"SELECT * FROM MEMBER_COMMENT where MEMBER_COMMENT_ID = ?";
	private static final String DELETE = 
		"DELETE FROM MEMBER_COMMENT where MEMBER_COMMENT_ID = ?";
	private static final String UPDATE = 
		"UPDATE MEMBER_COMMENT set MEMBER_A_ID=?,MEMBER_B_ID=?,MEMBER_COMMENT_CONTENT=?,MEMBER_COMMENT_LEVEL=?,MEMBER_COMMENT_DATE=?,MEMBER_COMMENT_STATUS=?,MEMBER_COMMENT_STATUS_EMP=?,MEMBER_COMMENT_STATUS_COMM=? where MEMBER_COMMENT_ID = ?";


	@Override
	public void insert(MemberCommentVO memberCommVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, memberCommVO.getMemberAId());
			ptmt.setString(2, memberCommVO.getMemberBId());
			ptmt.setString(3, memberCommVO.getMemberCommentContent());
			ptmt.setDouble(4, memberCommVO.getMemberCommentLevel());
			ptmt.setDate(5, memberCommVO.getMemberCommentDate());
			ptmt.setString(6, memberCommVO.getMemberCommStatus());
			ptmt.setString(7, memberCommVO.getMemberCommStatusEmp());
			ptmt.setString(8, memberCommVO.getMemberCommStatusComm());

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
	public void delete(String memberCommId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, memberCommId);
			
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
	public void update(MemberCommentVO memberCommVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, memberCommVO.getMemberAId());
			ptmt.setString(2, memberCommVO.getMemberBId());
			ptmt.setString(3, memberCommVO.getMemberCommentContent());
			ptmt.setDouble(4, memberCommVO.getMemberCommentLevel());
			ptmt.setDate(5, memberCommVO.getMemberCommentDate());
			ptmt.setString(6, memberCommVO.getMemberCommStatus());
			ptmt.setString(7, memberCommVO.getMemberCommStatusEmp());
			ptmt.setString(8, memberCommVO.getMemberCommStatusComm());
			ptmt.setString(9, memberCommVO.getMemberCommentId());
			
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
	public MemberCommentVO selectOne(String memberCommId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		MemberCommentVO memberCommVO = new MemberCommentVO();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, memberCommId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				memberCommVO.setMemberCommentId(rs.getString("MEMBER_COMMENT_ID"));
				memberCommVO.setMemberAId(rs.getString("MEMBER_A_ID"));
				memberCommVO.setMemberBId(rs.getString("MEMBER_B_ID"));
				memberCommVO.setMemberCommentContent(rs.getString("MEMBER_COMMENT_CONTENT"));
				memberCommVO.setMemberCommentLevel(rs.getDouble("MEMBER_COMMENT_LEVEL"));
				memberCommVO.setMemberCommentDate(rs.getDate("MEMBER_COMMENT_DATE"));
				memberCommVO.setMemberCommStatus(rs.getString("MEMBER_COMMENT_STATUS"));
				memberCommVO.setMemberCommStatusEmp(rs.getString("MEMBER_COMMENT_STATUS_EMP"));
				memberCommVO.setMemberCommStatusComm(rs.getString("MEMBER_COMMENT_STATUS_COMM"));
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
		return memberCommVO;
	}

	@Override
	public List<MemberCommentVO> selectAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		MemberCommentVO memberCommVO = null;
		List<MemberCommentVO> list = new ArrayList<MemberCommentVO>();;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				memberCommVO = new MemberCommentVO();
				memberCommVO.setMemberCommentId(rs.getString("MEMBER_COMMENT_ID"));
				memberCommVO.setMemberAId(rs.getString("MEMBER_A_ID"));
				memberCommVO.setMemberBId(rs.getString("MEMBER_B_ID"));
				memberCommVO.setMemberCommentContent(rs.getString("MEMBER_COMMENT_CONTENT"));
				memberCommVO.setMemberCommentLevel(rs.getDouble("MEMBER_COMMENT_LEVEL"));
				memberCommVO.setMemberCommentDate(rs.getDate("MEMBER_COMMENT_DATE"));
				memberCommVO.setMemberCommStatus(rs.getString("MEMBER_COMMENT_STATUS"));
				memberCommVO.setMemberCommStatusEmp(rs.getString("MEMBER_COMMENT_STATUS_EMP"));
				memberCommVO.setMemberCommStatusComm(rs.getString("MEMBER_COMMENT_STATUS_COMM"));
				list.add(memberCommVO);
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
