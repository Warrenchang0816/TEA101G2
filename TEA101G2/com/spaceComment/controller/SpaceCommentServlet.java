package com.spaceComment.controller;

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

import com.spaceComment.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/spacecomment/spacecomment.do")
public class SpaceCommentServlet extends HttpServlet {
	
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
				String str = req.getParameter("spaceCommentId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入場地明細ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacecomment/spaceCommentHome.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacecomment/spaceCommentHome.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				SpaceCommentService spaceCommentSvc = new SpaceCommentService();
				SpaceCommentVO spaceCommentVO = spaceCommentSvc.selectOneSpaceComment(str);
				if (spaceCommentVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacecomment/spaceCommentHome.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("spaceCommentVO", spaceCommentVO); // 資料庫取出的spaceCommentVO物件,存入req
				String url = "/frontend/spacecomment/listOneSpaceComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneSpaceComment.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacecomment/spaceCommentHome.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllSpaceComment.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String spaceCommentId = req.getParameter("spaceCommentId");

				/*************************** 2.開始查詢資料 ****************************************/
				SpaceCommentService spaceCommentSvc = new SpaceCommentService();
				SpaceCommentVO spaceCommentVO = spaceCommentSvc.selectOneSpaceComment(spaceCommentId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("spaceCommentVO", spaceCommentVO); // 資料庫取出的spaceCommentVO物件,存入req
				String url = "/frontend/spacecomment/updateSpaceComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateSpaceComment.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacecomment/listAllSpaceComment.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自updateSpaceComment.jsp的請求
			List<String> errorMessages = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMessages);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String spaceCommentId = new String(req.getParameter("spaceCommentId").trim());
				if (spaceCommentId == null || spaceCommentId.trim().length() == 0) {
					errorMessages.add("場地評價ID請勿空白");
				}

				String spaceId = req.getParameter("spaceId");
				if (spaceId == null || spaceId.trim().length() == 0) {
					errorMessages.add("場地ID請勿空白");
				}
				
				String memId = req.getParameter("memId");
				if (memId == null || memId.trim().length() == 0) {
					errorMessages.add("會員ID請勿空白");
				}
				
				String spaceCommentContent = req.getParameter("spaceCommentContent");
				if (spaceCommentContent == null || spaceCommentContent.trim().length() == 0) {
					errorMessages.add("評價內容請勿空白");
				}
				
				Double spaceCommentLevel = null;
				try {
					spaceCommentLevel = Double.parseDouble(req.getParameter("spaceCommentLevel").trim());
					if(spaceCommentLevel <= 0 || spaceCommentLevel > 5) errorMessages.add("場地評價星等: 請確認");
				} catch (NumberFormatException e) {
					spaceCommentLevel = 0.0;
					errorMessages.add("場地評價星等錯誤");
				}

				java.sql.Date spaceCommentDate = null;
				try {
					spaceCommentDate = java.sql.Date.valueOf(req.getParameter("spaceCommentDate").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("場地評價日期錯誤");
				}
				
				
				SpaceCommentVO spaceCommentVO = new SpaceCommentVO();
				spaceCommentVO.setSpaceCommentId(spaceCommentId);
				spaceCommentVO.setSpaceId(spaceId);
				spaceCommentVO.setMemberId(memId);
				spaceCommentVO.setSpaceCommentContent(spaceCommentContent);
				spaceCommentVO.setSpaceCommentLevel(spaceCommentLevel);
				spaceCommentVO.setSpaceCommentDate(spaceCommentDate);
				
				// Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					req.setAttribute("spaceCommentVO", spaceCommentVO); // 含有輸入格式錯誤的spaceCommentVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacecomment/updateSpaceComment.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				SpaceCommentService spaceCommentSvc = new SpaceCommentService();
				spaceCommentVO = spaceCommentSvc.updateSpaceComment(spaceCommentVO);
				System.out.println(spaceCommentVO);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("spaceCommentVO", spaceCommentVO);
				String url = "/frontend/spacecomment/listOneSpaceComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMessages.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacecomment/updateSpaceComment.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { 
			List<String> errorMessages = new LinkedList<String>();
			 //Store this set in the request scope, in case we need to
			 //send the ErrorPage view.
			req.setAttribute("errorMessages", errorMessages);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				
				String spaceCommentId = new String(req.getParameter("spaceCommentId").trim());
				if (spaceCommentId == null || spaceCommentId.trim().length() == 0) {
					errorMessages.add("場地評價ID請勿空白");
				}

				String spaceId = req.getParameter("spaceId");
				if (spaceId == null || spaceId.trim().length() == 0) {
					errorMessages.add("場地ID請勿空白");
				}
				
				String memId = req.getParameter("memId");
				if (memId == null || memId.trim().length() == 0) {
					errorMessages.add("會員ID請勿空白");
				}
				
				String spaceCommentContent = req.getParameter("spaceCommentContent");
				if (spaceCommentContent == null || spaceCommentContent.trim().length() == 0) {
					errorMessages.add("場地評價內容請勿空白");
				}
				
				Double spaceCommentLevel = null;
				try {
					spaceCommentLevel = Double.parseDouble(req.getParameter("spaceCommentLevel").trim());
					if(spaceCommentLevel <= 0 || spaceCommentLevel > 5) errorMessages.add("場地評價星等: 請確認");
				} catch (NumberFormatException e) {
					spaceCommentLevel = 0.0;
					errorMessages.add("場地評價星等錯誤");
				}

				java.sql.Date spaceCommentDate = null;
				try {
					spaceCommentDate = java.sql.Date.valueOf(req.getParameter("spaceCommentDate").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					errorMessages.add("場地評價日期錯誤");
				}

				
				SpaceCommentVO spaceCommentVO = new SpaceCommentVO();
				spaceCommentVO.setSpaceCommentId(spaceCommentId);
				spaceCommentVO.setSpaceId(spaceId);
				spaceCommentVO.setMemberId(memId);
				spaceCommentVO.setSpaceCommentContent(spaceCommentContent);
				spaceCommentVO.setSpaceCommentLevel(spaceCommentLevel);
				spaceCommentVO.setSpaceCommentDate(spaceCommentDate);
				
				// Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					req.setAttribute("spaceCommentVO", spaceCommentVO); // 含有輸入格式錯誤的spaceCommentVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacecomment/updateSpaceComment.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始新增資料 ***************************************/
				SpaceCommentService spaceCommentSvc = new SpaceCommentService();
				spaceCommentVO = spaceCommentSvc.addSpaceComment(spaceCommentVO);
				System.out.println(spaceCommentVO);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/frontend/spacecomment/listAllSpaceComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMessages.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacecomment/addSpaceComment.jsp");
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
				String spaceCommentId = new String(req.getParameter("spaceCommentId"));

				/*************************** 2.開始刪除資料 ***************************************/
				SpaceCommentService spaceCommentSvc = new SpaceCommentService();
				spaceCommentSvc.deleteSpaceComment(spaceCommentId);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/frontend/spacecomment/listAllSpaceComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacecomment/listAllspaceComment.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
