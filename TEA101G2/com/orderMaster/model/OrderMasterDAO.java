package com.orderMaster.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.orderDetail.model.*;

public class OrderMasterDAO implements OrderMasterDAOInterface{
	
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
//	String userid = "TEA101G2";
//	String passwd = "123456";
	

	private static final String INSERT_STMT =
		"INSERT INTO ORDER_MASTER (ORDER_MASTER_ID, MEMBER_ID, ORDER_CREATE_DATE, ORDER_AMOUNT, ORDER_STATUS) VALUES ('OMS' || lpad(ORDER_MASTER_ID_SEQ.NEXTVAL, 6, '0'),?,?,?,?)";
	private static final String SELECT_ALL_STMT = 
		"SELECT * FROM ORDER_MASTER order by ORDER_MASTER_ID";
	private static final String SELECT_ONE_STMT = 
		"SELECT * FROM ORDER_MASTER where ORDER_MASTER_ID = ?";
	private static final String DELETE = 
		"DELETE FROM ORDER_MASTER where ORDER_MASTER_ID = ?";
	private static final String UPDATE = 
		"UPDATE ORDER_MASTER set MEMBER_ID=?,ORDER_CREATEDATE=?,ORDER_AMOUNT=?,ORDER_STATUS=? where ORDER_MASTER_ID = ?";
	/*******************************訂單確認已付款，更改訂單狀態*******************************/
	private static final String UPDATE_STATUS = 
			"UPDATE ORDER_MASTER set ORDER_STATUS=? where ORDER_MASTER_ID = ?";
//	private static final String SELECT_ALL_BY_MEMBERID_STMT = 
//			"SELECT * FROM ORDER_MASTER where MEMBER_ID = ? order by ORDER_MASTER_ID";

	@Override
	public void insert(OrderMasterVO orderMasterVO) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(INSERT_STMT);
			
			ptmt.setString(1, orderMasterVO.getMemberId());
			ptmt.setDate(2, orderMasterVO.getOrderCreateDate());
			ptmt.setInt(3, orderMasterVO.getOrderAmount());
			ptmt.setString(4, orderMasterVO.getOrderStatus());

			ptmt.executeUpdate();
		
		} catch (Exception e) {
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
	
	/***********************同時新增OrderMaster以及OrderDetail***********************/
	@Override
	public String insertwithOrderDetail(OrderMasterVO orderMasterVO, List<OrderDetailVO> odlist) {
		Connection con = null;
		PreparedStatement ptmt = null;
		String orderMasterId = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			String cols[] = {"ORDER_MASTER_ID"};
			ptmt = con.prepareStatement(INSERT_STMT, cols);
			
			ptmt.setString(1, orderMasterVO.getMemberId());
			ptmt.setDate(2, orderMasterVO.getOrderCreateDate());
			ptmt.setInt(3, orderMasterVO.getOrderAmount());
			ptmt.setString(4, orderMasterVO.getOrderStatus());
			
			ptmt.executeUpdate();
			
			/***********************取得當前新增的自增主鍵***********************/
			String nowOMId = null;
			
			ResultSet rs = ptmt.getGeneratedKeys();
			//System.out.println(rs);
			if(rs.next()) {
				nowOMId = rs.getString(1);
				System.out.println("OrderMaster自增主鍵值="+ nowOMId + "剛新增成功的訂單編號");
			}else {
				System.out.println("未取得OrderMaster自增主鍵值");
			}
			rs.close();
			
			/**************************新增訂單明細******************************/
			OrderDetailDAO oddao = new OrderDetailDAO();
			System.out.println("共有"+ odlist.size() + "筆訂單明細準備被新增");
			for(OrderDetailVO orderDetailVO : odlist) {
				orderDetailVO.setOrderMasterId(nowOMId);
				oddao.insertwithOrderMaster(orderDetailVO, con);
				System.out.println("訂單明細新增成功");
			}
			
			con.commit();
			orderMasterId = nowOMId;
			System.out.println(orderMasterId);
			System.out.println("新增訂單編號" + nowOMId + "時，共有明細" + odlist.size() + "筆同時被新增");
			
		} catch (Exception e) {
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back due to OrderMaster");
					e.printStackTrace(System.err);
					con.rollback();
				} catch (SQLException se) {
					e.printStackTrace(System.err);
				}
			}
		}finally {
			
			try {
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
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
		return orderMasterId;
	}

	@Override
	public void delete(String orderMasterId) {
		Connection con = null;
		PreparedStatement ptmt = null;

		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(DELETE);
			
			ptmt.setString(1, orderMasterId);
			
			ptmt.executeUpdate();
			
			} catch (Exception e) {
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
			con = ds.getConnection();
			ptmt = con.prepareStatement(UPDATE);
			
			ptmt.setString(1, orderMasterVO.getMemberId());
			ptmt.setDate(2, orderMasterVO.getOrderCreateDate());
			ptmt.setInt(3, orderMasterVO.getOrderAmount());
			ptmt.setString(4, orderMasterVO.getOrderStatus());
			ptmt.setString(5, orderMasterVO.getOrderMasterId());
			
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
	/*******************************訂單確認已付款，更改訂單狀態*******************************/
	@Override
	public void purchaseDone(String orderMasterId) {
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try {
			con = ds.getConnection();
			ptmt = con.prepareStatement(UPDATE_STATUS);
			
			ptmt.setString(1, "F");
			ptmt.setString(2, orderMasterId);
			
			ptmt.executeUpdate();
			
		}catch (Exception e) {
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back due to OrderMaster");
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
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ONE_STMT);
			
			ptmt.setString(1, orderMasterId);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				orderMasterVO.setOrderMasterId(rs.getString("ORDER_MASTER_ID"));
				orderMasterVO.setMemberId(rs.getString("MEMBER_ID"));
				orderMasterVO.setOrderCreateDate(rs.getDate("ORDER_CREATE_DATE"));
				orderMasterVO.setOrderAmount(rs.getInt("ORDER_AMOUNT"));
				orderMasterVO.setOrderStatus(rs.getString("ORDER_STATUS"));
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
			con = ds.getConnection();
			ptmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrderMasterId(rs.getString("ORDER_MASTER_ID"));
				orderMasterVO.setMemberId(rs.getString("MEMBER_ID"));
				orderMasterVO.setOrderCreateDate(rs.getDate("ORDER_CREATE_DATE"));
				orderMasterVO.setOrderAmount(rs.getInt("ORDER_AMOUNT"));
				orderMasterVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				list.add(orderMasterVO);
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
//			con = ds.getConnection();
//			ptmt = con.prepareStatement(SELECT_ALL_BY_MEMBERID_STMT);
//			
//			ptmt.setString(1, memberId);
//			
//			rs = ptmt.executeQuery();
//			while (rs.next()) {
//				orderMasterVO = new OrderMasterVO();
//				orderMasterVO.setOrderMasterId(rs.getString("ORDER_MASTER_ID"));
//				orderMasterVO.setMemberId(rs.getString("MEMBER_ID"));
//				orderMasterVO.setOrderCreateDate(rs.getDate("ORDER_CREATEDATE"));
//				orderMasterVO.setOrderAmount(rs.getInt("ORDER_AMOUNT"));
//				orderMasterVO.setOrderStatus(rs.getString("ORDER_STATUS"));
//				list.add(orderMasterVO);
//			}
//
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