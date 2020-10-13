package com.space.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.space.model.*;
import com.spacePhoto.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/space/space.do")
public class SpaceServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自listAllSpace.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
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

				/*************************** 2.開始查詢資料 *****************************************/
				SpaceService spaceSvc = new SpaceService();
				SpaceVO spaceVO = spaceSvc.selectOneSpace(str);

				if (spaceVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/spaceHome.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("spaceVO", spaceVO); // 資料庫取出的spaceVO物件,存入req
				String url = "/frontend/space/listOneSpace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneSpace.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/spaceHome.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllSpace.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 ****************************************/
			String str = req.getParameter("spaceId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入場地ID");
			}
			/*************************** 2.開始查詢資料 ****************************************/
			SpaceService spaceSvc = new SpaceService();
			SpaceVO spaceVO = spaceSvc.selectOneSpace(str);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("spaceVO", spaceVO); // 資料庫取出的spaceVO物件,存入req
			String url = "/frontend/space/updateSpace.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateSpace.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/listAllSpace.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("update".equals(action)) { // 來自updateSpace.jsp的請求
			List<String> errorMessages = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMessages);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String spaceId = new String(req.getParameter("spaceId").trim());

				String memId = req.getParameter("memId");

				String empId = req.getParameter("empId");

				String spaceAddress = req.getParameter("spaceAddress").trim();
				if (spaceAddress == null || spaceAddress.trim().length() == 0) {
					errorMessages.add("場地地址請勿空白");
				}

				Double spaceLng = null;
				try {
					spaceLng = Double.parseDouble(req.getParameter("spaceLng").trim());
					if (spaceLng < 0)
						errorMessages.add("請確認經度");
				} catch (NumberFormatException e) {
					errorMessages.add("經度錯誤");
				}

				Double spaceLat = null;
				try {
					spaceLat = Double.parseDouble(req.getParameter("spaceLat").trim());
					if (spaceLat > 90 || spaceLat < -90)
						errorMessages.add("請確認緯度");
				} catch (NumberFormatException e) {
					errorMessages.add("緯度錯誤");
				}

				String spaceName = req.getParameter("spaceName");
				// spaceName錯誤處理
				String spaceNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (spaceName == null || spaceName.trim().length() == 0) {
					errorMessages.add("場地名稱: 請勿空白");
				} else if (!spaceName.trim().matches(spaceNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMessages.add("場地名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String spaceText = req.getParameter("spaceText");

				String spaceType = req.getParameter("spaceType");

				String spaceEquipment = req.getParameter("spaceEquipment");

				String spaceContain = req.getParameter("spaceContain");
				if (spaceContain == null || spaceContain.trim().length() == 0) {
					errorMessages.add("場地容納人數請勿空白");
				}

				String spaceRule = req.getParameter("spaceRule");
				if (spaceRule == null || spaceRule.trim().length() == 0) {
					errorMessages.add("場地規則請勿空白");
				}

				String spaceRefund = req.getParameter("spaceRefund");
				if (spaceRule == null || spaceRule.trim().length() == 0) {
					errorMessages.add("場地退款須知請勿空白");
				}

				String spaceStatus = req.getParameter("spaceStatus");

				java.sql.Date spaceSignupDate = null;
				try {
					spaceSignupDate = java.sql.Date.valueOf(req.getParameter("spaceSignupDate").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}

				java.sql.Date spaceOnsaleDate = null;
				try {
					spaceOnsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOnsaleDate").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("商品上架日期錯誤");
				}

				java.sql.Date spaceOffsaleDate = null;
				try {
					spaceOffsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOffsaleDate").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("商品下架日期錯誤");
				}

				SpaceVO spaceVO = new SpaceVO();
				spaceVO.setSpaceId(spaceId);
				spaceVO.setMemberId(memId);
				spaceVO.setEmpId(empId);
				spaceVO.setSpaceAddress(spaceAddress);
				spaceVO.setSpaceLng(spaceLng);
				spaceVO.setSpaceLat(spaceLat);
				spaceVO.setSpaceName(spaceName);
				spaceVO.setSpaceText(spaceText);
				spaceVO.setSpaceType(spaceType);
				spaceVO.setSpaceEquipment(spaceEquipment);
				spaceVO.setSpaceContain(spaceContain);
				spaceVO.setSpaceRule(spaceRule);
				spaceVO.setSpaceRefund(spaceRefund);
				spaceVO.setSpaceStatus(spaceStatus);
				spaceVO.setSpaceSignupDate(spaceSignupDate);
				spaceVO.setSpaceOnsaleDate(spaceOnsaleDate);
				spaceVO.setSpaceOffsaleDate(spaceOffsaleDate);

				// Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					req.setAttribute("spaceVO", spaceVO); // 含有輸入格式錯誤的spaceVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/updateSpace.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				SpaceService spaceSvc = new SpaceService();
				spaceVO = spaceSvc.updateSpace(spaceVO);
				System.out.println(spaceVO);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("spaceVO", spaceVO); // 資料庫update成功後,正確的的spaceVO物件,存入req
				String url = "/frontend/space/listOneSpace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneSpace.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMessages.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/updateSpace.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			List<String> errorMessages = new LinkedList<String>();
//			 Store this set in the request scope, in case we need to
//			 send the ErrorPage view.
			req.setAttribute("errorMessages", errorMessages);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

//				String spaceId = new String(req.getParameter("spaceId").trim());

				String memId = req.getParameter("memId");

				String empId = req.getParameter("empId");

				String spaceAddress = req.getParameter("spaceAddress").trim();
				if (spaceAddress == null || spaceAddress.trim().length() == 0) {
					errorMessages.add("場地地址請勿空白");
				}

				Double spaceLng = null;
				try {
					spaceLng = Double.parseDouble(req.getParameter("spaceLng").trim());
					if (spaceLng < 0)
						errorMessages.add("請確認經度");
				} catch (NumberFormatException e) {
					errorMessages.add("經度錯誤");
				}

				Double spaceLat = null;
				try {
					spaceLat = Double.parseDouble(req.getParameter("spaceLat").trim());
					if (spaceLat > 90 || spaceLat < -90)
						errorMessages.add("請確認緯度");
				} catch (NumberFormatException e) {
					errorMessages.add("緯度錯誤");
				}

				String spaceName = req.getParameter("spaceName");
				// spaceName錯誤處理
				String spaceNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (spaceName == null || spaceName.trim().length() == 0) {
					errorMessages.add("場地名稱: 請勿空白");
				} else if (!spaceName.trim().matches(spaceNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMessages.add("場地名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String spaceText = req.getParameter("spaceText");
				if (spaceText == null || spaceText.trim().length() == 0) {
					errorMessages.add("場地介紹請勿空白");
				}

				String spaceType = req.getParameter("spaceType");
				if (spaceText == null || spaceText.trim().length() == 0) {
					errorMessages.add("場地類型請勿空白");
				}

				String spaceEquipment = req.getParameter("spaceEquipment");

				String spaceContain = req.getParameter("spaceContain");
				if (spaceContain == null || spaceContain.trim().length() == 0) {
					errorMessages.add("場地容納人數請勿空白");
				}

				String spaceRule = req.getParameter("spaceRule");
				if (spaceRule == null || spaceRule.trim().length() == 0) {
					errorMessages.add("場地規則請勿空白");
				}

				String spaceRefund = req.getParameter("spaceRefund");
				if (spaceRule == null || spaceRule.trim().length() == 0) {
					errorMessages.add("場地退款須知請勿空白");
				}

				java.sql.Date spaceSignupDate = null;
				try {
					spaceSignupDate = new java.sql.Date(System.currentTimeMillis());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}

				SpaceVO spaceVO = new SpaceVO();
//				spaceVO.setSpaceId(spaceId);
				spaceVO.setMemberId(memId);
				spaceVO.setEmpId("EMP00001");
				spaceVO.setSpaceAddress(spaceAddress);
				spaceVO.setSpaceLng(spaceLng);
				spaceVO.setSpaceLat(spaceLat);
				spaceVO.setSpaceName(spaceName);
				spaceVO.setSpaceText(spaceText);
				spaceVO.setSpaceType(spaceType);
				spaceVO.setSpaceEquipment(spaceEquipment);
				spaceVO.setSpaceContain(spaceContain);
				spaceVO.setSpaceRule(spaceRule);
				spaceVO.setSpaceRefund(spaceRefund);
				spaceVO.setSpaceStatus("F");
				spaceVO.setSpaceSignupDate(spaceSignupDate);
				spaceVO.setSpaceOnsaleDate(null);
				spaceVO.setSpaceOffsaleDate(null);

//				 Send the use back to the form, if there were errors
//				if (!errorMessages.isEmpty()) {
//					System.out.println("輸入格式錯誤");
//					req.setAttribute("spaceVO", spaceVO); // 含有輸入格式錯誤的spaceVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/addSpace.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}
				/*************************** 2.開始新增資料 ***************************************/
				SpaceService spaceSvc = new SpaceService();
				spaceVO = spaceSvc.addSpace(spaceVO);
				System.out.println(spaceVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/frontend/space/listAllSpace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllSpace.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMessages.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/addSpace.jsp");
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
				String spaceId = new String(req.getParameter("spaceId"));

				/*************************** 2.開始刪除資料 ***************************************/
				SpaceService spaceSvc = new SpaceService();
				spaceSvc.deleteSpace(spaceId);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/frontend/space/listAllSpace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/listAllSpace.jsp");
				failureView.forward(req, res);
			}
		}

		if ("search_space".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String spaceAddress = req.getParameter("spaceAddress");
				String spaceType = req.getParameter("spaceType");
				String spaceContain = req.getParameter("spaceContain");
				System.out.println(spaceAddress);
				System.out.println(spaceType);
				System.out.println(spaceContain);
				
				SpaceService spaceSvc = new SpaceService();
				List<SpaceVO> spaceVO = spaceSvc.searchSpace(spaceAddress, spaceType, spaceContain);
				for(SpaceVO list : spaceVO) {
					System.out.println(list.getSpaceAddress());
				}
				req.setAttribute("spaceVO", spaceVO);
				
				//TODO ****************************************
//				String url = "/frontend/space/todo";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("查詢資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/listAllSpace.jsp");
				failureView.forward(req, res);
			}

		}
	}
}
