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

import com.orderMaster.model.OrderMasterVO;
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
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllSpaceDetail.jsp的請求

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
		
		if ("listAllSpaceDetailBySpace".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/************************** 用spaceId把spaceDetailId撈出來 **********************************/
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
				List<SpaceDetailVO> spaceDetailIdList = spaceDetailSvc.getSpaceIdList(str);
				System.out.println(spaceDetailIdList);
				if (spaceDetailIdList == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/spaceDetailHome");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ********************************/
				req.setAttribute("spaceDetailIdList", spaceDetailIdList); // 資料庫取出的spaceDetailVO物件,存入req
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
		
		if ("update".equals(action)) { // 來自update_spacedetail_input.jsp的請求
			List<String> errorMessages = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMessages);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String spaceDetailId = new String(req.getParameter("spaceDetailId").trim());
				if (spaceDetailId == null || spaceDetailId.trim().length() == 0) {
					errorMessages.add("場地明細ID請勿空白");
				}

				String spaceId = req.getParameter("spaceId");
				if (spaceId == null || spaceId.trim().length() == 0) {
					errorMessages.add("場地ID請勿空白");
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
				
				
				SpaceDetailVO spaceDetailVO = new SpaceDetailVO();
				spaceDetailVO.setSpaceDetailId(spaceDetailId);
				spaceDetailVO.setSpaceId(spaceId);
				spaceDetailVO.setSpaceDetailFreeDate(spaceDetailFreeDate);
				spaceDetailVO.setSpaceDetailFreeTimeStart(spaceDetailFreeTimeStart);
				spaceDetailVO.setSpaceDetailFreeTimeEnd(spaceDetailFreeTimeEnd);
				spaceDetailVO.setSpaceDetailCharge(spaceDetailCharge);
				
				// Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					req.setAttribute("spaceDetailVO", spaceDetailVO); // 含有輸入格式錯誤的spaceDetailVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/updateSpaceDetail.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				SpaceDetailService spaceDetailSvc = new SpaceDetailService();
				spaceDetailVO = spaceDetailSvc.updateSpaceDetail(spaceDetailVO);
				System.out.println(spaceDetailVO);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("spaceDetailVO", spaceDetailVO);
				String url = "/frontend/spacedetail/listOneSpaceDetail.jsp";
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
				
//				String spaceDetailId = new String(req.getParameter("spaceDetailId").trim());
//				if (spaceDetailId == null || spaceDetailId.trim().length() == 0) {
//					errorMessages.add("場地明細ID請勿空白");
//				}

//				String spaceId = req.getParameter("spaceId");
//				if (spaceId == null || spaceId.trim().length() == 0) {
//					errorMessages.add("場地ID請勿空白");
//				}

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
				
				
				SpaceDetailVO spaceDetailVO = new SpaceDetailVO();
				spaceDetailVO.setSpaceId("SPACE00001");
				spaceDetailVO.setSpaceDetailFreeDate(spaceDetailFreeDate);
				spaceDetailVO.setSpaceDetailFreeTimeStart(spaceDetailFreeTimeStart);
				spaceDetailVO.setSpaceDetailFreeTimeEnd(spaceDetailFreeTimeEnd);
				spaceDetailVO.setSpaceDetailCharge(spaceDetailCharge);
				
				
//				 Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					req.setAttribute("spaceDetailVO", spaceDetailVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/addSpaceDetail.jsp");
					failureView.forward(req, res);
				}
				/*************************** 2.新增資料 ***************************************/
				SpaceDetailService spaceDetailSvc = new SpaceDetailService();
				spaceDetailVO = spaceDetailSvc.addSpaceDetail(spaceDetailVO);
				System.out.println(spaceDetailVO);
				
				/*************************** 3.資料轉交(Send the Success view) ***********/
				String url = "/frontend/spacedetail/listAllSpaceDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMessages.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/addSpaceDetail.jsp");
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
				String url = "/frontend/spacedetail/listAllSpaceDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/listAllSpaceDetail.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
