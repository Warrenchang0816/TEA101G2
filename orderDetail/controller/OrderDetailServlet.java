package com.orderDetail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderDetail.model.OrderDetailService;
import com.orderDetail.model.OrderDetailVO;


@WebServlet("/OrderDetailServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class OrderDetailServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
    public OrderDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("backend_AddOrderDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderMasterId = req.getParameter("orderMasterId").trim();
				if(orderMasterId == null || orderMasterId.isEmpty()) errorMsgs.add("訂單編號: 請勿空白");
				
				String spaceDetailId = req.getParameter("spaceDetailId").trim();
				if(spaceDetailId == null || spaceDetailId.isEmpty()) errorMsgs.add("場地明細編號: 請勿空白");
				
				java.sql.Timestamp rentStartTime = null;
				try {
					rentStartTime = java.sql.Timestamp.valueOf(req.getParameter("rentStartTime").trim());
				} catch (IllegalArgumentException e) {
					rentStartTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("租借開始時間: 格式錯誤");
				}
				
				java.sql.Timestamp rentEndTime = null;
				try {
					rentEndTime = java.sql.Timestamp.valueOf(req.getParameter("rentEndTime").trim());
				} catch (IllegalArgumentException e) {
					rentEndTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("租借結束時間: 格式錯誤");
				}
				
				OrderDetailVO addOrderDetail = new OrderDetailVO();
				addOrderDetail.setOrderMasterId(orderMasterId);
				addOrderDetail.setSpaceDetailId(spaceDetailId);
				addOrderDetail.setRentStartTime(rentStartTime);
				addOrderDetail.setRentEndTime(rentEndTime);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addOrderDetail", addOrderDetail);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/orderDetail/addOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderDetailService orderDetailServ = new OrderDetailService();
				addOrderDetail = orderDetailServ.addOrderDetail(addOrderDetail);
				
				
				String url = "/backend/orderDetail/selectAllOrderDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_SelectOneOrderDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderDetailId = req.getParameter("orderDetailId").trim();
				if(orderDetailId == null || orderDetailId.isEmpty()) errorMsgs.add("訂單明細編號: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/orderDetail/orderDetail.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderDetailService orderDetailServ = new OrderDetailService();
				OrderDetailVO selectOneOrderDetail = new OrderDetailVO();
				selectOneOrderDetail = orderDetailServ.selectOneOrderDetail(orderDetailId);
				
				if (selectOneOrderDetail == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/orderDetail/orderDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneOrderDetail", selectOneOrderDetail);

				String url = "/backend/orderDetail/selectOneOrderDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_DeleteOrderDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderDetailId = req.getParameter("orderDetailId").trim();

				OrderDetailService orderDetailServ = new OrderDetailService();
				OrderDetailVO deleteOrderDetail = new OrderDetailVO();
				orderDetailServ.deleteOrderDetail(orderDetailId);

				String url = "/backend/orderDetail/selectAllOrderDetail.jsp";
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
				String orderDetailId = req.getParameter("orderDetailId").trim();

				OrderDetailService orderDetailServ = new OrderDetailService();
				OrderDetailVO selectOneUpdate = new OrderDetailVO();
				selectOneUpdate = orderDetailServ.selectOneOrderDetail(orderDetailId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/backend/orderDetail/updateOrderDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_UpdateOrderDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderDetailId = req.getParameter("orderDetailId").trim();
				
				String orderMasterId = req.getParameter("orderMasterId").trim();
				if(orderMasterId == null || orderMasterId.isEmpty()) errorMsgs.add("訂單編號: 請勿空白");
				
				String spaceDetailId = req.getParameter("spaceDetailId").trim();
				if(spaceDetailId == null || spaceDetailId.isEmpty()) errorMsgs.add("場地明細編號: 請勿空白");
				
				java.sql.Timestamp rentStartTime = null;
				try {
					rentStartTime = java.sql.Timestamp.valueOf(req.getParameter("rentStartTime").trim());
				} catch (IllegalArgumentException e) {
					rentStartTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("租借開始時間: 格式錯誤");
				}
				
				java.sql.Timestamp rentEndTime = null;
				try {
					rentEndTime = java.sql.Timestamp.valueOf(req.getParameter("rentEndTime").trim());
				} catch (IllegalArgumentException e) {
					rentEndTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("租借結束時間: 格式錯誤");
				}
				
				OrderDetailVO updateOrderDetail = new OrderDetailVO();
				updateOrderDetail.setOrderDetailId(orderDetailId);
				updateOrderDetail.setOrderMasterId(orderMasterId);
				updateOrderDetail.setSpaceDetailId(spaceDetailId);
				updateOrderDetail.setRentStartTime(rentStartTime);
				updateOrderDetail.setRentEndTime(rentEndTime);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateOrderDetail);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/orderDetail/updateOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderDetailService orderDetailServ = new OrderDetailService();
				updateOrderDetail = orderDetailServ.updateOrderDetail(updateOrderDetail);
//				req.setAttribute("updateSpacePhoto", updateSpacePhoto);

				String url = "/backend/orderDetail/selectAllOrderDetail.jsp";
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
		
		
		if ("addOrderDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderMasterId = req.getParameter("orderMasterId").trim();
				if(orderMasterId == null || orderMasterId.isEmpty()) errorMsgs.add("訂單編號: 請勿空白");
				
				String spaceDetailId = req.getParameter("spaceDetailId").trim();
				if(spaceDetailId == null || spaceDetailId.isEmpty()) errorMsgs.add("場地明細編號: 請勿空白");
				
				OrderDetailVO addOrderDetail = new OrderDetailVO();
				addOrderDetail.setOrderMasterId(orderMasterId);
				addOrderDetail.setSpaceDetailId(spaceDetailId);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addOrderDetail", addOrderDetail);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderDetail/addOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderDetailService orderDetailServ = new OrderDetailService();
				addOrderDetail = orderDetailServ.addOrderDetail(addOrderDetail);
				
				
				String url = "/frontend/orderDetail/selectAllOrderDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("selectOneOrderDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderDetailId = req.getParameter("orderDetailId").trim();
				if(orderDetailId == null || orderDetailId.isEmpty()) errorMsgs.add("訂單明細編號: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderDetail/orderDetail.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderDetailService orderDetailServ = new OrderDetailService();
				OrderDetailVO selectOneOrderDetail = new OrderDetailVO();
				selectOneOrderDetail = orderDetailServ.selectOneOrderDetail(orderDetailId);
				
				if (selectOneOrderDetail == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderDetail/orderDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneOrderDetail", selectOneOrderDetail);

				String url = "/frontend/orderDetail/selectOneOrderDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("deleteOrderDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderDetailId = req.getParameter("orderDetailId").trim();

				OrderDetailService orderDetailServ = new OrderDetailService();
				OrderDetailVO deleteOrderDetail = new OrderDetailVO();
				orderDetailServ.deleteOrderDetail(orderDetailId);

				String url = "/frontend/orderDetail/selectAllOrderDetail.jsp";
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
				String orderDetailId = req.getParameter("orderDetailId").trim();

				OrderDetailService orderDetailServ = new OrderDetailService();
				OrderDetailVO selectOneUpdate = new OrderDetailVO();
				selectOneUpdate = orderDetailServ.selectOneOrderDetail(orderDetailId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/frontend/orderDetail/updateOrderDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("updateOrderDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderDetailId = req.getParameter("orderDetailId").trim();
				
				String orderMasterId = req.getParameter("orderMasterId").trim();
				if(orderMasterId == null || orderMasterId.isEmpty()) errorMsgs.add("訂單編號: 請勿空白");
				
				String spaceDetailId = req.getParameter("spaceDetailId").trim();
				if(spaceDetailId == null || spaceDetailId.isEmpty()) errorMsgs.add("場地明細編號: 請勿空白");
				
				OrderDetailVO updateOrderDetail = new OrderDetailVO();
				updateOrderDetail.setOrderDetailId(orderDetailId);
				updateOrderDetail.setOrderMasterId(orderMasterId);
				updateOrderDetail.setSpaceDetailId(spaceDetailId);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateOrderDetail);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderDetail/updateOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderDetailService orderDetailServ = new OrderDetailService();
				updateOrderDetail = orderDetailServ.updateOrderDetail(updateOrderDetail);
//				req.setAttribute("updateSpacePhoto", updateSpacePhoto);

				String url = "/frontend/orderDetail/selectAllOrderDetail.jsp";
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
