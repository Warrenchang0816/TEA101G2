package com.memberComment.controller;

import java.io.IOException;
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

import com.memberComment.model.MemberCommServiceB;
import com.memberComment.model.MemberCommentServiceB;
import com.memberComment.model.MemberCommentVO;


@WebServlet("/MemberCommServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberCommServletB extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public MemberCommServletB() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("backend_AddMemberComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberAId = req.getParameter("memberAId").trim();
				if(memberAId == null || memberAId.isEmpty()) errorMsgs.add("會員(甲方)編號: 請勿空白");
				
				String memberBId = req.getParameter("memberBId").trim();
				if(memberBId == null || memberBId.isEmpty()) errorMsgs.add("會員(乙方)編號: 請勿空白");
				
				String Comm = req.getParameter("comm").trim();
				if(Comm == null || Comm.isEmpty()) errorMsgs.add("評論內容: 請勿空白");
				
				Double CommLevel = null;
				try {
					CommLevel = Double.parseDouble(req.getParameter("commLevel").trim());
					if(CommLevel <= 0 || CommLevel > 5 ) errorMsgs.add("評價等級: 請給1~5");
				} catch (NumberFormatException e) {
					CommLevel = 0.0;
					errorMsgs.add("評論等級請填數字1~5");
				}
				
				java.sql.Date CommDate = null;
				try {
					CommDate = java.sql.Date.valueOf(req.getParameter("commDate").trim());
				} catch (IllegalArgumentException e) {
					CommDate = new java.sql.Date(System.currentTimeMillis());
				}

				MemberCommentVO addMemberComm = new MemberCommentVO();
				addMemberComm.setMemberAId(memberAId);
				addMemberComm.setMemberBId(memberBId);
				addMemberComm.setMemberCommentContent(Comm);
				addMemberComm.setMemberCommentLevel(CommLevel);
				addMemberComm.setMemberCommentDate(CommDate);
				addMemberComm.setMemberCommStatus("Y");
				addMemberComm.setMemberCommStatusEmp("");
				addMemberComm.setMemberCommStatusComm("");
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addMemberComm", addMemberComm); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/memberComm/addMemberComm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				MemberCommServiceB memberCommServ = new MemberCommServiceB();
				addMemberComm = memberCommServ.addMemberComm(addMemberComm);

				String url = "/backend/memberComm/selectAllMemberComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_SelectOneMemberComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberCommId = req.getParameter("memberCommId").trim();
				if(memberCommId == null || memberCommId.isEmpty()) errorMsgs.add("會員評價編號: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/memberComm/memberComm.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberCommServiceB memberCommServ = new MemberCommServiceB();
				MemberCommentVO selectOneMemberComm = new MemberCommentVO();
				selectOneMemberComm = memberCommServ.selectOneMemberComm(memberCommId);
				
				if (selectOneMemberComm == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/memberComm/memberComm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneMemberComm", selectOneMemberComm);

				String url = "/backend/memberComm/selectOneMemberComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}
		
		if ("backend_SelectMemberCommByMember".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberId = req.getParameter("memberId").trim();
				req.setAttribute("memberId", memberId);
				
				String memberName = req.getParameter("memberName").trim();
				req.setAttribute("memberName", memberName);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/member/memberProfile.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberCommServiceB memberCommServ = new MemberCommServiceB();
				List<MemberCommentVO> selectMemberCommByMember = new ArrayList<MemberCommentVO>();
				selectMemberCommByMember = memberCommServ.selectAllMemberCommByMember(memberId);
				
				if (selectMemberCommByMember == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/member/memberProfile.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectMemberCommByMember", selectMemberCommByMember);

				String url = "/backend/memberComm/memberComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_DeleteMemberComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberCommId = req.getParameter("memberCommId").trim();

				MemberCommServiceB memberCommServ = new MemberCommServiceB();
				MemberCommentVO deleteMemberComm = new MemberCommentVO();
				memberCommServ.deleteMemberComm(memberCommId);

				String url = "/backend/memberComm/selectAllMemberComm.jsp";
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
				String memberCommId = req.getParameter("memberCommId").trim();

				MemberCommServiceB memberCommServ = new MemberCommServiceB();
				MemberCommentVO selectOneUpdate = new MemberCommentVO();
				selectOneUpdate = memberCommServ.selectOneMemberComm(memberCommId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/backend/memberComm/updateMemberComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_UpdateMemberComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberCommId = req.getParameter("memberCommId").trim();
				
				String memberAId = req.getParameter("memberAId").trim();
				if(memberAId == null || memberAId.isEmpty()) errorMsgs.add("會員(甲方)編號: 請勿空白");
				
				String memberBId = req.getParameter("memberBId").trim();
				if(memberBId == null || memberBId.isEmpty()) errorMsgs.add("會員(乙方)編號: 請勿空白");
				
				String Comm = req.getParameter("comm").trim();
				if(Comm == null || Comm.isEmpty()) errorMsgs.add("評論內容: 請勿空白");
				
				Double CommLevel = null;
				try {
					CommLevel = Double.parseDouble(req.getParameter("commLevel").trim());
					if(CommLevel <= 0 || CommLevel > 5 ) errorMsgs.add("評價等級: 請給1~5");
				} catch (NumberFormatException e) {
					CommLevel = 0.0;
					errorMsgs.add("評論等級請填數字1~5");
				}
				
				java.sql.Date CommDate = null;
				try {
					CommDate = java.sql.Date.valueOf(req.getParameter("commDate").trim());
				} catch (IllegalArgumentException e) {
					CommDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				String memberCommStatus = req.getParameter("memberCommStatus").trim();
				
				String memberCommStatusEmp = req.getParameter("memberCommStatusEmp").trim();
				
				String memberCommStatusComm = req.getParameter("memberCommStatusComm").trim();
				
				MemberCommentVO updateMemberComm = new MemberCommentVO();
				updateMemberComm.setMemberCommentId(memberCommId);
				updateMemberComm.setMemberAId(memberAId);
				updateMemberComm.setMemberBId(memberBId);
				updateMemberComm.setMemberCommentContent(Comm);
				updateMemberComm.setMemberCommentLevel(CommLevel);
				updateMemberComm.setMemberCommentDate(CommDate);
				updateMemberComm.setMemberCommStatus(memberCommStatus);
				updateMemberComm.setMemberCommStatusEmp(memberCommStatusEmp);
				updateMemberComm.setMemberCommStatusComm(memberCommStatusComm);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateMemberComm); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/memberComm/updateMemberComm.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberCommServiceB memberCommServ = new MemberCommServiceB();
				updateMemberComm = memberCommServ.updateMemberComm(updateMemberComm);
				req.setAttribute("selectOneUpdate", updateMemberComm);

				String url = "/backend/memberComm/selectAllMemberComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}
		
		if ("backend_UpdateMemberCommStatus".equals(action)) {
			System.out.println("backend_UpdateMemberCommStatus");
			try {
				String memberCommId = req.getParameter("memberCommId").trim();
				System.out.println("memberCommId:"+memberCommId);
				String memberCommStatus = req.getParameter("memberCommStatus").trim();
				System.out.println("memberCommStatus:"+memberCommStatus);
				System.out.println("backend_UpdateMemberCommStatus11111");
				MemberCommentServiceB mcsb = new MemberCommentServiceB();
				mcsb.updateMemberCommStatus(memberCommId, memberCommStatus);
				System.out.println("backend_UpdateMemberCommStatus2222222");
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
	}
}

//======================================================================================================================================

		
		
	