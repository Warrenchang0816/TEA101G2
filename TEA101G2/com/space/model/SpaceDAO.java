package com.space.model;

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

public class SpaceDAO implements SpaceDAO_interface {

	// 用DataSource連線
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
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G2";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO SPACE VALUES ('SPACE' || lpad(SPACE_ID_SEQ.NEXTVAL, 5, '0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_STMT = "SELECT * FROM SPACE order by SPACE_ID";
	private static final String SELECT_ONE_STMT = "SELECT * FROM SPACE where SPACE_ID = ?";
	private static final String SELECT_ALL_BY_MEMBERID = "SELECT * FROM SPACE where MEMBER_ID = ?";
	private static final String DELETE = "DELETE FROM SPACE where SPACE_ID = ?";
	private static final String UPDATE = "UPDATE SPACE set SPACE_ADDRESS=?,SPACE_NAME=?,SPACE_TEXT=?,SPACE_TYPE=?,SPACE_EQUMENT=?,SPACE_CONTAIN=?,SPACE_RULE=?,SPACE_REFUND=?,SPACE_ONSALE_DATE=?,SPACE_OFFSALE_DATE=? where SPACE_ID=?";
	// 上架申請修改space_status
	private static final String UPDATE_STATUS = "UPDATE SPACE set SPACE_STATUS=? where SPACE_ID=?";
 

	@Override
	public void insert(SpaceVO spaceVO) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(INSERT_STMT);

			ptmt.setString(1, spaceVO.getMemberId());
			ptmt.setString(2, spaceVO.getEmpId());
			ptmt.setString(3, spaceVO.getSpaceAddress());
			ptmt.setDouble(4, spaceVO.getSpaceLng());
			ptmt.setDouble(5, spaceVO.getSpaceLat());
			ptmt.setString(6, spaceVO.getSpaceName());
			ptmt.setString(7, spaceVO.getSpaceText());
			ptmt.setString(8, spaceVO.getSpaceType());
			ptmt.setString(9, spaceVO.getSpaceEquipment());
			ptmt.setString(10, spaceVO.getSpaceContain());
			ptmt.setString(11, spaceVO.getSpaceRule());
			ptmt.setString(12, spaceVO.getSpaceRefund());
			ptmt.setString(13, spaceVO.getSpaceStatus());
			ptmt.setDate(14, spaceVO.getSpaceSignupDate());
			ptmt.setDate(15, spaceVO.getSpaceOnsaleDate());
			ptmt.setDate(16, spaceVO.getSpaceOffsaleDate());
			ptmt.setString(17, "");
			ptmt.setString(18, "");

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
	public void delete(String spaceId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(DELETE);

			ptmt.setString(1, spaceId);

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
	public void update(SpaceVO spaceVO) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(UPDATE);

			ptmt.setString(1, spaceVO.getSpaceAddress());
			ptmt.setString(2, spaceVO.getSpaceName());
			ptmt.setString(3, spaceVO.getSpaceText());
			ptmt.setString(4, spaceVO.getSpaceType());
			ptmt.setString(5, spaceVO.getSpaceEquipment());
			ptmt.setString(6, spaceVO.getSpaceContain());
			ptmt.setString(7, spaceVO.getSpaceRule());
			ptmt.setString(8, spaceVO.getSpaceRefund());
			ptmt.setDate(9, spaceVO.getSpaceOnsaleDate());
			ptmt.setDate(10, spaceVO.getSpaceOffsaleDate());
			ptmt.setString(11, spaceVO.getSpaceId());

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
	public void updateSpaceStatus(SpaceVO spaceVO) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(UPDATE_STATUS);

			ptmt.setString(1, spaceVO.getSpaceStatus());
			ptmt.setString(2, spaceVO.getSpaceId());

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
	public SpaceVO selectOne(String spaceId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		SpaceVO spaceVO = new SpaceVO();

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ONE_STMT);

			ptmt.setString(1, spaceId);

			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceVO.setMemberId(rs.getString("MEMBER_ID"));
				spaceVO.setEmpId(rs.getString("EMP_ID"));
				spaceVO.setSpaceAddress(rs.getString("SPACE_ADDRESS"));
				spaceVO.setSpaceLng(rs.getDouble("SPACE_LNG"));
				spaceVO.setSpaceLat(rs.getDouble("SPACE_LAT"));
				spaceVO.setSpaceName(rs.getString("SPACE_NAME"));
				spaceVO.setSpaceText(rs.getString("SPACE_TEXT"));
				spaceVO.setSpaceType(rs.getString("SPACE_TYPE"));
				spaceVO.setSpaceEquipment(rs.getString("SPACE_EQUMENT"));
				spaceVO.setSpaceContain(rs.getString("SPACE_CONTAIN"));
				spaceVO.setSpaceRule(rs.getString("SPACE_RULE"));
				spaceVO.setSpaceRefund(rs.getString("SPACE_REFUND"));
				spaceVO.setSpaceStatus(rs.getString("SPACE_STATUS"));
				spaceVO.setSpaceSignupDate(rs.getDate("SPACE_SIGNUP_DATE"));
				spaceVO.setSpaceOnsaleDate(rs.getDate("SPACE_ONSALE_DATE"));
				spaceVO.setSpaceOffsaleDate(rs.getDate("SPACE_OFFSALE_DATE"));
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
		return spaceVO;
	}

	@Override
	public List<SpaceVO> getAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		SpaceVO spaceVO = null;
		List<SpaceVO> list = new ArrayList<SpaceVO>();
		;

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ALL_STMT);

			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceVO = new SpaceVO();
				spaceVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceVO.setMemberId(rs.getString("MEMBER_ID"));
				spaceVO.setEmpId(rs.getString("EMP_ID"));
				spaceVO.setSpaceAddress(rs.getString("SPACE_ADDRESS"));
				spaceVO.setSpaceLng(rs.getDouble("SPACE_LNG"));
				spaceVO.setSpaceLat(rs.getDouble("SPACE_LAT"));
				spaceVO.setSpaceName(rs.getString("SPACE_NAME"));
				spaceVO.setSpaceText(rs.getString("SPACE_TEXT"));
				spaceVO.setSpaceType(rs.getString("SPACE_TYPE"));
				spaceVO.setSpaceEquipment(rs.getString("SPACE_EQUMENT"));
				spaceVO.setSpaceContain(rs.getString("SPACE_CONTAIN"));
				spaceVO.setSpaceRule(rs.getString("SPACE_RULE"));
				spaceVO.setSpaceRefund(rs.getString("SPACE_REFUND"));
				spaceVO.setSpaceStatus(rs.getString("SPACE_STATUS"));
				spaceVO.setSpaceSignupDate(rs.getDate("SPACE_SIGNUP_DATE"));
				spaceVO.setSpaceOnsaleDate(rs.getDate("SPACE_ONSALE_DATE"));
				spaceVO.setSpaceOffsaleDate(rs.getDate("SPACE_OFFSALE_DATE"));

				list.add(spaceVO);
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
	public List<SpaceVO> searchSpace(String subQuery, String spaceAddress, String spaceType, String spaceContain) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		SpaceVO spaceVO = null;
		List<SpaceVO> list = new ArrayList<SpaceVO>();

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(subQuery);

			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceVO = new SpaceVO();
				spaceVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceVO.setMemberId(rs.getString("MEMBER_ID"));
				spaceVO.setEmpId(rs.getString("EMP_ID"));
				spaceVO.setSpaceAddress(rs.getString("SPACE_ADDRESS"));
				spaceVO.setSpaceLng(rs.getDouble("SPACE_LNG"));
				spaceVO.setSpaceLat(rs.getDouble("SPACE_LAT"));
				spaceVO.setSpaceName(rs.getString("SPACE_NAME"));
				spaceVO.setSpaceText(rs.getString("SPACE_TEXT"));
				spaceVO.setSpaceType(rs.getString("SPACE_TYPE"));
				spaceVO.setSpaceEquipment(rs.getString("SPACE_EQUMENT"));
				spaceVO.setSpaceContain(rs.getString("SPACE_CONTAIN"));
				spaceVO.setSpaceRule(rs.getString("SPACE_RULE"));
				spaceVO.setSpaceRefund(rs.getString("SPACE_REFUND"));
				spaceVO.setSpaceStatus(rs.getString("SPACE_STATUS"));
				spaceVO.setSpaceSignupDate(rs.getDate("SPACE_SIGNUP_DATE"));
				spaceVO.setSpaceOnsaleDate(rs.getDate("SPACE_ONSALE_DATE"));
				spaceVO.setSpaceOffsaleDate(rs.getDate("SPACE_OFFSALE_DATE"));
				list.add(spaceVO);
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
	public List<SpaceVO> selectAllByMemId(String memberId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		List<SpaceVO> spacelist = new ArrayList<SpaceVO>();
		SpaceVO spaceVO = null;
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ALL_BY_MEMBERID);

			ptmt.setString(1, memberId);

			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceVO = new SpaceVO();
				spaceVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceVO.setMemberId(rs.getString("MEMBER_ID"));
				spaceVO.setEmpId(rs.getString("EMP_ID"));
				spaceVO.setSpaceAddress(rs.getString("SPACE_ADDRESS"));
				spaceVO.setSpaceLng(rs.getDouble("SPACE_LNG"));
				spaceVO.setSpaceLat(rs.getDouble("SPACE_LAT"));
				spaceVO.setSpaceName(rs.getString("SPACE_NAME"));
				spaceVO.setSpaceText(rs.getString("SPACE_TEXT"));
				spaceVO.setSpaceType(rs.getString("SPACE_TYPE"));
				spaceVO.setSpaceEquipment(rs.getString("SPACE_EQUMENT"));
				spaceVO.setSpaceContain(rs.getString("SPACE_CONTAIN"));
				spaceVO.setSpaceRule(rs.getString("SPACE_RULE"));
				spaceVO.setSpaceRefund(rs.getString("SPACE_REFUND"));
				spaceVO.setSpaceStatus(rs.getString("SPACE_STATUS"));
				spaceVO.setSpaceSignupDate(rs.getDate("SPACE_SIGNUP_DATE"));
				spaceVO.setSpaceOnsaleDate(rs.getDate("SPACE_ONSALE_DATE"));
				spaceVO.setSpaceOffsaleDate(rs.getDate("SPACE_OFFSALE_DATE"));
				spacelist.add(spaceVO);
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
		return spacelist;
	}
}
