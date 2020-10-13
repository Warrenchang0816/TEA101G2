package com.spaceComment.controller;

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

import com.spaceComment.model.SpaceCommServiceB;
import com.spaceComment.model.SpaceCommentVO;


@WebServlet("/SpaceCommServletB")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class SpaceCommServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public SpaceCommServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("backend_AddSpaceComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceId = req.getParameter("spaceId").trim();
				if(spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");
				
				String memberId = req.getParameter("memberId").trim();
				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				String Comm = req.getParameter("comm").trim();
				if(Comm == null || Comm.isEmpty()) errorMsgs.add("評論內容: 請勿空白");
				
				Double CommLevel = null;
				try {
					CommLevel = Double.parseDouble(req.getParameter("commLevel").trim());
				} catch (NumberFormatException e) {
					CommLevel = 0.0;
				}
				
				java.sql.Date CommDate = null;
				try {
					CommDate = java.sql.Date.valueOf(req.getParameter("commDate").trim());
				} catch (IllegalArgumentException e) {
					CommDate = new java.sql.Date(System.currentTimeMillis());
				}

				SpaceCommentVO addSpaceComm = new SpaceCommentVO();
				addSpaceComm.setSpaceId(spaceId);
				addSpaceComm.setMemberId(memberId);
				addSpaceComm.setSpaceCommentContent(Comm);
				addSpaceComm.setSpaceCommentLevel(CommLevel);
				addSpaceComm.setSpaceCommentDate(CommDate);
				addSpaceComm.setSpaceCommStatus("Y");
				addSpaceComm.setSpaceCommStatusEmp("");
				addSpaceComm.setSpaceCommStatusComm("");
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addSpaceComm", addSpaceComm); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spaceComm/addSpaceComm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				SpaceCommServiceB spaceCommServ = new SpaceCommServiceB();
				addSpaceComm = spaceCommServ.addSpaceComm(addSpaceComm);

				String url = "/backend/spaceComm/selectAllSpaceComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_SelectOneSpaceComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceCommId = req.getParameter("spaceCommId").trim();
				if(spaceCommId == null || spaceCommId.isEmpty()) errorMsgs.add("場地評價編號: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spaceComm/spaceComm.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceCommServiceB spaceCommServ = new SpaceCommServiceB();
				SpaceCommentVO selectOneSpaceComm = new SpaceCommentVO();
				selectOneSpaceComm = spaceCommServ.selectOneSpaceComm(spaceCommId);
				
				if (selectOneSpaceComm == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spaceComm/spaceComm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneSpaceComm", selectOneSpaceComm);

				String url = "/backend/spaceComm/selectOneSpaceComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}
		
		if ("backend_SelectSpaceCommBySpace".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceId = req.getParameter("spaceId").trim();
				req.setAttribute("spaceId", spaceId);
				
				String spaceName = req.getParameter("spaceName").trim();
				req.setAttribute("spaceName", spaceName);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/space/spaceProfile.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceCommServiceB spaceCommServ = new SpaceCommServiceB();
				List<SpaceCommentVO> selectSpaceCommBySpace = new ArrayList<SpaceCommentVO>();
				selectSpaceCommBySpace = spaceCommServ.selectAllSpaceCommBySpace(spaceId);
				
				if (selectSpaceCommBySpace == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/space/spaceProfile.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectSpaceCommBySpace", selectSpaceCommBySpace);

				String url = "/backend/spaceComm/spaceComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_DeleteSpaceComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceCommId = req.getParameter("spaceCommId").trim();

				SpaceCommServiceB spaceCommServ = new SpaceCommServiceB();
				SpaceCommentVO deleteSpaceComm = new SpaceCommentVO();
				spaceCommServ.deleteSpaceComm(spaceCommId);

				String url = "/backend/spaceComm/selectAllSpaceComm.jsp";
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
				String spaceCommId = req.getParameter("spaceCommId").trim();

				SpaceCommServiceB spaceCommServ = new SpaceCommServiceB();
				SpaceCommentVO selectOneUpdate = new SpaceCommentVO();
				selectOneUpdate = spaceCommServ.selectOneSpaceComm(spaceCommId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/backend/spaceComm/updateSpaceComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_UpdateSpaceComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceCommId = req.getParameter("spaceCommId").trim();
				
				String spaceId = req.getParameter("spaceId").trim();
				if(spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");
				
				String memberId = req.getParameter("memberId").trim();
				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				String Comm = req.getParameter("comm").trim();
				if(Comm == null || Comm.isEmpty()) errorMsgs.add("評論內容: 請勿空白");
				
				Double CommLevel = null;
				try {
					CommLevel = Double.parseDouble(req.getParameter("commLevel").trim());
				} catch (NumberFormatException e) {
					CommLevel = 0.0;
				}
				
				java.sql.Date CommDate = null;
				try {
					CommDate = java.sql.Date.valueOf(req.getParameter("commDate").trim());
				} catch (IllegalArgumentException e) {
					CommDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				String spaceCommStatus = req.getParameter("spaceCommStatus").trim();
				
				String spaceCommStatusEmp = req.getParameter("spaceCommStatusEmp").trim();
				
				String spaceCommStatusComm = req.getParameter("spaceCommStatusComm").trim();
				
				SpaceCommentVO updateSpaceComm = new SpaceCommentVO();
				updateSpaceComm.setSpaceCommentId(spaceCommId);
				updateSpaceComm.setSpaceId(spaceId);
				updateSpaceComm.setMemberId(memberId);
				updateSpaceComm.setSpaceCommentContent(Comm);
				updateSpaceComm.setSpaceCommentLevel(CommLevel);
				updateSpaceComm.setSpaceCommentDate(CommDate);
				updateSpaceComm.setSpaceCommStatus(spaceCommStatusComm);
				updateSpaceComm.setSpaceCommStatusEmp(spaceCommStatusEmp);
				updateSpaceComm.setSpaceCommStatusComm(spaceCommStatusComm);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateSpaceComm); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spaceComm/updateSpaceComm.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceCommServiceB spaceCommServ = new SpaceCommServiceB();
				updateSpaceComm = spaceCommServ.updateSpaceComm(updateSpaceComm);
				req.setAttribute("selectOneUpdate", updateSpaceComm);

				String url = "/backend/spaceComm/selectAllSpaceComm.jsp";
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
