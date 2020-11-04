package com.spaceDetail.model;

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


public class SpaceDetailDAO implements SpaceDetailDAO_interface {
	
	//用DataSource連線
			private static DataSource ds = null;
			static {
				try {
					Context ctx = new InitialContext();
					ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TEA101G2");
				}
				catch(NamingException e){
					e.printStackTrace();
				}
			}
	
	//用JDBC連線
//	String driver = "oracle.jdbc.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "TEA101G2SP";
//	String passwd = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO SPACE_DETAIL VALUES ('SD' || lpad(SPACE_DETAIL_ID_SEQ.NEXTVAL, 5, '0'),?,?,?,?,?)";
	private static final String SELECT_ALL_STMT =
			"SELECT * FROM SPACE_DETAIL order by SPACE_DETAIL_ID";
	//誠實業者必備最低價格顯示
	private static final String SELECT_ONE_lOWPRICE =
			"SELECT * FROM(SELECT * FROM SPACE_DETAIL WHERE SPACE_ID = ? ORDER BY SPACE_DETAIL_CHARGE)WHERE ROWNUM = 1";
	private static final String SELECT_ONE_STMT =
			"SELECT * FROM SPACE_DETAIL where SPACE_DETAIL_ID = ?";
	//根據spaceId滾出所有場地明細(按照時間排序)
	private static final String SELECT_ALL_SPACEID =
			"SELECT * FROM SPACE_DETAIL where SPACE_ID = ? ORDER BY SPACE_DETAIL_FREETIME_START";
	private static final String DELETE =
			"DELETE FROM SPACE_DETAIL where SPACE_DETAIL_ID = ?";
	private static final String UPDATE =
			"UPDATE SPACE_DETAIL set SPACE_ID=?,SPACE_DETAIL_FREEDATE=?,SPACE_DETAIL_FREETIME_START=?,SPACE_DETAIL_FREETIME_END=?,SPACE_DETAIL_CHARGE=? where SPACE_DETAIL_ID=?";
	//遠征第三站：用SpaceDetailId呼叫一個SpaceId
	private static final String SELECT_ONESPACE_BY_SDID =
			"SELECT SPACE_ID FROM(SELECT * FROM SPACE_DETAIL WHERE SPACE_DETAIL_ID = ?)WHERE ROWNUM = 1";
	
	@Override
	public SpaceDetailVO insert(SpaceDetailVO spaceDetailVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, spaceDetailVO.getSpaceId());
			ptmt.setDate(2, spaceDetailVO.getSpaceDetailFreeDate());
			ptmt.setTimestamp(3, spaceDetailVO.getSpaceDetailFreeTimeStart());
			ptmt.setTimestamp(4, spaceDetailVO.getSpaceDetailFreeTimeEnd());
			ptmt.setInt(5, spaceDetailVO.getSpaceDetailCharge());
				
			ptmt.executeUpdate();
			
			} catch (Exception e) {
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
		return spaceDetailVO;
	}

	@Override
	public void delete(String spaceDetailId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, spaceDetailId);
			
			ptmt.executeUpdate();
			
			}catch (Exception e) {
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
	public void update(SpaceDetailVO spaceDetailVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, spaceDetailVO.getSpaceId());
			ptmt.setDate(2, spaceDetailVO.getSpaceDetailFreeDate());
			ptmt.setTimestamp(3, spaceDetailVO.getSpaceDetailFreeTimeStart());
			ptmt.setTimestamp(4, spaceDetailVO.getSpaceDetailFreeTimeEnd());
			ptmt.setInt(5, spaceDetailVO.getSpaceDetailCharge());
			ptmt.setString(6, spaceDetailVO.getSpaceDetailId());

			ptmt.executeUpdate();
			
		}catch (Exception e) {
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
	public SpaceDetailVO selectOne(String spaceDetailId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		SpaceDetailVO spaceDetailVO = new SpaceDetailVO();
		
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, spaceDetailId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceDetailVO.setSpaceDetailId(rs.getString("SPACE_DETAIL_ID"));
				spaceDetailVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceDetailVO.setSpaceDetailFreeDate(rs.getDate("SPACE_DETAIL_FREEDATE"));
				spaceDetailVO.setSpaceDetailFreeTimeStart(rs.getTimestamp("SPACE_DETAIL_FREETIME_START"));
				spaceDetailVO.setSpaceDetailFreeTimeEnd(rs.getTimestamp("SPACE_DETAIL_FREETIME_END"));
				spaceDetailVO.setSpaceDetailCharge(rs.getInt("SPACE_DETAIL_CHARGE"));
			}

		} catch (Exception e) {
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
		return spaceDetailVO;
	}
	//遠征第三站：用SpaceDetailId呼叫一個SpaceId
	@Override
	public String selectOneSpaceId(String spaceDetailId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		String spaceId = null;
		
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ONESPACE_BY_SDID);
			
			ptmt.setString(1, spaceDetailId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceId = rs.getString("SPACE_DETAIL_ID");
			}

		} catch (Exception e) {
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
		return spaceId;
	}
	
	//誠實業者必備顯示最低價格
	@Override
	public SpaceDetailVO selectOneLowest(String spaceId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		SpaceDetailVO spaceDetailVO = new SpaceDetailVO();
		
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ONE_lOWPRICE);
			
			ptmt.setString(1, spaceId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceDetailVO.setSpaceDetailId(rs.getString("SPACE_DETAIL_ID"));
				spaceDetailVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceDetailVO.setSpaceDetailFreeDate(rs.getDate("SPACE_DETAIL_FREEDATE"));
				spaceDetailVO.setSpaceDetailFreeTimeStart(rs.getTimestamp("SPACE_DETAIL_FREETIME_START"));
				spaceDetailVO.setSpaceDetailFreeTimeEnd(rs.getTimestamp("SPACE_DETAIL_FREETIME_END"));
				spaceDetailVO.setSpaceDetailCharge(rs.getInt("SPACE_DETAIL_CHARGE"));
			}

		} catch (Exception e) {
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
		return spaceDetailVO;
	}

	@Override
	public List<SpaceDetailVO> getAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		SpaceDetailVO spaceDetailVO = null;
		List<SpaceDetailVO> list = new ArrayList<SpaceDetailVO>();;
		
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceDetailVO = new SpaceDetailVO();
				spaceDetailVO.setSpaceDetailId(rs.getString("SPACE_DETAIL_ID"));
				spaceDetailVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceDetailVO.setSpaceDetailFreeDate(rs.getDate("SPACE_DETAIL_FREEDATE"));
				spaceDetailVO.setSpaceDetailFreeTimeStart(rs.getTimestamp("SPACE_DETAIL_FREETIME_START"));
				spaceDetailVO.setSpaceDetailFreeTimeEnd(rs.getTimestamp("SPACE_DETAIL_FREETIME_END"));
				spaceDetailVO.setSpaceDetailCharge(rs.getInt("SPACE_DETAIL_CHARGE"));
				
				list.add(spaceDetailVO);
			}

			}catch (Exception e) {
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
	public List<SpaceDetailVO> getSpaceIdList(String spaceId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		SpaceDetailVO spaceDetailVO = null;
		List<SpaceDetailVO> list = new ArrayList<SpaceDetailVO>();;
		
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ALL_SPACEID);
			
			ptmt.setString(1, spaceId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceDetailVO = new SpaceDetailVO();
				spaceDetailVO.setSpaceDetailId(rs.getString("SPACE_DETAIL_ID"));
				spaceDetailVO.setSpaceId(rs.getString("SPACE_ID"));
				spaceDetailVO.setSpaceDetailFreeDate(rs.getDate("SPACE_DETAIL_FREEDATE"));
				spaceDetailVO.setSpaceDetailFreeTimeStart(rs.getTimestamp("SPACE_DETAIL_FREETIME_START"));
				spaceDetailVO.setSpaceDetailFreeTimeEnd(rs.getTimestamp("SPACE_DETAIL_FREETIME_END"));
				spaceDetailVO.setSpaceDetailCharge(rs.getInt("SPACE_DETAIL_CHARGE"));
				
				list.add(spaceDetailVO);
			}

			}catch (Exception e) {
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
