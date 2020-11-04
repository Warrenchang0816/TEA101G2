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

public class SpaceCommentDAO implements SpaceCommentDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA101G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 用JDBC連線
//	String driver = "oracle.jdbc.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "TEA101G2";
//	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO SPACE_COMMENT VALUES ('SCOMMENT' || lpad(SPACE_COMMENT_ID_SEQ.NEXTVAL, 5, '0'),?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_STMT = "SELECT * FROM SPACE_COMMENT order by SPACE_COMMENT_ID";
	// 根據SpaceId取全部SpaceComment
	private static final String SELECT_ALL_STMT_BY_SPACE = "SELECT * FROM SPACE_COMMENT where SPACE_ID = ? order by SPACE_COMMENT_DATE DESC";
	private static final String SELECT_ONE_STMT = "SELECT * FROM SPACE_COMMENT where SPACE_COMMENT_ID = ?";
	private static final String DELETE = "DELETE FROM SPACE_COMMENT where SPACE_COMMENT_ID = ?";
	private static final String UPDATE = "UPDATE SPACE_COMMENT set SPACE_COMMENT_CONTENT=?,SPACE_COMMENT_LEVEL=? where SPACE_COMMENT_ID=?";

	private static final String GET_SPACE_RATING = "SELECT ROUND(AVG(SPACE_COMMENT_LEVEL),1) AS RATING FROM SPACE_COMMENT WHERE SPACE_ID=?";

	private static final String GET_SPACE_COMMENT_STATUS = "SELECT OM.MEMBER_ID, OD.SPACE_DETAIL_ID, SD.SPACE_ID FROM ORDER_MASTER OM"
			+ " JOIN ORDER_DETAIL OD ON(OD.ORDER_MASTER_ID = OM.ORDER_MASTER_ID AND OM.MEMBER_ID =?)"
			+ " JOIN SPACE_DETAIL SD ON(SD.SPACE_DETAIL_ID = OD.SPACE_DETAIL_ID AND SD.SPACE_ID =?)";

	private static final String GET_SPACE_COMMENT_COUNT = "SELECT COUNT(SPACE_ID) AS COMMENT_COUNT FROM SPACE_COMMENT WHERE SPACE_ID=?";

	@Override
	public void insert(SpaceCommentVO spaceCommentVO) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(INSERT_STMT);

			ptmt.setString(1, spaceCommentVO.getSpaceId());
			ptmt.setString(2, spaceCommentVO.getMemberId());
			ptmt.setString(3, spaceCommentVO.getSpaceCommentContent());
			ptmt.setDouble(4, spaceCommentVO.getSpaceCommentLevel());
			ptmt.setDate(5, spaceCommentVO.getSpaceCommentDate());
			ptmt.setString(6, spaceCommentVO.getSpaceCommStatus());
			ptmt.setString(7, spaceCommentVO.getSpaceCommStatusEmp());
			ptmt.setString(8, spaceCommentVO.getSpaceCommStatusComm());

			ptmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	public void delete(String spaceCommentId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(DELETE);

			ptmt.setString(1, spaceCommentId);

			ptmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
			con = ds.getConnection();
			ptmt = con.prepareStatement(UPDATE);

			ptmt.setString(1, spaceCommentVO.getSpaceCommentContent());
			ptmt.setDouble(2, spaceCommentVO.getSpaceCommentLevel());
			ptmt.setString(3, spaceCommentVO.getSpaceCommentId());

			ptmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
			con = ds.getConnection();
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
				spaceCommentVO.setSpaceCommStatus(rs.getString("SPACE_COMMENT_STATUS"));
				spaceCommentVO.setSpaceCommStatusEmp(rs.getString("SPACE_COMMENT_STATUS_EMP"));
				spaceCommentVO.setSpaceCommStatusComm(rs.getString("SPACE_COMMENT_STATUS_COMM"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
			}
			if (con != null) {
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
		List<SpaceCommentVO> list = new ArrayList<SpaceCommentVO>();
		;

		try {
			con = ds.getConnection();
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
				spaceCommentVO.setSpaceCommStatus(rs.getString("SPACE_COMMENT_STATUS"));
				spaceCommentVO.setSpaceCommStatusEmp(rs.getString("SPACE_COMMENT_STATUS_EMP"));
				spaceCommentVO.setSpaceCommStatusComm(rs.getString("SPACE_COMMENT_STATUS_COMM"));

				list.add(spaceCommentVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	// 根據SpaceId取全部SpaceComment
	public List<SpaceCommentVO> getAllCommBySpace(String spaceId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		SpaceCommentVO spaceCommentVO = null;
		List<SpaceCommentVO> list = new ArrayList<SpaceCommentVO>();
		;

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ALL_STMT_BY_SPACE);

			ptmt.setString(1, spaceId);

			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceCommentVO = new SpaceCommentVO();
				spaceCommentVO.setSpaceCommentId(rs.getString("SPACE_COMMENT_ID"));
				spaceCommentVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceCommentVO.setMemberId(rs.getString("MEMBER_ID"));
				spaceCommentVO.setSpaceCommentContent(rs.getString("SPACE_COMMENT_CONTENT"));
				spaceCommentVO.setSpaceCommentLevel(rs.getDouble("SPACE_COMMENT_LEVEL"));
				spaceCommentVO.setSpaceCommentDate(rs.getDate("SPACE_COMMENT_DATE"));
				spaceCommentVO.setSpaceCommStatus(rs.getString("SPACE_COMMENT_STATUS"));
				spaceCommentVO.setSpaceCommStatusEmp(rs.getString("SPACE_COMMENT_STATUS_EMP"));
				spaceCommentVO.setSpaceCommStatusComm(rs.getString("SPACE_COMMENT_STATUS_COMM"));

				list.add(spaceCommentVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	public Double getSpaceRating(String spaceId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Double rating = null;

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(GET_SPACE_RATING);
			ptmt.setString(1, spaceId);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				rating = (rs.getDouble("RATING"));
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
			if (ptmt != null) {
				try {
					ptmt.close();
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
		return rating;
	}

	@Override
	public List<String> getSpaceCommentStatus(String memberId, String spaceId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(GET_SPACE_COMMENT_STATUS);

			ptmt.setString(1, memberId);
			ptmt.setString(2, spaceId);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				String memberStatus = rs.getString("MEMBER_ID");
				String spaceStatus = rs.getString("SPACE_ID");
				System.out.println(memberStatus);
				System.out.println(spaceStatus);
				System.out.println("---------------------------------");
				list.add(memberStatus);
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
			if (ptmt != null) {
				try {
					ptmt.close();
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
	public int getSpaceCommentCount(String spaceId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(GET_SPACE_COMMENT_COUNT);
			ptmt.setString(1, spaceId);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("COMMENT_COUNT");
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
			if (ptmt != null) {
				try {
					ptmt.close();
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
		return count;
	}

}
