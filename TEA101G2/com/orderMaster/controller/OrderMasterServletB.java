package com.orderMaster.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderMaster.model.OrderMasterServiceB;
import com.orderMaster.model.OrderMasterVO;


@WebServlet("/OrderMasterServletB")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class OrderMasterServletB extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public OrderMasterServletB() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("backend_AddOrderMaster".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberId = req.getParameter("memberId").trim();
				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				java.sql.Date orderCreatDate = null;
				try {
					orderCreatDate = java.sql.Date.valueOf(req.getParameter("orderCreatDate").trim());
				} catch (IllegalArgumentException e) {
					orderCreatDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				Integer orderAmount = null;
				try {
					orderAmount = Integer.parseInt(req.getParameter("orderAmount").trim());
					if(orderAmount <= 0) errorMsgs.add("訂單金額: 請確認金額");
				} catch (NumberFormatException e) {
					orderAmount = 0;
					errorMsgs.add("訂單金額: 必須為數字");
				}
				
				String orderStatus = req.getParameter("orderStatus").trim();
				if(orderStatus == null || orderStatus.isEmpty()) errorMsgs.add("訂單狀態: 請勿空白");

				OrderMasterVO addOrderMaster = new OrderMasterVO();
				addOrderMaster.setMemberId(memberId);
				addOrderMaster.setOrderCreateDate(orderCreatDate);
				addOrderMaster.setOrderAmount(orderAmount);
				addOrderMaster.setOrderStatus(orderStatus);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addOrderMaster", addOrderMaster); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/orderMaster/addOrderMaster.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderMasterServiceB orderMasterServ = new OrderMasterServiceB();
				addOrderMaster = orderMasterServ.addOrderMaster(addOrderMaster);

				String url = "/backend/orderMaster/selectAllOrderMaster.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_SelectOneOrderMaster".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderMasterId = req.getParameter("orderMasterId").trim();
				if(orderMasterId == null || orderMasterId.isEmpty()) errorMsgs.add("訂單編號: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/orderMaster/orderMaster.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderMasterServiceB orderMasterServ = new OrderMasterServiceB();
				OrderMasterVO selectOneOrderMaster = new OrderMasterVO();
				selectOneOrderMaster = orderMasterServ.selectOneOrderMaster(orderMasterId);
				
				if (selectOneOrderMaster == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/orderMaster/orderMaster.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneOrderMaster", selectOneOrderMaster);

				String url = "/backend/orderMaster/selectOneOrderMaster.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}
		
		if ("backend_SelectOrderMasterByMember".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberId = req.getParameter("memberId").trim();
				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				String memberName = req.getParameter("memberName").trim();
				if(memberName == null || memberName.isEmpty()) errorMsgs.add("會員姓名: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/member/selectMember.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderMasterServiceB orderMasterServ = new OrderMasterServiceB();
				List<OrderMasterVO> selectOrderMasterByMember = new ArrayList<OrderMasterVO>();
				selectOrderMasterByMember = orderMasterServ.selectAllOrderMasterByMember(memberId);
				
				if (selectOrderMasterByMember == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/member/selectMember.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOrderMasterByMember", selectOrderMasterByMember);
				req.setAttribute("memberName", memberName);
				req.setAttribute("memberId", memberId);

				String url = "/backend/orderMaster/selectOrderMasterByMember.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}	
		
		if ("backend_DeleteOrderMaster".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderMasterId = req.getParameter("orderMasterId").trim();

				OrderMasterServiceB orderMasterServ = new OrderMasterServiceB();
				OrderMasterVO deleteOrderMaster = new OrderMasterVO();
				orderMasterServ.deleteOrderMaster(orderMasterId);

				String url = "/backend/orderMaster/selectAllOrderMaster.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_SelectOneUpdate".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderMasterId = req.getParameter("orderMasterId").trim();

				OrderMasterServiceB orderMasterServ = new OrderMasterServiceB();
				OrderMasterVO selectOneUpdate = new OrderMasterVO();
				selectOneUpdate = orderMasterServ.selectOneOrderMaster(orderMasterId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/backend/orderMaster/updateOrderMaster.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_UpdateOrderMaster".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderMasterId = req.getParameter("orderMasterId").trim();
				
				OrderMasterServiceB orderMasterServ = new OrderMasterServiceB();
				OrderMasterVO OrderMaster = orderMasterServ.selectOneOrderMaster(orderMasterId);
				
				String orderStatusEmp = req.getParameter("orderStatusEmp").trim();
				
				OrderMasterVO updateOrderMaster = new OrderMasterVO();
				updateOrderMaster.setOrderMasterId(orderMasterId);
				updateOrderMaster.setMemberId(OrderMaster.getMemberId());
				updateOrderMaster.setOrderCreateDate(OrderMaster.getOrderCreateDate());
				updateOrderMaster.setOrderAmount(OrderMaster.getOrderAmount());
				updateOrderMaster.setOrderStatus("R");
				updateOrderMaster.setOrderStatusComm(OrderMaster.getOrderStatusComm());
				updateOrderMaster.setOrderStatusEmp(orderStatusEmp);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/orderMaster/selectOrderMasterStatusR.jsp");
					failureView.forward(req, res);
					return;
				}

				orderMasterServ = new OrderMasterServiceB();
				updateOrderMaster = orderMasterServ.updateOrderMaster(updateOrderMaster);

				String url = "/backend/orderMaster/selectOrderMasterStatusR.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}	
		

//======================================================================================================================================

		
		if ("addOrderMaster".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberId = req.getParameter("memberId").trim();
				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				java.sql.Date orderCreatDate = null;
				try {
					orderCreatDate = java.sql.Date.valueOf(req.getParameter("orderCreatDate").trim());
				} catch (IllegalArgumentException e) {
					orderCreatDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				String orderStatus = req.getParameter("orderStatus").trim();
				if(orderStatus == null || orderStatus.isEmpty()) errorMsgs.add("訂單狀態: 請勿空白");

				OrderMasterVO addOrderMaster = new OrderMasterVO();
				addOrderMaster.setMemberId(memberId);
				addOrderMaster.setOrderCreateDate(orderCreatDate);
				addOrderMaster.setOrderStatus(orderStatus);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addOrderMaster", addOrderMaster); 
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderMaster/addOrderMaster.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderMasterServiceB orderMasterServ = new OrderMasterServiceB();
				addOrderMaster = orderMasterServ.addOrderMaster(addOrderMaster);

				String url = "/frontend/orderMaster/selectAllOrderMaster.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("selectOneOrderMaster".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderMasterId = req.getParameter("orderMasterId").trim();
				if(orderMasterId == null || orderMasterId.isEmpty()) errorMsgs.add("訂單編號: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderMaster/orderMaster.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderMasterServiceB orderMasterServ = new OrderMasterServiceB();
				OrderMasterVO selectOneOrderMaster = new OrderMasterVO();
				selectOneOrderMaster = orderMasterServ.selectOneOrderMaster(orderMasterId);
				
				if (selectOneOrderMaster == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderMaster/orderMaster.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneOrderMaster", selectOneOrderMaster);

				String url = "/frontend/orderMaster/selectOneOrderMaster.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("deleteOrderMaster".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderMasterId = req.getParameter("orderMasterId").trim();

				OrderMasterServiceB orderMasterServ = new OrderMasterServiceB();
				OrderMasterVO deleteOrderMaster = new OrderMasterVO();
				orderMasterServ.deleteOrderMaster(orderMasterId);

				String url = "/frontend/orderMaster/selectAllOrderMaster.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("selectOneUpdate".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderMasterId = req.getParameter("orderMasterId").trim();

				OrderMasterServiceB orderMasterServ = new OrderMasterServiceB();
				OrderMasterVO selectOneUpdate = new OrderMasterVO();
				selectOneUpdate = orderMasterServ.selectOneOrderMaster(orderMasterId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/frontend/orderMaster/updateOrderMaster.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("updateOrderMaster".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderMasterId = req.getParameter("orderMasterId").trim();
				
				String memberId = req.getParameter("memberId").trim();
				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				java.sql.Date orderCreatDate = null;
				try {
					orderCreatDate = java.sql.Date.valueOf(req.getParameter("orderCreatDate").trim());
				} catch (IllegalArgumentException e) {
					orderCreatDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				String orderStatus = req.getParameter("orderStatus").trim();
				if(orderStatus == null || orderStatus.isEmpty()) errorMsgs.add("訂單狀態: 請勿空白");

				
				OrderMasterVO updateOrderMaster = new OrderMasterVO();
				updateOrderMaster.setOrderMasterId(orderMasterId);
				updateOrderMaster.setMemberId(memberId);
				updateOrderMaster.setOrderCreateDate(orderCreatDate);
				updateOrderMaster.setOrderStatus(orderStatus);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateOrderMaster); 
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderMaster/updateOrderMaster.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderMasterServiceB orderMasterServ = new OrderMasterServiceB();
				updateOrderMaster = orderMasterServ.updateOrderMaster(updateOrderMaster);
//				req.setAttribute("selectOneUpdate", updateMemberComm);

				String url = "/frontend/orderMaster/selectAllOrderMaster.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}	
		
	}

}
