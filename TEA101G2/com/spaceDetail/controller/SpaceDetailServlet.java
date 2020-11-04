package com.spaceDetail.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.orderDetail.model.OrderDetailService;
import com.orderDetail.model.OrderDetailVO;
import com.orderMaster.model.OrderMasterVO;
import com.space.model.SpaceService;
import com.space.model.SpaceVO;
import com.spaceDetail.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/spacedetail/spacedetail.do")
public class SpaceDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("spaceDetailId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("場地明細ID請勿空白");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/spaceDetailHome");
					failureView.forward(req, res);
					return;// 程式中斷
				}


				/*************************** 2.開始查詢資料 *****************************************/
				SpaceDetailService spaceDetailSvc = new SpaceDetailService();
				SpaceDetailVO spaceDetailVO = spaceDetailSvc.selectOneSpaceDetail(str);
				if (spaceDetailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/spaceDetailHome");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ********************************/
				req.setAttribute("spaceDetailVO", spaceDetailVO); // 資料庫取出的spaceDetailVO物件,存入req
				String url = "/frontend/spacedetail/listOneSpaceDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneSpaceDetail.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/spaceDetailHome");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllSpaceDetailForEdit.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String spaceDetailId = req.getParameter("spaceDetailId");

				/*************************** 2.開始查詢資料 ****************************************/
				SpaceDetailService spaceDetailSvc = new SpaceDetailService();
				SpaceDetailVO spaceDetailVO = spaceDetailSvc.selectOneSpaceDetail(spaceDetailId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("spaceDetailVO", spaceDetailVO); // 資料庫取出的spaceDetailVO物件,存入req
				String url = "/frontend/spacedetail/updateSpaceDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_spacedetail_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/listAllSpaceDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		/***************************** 場主按下"編輯場地明細"按鈕後執行 *****************************/
		if ("listAllSpaceDetailBySpaceForEdit".equals(action)) {
			Queue<String> errorMessages = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMessages);
			
			try {
				/************************** 用spaceId把spaceDetailId撈出來 ******************************/
				String spaceId = req.getParameter("spaceId");
				if (spaceId == null || (spaceId.trim()).length() == 0) {
					errorMessages.add("請輸入場地ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/home.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/************************************* 開始撈資料 *************************************************/
				SpaceDetailService spaceDetailSvc = new SpaceDetailService();
				OrderDetailService ods = new OrderDetailService();
				List<SpaceDetailVO> spaceDetailIdList = spaceDetailSvc.getSpaceIdList(spaceId);
				if (spaceDetailIdList == null) {
					errorMessages.add("查無資料");
				}
				List<SpaceDetailVO> calendarList = spaceDetailSvc.getSpaceIdList(spaceId);
				List<OrderDetailVO> odlist = new ArrayList<OrderDetailVO>();
				//兩個for迴圈滾出相關orderDetailVO
				for(int i = 0; i < spaceDetailIdList.size(); i++) {
					List<OrderDetailVO> odlisttemp = ods.selectAllOrderDetailBySD(spaceDetailIdList.get(i).getSpaceDetailId());
					System.out.println("取得場地明細編號："+ spaceDetailIdList.get(i).getSpaceDetailId() + "，該時段已被預約" + odlisttemp.size()+"筆資料");
					for(OrderDetailVO odVO : odlisttemp) {
						odlist.add(odVO);
					}
				}
				
				System.out.println("取得存取好的odlist，共" + odlist.size() + "張訂單相關");
				System.out.println("日期轉換完成，資料如下" + spaceDetailIdList);
				// Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					SpaceService ss = new SpaceService();
					SpaceVO spaceVO = ss.selectOneSpace(spaceId);
					req.setAttribute("spaceVO", spaceVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/listOneSpace.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ********************************/
				req.setAttribute("spaceDetailIdList", spaceDetailIdList); // 資料庫取出的spaceDetailVO物件,存入req
				req.setAttribute("spaceId", spaceId);
				req.setAttribute("calendarList", calendarList);
				req.setAttribute("odlist", odlist);
				String url = "/frontend/spacedetail/listAllSpaceDetailForEdit.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMessages.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}
		/************************** 按下listOneSpace中訂購按鈕後執行 ******************************/
		if ("listAllSpaceDetailBySpace".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/************************** 用spaceId把spaceDetailId撈出來 ******************************/
				String str = req.getParameter("spaceId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入場地ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/spaceHome.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/************************************* 開始撈資料 *************************************************/
				SpaceDetailService spaceDetailSvc = new SpaceDetailService();
				OrderDetailService ods = new OrderDetailService();
				List<SpaceDetailVO> spaceDetailIdList = spaceDetailSvc.getSpaceIdList(str);
				List<SpaceDetailVO> calendarList = spaceDetailSvc.getSpaceIdList(str);
				List<OrderDetailVO> odlist = new ArrayList<OrderDetailVO>();
				//兩個for迴圈滾出相關orderDetailVO
				for(int i = 0; i < spaceDetailIdList.size(); i++) {
					List<OrderDetailVO> odlisttemp = ods.selectAllOrderDetailBySD(spaceDetailIdList.get(i).getSpaceDetailId());
					System.out.println("取得場地明細編號："+ spaceDetailIdList.get(i).getSpaceDetailId() + "，該時段已被預約" + odlisttemp.size()+"筆資料");
					for(OrderDetailVO odVO : odlisttemp) {
						odlist.add(odVO);
					}
				}
				
				System.out.println("取得存取好的odlist，共" + odlist.size() + "張訂單相關");
				System.out.println("日期轉換完成，資料如下" + spaceDetailIdList);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/home.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ********************************/
				req.setAttribute("spaceDetailIdList", spaceDetailIdList); // 資料庫取出的spaceDetailVO物件,存入req
				req.setAttribute("calendarList", calendarList);
				req.setAttribute("odlist", odlist);
				req.setAttribute("spaceId", str);
				String url = "/frontend/spacedetail/listAllSpaceDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自updateSpaceDetail.jsp的請求
			List<String> errorMessages = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMessages", errorMessages);
			List<String> justChangeAmount = new LinkedList<String>();
			req.setAttribute("justChangeAmount", justChangeAmount);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String spaceDetailId = new String(req.getParameter("spaceDetailId").trim());
				if (spaceDetailId == null || spaceDetailId.trim().length() == 0) {
					errorMessages.add("場地明細ID是空白的!");
				}

				String spaceId = req.getParameter("spaceId");
				if (spaceId == null || spaceId.trim().length() == 0) {
					errorMessages.add("場地ID是空白的!");
				}

				java.sql.Date spaceDetailFreeDate = null;
				try {
					spaceDetailFreeDate = java.sql.Date.valueOf(req.getParameter("spaceDetailFreeDate").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("場地開放日期錯誤");
				}
				
				java.sql.Timestamp spaceDetailFreeTimeStart = null;
				try {
					spaceDetailFreeTimeStart = java.sql.Timestamp.valueOf(req.getParameter("spaceDetailFreeTimeStart").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("場地開放起始時間錯誤");
				}
				
				java.sql.Timestamp spaceDetailFreeTimeEnd = null;
				try {
					spaceDetailFreeTimeEnd = java.sql.Timestamp.valueOf(req.getParameter("spaceDetailFreeTimeEnd").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("場地開放結束時間錯誤");
				}
				
				Integer spaceDetailCharge = null;
				try {
					spaceDetailCharge = Integer.parseInt(req.getParameter("spaceDetailCharge").trim());
					if(spaceDetailCharge <= 0) errorMessages.add("場地租借費用: 請確認金額");
				} catch (NumberFormatException e) {
					spaceDetailCharge = 0;
					errorMessages.add("場地租借費用: 必須為數字");
				}
				
				/*************************** 判斷新的場地明細時間是否與已建立的場地明細時間重覆 ********************************/
				SpaceDetailService spaceDetailSvc = new SpaceDetailService();
				List<SpaceDetailVO> spaceDetailIdList = spaceDetailSvc.getSpaceIdList(spaceId);
				Long freeTimeStart = spaceDetailFreeTimeStart.getTime();
				Long freeTimeEnd = spaceDetailFreeTimeEnd.getTime();
				
				if(freeTimeStart == null || freeTimeStart.equals(null) || freeTimeEnd == null || freeTimeEnd.equals(null)) {
					errorMessages.add("請輸入場地租借起始/結束時間");
				}
				
				if(freeTimeEnd-freeTimeStart <= 0) {
					errorMessages.add("請確認場地租借起始/結束時間");
				}
				
				for(SpaceDetailVO spaceDetailVO : spaceDetailIdList) {
					Long oldFreeTimeStart = spaceDetailVO.getSpaceDetailFreeTimeStart().getTime();
					Long oldFreeTimeEnd = spaceDetailVO.getSpaceDetailFreeTimeEnd().getTime();
					System.out.print(oldFreeTimeStart+"/");
					System.out.print(oldFreeTimeEnd+"/");
					System.out.print(freeTimeStart+"/");
					System.out.println(freeTimeEnd);
					if((freeTimeStart).equals(oldFreeTimeStart) && (freeTimeEnd).equals(oldFreeTimeEnd)) {
						justChangeAmount.add("只是改個金額，放行!");
					}else if((freeTimeStart >= oldFreeTimeStart) && (freeTimeStart < oldFreeTimeEnd)) {
						errorMessages.add("該時段起始時間已有設定");
					}else if((freeTimeEnd > oldFreeTimeStart) && (freeTimeEnd <= oldFreeTimeEnd)) {
						errorMessages.add("該時段結束時間已有設定");
					}else if((freeTimeStart <= oldFreeTimeStart) && (freeTimeEnd >= oldFreeTimeEnd)) {
						errorMessages.add("該時段已設定付費");
					}
				}
				
				System.out.println("改金額而已"+justChangeAmount);
				SpaceDetailVO spaceDetailVO = new SpaceDetailVO();
				spaceDetailVO.setSpaceDetailId(spaceDetailId);
				spaceDetailVO.setSpaceId(spaceId);
				spaceDetailVO.setSpaceDetailFreeDate(spaceDetailFreeDate);
				spaceDetailVO.setSpaceDetailFreeTimeStart(spaceDetailFreeTimeStart);
				spaceDetailVO.setSpaceDetailFreeTimeEnd(spaceDetailFreeTimeEnd);
				spaceDetailVO.setSpaceDetailCharge(spaceDetailCharge);
				
				// Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					if(justChangeAmount.isEmpty()) {
						req.setAttribute("spaceDetailVO", spaceDetailVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/updateSpaceDetail.jsp");
						failureView.forward(req, res);
						System.out.println("知錯了...準備轉交原畫面");
						return;
					}
				}

				/*************************** 2.開始修改資料 *****************************************/
				spaceDetailVO = spaceDetailSvc.updateSpaceDetail(spaceDetailVO);
				SpaceService svc = new SpaceService();
				SpaceVO spaceVO = svc.selectOneSpace(spaceId);
				System.out.println(spaceDetailVO + "修改完畢");
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("spaceVO", spaceVO);
				req.setAttribute("spaceDetailVO", spaceDetailVO);
				String url = "/frontend/space/listOneSpace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMessages.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/updateSpaceDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // addSpaceDetail.jsp
			List<String> errorMessages = new LinkedList<String>();
			 //Store this set in the request scope, in case we need to
			 //send the ErrorPage view.
			req.setAttribute("errorMessages", errorMessages);

			try {

				String spaceId = req.getParameter("spaceId");
				if (spaceId == null || spaceId.trim().length() == 0) {
					errorMessages.add("場地ID是空白的!!");
				}

				java.sql.Date spaceDetailFreeDate = null;
				try {
					spaceDetailFreeDate = java.sql.Date.valueOf(req.getParameter("spaceDetailFreeDate").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("場地開放日期錯誤");
				}
				
				java.sql.Timestamp spaceDetailFreeTimeStart = null;
				try {
					spaceDetailFreeTimeStart = java.sql.Timestamp.valueOf(req.getParameter("spaceDetailFreeTimeStart").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("場地開放起始時間錯誤");
				}
				
				java.sql.Timestamp spaceDetailFreeTimeEnd = null;
				try {
					spaceDetailFreeTimeEnd = java.sql.Timestamp.valueOf(req.getParameter("spaceDetailFreeTimeEnd").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("場地開放結束時間錯誤");
				}
				
				Integer spaceDetailCharge = null;
				try {
					spaceDetailCharge = Integer.parseInt(req.getParameter("spaceDetailCharge").trim());
					if(spaceDetailCharge <= 0) errorMessages.add("場地租借費用: 請確認金額");
				} catch (NumberFormatException e) {
					spaceDetailCharge = 0;
					errorMessages.add("場地租借費用: 必須為數字");
				}
				
				/*************************** 判斷新的場地明細時間是否與已建立的場地明細時間重覆 ********************************/
				SpaceDetailService spaceDetailSvc = new SpaceDetailService();
				List<SpaceDetailVO> spaceDetailIdList = spaceDetailSvc.getSpaceIdList(spaceId);
				if (spaceDetailIdList == null) {
					errorMessages.add("查無資料");
				}
				Long freeTimeStart = spaceDetailFreeTimeStart.getTime();
				Long freeTimeEnd = spaceDetailFreeTimeEnd.getTime();
				
				if(freeTimeStart == null || freeTimeStart.equals(null) || freeTimeEnd == null || freeTimeEnd.equals(null)) {
					errorMessages.add("請輸入場地租借起始/結束時間");
				}
				
				if(freeTimeEnd-freeTimeStart <= 0) {
					errorMessages.add("請確認場地租借起始/結束時間");
				}
				
				for(SpaceDetailVO spaceDetailVO : spaceDetailIdList) {
					Long oldFreeTimeStart = spaceDetailVO.getSpaceDetailFreeTimeStart().getTime();
					Long oldFreeTimeEnd = spaceDetailVO.getSpaceDetailFreeTimeEnd().getTime();
					if((freeTimeStart >= oldFreeTimeStart) && (freeTimeStart < oldFreeTimeEnd)) {
						errorMessages.add("該時段起始時間已有設定");
					}
					if((freeTimeEnd > oldFreeTimeStart) && (freeTimeEnd <= oldFreeTimeEnd)) {
						errorMessages.add("該時段結束時間已有設定");
					}
					if((freeTimeStart <= oldFreeTimeStart) && (freeTimeEnd >= oldFreeTimeEnd)) {
						errorMessages.add("該時段已有資料");
					}
				}
				
				SpaceDetailVO spaceDetailVO = new SpaceDetailVO();
				spaceDetailVO.setSpaceId(spaceId);
				spaceDetailVO.setSpaceDetailFreeDate(spaceDetailFreeDate);
				spaceDetailVO.setSpaceDetailFreeTimeStart(spaceDetailFreeTimeStart);
				spaceDetailVO.setSpaceDetailFreeTimeEnd(spaceDetailFreeTimeEnd);
				spaceDetailVO.setSpaceDetailCharge(spaceDetailCharge);
				
				System.out.println(errorMessages);
//				 Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					SpaceService ss = new SpaceService();
					SpaceVO spaceVO = ss.selectOneSpace(spaceId);
					req.setAttribute("spaceVO", spaceVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/listOneSpace.jsp");
					failureView.forward(req, res);
					System.out.println("知錯了...準備轉交原畫面");
					return;
				}
				/*************************** 2.新增資料 ***************************************/
				SpaceDetailVO sdvo = spaceDetailSvc.addSpaceDetail(spaceDetailVO);
				System.out.println("新增的訂單明細：" + sdvo);
				SpaceService ss = new SpaceService();
				SpaceVO spaceVO = ss.selectOneSpace(spaceId);
				
				/*************************** 3.資料轉交(Send the Success view) ***********/
				req.setAttribute("spaceVO", spaceVO);
				String url = "/frontend/space/listOneSpace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMessages.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/home.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllSpace.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String spaceDetailId = new String(req.getParameter("spaceDetailId"));

				/*************************** 2.開始刪除資料 ***************************************/
				SpaceDetailService spaceDetailSvc = new SpaceDetailService();
				spaceDetailSvc.deleteSpaceDetail(spaceDetailId);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/frontend/space/listAllSpaceForEdit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交listAllSpaceForEdit.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/listAllSpaceDetailForEdit.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
