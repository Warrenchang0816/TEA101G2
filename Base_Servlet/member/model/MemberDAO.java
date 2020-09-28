package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class MemberDAO implements MemberDAOInterface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G2";
	String passwd = "TEA101G2";
	

	private static final String INSERT_STMT = 
	    "INSERT INTO MEMBER VALUES ('MEM' || lpad(MEMBER_ID_SEQ.NEXTVAL, 5, '0' ),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM MEMBER order by MEMBER_ID";
	private static final String SELECT_ONE_STMT = 
		"SELECT * FROM MEMBER where MEMBER_ID = ?";
	private static final String DELETE = 
		"DELETE FROM MEMBER where MEMBER_ID = ?";
	private static final String UPDATE = 
		"UPDATE MEMBER set MEMBER_ACCOUNT=?,MEMBER_PASSWORD=?,MEMBER_NAME=?,MEMBER_NICKNAME=?,MEMBER_EMAIL=?,MEMBER_PHOTO=?,MEMBER_PHONE=?,MEMBER_ADDRESS=?,MEMBER_BIRTH=?,MEMBER_SEX=?,MEMBER_COUNTRY=?,MEMBER_SIGNUP_DATE=?,MEMBER_AUTH=?,MEMBER_STATUS=? where MEMBER_ID = ?";


	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, memberVO.getMemberAccount());
			ptmt.setString(2, memberVO.getMemberPassword());
			ptmt.setString(3, memberVO.getMemberName());
			ptmt.setString(4, memberVO.getMemberNickname());
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
	public void delete(String memberId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, memberId);
			
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
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, memberVO.getMemberAccount());
			ptmt.setString(2, memberVO.getMemberPassword());
			ptmt.setString(3, memberVO.getMemberName());
			ptmt.setString(4, memberVO.getMemberNickname());
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
			ptmt.setString(15, memberVO.getMemberId());

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
	public MemberVO selectOne(String memberId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		MemberVO memberVO = new MemberVO();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, memberId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				memberVO.setMemberId(rs.getString("MEMBER_ID"));
				memberVO.setMemberAccount(rs.getString("MEMBER_ACCOUNT"));
				memberVO.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
				memberVO.setMemberName(rs.getString("MEMBER_NAME"));
				memberVO.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
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
		return memberVO;
	}

	@Override
	public List<MemberVO> selectAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		MemberVO memberVO = null;
		List<MemberVO> list = new ArrayList<MemberVO>();;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberId(rs.getString("MEMBER_ID"));
				memberVO.setMemberAccount(rs.getString("MEMBER_ACCOUNT"));
				memberVO.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
				memberVO.setMemberName(rs.getString("MEMBER_NAME"));
				memberVO.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
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

}
