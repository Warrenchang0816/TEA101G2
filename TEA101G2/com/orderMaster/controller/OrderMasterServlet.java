package com.orderMaster.controller;

import java.io.IOException;
import java.sql.Date;
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

import com.orderMaster.model.*;
import com.orderDetail.model.*;
import com.spaceDetail.model.*;


@WebServlet("/OrderMasterServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class OrderMasterServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public OrderMasterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

		
		if ("addOrderMaster".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberId = req.getParameter("memberId").trim();
				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				java.sql.Date orderCreateDate = null;
				try {
					orderCreateDate = java.sql.Date.valueOf(req.getParameter("orderCreateDate").trim());
					System.out.println(orderCreateDate);
				} catch (IllegalArgumentException e) {
					orderCreateDate = new java.sql.Date(System.currentTimeMillis());
				}
				

				OrderMasterVO addOrderMaster = new OrderMasterVO();
				addOrderMaster.setMemberId(memberId);
				addOrderMaster.setOrderCreateDate(orderCreateDate);
				addOrderMaster.setOrderStatus("N");
				addOrderMaster.setOrderAmount(0);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addOrderMaster", addOrderMaster); 
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/ordermaster/addOrderMaster.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderMasterService orderMasterServ = new OrderMasterService();
				addOrderMaster = orderMasterServ.addOrderMaster(addOrderMaster);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/ordermaster/orderMaster.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ********************************/
				String url = "/frontend/ordermaster/listAllOrderMaster.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}
		
		if ("addOrderMasterwithOrderDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				String memberId = req.getParameter("memberId").trim();
//				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				java.sql.Date orderCreateDate = null;
				try {
					orderCreateDate = java.sql.Date.valueOf(req.getParameter("orderCreateDate").trim());
					System.out.println(orderCreateDate);
				} catch (IllegalArgumentException e) {
					orderCreateDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				OrderMasterVO addOrderMaster = new OrderMasterVO();
				addOrderMaster.setMemberId("MEM00001");
				addOrderMaster.setOrderCreateDate(orderCreateDate);
				addOrderMaster.setOrderStatus("N");
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addOrderMaster", addOrderMaster); 
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/ordermaster/addOrderMaster.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//撈出odlist要用的資料
				String[] spaceDetaillist = null;
				spaceDetaillist = req.getParameterValues("spaceDetailId");
				System.out.println(spaceDetaillist.length);
				
				String[] rentStartTimelist = null;
				rentStartTimelist = req.getParameterValues("rentStartTime");
				System.out.println(rentStartTimelist);
				
				String[] rentEndTimelist = null;
				rentEndTimelist = req.getParameterValues("rentEndTime");
				System.out.println(rentEndTimelist);
				
				String[] spaceDetailChargelist = null;
				spaceDetailChargelist = req.getParameterValues("spaceDetailCharge");
				System.out.println(spaceDetailChargelist);
				
				Integer rentCharge = 0;
				
				
				//撈出資料建立OrderDetailVO，並將OrderMaster總金額修改成訂單總金額
				List<OrderDetailVO> odlist = new ArrayList<OrderDetailVO>();
				OrderDetailService orderDetailSvc = new OrderDetailService();
				SpaceDetailServiceB spaceDetailSvc = new SpaceDetailServiceB();
				
				
				for (int i = 0; i < rentStartTimelist.length; i++) {
					if(rentStartTimelist[i] == null || rentStartTimelist[i].isEmpty() || rentEndTimelist[i] == null || rentEndTimelist[i].isEmpty()) {}
					else {	
						OrderDetailVO orderDetailVO = new OrderDetailVO();
						orderDetailVO.setSpaceDetailId(spaceDetaillist[i]);
						//判斷訂單時間是否合法
						SpaceDetailVO spaceDetailVO = spaceDetailSvc.selectOneSpaceDetail(spaceDetaillist[i]);
						long spaceStartTimeLong = (spaceDetailVO.getSpaceDetailFreeTimeStart().getTime());
						long spaceEndTimeLong = (spaceDetailVO.getSpaceDetailFreeTimeEnd().getTime());
						long rentStartTimeLong = (java.sql.Timestamp.valueOf(rentStartTimelist[i]).getTime());	
						long rentEndTimeLong = (java.sql.Timestamp.valueOf(rentEndTimelist[i]).getTime());
						
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
						List<OrderDetailVO> oldorderlist = orderDetailSvc.selectAllOrderDetailBySD(spaceDetaillist[i]);
						//判斷預訂時間是否已有人預約
						for(OrderDetailVO orderDetailVO1 : oldorderlist) {
							if(rentStartTimeLong >= (orderDetailVO1.getRentStartTime().getTime()) && rentStartTimeLong < (orderDetailVO1.getRentEndTime().getTime())) {
								errorMsgs.add("該時段開始時間已有人預約");
							}
							if(rentEndTimeLong > (orderDetailVO1.getRentStartTime().getTime()) && rentEndTimeLong <= (orderDetailVO1.getRentEndTime().getTime())) {
								errorMsgs.add("該時段結束時間已有人預約");
							}
							if(rentStartTimeLong <= (orderDetailVO1.getRentStartTime().getTime()) && rentEndTimeLong >= (orderDetailVO1.getRentEndTime().getTime())) {
								errorMsgs.add("該時段內已有人預約");
							}
						}
						//開始建立
						orderDetailVO.setRentStartTime(java.sql.Timestamp.valueOf(rentStartTimelist[i]));
						System.out.println("第"+ (i + 1) +"筆訂單，預訂開始時間：" + rentStartTimelist[i]);
						orderDetailVO.setRentEndTime(java.sql.Timestamp.valueOf(rentEndTimelist[i]));
						System.out.println("第"+ (i + 1) +"筆訂單，預訂結束時間：" + rentEndTimelist[i]);
						odlist.add(orderDetailVO);
						//將訂單金額新增至rentCharge
						rentCharge += (Integer.parseInt(spaceDetailChargelist[i]) * (int)(rentEndTimeLong-rentStartTimeLong)/1000/60/60);
					}
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/listAllSpaceDetail.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				System.out.println("訂單金額共"+rentCharge+"元，設定至OrderMaster的OrderAmount");
				addOrderMaster.setOrderAmount(rentCharge);
				System.out.println("資料無誤，開始新增資料");
				OrderMasterService orderMasterServ = new OrderMasterService();
				orderMasterServ.insertwithOrderDetail(addOrderMaster, odlist);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ********************************/
				req.setAttribute("memberId", addOrderMaster.getMemberId());
				req.setAttribute("selectOneOrderMaster", addOrderMaster);
				String url = "/frontend/ordermaster/selectOneOrderMaster.jsp";
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

				OrderMasterService orderMasterServ = new OrderMasterService();
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
				String orderMasterId = new String(req.getParameter("orderMasterId").trim());

				OrderMasterService orderMasterServ = new OrderMasterService();
				orderMasterServ.deleteOrderMaster(orderMasterId);

				String url = "/frontend/ordermaster/selectAllOrderMaster.jsp";
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
				if (orderMasterId == null || (orderMasterId.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				
				OrderMasterService orderMasterServ = new OrderMasterService();
				OrderMasterVO selectOneUpdate = orderMasterServ.selectOneOrderMaster(orderMasterId);
				
				req.setAttribute("selectOneUpdate", selectOneUpdate);
				String url = "/frontend/ordermaster/updateOrderMaster.jsp";
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
				
				java.sql.Date orderCreateDate = null;
				try {
					orderCreateDate = java.sql.Date.valueOf(req.getParameter("orderCreateDate").trim());
				} catch (IllegalArgumentException e) {
					orderCreateDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				String orderStatus = req.getParameter("orderStatus").trim();
				if(orderStatus == null || orderStatus.isEmpty()) errorMsgs.add("訂單狀態: 請勿空白");
				
				Integer orderAmount = null;
				try {
					orderAmount = Integer.parseInt(req.getParameter("orderAmount").trim());
					if(orderAmount < 0) errorMsgs.add("訂單金額錯誤");
				} catch (NumberFormatException e) {
					errorMsgs.add("訂單金額錯誤");
				}
				
				OrderMasterVO updateOrderMaster = new OrderMasterVO();
				updateOrderMaster.setOrderMasterId(orderMasterId);
				updateOrderMaster.setMemberId(memberId);
				updateOrderMaster.setOrderCreateDate(orderCreateDate);
				updateOrderMaster.setOrderStatus(orderStatus);
				System.out.println();
				updateOrderMaster.setOrderAmount(orderAmount);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateOrderMaster); 
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderMaster/updateOrderMaster.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderMasterService orderMasterServ = new OrderMasterService();
				updateOrderMaster = orderMasterServ.updateOrderMaster(updateOrderMaster);
//				req.setAttribute("selectOneUpdate", updateMemberComm);

				String url = "/frontend/ordermaster/selectAllOrderMaster.jsp";
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
