package com.space.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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

import com.space.model.SpaceService;
import com.space.model.SpaceVO;

@WebServlet("/SpaceServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class SpaceServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public SpaceServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("backend_AddSpace".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberId = req.getParameter("memberId").trim();
//				String memberIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(memberId == null || memberId.isEmpty()) {
					errorMsgs.add("會員編號: 請勿空白");
				}
				
				String empId = req.getParameter("empId").trim();
				if(empId == null || empId.isEmpty()) {
					errorMsgs.add("員工編號: 請勿空白");
				}
				
				String spaceAddress = req.getParameter("spaceAddress").trim();
				if(spaceAddress == null || spaceAddress.isEmpty()) {
					errorMsgs.add("場地地址: 請勿空白");
				}
				
				String spaceName = req.getParameter("spaceName").trim();
				if(spaceName == null || spaceName.isEmpty()) {
					errorMsgs.add("場地名稱: 請勿空白");
				}
				
				String spaceType = req.getParameter("spaceType").trim();
				if(spaceType == null || spaceType.isEmpty()) {
					errorMsgs.add("場地類型: 請勿空白");
				}
				
				String spaceEqument = req.getParameter("spaceEqument").trim();
				if(spaceEqument == null || spaceEqument.isEmpty()) {
					errorMsgs.add("場地設備: 請勿空白");
				}
				
				String spaceContain = req.getParameter("spaceContain").trim();
				if(spaceContain == null || spaceContain.isEmpty()) {
					errorMsgs.add("場地容納人數: 請勿空白");
				}
				
				String spaceRule = req.getParameter("spaceRule").trim();
				if(spaceRule == null || spaceRule.isEmpty()) {
					errorMsgs.add("場地規範: 請勿空白");
				}
				
				String spaceRefund = req.getParameter("spaceRefund").trim();
				if(spaceRefund == null || spaceRefund.isEmpty()) {
					errorMsgs.add("退費機制: 請勿空白");
				}
				
				String spaceStatus = req.getParameter("spaceStatus").trim();
				if(spaceStatus == null || spaceStatus.isEmpty()) {
					errorMsgs.add("場地狀態: 請勿空白");
				}
				
				Date spaceSignupDate = null;
				try {
					spaceSignupDate = java.sql.Date.valueOf(req.getParameter("spaceSignupDate").trim());
				} catch (IllegalArgumentException e) {
					spaceSignupDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("場地申請日期: 請勿空白");
				}
				
				Date spaceOnsaleDate = null;
				try {
					spaceOnsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOnsaleDate").trim());
				} catch (IllegalArgumentException e) {
					spaceOnsaleDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				Date spaceOffsaleDate;
				try {
					spaceOffsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOffsaleDate").trim());
				} catch (IllegalArgumentException e) {
					spaceOffsaleDate = new java.sql.Date(System.currentTimeMillis());
				}

				SpaceVO addSpace = new SpaceVO();
				addSpace.setMemberId(memberId);
				addSpace.setEmpId(empId);
				addSpace.setSpaceAddress(spaceAddress);
				addSpace.setSpaceName(spaceName);
				addSpace.setSpaceType(spaceType);
				addSpace.setSpaceEqument(spaceEqument);
				addSpace.setSpaceContain(spaceContain);
				addSpace.setSpaceRule(spaceRule);
				addSpace.setSpaceRefund(spaceRefund);
				addSpace.setSpaceStatus(spaceStatus);
				addSpace.setSpaceSignupDate(spaceSignupDate);
				addSpace.setSpaceOnsaleDate(spaceOnsaleDate);
				addSpace.setSpaceOffsaleDate(spaceOffsaleDate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addSpace", addSpace); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/space/addSpace.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceService spaceServ = new SpaceService();
				addSpace = spaceServ.addSpace(addSpace);

				String url = "/backend/space/selectAllSapce.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_SelectOneSpace".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceId = req.getParameter("spaceId").trim();
				if (spaceId == null || (spaceId.trim()).length() == 0) {
					errorMsgs.add("請輸入場地編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/space/space.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceService spaceServ = new SpaceService();
				SpaceVO selectOneSpace = new SpaceVO();
				selectOneSpace = spaceServ.selectOneSpace(spaceId);
				if (selectOneSpace == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/space/space.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneSpace", selectOneSpace);
				
				String url = "/backend/space/selectOneSpace.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_DeleteSpace".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceId = req.getParameter("spaceId").trim();

				SpaceService spaceServ = new SpaceService();
				SpaceVO deleteSpace = new SpaceVO();
				spaceServ.deleteSpace(spaceId);

				String url = "/backend/space/selectAllSapce.jsp";
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
				String spaceId = req.getParameter("spaceId").trim();

				SpaceService spaceServ = new SpaceService();
				SpaceVO selectOneUpdate = new SpaceVO();
				selectOneUpdate = spaceServ.selectOneSpace(spaceId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/backend/space/updateSpace.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_UpdateSpace".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceId = req.getParameter("spaceId").trim();
				
				String memberId = req.getParameter("memberId").trim();
//				String memberIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(memberId == null || memberId.isEmpty()) {
					errorMsgs.add("會員編號: 請勿空白");
				}
				
				String empId = req.getParameter("empId").trim();
				if(empId == null || empId.isEmpty()) {
					errorMsgs.add("員工編號: 請勿空白");
				}
				
				String spaceAddress = req.getParameter("spaceAddress").trim();
				if(spaceAddress == null || spaceAddress.isEmpty()) {
					errorMsgs.add("場地地址: 請勿空白");
				}
				
				String spaceName = req.getParameter("spaceName").trim();
				if(spaceName == null || spaceName.isEmpty()) {
					errorMsgs.add("場地名稱: 請勿空白");
				}
				
				String spaceType = req.getParameter("spaceType").trim();
				if(spaceType == null || spaceType.isEmpty()) {
					errorMsgs.add("場地類型: 請勿空白");
				}
				
				String spaceEqument = req.getParameter("spaceEqument").trim();
				if(spaceEqument == null || spaceEqument.isEmpty()) {
					errorMsgs.add("場地設備: 請勿空白");
				}
				
				String spaceContain = req.getParameter("spaceContain").trim();
				if(spaceContain == null || spaceContain.isEmpty()) {
					errorMsgs.add("場地容納人數: 請勿空白");
				}
				
				String spaceRule = req.getParameter("spaceRule").trim();
				if(spaceRule == null || spaceRule.isEmpty()) {
					errorMsgs.add("場地規範: 請勿空白");
				}
				
				String spaceRefund = req.getParameter("spaceRefund").trim();
				if(spaceRefund == null || spaceRefund.isEmpty()) {
					errorMsgs.add("退費機制: 請勿空白");
				}
				
				String spaceStatus = req.getParameter("spaceStatus").trim();
				if(spaceStatus == null || spaceStatus.isEmpty()) {
					errorMsgs.add("場地狀態: 請勿空白");
				}
				
				Date spaceSignupDate = null;
				try {
					spaceSignupDate = java.sql.Date.valueOf(req.getParameter("spaceSignupDate").trim());
				} catch (IllegalArgumentException e) {
					spaceSignupDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("場地申請日期: 請勿空白");
				}
				
				Date spaceOnsaleDate = null;
				try {
					spaceOnsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOnsaleDate").trim());
				} catch (IllegalArgumentException e) {
					spaceOnsaleDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				Date spaceOffsaleDate;
				try {
					spaceOffsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOffsaleDate").trim());
				} catch (IllegalArgumentException e) {
					spaceOffsaleDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				SpaceVO updateSpace = new SpaceVO();
				updateSpace.setSpaceId(spaceId);
				updateSpace.setMemberId(memberId);
				updateSpace.setEmpId(empId);
				updateSpace.setSpaceAddress(spaceAddress);
				updateSpace.setSpaceName(spaceName);
				updateSpace.setSpaceType(spaceType);
				updateSpace.setSpaceEqument(spaceEqument);
				updateSpace.setSpaceContain(spaceContain);
				updateSpace.setSpaceRule(spaceRule);
				updateSpace.setSpaceRefund(spaceRefund);
				updateSpace.setSpaceStatus(spaceStatus);
				updateSpace.setSpaceSignupDate(spaceSignupDate);
				updateSpace.setSpaceOnsaleDate(spaceOnsaleDate);
				updateSpace.setSpaceOffsaleDate(spaceOffsaleDate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateSpace); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/space/updateSpace.jsp");
					failureView.forward(req, res);
					return;
				}
				

				SpaceService spaceServ = new SpaceService();
				updateSpace = spaceServ.updateSapce(updateSpace);
				req.setAttribute("updateSpace", updateSpace);

				String url = "/backend/space/selectAllSapce.jsp";
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

		
		
		if ("addSpace".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberId = req.getParameter("memberId").trim();
//				String memberIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(memberId == null || memberId.isEmpty()) {
					errorMsgs.add("會員編號: 請勿空白");
				}
				
				String empId = req.getParameter("empId").trim();
				if(empId == null || empId.isEmpty()) {
					errorMsgs.add("員工編號: 請勿空白");
				}
				
				String spaceAddress = req.getParameter("spaceAddress").trim();
				if(spaceAddress == null || spaceAddress.isEmpty()) {
					errorMsgs.add("場地地址: 請勿空白");
				}
				
				String spaceName = req.getParameter("spaceName").trim();
				if(spaceName == null || spaceName.isEmpty()) {
					errorMsgs.add("場地名稱: 請勿空白");
				}
				
				String spaceType = req.getParameter("spaceType").trim();
				if(spaceType == null || spaceType.isEmpty()) {
					errorMsgs.add("場地類型: 請勿空白");
				}
				
				String spaceEqument = req.getParameter("spaceEqument").trim();
				if(spaceEqument == null || spaceEqument.isEmpty()) {
					errorMsgs.add("場地設備: 請勿空白");
				}
				
				String spaceContain = req.getParameter("spaceContain").trim();
				if(spaceContain == null || spaceContain.isEmpty()) {
					errorMsgs.add("場地容納人數: 請勿空白");
				}
				
				String spaceRule = req.getParameter("spaceRule").trim();
				if(spaceRule == null || spaceRule.isEmpty()) {
					errorMsgs.add("場地規範: 請勿空白");
				}
				
				String spaceRefund = req.getParameter("spaceRefund").trim();
				if(spaceRefund == null || spaceRefund.isEmpty()) {
					errorMsgs.add("退費機制: 請勿空白");
				}
				
				String spaceStatus = req.getParameter("spaceStatus").trim();
				if(spaceStatus == null || spaceStatus.isEmpty()) {
					errorMsgs.add("場地狀態: 請勿空白");
				}
				
				Date spaceSignupDate = null;
				try {
					spaceSignupDate = java.sql.Date.valueOf(req.getParameter("spaceSignupDate").trim());
				} catch (IllegalArgumentException e) {
					spaceSignupDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("場地申請日期: 請勿空白");
				}
				
				Date spaceOnsaleDate = null;
				try {
					spaceOnsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOnsaleDate").trim());
				} catch (IllegalArgumentException e) {
					spaceOnsaleDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				Date spaceOffsaleDate;
				try {
					spaceOffsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOffsaleDate").trim());
				} catch (IllegalArgumentException e) {
					spaceOffsaleDate = new java.sql.Date(System.currentTimeMillis());
				}

				SpaceVO addSpace = new SpaceVO();
				addSpace.setMemberId(memberId);
				addSpace.setEmpId(empId);
				addSpace.setSpaceAddress(spaceAddress);
				addSpace.setSpaceName(spaceName);
				addSpace.setSpaceType(spaceType);
				addSpace.setSpaceEqument(spaceEqument);
				addSpace.setSpaceContain(spaceContain);
				addSpace.setSpaceRule(spaceRule);
				addSpace.setSpaceRefund(spaceRefund);
				addSpace.setSpaceStatus(spaceStatus);
				addSpace.setSpaceSignupDate(spaceSignupDate);
				addSpace.setSpaceOnsaleDate(spaceOnsaleDate);
				addSpace.setSpaceOffsaleDate(spaceOffsaleDate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addSpace", addSpace); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/addSpace.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceService spaceServ = new SpaceService();
				addSpace = spaceServ.addSpace(addSpace);

				String url = "/frontend/space/selectAllSapce.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("selectOneSpace".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceId = req.getParameter("spaceId").trim();
				if (spaceId == null || (spaceId.trim()).length() == 0) {
					errorMsgs.add("請輸入場地編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/space.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceService spaceServ = new SpaceService();
				SpaceVO selectOneSpace = new SpaceVO();
				selectOneSpace = spaceServ.selectOneSpace(spaceId);
				if (selectOneSpace == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/space.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneSpace", selectOneSpace);
				
				String url = "/frontend/space/selectOneSpace.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("deleteSpace".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceId = req.getParameter("spaceId").trim();

				SpaceService spaceServ = new SpaceService();
				SpaceVO deleteSpace = new SpaceVO();
				spaceServ.deleteSpace(spaceId);

				String url = "/frontend/space/selectAllSapce.jsp";
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
				String spaceId = req.getParameter("spaceId").trim();

				SpaceService spaceServ = new SpaceService();
				SpaceVO selectOneUpdate = new SpaceVO();
				selectOneUpdate = spaceServ.selectOneSpace(spaceId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/frontend/space/updateSpace.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("updateSpace".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceId = req.getParameter("spaceId").trim();
				
				String memberId = req.getParameter("memberId").trim();
//				String memberIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(memberId == null || memberId.isEmpty()) {
					errorMsgs.add("會員編號: 請勿空白");
				}
				
				String empId = req.getParameter("empId").trim();
				if(empId == null || empId.isEmpty()) {
					errorMsgs.add("員工編號: 請勿空白");
				}
				
				String spaceAddress = req.getParameter("spaceAddress").trim();
				if(spaceAddress == null || spaceAddress.isEmpty()) {
					errorMsgs.add("場地地址: 請勿空白");
				}
				
				String spaceName = req.getParameter("spaceName").trim();
				if(spaceName == null || spaceName.isEmpty()) {
					errorMsgs.add("場地名稱: 請勿空白");
				}
				
				String spaceType = req.getParameter("spaceType").trim();
				if(spaceType == null || spaceType.isEmpty()) {
					errorMsgs.add("場地類型: 請勿空白");
				}
				
				String spaceEqument = req.getParameter("spaceEqument").trim();
				if(spaceEqument == null || spaceEqument.isEmpty()) {
					errorMsgs.add("場地設備: 請勿空白");
				}
				
				String spaceContain = req.getParameter("spaceContain").trim();
				if(spaceContain == null || spaceContain.isEmpty()) {
					errorMsgs.add("場地容納人數: 請勿空白");
				}
				
				String spaceRule = req.getParameter("spaceRule").trim();
				if(spaceRule == null || spaceRule.isEmpty()) {
					errorMsgs.add("場地規範: 請勿空白");
				}
				
				String spaceRefund = req.getParameter("spaceRefund").trim();
				if(spaceRefund == null || spaceRefund.isEmpty()) {
					errorMsgs.add("退費機制: 請勿空白");
				}
				
				String spaceStatus = req.getParameter("spaceStatus").trim();
				if(spaceStatus == null || spaceStatus.isEmpty()) {
					errorMsgs.add("場地狀態: 請勿空白");
				}
				
				Date spaceSignupDate = null;
				try {
					spaceSignupDate = java.sql.Date.valueOf(req.getParameter("spaceSignupDate").trim());
				} catch (IllegalArgumentException e) {
					spaceSignupDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("場地申請日期: 請勿空白");
				}
				
				Date spaceOnsaleDate = null;
				try {
					spaceOnsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOnsaleDate").trim());
				} catch (IllegalArgumentException e) {
					spaceOnsaleDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				Date spaceOffsaleDate;
				try {
					spaceOffsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOffsaleDate").trim());
				} catch (IllegalArgumentException e) {
					spaceOffsaleDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				SpaceVO updateSpace = new SpaceVO();
				updateSpace.setSpaceId(spaceId);
				updateSpace.setMemberId(memberId);
				updateSpace.setEmpId(empId);
				updateSpace.setSpaceAddress(spaceAddress);
				updateSpace.setSpaceName(spaceName);
				updateSpace.setSpaceType(spaceType);
				updateSpace.setSpaceEqument(spaceEqument);
				updateSpace.setSpaceContain(spaceContain);
				updateSpace.setSpaceRule(spaceRule);
				updateSpace.setSpaceRefund(spaceRefund);
				updateSpace.setSpaceStatus(spaceStatus);
				updateSpace.setSpaceSignupDate(spaceSignupDate);
				updateSpace.setSpaceOnsaleDate(spaceOnsaleDate);
				updateSpace.setSpaceOffsaleDate(spaceOffsaleDate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateSpace); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/updateSpace.jsp");
					failureView.forward(req, res);
					return;
				}
				

				SpaceService spaceServ = new SpaceService();
				updateSpace = spaceServ.updateSapce(updateSpace);
				req.setAttribute("updateSpace", updateSpace);

				String url = "/frontend/space/selectAllSapce.jsp";
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
