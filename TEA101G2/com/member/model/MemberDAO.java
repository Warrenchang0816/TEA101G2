package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO implements MemberDAO_interface {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "TEA101G2";
	private static final String PASSWORD = "TEA101G2";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT = "INSERT INTO MEMBER ( MEMBER_ID, MEMBER_ACCOUNT, MEMBER_PASSWORD, MEMBER_NAME, MEMBER_NICKNAME, MEMBER_EMAIL, MEMBER_PHOTO, MEMBER_PHONE, MEMBER_ADDRESS, MEMBER_BIRTH, MEMBER_SEX, MEMBER_COUNTRY, MEMBER_SIGNUP_DATE, MEMBER_AUTH, MEMBER_STATUS, MEMBER_ONLINE ) VALUES ( 'MEM' || lpad(MEMBER_ID_SEQ.NEXTVAL, 5, '0' ),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL = "SELECT * FROM MEMBER";
	private static final String GET_ONE = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
	private static final String GET_ONE_BY_ACCOUNT = "SELECT * FROM MEMBER WHERE MEMBER_ACCOUNT = ?";
	private static final String DELETE = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";
	private static final String UPDATE = "UPDATE MEMBER SET MEMBER_ACCOUNT= ?, MEMBER_PASSWORD=?, MEMBER_NAME=?, MEMBER_NICKNAME=?, "
			+ "MEMBER_EMAIL=?, MEMBER_PHOTO=?, MEMBER_PHONE=?, MEMBER_ADDRESS=?, MEMBER_BIRTH=?, MEMBER_SEX=?, MEMBER_COUNTRY=?, "
			+ "MEMBER_SIGNUP_DATE=?, MEMBER_AUTH=?,MEMBER_STATUS=?,MEMBER_ONLINE=? WHERE MEMBER_ID = ?";
	private static final String USER_LOGIN = "SELECT * FROM MEMBER WHERE MEMBER_ACCOUNT = ? and MEMBER_PASSWORD=?";
	
	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, memberVO.getMemberAccount());
			pstmt.setString(2, memberVO.getMemberPassword());
			pstmt.setString(3, memberVO.getMemberName());
			pstmt.setString(4, memberVO.getMemberNickName());
			pstmt.setString(5, memberVO.getMemberEmail());
			pstmt.setBytes(6, memberVO.getMemberPhoto());
			pstmt.setString(7, memberVO.getMemberPhone());
			pstmt.setString(8, memberVO.getMemberAddress());
			pstmt.setDate(9, memberVO.getMemberBirth());
			pstmt.setString(10, memberVO.getMemberSex());
			pstmt.setString(11, memberVO.getMemberCountry());
			pstmt.setDate(12, memberVO.getMemberSignupDate());
			pstmt.setInt(13, memberVO.getMemberAuth());
			pstmt.setString(14, memberVO.getMemberStatus());
			pstmt.setString(15, "N");
			pstmt.executeUpdate();
			System.out.println("success");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, memberVO.getMemberAccount());
			ptmt.setString(2, memberVO.getMemberPassword());
			ptmt.setString(3, memberVO.getMemberName());
			ptmt.setString(4, memberVO.getMemberNickName());
			ptmt.setString(5, memberVO.getMemberEmail());
			ptmt.setBytes(6, memberVO.getMemberPhoto());
			ptmt.setString(7, memberVO.getMemberPhone());
			ptmt.setString(8, memberVO.getMemberAddress());
			ptmt.setDate(9, memberVO.getMemberBirth());
			ptmt.setString(10, memberVO.getMemberSex());
			ptmt.setString(11, memberVO.getMemberCountry());
			ptmt.setDate(12, memberVO.getMemberSignupDate());
			ptmt.setInt(13, memberVO.getMemberAuth());
			ptmt.setString(14, memberVO.getMemberStatus());
			ptmt.setString(15, memberVO.getMemberOnline());
			ptmt.setString(16, memberVO.getMemberId());

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
	public void delete(String memberId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, memberId);
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
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
	public MemberVO findByPrimaryKey(String memberId) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberId(memberId);
				memberVO.setMemberAccount(rs.getString("MEMBER_ACCOUNT"));
				memberVO.setMemberPassword(rs.getNString("MEMBER_PASSWORD"));
				memberVO.setMemberName(rs.getString("MEMBER_NAME"));
				memberVO.setMemberNickName(rs.getString("MEMBER_NICKNAME"));
				memberVO.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				memberVO.setMemberPhoto(rs.getBytes("MEMBER_PHOTO"));
				memberVO.setMemberPhone(rs.getString("MEMBER_PHONE"));
				memberVO.setMemberAddress(rs.getString("MEMBER_ADDRESS"));
				memberVO.setMemberBirth(rs.getDate("MEMBER_BIRTH"));
				memberVO.setMemberSex(rs.getString("MEMBER_SEX"));
				memberVO.setMemberCountry(rs.getString("MEMBER_COUNTRY"));
				memberVO.setMemberSignupDate(rs.getDate("MEMBER_SIGNUP_DATE"));
				memberVO.setMemberAuth(rs.getInt("MEMBER_AUTH"));
				memberVO.setMemberStatus(rs.getString("MEMBER_STATUS"));
				memberVO.setMemberOnline(rs.getString("MEMBER_ONLINE"));
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

		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		MemberVO memberVO = null;
		List<MemberVO> list = new ArrayList<MemberVO>();;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			ptmt = con.prepareStatement(GET_ALL);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberId(rs.getString("MEMBER_ID"));
				memberVO.setMemberAccount(rs.getString("MEMBER_ACCOUNT"));
				memberVO.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
				memberVO.setMemberName(rs.getString("MEMBER_NAME"));
				memberVO.setMemberNickName(rs.getString("MEMBER_NICKNAME"));
				memberVO.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				memberVO.setMemberPhoto(rs.getBytes("MEMBER_PHOTO"));
				memberVO.setMemberPhone(rs.getString("MEMBER_PHONE"));
				memberVO.setMemberAddress(rs.getString("MEMBER_ADDRESS"));
				memberVO.setMemberBirth(rs.getDate("MEMBER_BIRTH"));
				memberVO.setMemberSex(rs.getString("MEMBER_SEX"));
				memberVO.setMemberCountry(rs.getString("MEMBER_COUNTRY"));
				memberVO.setMemberSignupDate(rs.getDate("MEMBER_SIGNUP_DATE"));
				memberVO.setMemberAuth(rs.getInt("MEMBER_AUTH"));
				memberVO.setMemberStatus(rs.getString("MEMBER_STATUS"));
				memberVO.setMemberOnline(rs.getString("MEMBER_ONLINE"));
				
				list.add(memberVO);
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

	@Override
	public MemberVO LoginAuthenticate(String account, String password) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(USER_LOGIN);

			pstmt.setString(1, account);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberId(rs.getString("MEMBER_ID"));
				memberVO.setMemberAccount(rs.getString("MEMBER_ACCOUNT"));
				memberVO.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
				memberVO.setMemberName(rs.getString("MEMBER_NAME"));
				memberVO.setMemberNickName(rs.getString("MEMBER_NICKNAME"));
				memberVO.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				memberVO.setMemberPhoto(rs.getBytes("MEMBER_PHOTO"));
				memberVO.setMemberPhone(rs.getString("MEMBER_PHONE"));
				memberVO.setMemberAddress(rs.getString("MEMBER_ADDRESS"));
				memberVO.setMemberBirth(rs.getDate("MEMBER_BIRTH"));
				memberVO.setMemberSex(rs.getString("MEMBER_SEX"));
				memberVO.setMemberCountry(rs.getString("MEMBER_COUNTRY"));
				memberVO.setMemberSignupDate(rs.getDate("MEMBER_SIGNUP_DATE"));
				memberVO.setMemberAuth(rs.getInt("MEMBER_AUTH"));
				memberVO.setMemberStatus(rs.getString("MEMBER_STATUS"));
				memberVO.setMemberOnline(rs.getString("MEMBER_ONLINE"));
			} else {
				System.out.println("QQ fail");
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
		return memberVO;
	}

	@Override
	public MemberVO findByAccount(String Account) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_BY_ACCOUNT);

			pstmt.setString(1, Account);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberId(rs.getString("MEMBER_ID"));
				memberVO.setMemberAccount(Account);
				memberVO.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
				memberVO.setMemberName(rs.getString("MEMBER_NAME"));
				memberVO.setMemberNickName(rs.getString("MEMBER_NICKNAME"));
				memberVO.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				memberVO.setMemberPhoto(rs.getBytes("MEMBER_PHOTO"));
				memberVO.setMemberPhone(rs.getString("MEMBER_PHONE"));
				memberVO.setMemberAddress(rs.getString("MEMBER_ADDRESS"));
				memberVO.setMemberBirth(rs.getDate("MEMBER_BIRTH"));
				memberVO.setMemberSex(rs.getString("MEMBER_SEX"));
				memberVO.setMemberCountry(rs.getString("MEMBER_COUNTRY"));
				memberVO.setMemberSignupDate(rs.getDate("MEMBER_SIGNUP_DATE"));
				memberVO.setMemberAuth(rs.getInt("MEMBER_AUTH"));
				memberVO.setMemberStatus(rs.getString("MEMBER_STATUS"));
				memberVO.setMemberOnline(rs.getString("MEMBER_ONLINE"));
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

		return memberVO;
	}
}
