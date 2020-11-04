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
import com.formList.model.FormListService;
import com.formList.model.FormListVO;
import com.mail.service.MailService;
import com.member.model.MemberServiceB;
import com.member.model.MemberVO;
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
				String memberId = req.getParameter("memberId").trim();
				if (memberId == null || memberId.trim().length() == 0) {
					errorMsgs.add("請先登入會員");
				}
				
				String spaceId = req.getParameter("spaceId");
				if (spaceId == null || (spaceId.trim()).length() == 0) {
					errorMsgs.add("請輸入場地ID");
				}
				
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
				addOrderMaster.setOrderStatus("T");
				
				
				//撈出odlist要用的資料
				String[] spaceDetaillist = null;
				spaceDetaillist = req.getParameterValues("spaceDetailId");
				System.out.println(spaceDetaillist.length);
				
				String[] rentStartTimelist = null;
				rentStartTimelist = req.getParameterValues("rentStartTime");
				for(int i = 0; i < rentStartTimelist.length; i++) {
					System.out.println(rentStartTimelist[i]);
				}
				
				
				String[] rentEndTimelist = null;
				rentEndTimelist = req.getParameterValues("rentEndTime");
				System.out.println(rentEndTimelist);
				
				String[] spaceDetailChargelistStr = null;
				spaceDetailChargelistStr = req.getParameterValues("spaceDetailCharge");
				Integer rentCharge = 0;
				
				
				//撈出資料建立OrderDetailVO，並將OrderMaster總金額修改成訂單總金額
				List<OrderDetailVO> odlist = new ArrayList<OrderDetailVO>();
				OrderDetailService orderDetailSvc = new OrderDetailService();
				SpaceDetailService spaceDetailSvc = new SpaceDetailService();
				
				for (int i = 0; i < rentStartTimelist.length; i++) {
					if(!(rentStartTimelist[i] == null || rentStartTimelist[i].trim().isEmpty() || rentStartTimelist[i].trim().equals("") || rentEndTimelist[i] == null || rentEndTimelist[i].trim().isEmpty() || rentEndTimelist[i].trim().equals(""))){
					
						OrderDetailVO orderDetailVO = new OrderDetailVO();
						orderDetailVO.setSpaceDetailId(spaceDetaillist[i]);
						//判斷訂單時間是否合法
						SpaceDetailVO spaceDetailVO = spaceDetailSvc.selectOneSpaceDetail(spaceDetaillist[i]);
						System.out.println("===================================");
						System.out.println(spaceDetaillist[i]);
						System.out.println(rentStartTimelist[i]);
						System.out.println(spaceDetailVO.getSpaceDetailFreeTimeStart().getTime());
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
						rentCharge += (Integer.parseInt(spaceDetailChargelistStr[i]) * ((int)(rentEndTimeLong-rentStartTimeLong)/1000/60/60));
					}
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					OrderDetailService ods = new OrderDetailService();
					List<SpaceDetailVO> spaceDetailIdList = spaceDetailSvc.getSpaceIdList(spaceId);
					List<SpaceDetailVO> calendarList = spaceDetailSvc.getSpaceIdList(spaceId);
					List<OrderDetailVO> orderDetaillist = new ArrayList<OrderDetailVO>();
					//兩個for迴圈滾出相關orderDetailVO
					for(int i = 0; i < spaceDetailIdList.size(); i++) {
						List<OrderDetailVO> odlisttemp = ods.selectAllOrderDetailBySD(spaceDetailIdList.get(i).getSpaceDetailId());
						System.out.println("取得場地明細編號："+ spaceDetailIdList.get(i).getSpaceDetailId() + "，該時段已被預約" + odlisttemp.size()+"筆資料");
						for(OrderDetailVO odVO : odlisttemp) {
							orderDetaillist.add(odVO);
						}
					}
					
					req.setAttribute("spaceDetailIdList", spaceDetailIdList); // 資料庫取出的spaceDetailVO物件,存入req
					req.setAttribute("spaceId", spaceId);
					req.setAttribute("calendarList", calendarList);
					req.setAttribute("odlist", odlist);
					req.setAttribute("errorMsgs", errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/listAllSpaceDetail.jsp");
					failureView.forward(req, res);
					System.out.println("送回去!!");
					return;// 程式中斷
				}
				
				System.out.println("訂單金額共" + rentCharge + "元，設定至OrderMaster的OrderAmount");
				addOrderMaster.setOrderAmount(rentCharge);
				System.out.println(addOrderMaster);
				System.out.println("資料無誤，開始新增資料");
				OrderMasterService orderMasterServ = new OrderMasterService();
				String orderMasterId = orderMasterServ.insertwithOrderDetail(addOrderMaster, odlist);
				System.out.println("orderMasterId:"+orderMasterId+"設定至orderMasterVO");
				addOrderMaster.setOrderMasterId(orderMasterId);
				System.out.println("設置完成的orderMasterVO:"+addOrderMaster);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ********************************/
				req.setAttribute("orderMasterVO", addOrderMaster);
				
//				req.setAttribute("orderMasterId", orderMasterId);
//				req.setAttribute("odlist", odlist);
				String url = "/frontend/ordermaster/orderCart.jsp";
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
				OrderMasterVO orderMasterVO = orderMasterServ.selectOneOrderMaster(orderMasterId);
				
				if (orderMasterVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/orderMaster/orderMaster.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println(orderMasterVO);
				req.setAttribute("orderMasterVO", orderMasterVO);

				String url = "/frontend/ordermaster/orderCart.jsp";
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
		
		/*************************** 確認付款，更改orderStatus ********************************/
		if ("purchasedone".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String orderMasterId = req.getParameter("orderMasterId").trim();
				if(orderMasterId == null || orderMasterId.isEmpty()) errorMsgs.add("訂單編號錯誤");
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/ordermaster/orderCart.jsp");
					failureView.forward(req, res);
					return;
				}

				OrderMasterService oms = new OrderMasterService();
				oms.purchaseDone(orderMasterId);
//				req.setAttribute("selectOneUpdate", updateMemberComm);
				
				OrderMasterVO omv = oms.selectOneOrderMaster(orderMasterId);
				MemberServiceB msb = new MemberServiceB();
				OrderMasterServiceB omsb = new OrderMasterServiceB();
				String memberId = omsb.selectOneOrderMaster(orderMasterId).getMemberId();
				java.sql.Date createDate = omv.getOrderCreateDate();
				MemberVO m = msb.selectOneMember(memberId);
				String mName = m.getMemberName();
				String mMail = m.getMemberEmail();
				FormListVO addMessage = new FormListVO();
				//*MESSAGE
//				MEMBER_ID----MEM00001
//				EMP_ID----EMP00001
//				FORM_LIST_TYPE----message
//				FORM_SOLU----收件會員
//				FORM_STATUS----'R':已讀,'M':未讀
				addMessage.setMemberId("MEM00001");
				addMessage.setEmpId("EMP00001");
				addMessage.setFormListCreateDate(new java.sql.Date(System.currentTimeMillis()));
				addMessage.setFormListType("message");
				addMessage.setFormListTitle("訂單["+orderMasterId+"] 成立通知!!");
				addMessage.setFormListContext("親愛的會員"+mName+"，您好<br />您預定的訂單已成功建立，有任何問題都可以聯絡我們，感謝。<br />訂單編號: " + orderMasterId + "，成立日期: " + new java.sql.Date(System.currentTimeMillis())) ;
				addMessage.setFormListStatus("M");
				addMessage.setFormListSolu(memberId);
				FormListService formListServ = new FormListService();
				formListServ.addFormList(addMessage);
				
				
				MailService mailService = new MailService();
				String messageText = "親愛的會員: "+ mName + "您好，" + "\n" + "您的訂單已經成立。" + "\n" + "訂單編號:" + orderMasterId ;
				mailService.sendMail(mMail, "訂單成立", messageText);

				String url = "/frontend/ordermaster/purchaseDone.jsp";
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
