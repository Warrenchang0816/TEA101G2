package com.orderMaster.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMasterDAOB implements OrderMasterDAOInterfaceB{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA101G2";
	String passwd = "TEA101G2";
	

	private static final String INSERT_STMT = 
	    "INSERT INTO ORDER_MASTER VALUES (ORDER_MASTER_ID_SEQ.NEXTVAL,?,?,?,?,?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM ORDER_MASTER order by ORDER_MASTER_ID";
	private static final String SELECT_ONE_STMT = 
		"SELECT * FROM ORDER_MASTER where ORDER_MASTER_ID = ?";
	private static final String DELETE = 
		"DELETE FROM ORDER_MASTER where ORDER_MASTER_ID = ?";
	private static final String UPDATE = 
		"UPDATE ORDER_MASTER set MEMBER_ID=?,ORDER_CREATE_DATE=?,ORDER_AMOUNT=?,ORDER_STATUS=?,ORDER_STATUS_EMP=?,ORDER_STATUS_COMM=? where ORDER_MASTER_ID = ?";
//	private static final String SELECT_ALL_BY_MEMBERID_STMT = 
//			"SELECT * FROM ORDER_MASTER where MEMBER_ID = ? order by ORDER_MASTER_ID";


	@Override
	public void insert(OrderMasterVO orderMasterVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, orderMasterVO.getMemberId());
			ptmt.setDate(2, orderMasterVO.getOrderCreateDate());
			ptmt.setInt(3, orderMasterVO.getOrderAmount());
			ptmt.setString(4, orderMasterVO.getOrderStatus());
			ptmt.setString(5, "");
			ptmt.setString(6, "");

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
	public void delete(String orderMasterId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, orderMasterId);
			
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
	public void update(OrderMasterVO orderMasterVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, orderMasterVO.getMemberId());
			ptmt.setDate(2, orderMasterVO.getOrderCreateDate());
			ptmt.setInt(3, orderMasterVO.getOrderAmount());
			ptmt.setString(4, orderMasterVO.getOrderStatus());
			ptmt.setString(5, orderMasterVO.getOrderStatusEmp());
			ptmt.setString(6, orderMasterVO.getOrderStatusComm());
			ptmt.setString(7, orderMasterVO.getOrderMasterId());
			
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
	public OrderMasterVO selectOne(String orderMasterId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		OrderMasterVO orderMasterVO = new OrderMasterVO();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, orderMasterId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				orderMasterVO.setOrderMasterId(rs.getString("ORDER_MASTER_ID"));
				orderMasterVO.setMemberId(rs.getString("MEMBER_ID"));
				orderMasterVO.setOrderCreateDate(rs.getDate("ORDER_CREATE_DATE"));
				orderMasterVO.setOrderAmount(rs.getInt("ORDER_AMOUNT"));
				orderMasterVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				orderMasterVO.setOrderStatusEmp(rs.getString("ORDER_STATUS_EMP"));
				orderMasterVO.setOrderStatusComm(rs.getString("ORDER_STATUS_COMM"));
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

		return orderMasterVO;
	}

	@Override
	public List<OrderMasterVO> selectAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		OrderMasterVO orderMasterVO = null;
		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrderMasterId(rs.getString("ORDER_MASTER_ID"));
				orderMasterVO.setMemberId(rs.getString("MEMBER_ID"));
				orderMasterVO.setOrderCreateDate(rs.getDate("ORDER_CREATE_DATE"));
				orderMasterVO.setOrderAmount(rs.getInt("ORDER_AMOUNT"));
				orderMasterVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				orderMasterVO.setOrderStatusEmp(rs.getString("ORDER_STATUS_EMP"));
				orderMasterVO.setOrderStatusComm(rs.getString("ORDER_STATUS_COMM"));
				list.add(orderMasterVO);
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

//	@Override
//	public List<OrderMasterVO> selectAllByMember(String memberId) {
//		Connection con = null;
//		PreparedStatement ptmt = null;
//		ResultSet rs = null;
//		
//		OrderMasterVO orderMasterVO = null;
//		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			ptmt = con.prepareStatement(SELECT_ALL_BY_MEMBERID_STMT);
//			
//			ptmt.setString(1, memberId);
//			
//			rs = ptmt.executeQuery();
//			while (rs.next()) {
//				orderMasterVO = new OrderMasterVO();
//				orderMasterVO.setOrderMasterId(rs.getString("ORDER_MASTER_ID"));
//				orderMasterVO.setMemberId(rs.getString("MEMBER_ID"));
//				orderMasterVO.setOrderCreatDate(rs.getDate("ORDER_CREATE_DATE"));
//				orderMasterVO.setOrderAmount(rs.getInt("ORDER_AMOUNT"));
//				orderMasterVO.setOrderStatus(rs.getString("ORDER_STATUS"));
//				list.add(orderMasterVO);
//			}
//
//			}catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (ptmt != null) {
//					try {
//						ptmt.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//		return list;
//	}

}
