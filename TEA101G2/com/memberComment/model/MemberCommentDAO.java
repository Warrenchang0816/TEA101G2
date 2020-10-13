package com.memberComment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberCommentDAO implements MemberCommentDAO_interface {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "TEA101G2";
	private static final String PASSWORD = "TEA101G2";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT = "INSERT INTO MEMBER_COMMENT VALUES ('MCOMMENT' || lpad(MEMBER_COMMENT_ID_SEQ.NEXTVAL, 5, '0'),?,?,?,?,?)";
	private static final String UPDATE = "UPDATE MEMBER_COMMENT SET MEMBER_A_ID=?,MEMBER_B_ID=?,MEMBER_COMMENT_CONTENT=?,MEMBER_COMMENT_LEVEL=?,MEMBER_COMMENT_DATE=? WHERE MEMBER_COMMENT_ID = ?";
	private static final String DELETE = "DELETE FROM MEMBER_COMMENT WHERE MEMBER_COMMENT_ID = ?";
	private static final String GET_ONE = "SELECT * FROM MEMBER_COMMENT WHERE MEMBER_COMMENT_ID = ?";
	private static final String GET_ALL = "SELECT * FROM MEMBER_COMMENT";
	private static final String GET_ALL_ID = "SELECT * FROM MEMBER_COMMENT WHERE MEMBER_A_ID = ?";

	@Override
	public void insert(MemberCommentVO memberCommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, memberCommentVO.getMemberAId());
			pstmt.setString(2, memberCommentVO.getMemberBId());
			pstmt.setString(3, memberCommentVO.getMemberCommentContent());
			pstmt.setDouble(4, memberCommentVO.getMemberCommentLevel());
			pstmt.setDate(5, memberCommentVO.getMemberCommentDate());
			pstmt.executeUpdate();
			System.out.println("comment insert success");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberCommentVO.getMemberAId());
			pstmt.setString(2, memberCommentVO.getMemberBId());
			pstmt.setString(3, memberCommentVO.getMemberCommentContent());
			pstmt.setDouble(4, memberCommentVO.getMemberCommentLevel());
			pstmt.setDate(5, memberCommentVO.getMemberCommentDate());
			pstmt.setString(6, memberCommentVO.getMemberCommentId());
			pstmt.executeUpdate();
			System.out.println("comment update success");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memberCommentId);
			pstmt.executeUpdate();
			System.out.println("comment delete success");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
				list.add(memberCommentVO);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
				list.add(memberCommentVO);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
}
