package com.orderDetail.controller;

import java.io.IOException;
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

import com.orderDetail.model.*;
import com.spaceDetail.model.*;
import com.space.model.*;


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
		
		
		if ("addOrderDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderMasterId = req.getParameter("orderMasterId").trim();
				if(orderMasterId == null || orderMasterId.isEmpty()) errorMsgs.add("訂單編號: 請勿空白");
				
				String spaceDetailId = req.getParameter("spaceDetailId").trim();
				if(spaceDetailId == null || spaceDetailId.isEmpty()) errorMsgs.add("場地明細編號: 請勿空白");

//---------------------------------判斷租借時間是否有效-----------------------------------------------------
				SpaceDetailService spaceDetailSvc = new SpaceDetailService();
				OrderDetailService orderDetailSvc = new OrderDetailService();
				SpaceDetailVO spaceDetailVO = spaceDetailSvc.selectOneSpaceDetail(spaceDetailId);
				List<OrderDetailVO> oldorderlist = orderDetailSvc.selectAllOrderDetailBySD(spaceDetailId);
				long spaceStartTimeLong = (spaceDetailVO.getSpaceDetailFreeTimeStart().getTime());
				long spaceEndTimeLong = (spaceDetailVO.getSpaceDetailFreeTimeEnd().getTime());
				
				
				//場地租借開始時間格式偵錯
				java.sql.Timestamp rentStartTime = null;
				try {
					rentStartTime = java.sql.Timestamp.valueOf(req.getParameter("rentStartTime").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMsgs.add("場地租借開始時間錯誤");
				}
				
				//場地租借結束時間格式偵錯
				java.sql.Timestamp rentEndTime = null;
				try {
					rentEndTime = java.sql.Timestamp.valueOf(req.getParameter("rentEndTime").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMsgs.add("場地租借結束時間錯誤");
				}
				
				
				long rentStartTimeLong = (rentStartTime.getTime());	
				long rentEndTimeLong = (rentEndTime.getTime());
				//租借總時間判斷
				if((rentEndTimeLong - rentStartTimeLong) <= 0) {
					errorMsgs.add("場地租借總時間錯誤");
				}
				
				//與場地租借時間比較
				if(rentStartTimeLong < spaceStartTimeLong) {
					errorMsgs.add("場地租借開始時間過早");
				}
				if(rentEndTimeLong > spaceEndTimeLong) {
					errorMsgs.add("場地租借結束時間過晚");
				}
				if((rentEndTimeLong - rentStartTimeLong) > (spaceEndTimeLong-spaceStartTimeLong)) {
					errorMsgs.add("場地租借總時間過長");
				}
				//與既有訂單租借時間比較
				for(OrderDetailVO orderDetailVO : oldorderlist) {
					if(rentStartTimeLong >= (orderDetailVO.getRentStartTime().getTime()) && rentStartTimeLong < (orderDetailVO.getRentEndTime().getTime())) {
						errorMsgs.add("該時段開始時間已有人預約");
					}
					if(rentEndTimeLong > (orderDetailVO.getRentStartTime().getTime()) && rentEndTimeLong <= (orderDetailVO.getRentEndTime().getTime())) {
						errorMsgs.add("該時段結束時間已有人預約");
					}
					if(rentStartTimeLong <= (orderDetailVO.getRentStartTime().getTime()) && rentEndTimeLong >= (orderDetailVO.getRentEndTime().getTime())) {
						errorMsgs.add("該時段內已有人預約");
					}
				}
				
				OrderDetailVO addOrderDetail = new OrderDetailVO();
				addOrderDetail.setOrderMasterId(orderMasterId);
				addOrderDetail.setSpaceDetailId(spaceDetailId);
				addOrderDetail.setRentStartTime(rentStartTime);
				addOrderDetail.setRentEndTime(rentEndTime);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addOrderDetail", addOrderDetail);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderdetail/addOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderDetailService orderDetailServ = new OrderDetailService();
				addOrderDetail = orderDetailServ.addOrderDetail(addOrderDetail);
				
				String url = "/frontend/orderdetail/selectAllOrderDetail.jsp";
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
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderdetail/orderDetail.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderdetail/orderDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneOrderDetail", selectOneOrderDetail);

				String url = "/frontend/orderdetail/selectOneOrderDetail.jsp";
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
				System.out.println(orderDetailId);
				OrderDetailService orderDetailServ = new OrderDetailService();
				orderDetailServ.deleteOrderDetail(orderDetailId);

				String url = "/frontend/orderdetail/selectAllOrderDetail.jsp";
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

				String url = "/frontend/orderdetail/updateOrderDetail.jsp";
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
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderdetail/updateOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderDetailService orderDetailServ = new OrderDetailService();
				updateOrderDetail = orderDetailServ.updateOrderDetail(updateOrderDetail);
//				req.setAttribute("updateSpacePhoto", updateSpacePhoto);

				String url = "/frontend/orderdetail/selectAllOrderDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}
		
		/**************************** 用orderMasterId把全部orderDetailVO滾成odlist ******************************/
		
		if ("listAllOrderDetail".equals(action)) {
			Queue<String> errorMessages = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMessages);
			
			try {
				String orderMasterId = req.getParameter("orderMasterId");
				if (orderMasterId == null || (orderMasterId.trim()).length() == 0) {
					errorMessages.add("請輸入orderMasterId");
				}
				// Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/ordermaster/selectAllOrderMaster.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/************************************* 開始滾資料 *************************************************/
				OrderDetailService ods = new OrderDetailService();
				List<OrderDetailVO> odlist = ods.selectAllOrderDetailByMaster(orderMasterId);
				System.out.println(odlist);
				if (odlist == null) {
					errorMessages.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/ordermaster/selectAllOrderMaster.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 查詢完成,準備轉交(Send the Success view) ********************************/
				req.setAttribute("odlist", odlist); // 資料庫取出的orderDetailVO物件,存入req
				String url = "/frontend/orderdetail/selectAllOrderDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMessages.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}
	}
}