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
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/home.jsp");
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
				errorMsgs.add("場地ID錯誤");
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
			System.out.println("FUCKKKKKK");
			List<String> errorMessages = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMessages);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String spaceId = req.getParameter("spaceId").trim();
				if (spaceId == null || spaceId.trim().length() == 0) {
					errorMessages.add("請確認場地");
				}
				
				String spaceAddress = req.getParameter("spaceAddress").trim();
				System.out.println("spaceAddress"+spaceAddress);
				if (spaceAddress == null || spaceAddress.trim().length() == 0) {
					errorMessages.add("場地地址請勿空白");
				}

//				Double spaceLng = null;
//				try {
//					spaceLng = Double.parseDouble(req.getParameter("spaceLng").trim());
//					if(spaceLng < 0) errorMessages.add("請確認經度");
//				} catch (NumberFormatException e) {
//					errorMessages.add("經度錯誤");
//				}
//				
//				Double spaceLat = null;
//				try {
//					spaceLat = Double.parseDouble(req.getParameter("spaceLat").trim());
//					if(spaceLat > 90 || spaceLat < -90) errorMessages.add("請確認緯度");
//				} catch (NumberFormatException e) {
//					errorMessages.add("緯度錯誤");
//				}

				String spaceName = req.getParameter("spaceName");
				// spaceName錯誤處理
				String spaceNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,90}$";
				System.out.println("spaceName:"+spaceName);
				if (spaceName == null || spaceName.trim().length() == 0) {
					errorMessages.add("場地名稱: 請勿空白");
				} else if (!spaceName.trim().matches(spaceNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMessages.add("場地名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String spaceText = req.getParameter("spaceText");
				System.out.println("spaceName:"+spaceName);
				String spaceType = req.getParameter("spaceType");
				System.out.println("spaceName:"+spaceName);
				if (spaceText == null || spaceText.trim().length() == 0) {
					errorMessages.add("場地類型請勿空白");
				}

				String[] spaceEquipment = req.getParameterValues("spaceEquipment");
				System.out.println(spaceEquipment);
				StringBuilder sbstr = new StringBuilder();
				for (String sob : spaceEquipment) {
					sbstr.append(sob);
				}
				System.out.println("場地設備有" + sbstr);

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

				java.sql.Date spaceOnsaleDate = null;
				try {
					spaceOnsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOnsaleDate").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("場地上架日期錯誤");
				}

				java.sql.Date spaceOffsaleDate = null;
				try {
					spaceOffsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOffsaleDate").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("場地下架日期錯誤");
				}

				SpaceVO spaceVO = new SpaceVO();
				spaceVO.setSpaceId(spaceId);
				spaceVO.setSpaceAddress(spaceAddress);
				spaceVO.setSpaceText(spaceText);
				spaceVO.setSpaceName(spaceName);
				spaceVO.setSpaceText(spaceText);
				spaceVO.setSpaceType(spaceType);
				spaceVO.setSpaceEquipment(sbstr.toString());
				spaceVO.setSpaceContain(spaceContain);
				spaceVO.setSpaceRule(spaceRule);
				spaceVO.setSpaceRefund(spaceRefund);
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
				spaceSvc.updateSpace(spaceVO);
				spaceVO = spaceSvc.selectOneSpace(spaceId);
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

		/***************************************
		 * 修改後申請上架
		 *******************************************/
		if ("applyforsale".equals(action)) { // 來自updateSpace.jsp的請求
			List<String> errorMessages = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMessages);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String spaceId = req.getParameter("spaceId");
				if (spaceId == null || (spaceId.trim()).length() == 0) {
					errorMessages.add("場地ID錯誤");
				}
				/*************************** 2.開始查詢資料 ****************************************/
				SpaceVO spaceVO = new SpaceVO();
				SpaceService spaceSvc = new SpaceService();
				/*******************************
				 * 先把整個SpaceVO滾出來，再設定要改的部分
				 ***************************/
				spaceVO = spaceSvc.selectOneSpace(spaceId);
				spaceVO.setSpaceStatus("N");
				spaceVO = spaceSvc.updateSpaceStatus(spaceVO);
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
				String memberId = req.getParameter("memberId").trim();
				if (memberId == null || memberId.trim().length() == 0) {
					errorMessages.add("請先登入會員");
				}

				String spaceAddress = req.getParameter("spaceAddress").trim();
				if (spaceAddress == null || spaceAddress.trim().length() == 0) {
					errorMessages.add("場地地址請勿空白");
				}

//				Double spaceLng = null;
//				try {
//					spaceLng = Double.parseDouble(req.getParameter("spaceLng").trim());
//					if(spaceLng < 0) errorMessages.add("請確認經度");
//				} catch (NumberFormatException e) {
//					errorMessages.add("經度錯誤");
//				}
//				
//				Double spaceLat = null;
//				try {
//					spaceLat = Double.parseDouble(req.getParameter("spaceLat").trim());
//					if(spaceLat > 90 || spaceLat < -90) errorMessages.add("請確認緯度");
//				} catch (NumberFormatException e) {
//					errorMessages.add("緯度錯誤");
//				}

				String spaceName = req.getParameter("spaceName");
				// spaceName錯誤處理
				String spaceNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,90}$";
				if (spaceName == null || spaceName.trim().length() == 0) {
					errorMessages.add("場地名稱: 請勿空白");
				} else if (!spaceName.trim().matches(spaceNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMessages.add("場地名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String spaceText = req.getParameter("spaceText");

				String spaceType = req.getParameter("spaceType");
				if (spaceText == null || spaceText.trim().length() == 0) {
					errorMessages.add("場地類型請勿空白");
				}

				String[] spaceEquipment = req.getParameterValues("spaceEquipment");
				StringBuilder sbstr = new StringBuilder();
				for (String sob : spaceEquipment) {
					sbstr.append(sob);
				}
				System.out.println("場地設備有" + sbstr);

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

				java.sql.Date spaceOnsaleDate = null;
				try {
					spaceOnsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOnsaleDate").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("場地上架日期錯誤");
				}

				java.sql.Date spaceOffsaleDate = null;
				try {
					spaceOffsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOffsaleDate").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("場地下架日期錯誤");
				}

				SpaceVO spaceVO = new SpaceVO();
				spaceVO.setMemberId(memberId);
				spaceVO.setEmpId("EMP00001");
				spaceVO.setSpaceAddress(spaceAddress);
				spaceVO.setSpaceLng(12.00);
				spaceVO.setSpaceLat(34.00);
				spaceVO.setSpaceName(spaceName);
				spaceVO.setSpaceText(spaceText);
				spaceVO.setSpaceType(spaceType);
				spaceVO.setSpaceEquipment(sbstr.toString());
				spaceVO.setSpaceContain(spaceContain);
				spaceVO.setSpaceRule(spaceRule);
				spaceVO.setSpaceRefund(spaceRefund);
				spaceVO.setSpaceStatus("F");
				spaceVO.setSpaceSignupDate(spaceSignupDate);
				spaceVO.setSpaceOnsaleDate(spaceOnsaleDate);
				spaceVO.setSpaceOffsaleDate(spaceOffsaleDate);

//				 Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					System.out.println("輸入格式錯誤");
					req.setAttribute("spaceVO", spaceVO); // 含有輸入格式錯誤的spaceVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/addSpace.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始新增資料 ***************************************/
				SpaceService spaceSvc = new SpaceService();
				spaceVO = spaceSvc.addSpace(spaceVO);
				System.out.println(spaceVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/frontend/space/memberSpace.jsp";
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
				String subQuery = req.getParameter("subQueryCommand");
				String spaceAddress = req.getParameter("spaceAddress");
				String spaceType = req.getParameter("spaceType");
				String spaceContain = req.getParameter("spaceContain");

				StringBuffer searchSpace = new StringBuffer("SELECT * FROM SPACE WHERE 123=123");

				if (subQuery != null && !subQuery.trim().equals("")) {
					searchSpace.insert(13, "(" + subQuery + ")");
				}

				if (spaceAddress != null && !spaceAddress.trim().equals("")) {
					searchSpace.append(" AND SPACE_ADDRESS LIKE '%" + spaceAddress + "%'");
				}

				if (spaceType != null && !spaceType.trim().equals("")) {
					searchSpace.append(" AND SPACE_TYPE ='" + spaceType + "'");
				}

				if (spaceContain != null && !spaceContain.trim().equals("")) {
					searchSpace.append(" AND SPACE_CONTAIN >= '" + spaceContain + "'");
				}

				String subQueryCommand = searchSpace.toString();
				SpaceService spaceSvc = new SpaceService();
				List<SpaceVO> spaceVO = spaceSvc.searchSpace(subQueryCommand, spaceAddress, spaceType, spaceContain);

				req.setAttribute("subQueryCommand", subQueryCommand);
				req.setAttribute("spaceVO", spaceVO);

				String url = "/frontend/searchResults.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("查詢資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/listAllSpace.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
