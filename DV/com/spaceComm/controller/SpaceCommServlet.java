package com.spaceComm.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spaceComm.model.SpaceCommService;
import com.spaceComm.model.SpaceCommVO;


@WebServlet("/SpaceCommServlet")
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

				SpaceCommVO addSpaceComm = new SpaceCommVO();
				addSpaceComm.setSpaceId(spaceId);
				addSpaceComm.setMemberId(memberId);
				addSpaceComm.setComm(Comm);
				addSpaceComm.setCommLevel(CommLevel);
				addSpaceComm.setCommDate(CommDate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addSpaceComm", addSpaceComm); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spaceComm/addSpaceComm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				SpaceCommService spaceCommServ = new SpaceCommService();
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

				SpaceCommService spaceCommServ = new SpaceCommService();
				SpaceCommVO selectOneSpaceComm = new SpaceCommVO();
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
		
		if ("backend_DeleteSpaceComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceCommId = req.getParameter("spaceCommId").trim();

				SpaceCommService spaceCommServ = new SpaceCommService();
				SpaceCommVO deleteSpaceComm = new SpaceCommVO();
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

				SpaceCommService spaceCommServ = new SpaceCommService();
				SpaceCommVO selectOneUpdate = new SpaceCommVO();
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
				
				
				SpaceCommVO updateSpaceComm = new SpaceCommVO();
				updateSpaceComm.setSpaceCommId(spaceCommId);
				updateSpaceComm.setSpaceId(spaceId);
				updateSpaceComm.setMemberId(memberId);
				updateSpaceComm.setComm(Comm);
				updateSpaceComm.setCommLevel(CommLevel);
				updateSpaceComm.setCommDate(CommDate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateSpaceComm); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spaceComm/updateSpaceComm.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceCommService spaceCommServ = new SpaceCommService();
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
		
		
		if ("addSpaceComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceId = req.getParameter("spaceId").trim();
				if(spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");
				
				String memberId = req.getParameter("memberId").trim();
				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
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

				SpaceCommVO addSpaceComm = new SpaceCommVO();
				addSpaceComm.setSpaceId(spaceId);
				addSpaceComm.setMemberId(memberId);
				addSpaceComm.setComm(Comm);
				addSpaceComm.setCommLevel(CommLevel);
				addSpaceComm.setCommDate(CommDate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addSpaceComm", addSpaceComm); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spaceComm/addSpaceComm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				SpaceCommService spaceCommServ = new SpaceCommService();
				addSpaceComm = spaceCommServ.addSpaceComm(addSpaceComm);

				String url = "/frontend/spaceComm/selectAllSpaceComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("selectOneSpaceComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceCommId = req.getParameter("spaceCommId").trim();
				if(spaceCommId == null || spaceCommId.isEmpty()) errorMsgs.add("場地評價編號: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spaceComm/spaceComm.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceCommService spaceCommServ = new SpaceCommService();
				SpaceCommVO selectOneSpaceComm = new SpaceCommVO();
				selectOneSpaceComm = spaceCommServ.selectOneSpaceComm(spaceCommId);
				
				if (selectOneSpaceComm == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spaceComm/spaceComm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneSpaceComm", selectOneSpaceComm);

				String url = "/frontend/spaceComm/selectOneSpaceComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("deleteSpaceComm".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceCommId = req.getParameter("spaceCommId").trim();

				SpaceCommService spaceCommServ = new SpaceCommService();
				SpaceCommVO deleteSpaceComm = new SpaceCommVO();
				spaceCommServ.deleteSpaceComm(spaceCommId);

				String url = "/frontend/spaceComm/selectAllSpaceComm.jsp";
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
				String spaceCommId = req.getParameter("spaceCommId").trim();

				SpaceCommService spaceCommServ = new SpaceCommService();
				SpaceCommVO selectOneUpdate = new SpaceCommVO();
				selectOneUpdate = spaceCommServ.selectOneSpaceComm(spaceCommId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/frontend/spaceComm/updateSpaceComm.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("updateSpaceComm".equals(action)) {
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
				
				
				SpaceCommVO updateSpaceComm = new SpaceCommVO();
				updateSpaceComm.setSpaceCommId(spaceCommId);
				updateSpaceComm.setSpaceId(spaceId);
				updateSpaceComm.setMemberId(memberId);
				updateSpaceComm.setComm(Comm);
				updateSpaceComm.setCommLevel(CommLevel);
				updateSpaceComm.setCommDate(CommDate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateSpaceComm); 
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spaceComm/updateSpaceComm.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceCommService spaceCommServ = new SpaceCommService();
				updateSpaceComm = spaceCommServ.updateSpaceComm(updateSpaceComm);
				req.setAttribute("selectOneUpdate", updateSpaceComm);

				String url = "/frontend/spaceComm/selectAllSpaceComm.jsp";
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
