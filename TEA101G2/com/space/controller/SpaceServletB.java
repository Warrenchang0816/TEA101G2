package com.space.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.member.model.MemberServiceB;
import com.member.model.MemberVO;
import com.space.model.SpaceServiceB;
import com.space.model.SpaceVO;

@WebServlet("/SpaceServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class SpaceServletB extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public SpaceServletB() {
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
				
				Double spaceLng = null;
				try {
					spaceLng = Double.parseDouble(req.getParameter("spaceLng").trim());
				} catch (NumberFormatException e) {
					spaceLng = 0.0;
				}
				
				Double spaceLat = null;
				try {
					spaceLat = Double.parseDouble(req.getParameter("spaceLat").trim());
				} catch (NumberFormatException e) {
					spaceLat = 0.0;
				}
				
				String spaceName = req.getParameter("spaceName").trim();
				if(spaceName == null || spaceName.isEmpty()) {
					errorMsgs.add("場地名稱: 請勿空白");
				}
				
				String spaceText = req.getParameter("spaceText").trim();
				
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
				addSpace.setSpaceLat(spaceLat);
				addSpace.setSpaceLng(spaceLng);
				addSpace.setSpaceName(spaceName);
				addSpace.setSpaceText(spaceText);
				addSpace.setSpaceType(spaceType);
				addSpace.setSpaceEquipment(spaceEqument);
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

				SpaceServiceB spaceServ = new SpaceServiceB();
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
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/space/selectSpace.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceServiceB spaceServ = new SpaceServiceB();
				SpaceVO selectOneSpace = new SpaceVO();
				selectOneSpace = spaceServ.selectOneSpace(spaceId);
				
				MemberServiceB memberServ = new MemberServiceB();
				MemberVO selectOneMember = new MemberVO();
				selectOneMember = memberServ.selectOneMember(selectOneSpace.getMemberId());
				
//				if (selectOneSpace == null) {
//					errorMsgs.add("查無資料");
//				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/space/selectSpace.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneSpace", selectOneSpace);
				req.setAttribute("selectOneMember", selectOneMember);
				req.setAttribute("spaceId", spaceId);
				
				String url = "/backend/space/spaceProfile.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}
		
		if ("backend_SelectSpaceByMember".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberId = req.getParameter("memberId").trim();
				if (memberId == null || (memberId.trim()).length() == 0) errorMsgs.add("請輸入會員編號");
				
				String memberName = req.getParameter("memberName").trim();
				if (memberName == null || (memberName.trim()).length() == 0) errorMsgs.add("請輸入會員姓名");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/member/selectMember.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceServiceB spaceServ = new SpaceServiceB();
				List<SpaceVO> selectSpaceByMember = new ArrayList<SpaceVO>();
				selectSpaceByMember = spaceServ.selectAllSpaceByMember(memberId);
				
				if (selectSpaceByMember == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/member/selectMember.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectSpaceByMember", selectSpaceByMember);
				req.setAttribute("memberId", memberId);
				req.setAttribute("memberName", memberName);
				
				String url = "/backend/space/selectSpaceByMember.jsp";
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

				SpaceServiceB spaceServ = new SpaceServiceB();
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

				SpaceServiceB spaceServ = new SpaceServiceB();
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
				
				String empId = req.getParameter("empId").trim();
				
				SpaceServiceB spaceServ = new SpaceServiceB();
				SpaceVO SpaceVO = spaceServ.selectOneSpace(spaceId);
				
				String spaceStatus = req.getParameter("spaceStatus").trim();
				
				Date today = java.sql.Date.valueOf(req.getParameter("today").trim());
				Date spaceOnsaleDate = null;
				try {
					spaceOnsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOnsaleDate").trim());
//					if(spaceStatus.equals("T") && (spaceOnsaleDate.getTime() < today.getTime())) {
//						errorMsgs.add("上架日期不可小於今日");
//					}
				} catch (IllegalArgumentException e) {
					spaceOnsaleDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				Date spaceOffsaleDate;
				try {
					spaceOffsaleDate = java.sql.Date.valueOf(req.getParameter("spaceOffsaleDate").trim());
//					if(spaceStatus.equals("F") && (spaceOffsaleDate.getTime() < today.getTime())) {
//						errorMsgs.add("下架日期不可小於今日");
//					}
				} catch (IllegalArgumentException e) {
					spaceOffsaleDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				String spaceStatusEmp = req.getParameter("spaceStatusEmp").trim();
				
				String spaceStatusComm = req.getParameter("spaceStatusComm").trim();
				if(spaceStatusComm == null || spaceStatusComm.isEmpty()) {
					errorMsgs.add("原因請勿空白");
				}
				
				SpaceVO updateSpace = new SpaceVO();
				updateSpace.setSpaceId(spaceId);
				updateSpace.setMemberId(SpaceVO.getMemberId());
				updateSpace.setEmpId(empId);
				updateSpace.setSpaceAddress(SpaceVO.getSpaceAddress());
				updateSpace.setSpaceLat(SpaceVO.getSpaceLat());
				updateSpace.setSpaceLng(SpaceVO.getSpaceLng());
				updateSpace.setSpaceName(SpaceVO.getSpaceName());
				updateSpace.setSpaceText(SpaceVO.getSpaceText());
				updateSpace.setSpaceType(SpaceVO.getSpaceType());
				updateSpace.setSpaceEquipment(SpaceVO.getSpaceEquipment());
				updateSpace.setSpaceContain(SpaceVO.getSpaceContain());
				updateSpace.setSpaceRule(SpaceVO.getSpaceRule());
				updateSpace.setSpaceRefund(SpaceVO.getSpaceRefund());
				updateSpace.setSpaceStatus(spaceStatus);
				updateSpace.setSpaceSignupDate(SpaceVO.getSpaceSignupDate());
				updateSpace.setSpaceOnsaleDate(spaceOnsaleDate);
				updateSpace.setSpaceOffsaleDate(spaceOffsaleDate);
				updateSpace.setSpaceStatusEmp(spaceStatusEmp);
				updateSpace.setSpaceStatusComm(spaceStatusComm);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateSpace); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/space/updateSpace.jsp");
					failureView.forward(req, res);
					return;
				}
				

				spaceServ = new SpaceServiceB();
				updateSpace = spaceServ.updateSapce(updateSpace);
//				req.setAttribute("updateSpace", updateSpace);

				String url = "/backend/space/selectSpaceStatus.jsp";
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

		
		

		
	}

}
