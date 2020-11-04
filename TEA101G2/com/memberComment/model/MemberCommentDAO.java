package com.memberComment.model;

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

public class MemberCommentDAO implements MemberCommentDAO_interface {
	private static final String INSERT = "INSERT INTO MEMBER_COMMENT VALUES ('MCOMMENT' || lpad(MEMBER_COMMENT_ID_SEQ.NEXTVAL, 5, '0'),?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE MEMBER_COMMENT set MEMBER_A_ID=?,MEMBER_B_ID=?,MEMBER_COMMENT_CONTENT=?,MEMBER_COMMENT_LEVEL=?,MEMBER_COMMENT_DATE=?,MEMBER_COMMENT_STATUS=?,MEMBER_COMMENT_STATUS_EMP=?,MEMBER_COMMENT_STATUS_COMM=? WHERE MEMBER_COMMENT_ID = ?";
	private static final String DELETE = "DELETE FROM MEMBER_COMMENT WHERE MEMBER_COMMENT_ID = ?";
	private static final String GET_ONE = "SELECT * FROM MEMBER_COMMENT WHERE MEMBER_COMMENT_ID = ?";
	private static final String GET_ALL = "SELECT * FROM MEMBER_COMMENT ORDER BY MEMBER_COMMENT_DATE DESC";
	private static final String GET_ALL_ID = "SELECT * FROM MEMBER_COMMENT WHERE MEMBER_A_ID = ? ORDER BY MEMBER_COMMENT_ID DESC";
	private static final String GET_MEMBER_RATING = "SELECT ROUND(AVG(MEMBER_COMMENT_LEVEL),1) AS RATING FROM MEMBER_COMMENT WHERE MEMBER_A_ID=?";

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(MemberCommentVO memberCommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, memberCommentVO.getMemberAId());
			pstmt.setString(2, memberCommentVO.getMemberBId());
			pstmt.setString(3, memberCommentVO.getMemberCommentContent());
			pstmt.setDouble(4, memberCommentVO.getMemberCommentLevel());
			pstmt.setDate(5, memberCommentVO.getMemberCommentDate());
			pstmt.setString(6, memberCommentVO.getMemberCommStatus());
			pstmt.setString(7, memberCommentVO.getMemberCommStatusEmp());
			pstmt.setString(8, memberCommentVO.getMemberCommStatusComm());

			pstmt.executeUpdate();
			System.out.println("comment insert success");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public void update(MemberCommentVO memberCommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberCommentVO.getMemberAId());
			pstmt.setString(2, memberCommentVO.getMemberBId());
			pstmt.setString(3, memberCommentVO.getMemberCommentContent());
			pstmt.setDouble(4, memberCommentVO.getMemberCommentLevel());
			pstmt.setDate(5, memberCommentVO.getMemberCommentDate());
			pstmt.setString(6, memberCommentVO.getMemberCommStatus());
			pstmt.setString(7, memberCommentVO.getMemberCommStatusEmp());
			pstmt.setString(8, memberCommentVO.getMemberCommStatusComm());
			pstmt.setString(9, memberCommentVO.getMemberCommentId());
			pstmt.executeUpdate();
			System.out.println("comment update success");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public void delete(String memberCommentId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memberCommentId);
			pstmt.executeUpdate();
			System.out.println("comment delete success");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public MemberCommentVO findByPrimaryKey(String memberCommentId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberCommentVO memberCommentVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, memberCommentId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberCommentVO = new MemberCommentVO();
				memberCommentVO.setMemberCommentId(rs.getString("MEMBER_COMMENT_ID"));
				memberCommentVO.setMemberAId(rs.getString("MEMBER_A_ID"));
				memberCommentVO.setMemberBId(rs.getString("MEMBER_B_ID"));
				memberCommentVO.setMemberCommentContent(rs.getString("MEMBER_COMMENT_CONTENT"));
				memberCommentVO.setMemberCommentLevel(rs.getDouble("MEMBER_COMMENT_LEVEL"));
				memberCommentVO.setMemberCommentDate(rs.getDate("MEMBER_COMMENT_DATE"));
				memberCommentVO.setMemberCommStatus(rs.getString("MEMBER_COMMENT_STATUS"));
				memberCommentVO.setMemberCommStatusEmp(rs.getString("MEMBER_COMMENT_STATUS_EMP"));
				memberCommentVO.setMemberCommStatusComm(rs.getString("MEMBER_COMMENT_STATUS_COMM"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
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
		return memberCommentVO;
	}

	@Override
	public List<MemberCommentVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberCommentVO memberCommentVO = null;

		List<MemberCommentVO> list = new ArrayList<MemberCommentVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberCommentVO = new MemberCommentVO();
				memberCommentVO.setMemberCommentId(rs.getString("MEMBER_COMMENT_ID"));
				memberCommentVO.setMemberAId(rs.getString("MEMBER_A_ID"));
				memberCommentVO.setMemberBId(rs.getString("MEMBER_B_ID"));
				memberCommentVO.setMemberCommentContent(rs.getString("MEMBER_COMMENT_CONTENT"));
				memberCommentVO.setMemberCommentLevel(rs.getDouble("MEMBER_COMMENT_LEVEL"));
				memberCommentVO.setMemberCommentDate(rs.getDate("MEMBER_COMMENT_DATE"));
				memberCommentVO.setMemberCommStatus(rs.getString("MEMBER_COMMENT_STATUS"));
				memberCommentVO.setMemberCommStatusEmp(rs.getString("MEMBER_COMMENT_STATUS_EMP"));
				memberCommentVO.setMemberCommStatusComm(rs.getString("MEMBER_COMMENT_STATUS_COMM"));
				list.add(memberCommentVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
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
		return list;
	}

	@Override
	public List<MemberCommentVO> getAllById(String memberCommentId) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberCommentVO memberCommentVO = null;
		List<MemberCommentVO> list = new ArrayList<MemberCommentVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ID);
			pstmt.setString(1, memberCommentId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberCommentVO = new MemberCommentVO();
				memberCommentVO.setMemberCommentId(rs.getString("MEMBER_COMMENT_ID"));
				memberCommentVO.setMemberAId(rs.getString("MEMBER_A_ID"));
				memberCommentVO.setMemberBId(rs.getString("MEMBER_B_ID"));
				memberCommentVO.setMemberCommentContent(rs.getString("MEMBER_COMMENT_CONTENT"));
				memberCommentVO.setMemberCommentLevel(rs.getDouble("MEMBER_COMMENT_LEVEL"));
				memberCommentVO.setMemberCommentDate(rs.getDate("MEMBER_COMMENT_DATE"));
				memberCommentVO.setMemberCommStatus(rs.getString("MEMBER_COMMENT_STATUS"));
				memberCommentVO.setMemberCommStatusEmp(rs.getString("MEMBER_COMMENT_STATUS_EMP"));
				memberCommentVO.setMemberCommStatusComm(rs.getString("MEMBER_COMMENT_STATUS_COMM"));
				list.add(memberCommentVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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
		return list;
	}

	@Override
	public Double getMemberRating(String memberId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Double rating = null;
		
		try {
			con = ds.getConnection();
			
//			SELECT ROUND(AVG(MEMBER_COMMENT_LEVEL),1) AS RATING FROM MEMBER_COMMENT WHERE MEMBER_A_ID=?
			pstmt = con.prepareStatement(GET_MEMBER_RATING);
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				rating = (rs.getDouble("RATING"));
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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
		return rating;
	}
}
