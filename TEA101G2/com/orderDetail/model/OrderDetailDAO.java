package com.orderDetail.model;

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

public class OrderDetailDAO implements OrderDetailDAOInterface{
	
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
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "TEA101G2SP";
//	String passwd = "123456";
	

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
	//呼叫同個場地明細的所有訂單明細
	private static final String SELECT_ALL_STMT_SPACEDETAIL = 
			"SELECT * FROM ORDER_DETAIL where SPACE_DETAIL_ID = ?";
	//呼叫同一個訂單內的所有訂單明細，照日期順序排列
	private static final String SELECT_ALL_BY_MASTERID_STMT = 
		"SELECT * FROM ORDER_DETAIL where ORDER_MASTER_ID = ? order by RENT_START_TIME";
	//遠征第二站：用OrderMasterId呼叫一個SpaceDetailId
	private static final String SELECT_ONE_BY_OM =
		"SELECT SPACE_DETAIL_ID FROM(SELECT * FROM ORDER_DETAIL WHERE ORDER_MASTER_ID = ?)WHERE ROWNUM = 1";

	@Override
	public void insert(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, orderDetailVO.getOrderMasterId());
			ptmt.setString(2, orderDetailVO.getSpaceDetailId());
			ptmt.setTimestamp(3, orderDetailVO.getRentStartTime());
			ptmt.setTimestamp(4, orderDetailVO.getRentEndTime());

			ptmt.executeUpdate();
			
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
	
	/*************************用接到的OM新增OrderDetail***************************/
	@Override
	public void insertwithOrderMaster(OrderDetailVO orderDetailVO, Connection con) {
		
		PreparedStatement ptmt = null;
		System.out.println(orderDetailVO.toString());
		try {
			ptmt = con.prepareStatement(INSERT_STMT);
			ptmt.setString(1, orderDetailVO.getOrderMasterId());
			ptmt.setString(2, orderDetailVO.getSpaceDetailId());
			ptmt.setTimestamp(3, orderDetailVO.getRentStartTime());
			ptmt.setTimestamp(4, orderDetailVO.getRentEndTime());
			
			ptmt.executeUpdate();  
			
		} catch (Exception e) {
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back due to OrderDetail error");
					con.rollback();
				} catch (SQLException se) {
					e.printStackTrace(System.err);
				}
			}
			
		}finally {
			if (ptmt != null) {
				try {
					ptmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
					
				}
			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
		}
	}
	
	@Override
	public void delete(String orderDetailId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, orderDetailId);
			
			ptmt.executeUpdate();
			
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
			con = ds.getConnection();
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, orderDetailVO.getOrderMasterId());
			ptmt.setString(2, orderDetailVO.getSpaceDetailId());
			ptmt.setTimestamp(3, orderDetailVO.getRentStartTime());
			ptmt.setTimestamp(4, orderDetailVO.getRentEndTime());
			ptmt.setString(5, orderDetailVO.getOrderDetailId());
			
			ptmt.executeUpdate();
			
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
	
	/*************************遠征第二站：取一個spaceDetailId***************************/
	@Override
	public String selectOneSPId(String orderMasterId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		String spaceDetailId = null;
		
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ONE_BY_OM);
			
			ptmt.setString(1, orderMasterId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				spaceDetailId = (rs.getString("SPACE_DETAIL_ID"));
			}
			System.out.println(spaceDetailId);

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

		return spaceDetailId;
	}
	
	
	@Override
	public OrderDetailVO selectOne(String orderDetailId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		try {
			con = ds.getConnection();
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
	public List<OrderDetailVO> selectAllBySD(String spaceDetailId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		OrderDetailVO orderDetailVO = null;
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ALL_STMT_SPACEDETAIL);
			
			ptmt.setString(1, spaceDetailId);
			
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
	public List<OrderDetailVO> selectAll() {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		OrderDetailVO orderDetailVO = null;
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		
		try {
			con = ds.getConnection();
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
	public List<OrderDetailVO> selectAllByMasterId(String orderMasterId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		OrderDetailVO orderDetailVO = null;
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();;
		
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ALL_BY_MASTERID_STMT);
			
			ptmt.setString(1, orderMasterId);
			
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