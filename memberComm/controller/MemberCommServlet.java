package com.memberComm.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.memberComm.model.MemberCommService;
import com.memberComm.model.MemberCommVO;


@WebServlet("/MemberCommServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberCommServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public MemberCommServlet() {
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
				
				Integer CommLevel = null;
				try {
					CommLevel = Integer.parseInt(req.getParameter("commLevel").trim());
					if(CommLevel <= 0 || CommLevel > 5 ) errorMsgs.add("評價等級: 請給1~5");
				} catch (NumberFormatException e) {
					CommLevel = 0;
					errorMsgs.add("評論等級請填數字1~5");
				}
				
				java.sql.Date CommDate = null;
				try {
					CommDate = java.sql.Date.valueOf(req.getParameter("commDate").trim());
				} catch (IllegalArgumentException e) {
					CommDate = new java.sql.Date(System.currentTimeMillis());
				}

				MemberCommVO addMemberComm = new MemberCommVO();
				addMemberComm.setMemberAId(memberAId);
				addMemberComm.setMemberBId(memberBId);
				addMemberComm.setComm(Comm);
				addMemberComm.setCommLevel(CommLevel);
				addMemberComm.setCommDate(CommDate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addMemberComm", addMemberComm); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/memberComm/addMemberComm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				MemberCommService memberCommServ = new MemberCommService();
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

				MemberCommService memberCommServ = new MemberCommService();
				MemberCommVO selectOneMemberComm = new MemberCommVO();
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
		
		if ("backend_DeleteMemberComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberCommId = req.getParameter("memberCommId").trim();

				MemberCommService memberCommServ = new MemberCommService();
				MemberCommVO deleteMemberComm = new MemberCommVO();
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

				MemberCommService memberCommServ = new MemberCommService();
				MemberCommVO selectOneUpdate = new MemberCommVO();
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
				
				Integer CommLevel = null;
				try {
					CommLevel = Integer.parseInt(req.getParameter("commLevel").trim());
					if(CommLevel <= 0 || CommLevel > 5 ) errorMsgs.add("評價等級: 請給1~5");
				} catch (NumberFormatException e) {
					CommLevel = 0;
					errorMsgs.add("評論等級請填數字1~5");
				}
				
				java.sql.Date CommDate = null;
				try {
					CommDate = java.sql.Date.valueOf(req.getParameter("commDate").trim());
				} catch (IllegalArgumentException e) {
					CommDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				MemberCommVO updateMemberComm = new MemberCommVO();
				updateMemberComm.setMemberCommId(memberCommId);
				updateMemberComm.setMemberAId(memberAId);
				updateMemberComm.setMemberBId(memberBId);
				updateMemberComm.setComm(Comm);
				updateMemberComm.setCommLevel(CommLevel);
				updateMemberComm.setCommDate(CommDate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateMemberComm); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/memberComm/updateMemberComm.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberCommService memberCommServ = new MemberCommService();
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
		

//======================================================================================================================================

		
		
		if ("addMemberComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberAId = req.getParameter("memberAId").trim();
				if(memberAId == null || memberAId.isEmpty()) errorMsgs.add("會員(甲方)編號: 請勿空白");
				
				String memberBId = req.getParameter("memberBId").trim();
				if(memberBId == null || memberBId.isEmpty()) errorMsgs.add("會員(乙方)編號: 請勿空白");
				
				String Comm = req.getParameter("comm").trim();
				if(Comm == null || Comm.isEmpty()) errorMsgs.add("評論內容: 請勿空白");
				
				Integer CommLevel = null;
				try {
					CommLevel = Integer.parseInt(req.getParameter("commLevel").trim());
					if(CommLevel <= 0 || CommLevel > 5 ) errorMsgs.add("評價等級: 請給1~5");
				} catch (NumberFormatException e) {
					CommLevel = 0;
					errorMsgs.add("評論等級請填數字1~5");
				}
				
				java.sql.Date CommDate = null;
				try {
					CommDate = java.sql.Date.valueOf(req.getParameter("commDate").trim());
				} catch (IllegalArgumentException e) {
					CommDate = new java.sql.Date(System.currentTimeMillis());
				}

				MemberCommVO addMemberComm = new MemberCommVO();
				addMemberComm.setMemberAId(memberAId);
				addMemberComm.setMemberBId(memberBId);
				addMemberComm.setComm(Comm);
				addMemberComm.setCommLevel(CommLevel);
				addMemberComm.setCommDate(CommDate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addMemberComm", addMemberComm); 
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/memberComm/addMemberComm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				MemberCommService memberCommServ = new MemberCommService();
				addMemberComm = memberCommServ.addMemberComm(addMemberComm);

				String url = "/frontend/memberComm/selectAllMemberComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("selectOneMemberComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberCommId = req.getParameter("memberCommId").trim();
				if(memberCommId == null || memberCommId.isEmpty()) errorMsgs.add("會員評價編號: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/memberComm/memberComm.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberCommService memberCommServ = new MemberCommService();
				MemberCommVO selectOneMemberComm = new MemberCommVO();
				selectOneMemberComm = memberCommServ.selectOneMemberComm(memberCommId);
				
				if (selectOneMemberComm == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/memberComm/memberComm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneMemberComm", selectOneMemberComm);

				String url = "/frontend/memberComm/selectOneMemberComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("deleteMemberComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberCommId = req.getParameter("memberCommId").trim();

				MemberCommService memberCommServ = new MemberCommService();
				MemberCommVO deleteMemberComm = new MemberCommVO();
				memberCommServ.deleteMemberComm(memberCommId);

				String url = "/frontend/memberComm/selectAllMemberComm.jsp";
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
				String memberCommId = req.getParameter("memberCommId").trim();

				MemberCommService memberCommServ = new MemberCommService();
				MemberCommVO selectOneUpdate = new MemberCommVO();
				selectOneUpdate = memberCommServ.selectOneMemberComm(memberCommId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/frontend/memberComm/updateMemberComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("updateMemberComm".equals(action)) {
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
				
				Integer CommLevel = null;
				try {
					CommLevel = Integer.parseInt(req.getParameter("commLevel").trim());
					if(CommLevel <= 0 || CommLevel > 5 ) errorMsgs.add("評價等級: 請給1~5");
				} catch (NumberFormatException e) {
					CommLevel = 0;
					errorMsgs.add("評論等級請填數字1~5");
				}
				
				java.sql.Date CommDate = null;
				try {
					CommDate = java.sql.Date.valueOf(req.getParameter("commDate").trim());
				} catch (IllegalArgumentException e) {
					CommDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				MemberCommVO updateMemberComm = new MemberCommVO();
				updateMemberComm.setMemberCommId(memberCommId);
				updateMemberComm.setMemberAId(memberAId);
				updateMemberComm.setMemberBId(memberBId);
				updateMemberComm.setComm(Comm);
				updateMemberComm.setCommLevel(CommLevel);
				updateMemberComm.setCommDate(CommDate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateMemberComm); 
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/memberComm/updateMemberComm.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberCommService memberCommServ = new MemberCommService();
				updateMemberComm = memberCommServ.updateMemberComm(updateMemberComm);
				req.setAttribute("selectOneUpdate", updateMemberComm);

				String url = "/frontend/memberComm/selectAllMemberComm.jsp";
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
