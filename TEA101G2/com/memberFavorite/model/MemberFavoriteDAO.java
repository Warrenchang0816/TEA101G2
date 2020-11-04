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

public class MemberFavoriteDAO implements MemberFavoriteDAO_interface {
	private static final String INSERT = "INSERT INTO MEMBER_FAVORITE VALUES ('FAVORITE' || lpad(MEMBER_FAVORITE_ID_SEQ.NEXTVAL, 5, '0' ),?,?)";
	private static final String UPDATE = "UPDATE MEMBER_FAVORITE SET MEMBER_ID=?,SPACE_ID=? WHERE MEMBER_FAVORITE_ID = ?";
	private static final String DELETE = "DELETE FROM MEMBER_FAVORITE WHERE MEMBER_FAVORITE_ID = ?";
	private static final String GET_ONE = "SELECT * FROM MEMBER_FAVORITE WHERE MEMBER_FAVORITE_ID = ?";
	private static final String GET_ALL = "SELECT * FROM MEMBER_FAVORITE";
	private static final String GET_ALL_ID = "SELECT * FROM MEMBER_FAVORITE WHERE MEMBER_ID = ?";
	private static final String GET_MEMBER_FAVORITE_STATUS = "SELECT * FROM MEMBER_FAVORITE WHERE MEMBER_ID=? AND SPACE_ID =?";

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
	public void insert(MemberFavoriteVO memberFavoriteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, memberFavoriteVO.getMemberId());
			pstmt.setString(2, memberFavoriteVO.getSpaceId());

			pstmt.executeUpdate();

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
	public void update(MemberFavoriteVO memberFavoriteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberFavoriteVO.getMemberId());
			pstmt.setString(2, memberFavoriteVO.getSpaceId());
			pstmt.setString(3, memberFavoriteVO.getMemberFavoriteId());

			pstmt.executeUpdate();

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
	public void delete(String memberFavoriteId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memberFavoriteId);

			pstmt.executeUpdate();

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
	public MemberFavoriteVO findByPrimaryKey(String memberFavoriteId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberFavoriteVO memberFavoriteVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, memberFavoriteId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberFavoriteVO = new MemberFavoriteVO();
				memberFavoriteVO.setMemberFavoriteId(rs.getString("MEMBER_FAVORITE_ID"));
				memberFavoriteVO.setMemberId(rs.getString("MEMBER_ID"));
				memberFavoriteVO.setSpaceId(rs.getString("SPACE_ID"));
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
		return memberFavoriteVO;
	}

	@Override
	public List<MemberFavoriteVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberFavoriteVO memberFavoriteVO = null;
		List<MemberFavoriteVO> list = new ArrayList<MemberFavoriteVO>();
		;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberFavoriteVO = new MemberFavoriteVO();
				memberFavoriteVO.setMemberFavoriteId(rs.getString("MEMBER_FAVORITE_ID"));
				memberFavoriteVO.setMemberId(rs.getString("MEMBER_ID"));
				memberFavoriteVO.setSpaceId(rs.getString("SPACE_ID"));
				list.add(memberFavoriteVO);
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
	public List<MemberFavoriteVO> getAllById(String memberId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberFavoriteVO memberFavoriteVO = null;
		List<MemberFavoriteVO> list = new ArrayList<MemberFavoriteVO>();
		;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ID);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberFavoriteVO = new MemberFavoriteVO();
				memberFavoriteVO.setMemberFavoriteId(rs.getString("MEMBER_FAVORITE_ID"));
				memberFavoriteVO.setMemberId(rs.getString("MEMBER_ID"));
				memberFavoriteVO.setSpaceId(rs.getString("SPACE_ID"));
				list.add(memberFavoriteVO);
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
	public MemberFavoriteVO getMemberFavoriteStatus(String memberId, String spaceId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberFavoriteVO memberFavoriteVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEMBER_FAVORITE_STATUS);

			pstmt.setString(1, memberId);
			pstmt.setString(2, spaceId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberFavoriteVO = new MemberFavoriteVO();
				memberFavoriteVO.setMemberFavoriteId(rs.getString("MEMBER_FAVORITE_ID"));
				memberFavoriteVO.setMemberId(rs.getString("MEMBER_ID"));
				memberFavoriteVO.setSpaceId(rs.getString("SPACE_ID"));
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
		return memberFavoriteVO;
	}
}
