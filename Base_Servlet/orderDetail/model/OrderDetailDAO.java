package com.orderDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO implements OrderDetailDAOInterface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G2";
	String passwd = "TEA101G2";
	

	private static final String INSERT_STMT = 
	    "INSERT INTO ORDER_DETAIL VALUES ('OD' || lpad(ORDER_DETAIL_ID_SEQ.NEXTVAL, 7, '0'),?,?,?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM ORDER_DETAIL order by ORDER_DETAIL_ID";
	private static final String SELECT_ONE_STMT = 
		"SELECT * FROM ORDER_DETAIL where ORDER_DETAIL_ID = ?";
	private static final String DELETE = 
		"DELETE FROM ORDER_DETAIL where ORDER_DETAIL_ID = ?";
	private static final String UPDATE = 
		"UPDATE ORDER_DETAIL set ORDER_MASTER_ID=?,SPACE_DETAIL_ID=?,RENT_START_TIME=?,RENT_END_TIME=? where ORDER_DETAIL_ID = ?";


	@Override
	public void insert(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, orderDetailVO.getOrderMasterId());
			ptmt.setString(2, orderDetailVO.getSpaceDetailId());
			ptmt.setTimestamp(3, orderDetailVO.getRentStartTime());
			ptmt.setTimestamp(4, orderDetailVO.getRentEndTime());

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
	public void delete(String orderDetailId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, orderDetailId);
			
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
	public void update(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, orderDetailVO.getOrderMasterId());
			ptmt.setString(2, orderDetailVO.getSpaceDetailId());
			ptmt.setTimestamp(3, orderDetailVO.getRentStartTime());
			ptmt.setTimestamp(4, orderDetailVO.getRentEndTime());
			ptmt.setString(5, orderDetailVO.getOrderDetailId());
			
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
	public OrderDetailVO selectOne(String orderDetailId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, orderDetailId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				orderDetailVO.setOrderDetailId(rs.getString("ORDER_DETAIL_ID"));
				orderDetailVO.setOrderMasterId(rs.getString("ORDER_MASTER_ID"));
				orderDetailVO.setSpaceDetailId(rs.getString("SPACE_DETAIL_ID"));
				orderDetailVO.setRentStartTime(rs.getTimestamp("RENT_START_TIME"));
				orderDetailVO.setRentEndTime(rs.getTimestamp("RENT_END_TIME"));
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

		return orderDetailVO;
	}

	@Override
	public List<OrderDetailVO> selectAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		OrderDetailVO orderDetailVO = null;
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderDetailId(rs.getString("ORDER_DETAIL_ID"));
				orderDetailVO.setOrderMasterId(rs.getString("ORDER_MASTER_ID"));
				orderDetailVO.setSpaceDetailId(rs.getString("SPACE_DETAIL_ID"));
				orderDetailVO.setRentStartTime(rs.getTimestamp("RENT_START_TIME"));
				orderDetailVO.setRentEndTime(rs.getTimestamp("RENT_END_TIME"));
				list.add(orderDetailVO);
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
