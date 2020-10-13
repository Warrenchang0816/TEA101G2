package com.spaceDetail.controller;

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

import com.spaceDetail.model.SpaceDetailServiceB;
import com.spaceDetail.model.SpaceDetailVO;

@WebServlet("/SpaceDetailServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class SpaceDetailServletB extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public SpaceDetailServletB() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("backend_AddSpaceDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceId = req.getParameter("spaceId").trim();
				if(spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");
				
				java.sql.Date spaceDetailFreeDate = null;
				try {
					spaceDetailFreeDate = java.sql.Date.valueOf(req.getParameter("spaceDetailFreeDate").trim());
				} catch (IllegalArgumentException e) {
					spaceDetailFreeDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("場地出租日期: 格式錯誤");
				}
				
				java.sql.Timestamp spaceDetailFreeTimeStart = null;
				try {
					spaceDetailFreeTimeStart = java.sql.Timestamp.valueOf(req.getParameter("spaceDetailFreeTimeStart").trim());
				} catch (IllegalArgumentException e) {
					spaceDetailFreeTimeStart = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("場地出租開始時間: 格式錯誤");
				}
				
				java.sql.Timestamp spaceDetailFreeTimeEnd = null;
				try {
					spaceDetailFreeTimeEnd = java.sql.Timestamp.valueOf(req.getParameter("spaceDetailFreeTimeEnd").trim());
				} catch (IllegalArgumentException e) {
					spaceDetailFreeTimeEnd = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("場地出租結束時間: 格式錯誤");
				}
				
				Integer spaceDetailCharge = null;
				try {
					spaceDetailCharge = Integer.parseInt(req.getParameter("spaceDetailCharge").trim());
					if(spaceDetailCharge <= 0) errorMsgs.add("場地時段金額: 請確認金額");
				} catch (NumberFormatException e) {
					spaceDetailCharge = 0;
					errorMsgs.add("場地時段金額: 必須為數字");
				}

				SpaceDetailVO addSpaceDetail = new SpaceDetailVO();
				addSpaceDetail.setSpaceId(spaceId);
				addSpaceDetail.setSpaceDetailFreeDate(spaceDetailFreeDate);
				addSpaceDetail.setSpaceDetailFreeTimeStart(spaceDetailFreeTimeStart);
				addSpaceDetail.setSpaceDetailFreeTimeEnd(spaceDetailFreeTimeEnd);
				addSpaceDetail.setSpaceDetailCharge(spaceDetailCharge);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addSpaceDetail", addSpaceDetail); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spaceDetail/addSpaceDetail.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceDetailServiceB spaceDetailServ = new SpaceDetailServiceB();
				addSpaceDetail = spaceDetailServ.addSpaceDetail(addSpaceDetail);

				String url = "/backend/spaceDetail/selectAllSpaceDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}

		}		
		
		if ("backend_SelectOneSpaceDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceDetailId = req.getParameter("spaceDetailId").trim();
				if(spaceDetailId == null || spaceDetailId.isEmpty()) errorMsgs.add("場地明細編號: 請勿空白");
			
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spaceDetail/spaceDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				SpaceDetailServiceB spaceDetailServ = new SpaceDetailServiceB();
				SpaceDetailVO selectOneSpaceDetail = new SpaceDetailVO();
				selectOneSpaceDetail = spaceDetailServ.selectOneSpaceDetail(spaceDetailId);
				
				if (selectOneSpaceDetail == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spaceDetail/spaceDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneSpaceDetail", selectOneSpaceDetail);

				String url = "/backend/spaceDetail/selectOneSpaceDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_DeleteSpaceDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceDetailId = req.getParameter("spaceDetailId").trim();

				SpaceDetailServiceB spaceDetailServ = new SpaceDetailServiceB();
				SpaceDetailVO deleteSpaceDetail = new SpaceDetailVO();
				spaceDetailServ.deleteSpaceDetail(spaceDetailId);

				String url = "/backend/spaceDetail/selectAllSpaceDetail.jsp";
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
				String spaceDetailId = req.getParameter("spaceDetailId").trim();

				SpaceDetailServiceB spaceDetailServ = new SpaceDetailServiceB();
				SpaceDetailVO selectOneUpdate = new SpaceDetailVO();
				selectOneUpdate = spaceDetailServ.selectOneSpaceDetail(spaceDetailId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/backend/spaceDetail/updateSpaceDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_UpdateSpaceDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceDetailId = req.getParameter("spaceDetailId").trim();
				
				String spaceId = req.getParameter("spaceId").trim();
				if(spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");
				
				java.sql.Date spaceDetailFreeDate = null;
				try {
					spaceDetailFreeDate = java.sql.Date.valueOf(req.getParameter("spaceDetailFreeDate").trim());
				} catch (IllegalArgumentException e) {
					spaceDetailFreeDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("場地出租日期: 格式錯誤");
				}
				
				java.sql.Timestamp spaceDetailFreeTimeStart = null;
				try {
					spaceDetailFreeTimeStart = java.sql.Timestamp.valueOf(req.getParameter("spaceDetailFreeTimeStart").trim());
				} catch (IllegalArgumentException e) {
					spaceDetailFreeTimeStart = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("場地出租開始時間: 格式錯誤");
				}
				
				java.sql.Timestamp spaceDetailFreeTimeEnd = null;
				try {
					spaceDetailFreeTimeEnd = java.sql.Timestamp.valueOf(req.getParameter("spaceDetailFreeTimeEnd").trim());
				} catch (IllegalArgumentException e) {
					spaceDetailFreeTimeEnd = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("場地出租結束時間: 格式錯誤");
				}
				
				Integer spaceDetailCharge = null;
				try {
					spaceDetailCharge = new Integer(req.getParameter("spaceDetailCharge").trim());
					if(spaceDetailCharge <= 0) errorMsgs.add("場地時段金額: 請確認金額");
				} catch (NumberFormatException e) {
					spaceDetailCharge = 0;
					errorMsgs.add("場地時段金額: 必須為數字");
				}
				
				SpaceDetailVO updateSpaceDetail = new SpaceDetailVO();
				updateSpaceDetail.setSpaceDetailId(spaceDetailId);
				updateSpaceDetail.setSpaceId(spaceId);
				updateSpaceDetail.setSpaceDetailFreeDate(spaceDetailFreeDate);
				updateSpaceDetail.setSpaceDetailFreeTimeStart(spaceDetailFreeTimeStart);
				updateSpaceDetail.setSpaceDetailFreeTimeEnd(spaceDetailFreeTimeEnd);
				updateSpaceDetail.setSpaceDetailCharge(spaceDetailCharge);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateSpaceDetail); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spaceDetail/updateSpaceDetail.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceDetailServiceB spaceDetailServ = new SpaceDetailServiceB();
				updateSpaceDetail = spaceDetailServ.updateSapceDetail(updateSpaceDetail);
				req.setAttribute("updateSpaceDetail", updateSpaceDetail);

				String url = "/backend/spaceDetail/selectAllSpaceDetail.jsp";
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

		
		
		if ("addSpaceDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceId = req.getParameter("spaceId").trim();
				if(spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");
				
				java.sql.Date spaceDetailFreeDate = null;
				try {
					spaceDetailFreeDate = java.sql.Date.valueOf(req.getParameter("spaceDetailFreeDate").trim());
				} catch (IllegalArgumentException e) {
					spaceDetailFreeDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("場地出租日期: 格式錯誤");
				}
				
				java.sql.Timestamp spaceDetailFreeTimeStart = null;
				try {
					spaceDetailFreeTimeStart = java.sql.Timestamp.valueOf(req.getParameter("spaceDetailFreeTimeStart").trim());
				} catch (IllegalArgumentException e) {
					spaceDetailFreeTimeStart = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("場地出租開始時間: 格式錯誤");
				}
				
				java.sql.Timestamp spaceDetailFreeTimeEnd = null;
				try {
					spaceDetailFreeTimeEnd = java.sql.Timestamp.valueOf(req.getParameter("spaceDetailFreeTimeEnd").trim());
				} catch (IllegalArgumentException e) {
					spaceDetailFreeTimeEnd = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("場地出租結束時間: 格式錯誤");
				}
				
				Integer spaceDetailCharge = null;
				try {
					spaceDetailCharge = Integer.parseInt(req.getParameter("spaceDetailCharge").trim());
					if(spaceDetailCharge <= 0) errorMsgs.add("場地時段金額: 請確認金額");
				} catch (NumberFormatException e) {
					spaceDetailCharge = 0;
					errorMsgs.add("場地時段金額: 必須為數字");
				}

				SpaceDetailVO addSpaceDetail = new SpaceDetailVO();
				addSpaceDetail.setSpaceId(spaceId);
				addSpaceDetail.setSpaceDetailFreeDate(spaceDetailFreeDate);
				addSpaceDetail.setSpaceDetailFreeTimeStart(spaceDetailFreeTimeStart);
				addSpaceDetail.setSpaceDetailFreeTimeEnd(spaceDetailFreeTimeEnd);
				addSpaceDetail.setSpaceDetailCharge(spaceDetailCharge);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addSpaceDetail", addSpaceDetail); 
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spaceDetail/addSpaceDetail.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceDetailServiceB spaceDetailServ = new SpaceDetailServiceB();
				addSpaceDetail = spaceDetailServ.addSpaceDetail(addSpaceDetail);

				String url = "/frontend/spaceDetail/selectAllSpaceDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}

		}		
		
		if ("selectOneSpaceDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceDetailId = req.getParameter("spaceDetailId").trim();
				if(spaceDetailId == null || spaceDetailId.isEmpty()) errorMsgs.add("場地明細編號: 請勿空白");
			
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spaceDetail/spaceDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				SpaceDetailServiceB spaceDetailServ = new SpaceDetailServiceB();
				SpaceDetailVO selectOneSpaceDetail = new SpaceDetailVO();
				selectOneSpaceDetail = spaceDetailServ.selectOneSpaceDetail(spaceDetailId);
				
				if (selectOneSpaceDetail == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spaceDetail/spaceDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneSpaceDetail", selectOneSpaceDetail);

				String url = "/frontend/spaceDetail/selectOneSpaceDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("deleteSpaceDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceDetailId = req.getParameter("spaceDetailId").trim();

				SpaceDetailServiceB spaceDetailServ = new SpaceDetailServiceB();
				SpaceDetailVO deleteSpaceDetail = new SpaceDetailVO();
				spaceDetailServ.deleteSpaceDetail(spaceDetailId);

				String url = "/frontend/spaceDetail/selectAllSpaceDetail.jsp";
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
				String spaceDetailId = req.getParameter("spaceDetailId").trim();

				SpaceDetailServiceB spaceDetailServ = new SpaceDetailServiceB();
				SpaceDetailVO selectOneUpdate = new SpaceDetailVO();
				selectOneUpdate = spaceDetailServ.selectOneSpaceDetail(spaceDetailId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/frontend/spaceDetail/updateSpaceDetail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("updateSpaceDetail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceDetailId = req.getParameter("spaceDetailId").trim();
				
				String spaceId = req.getParameter("spaceId").trim();
				if(spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");
				
				java.sql.Date spaceDetailFreeDate = null;
				try {
					spaceDetailFreeDate = java.sql.Date.valueOf(req.getParameter("spaceDetailFreeDate").trim());
				} catch (IllegalArgumentException e) {
					spaceDetailFreeDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("場地出租日期: 格式錯誤");
				}
				
				java.sql.Timestamp spaceDetailFreeTimeStart = null;
				try {
					spaceDetailFreeTimeStart = java.sql.Timestamp.valueOf(req.getParameter("spaceDetailFreeTimeStart").trim());
				} catch (IllegalArgumentException e) {
					spaceDetailFreeTimeStart = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("場地出租開始時間: 格式錯誤");
				}
				
				java.sql.Timestamp spaceDetailFreeTimeEnd = null;
				try {
					spaceDetailFreeTimeEnd = java.sql.Timestamp.valueOf(req.getParameter("spaceDetailFreeTimeEnd").trim());
				} catch (IllegalArgumentException e) {
					spaceDetailFreeTimeEnd = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("場地出租結束時間: 格式錯誤");
				}
				
				Integer spaceDetailCharge = null;
				try {
					spaceDetailCharge = new Integer(req.getParameter("spaceDetailCharge").trim());
					if(spaceDetailCharge <= 0) errorMsgs.add("場地時段金額: 請確認金額");
				} catch (NumberFormatException e) {
					spaceDetailCharge = 0;
					errorMsgs.add("場地時段金額: 必須為數字");
				}
				
				SpaceDetailVO updateSpaceDetail = new SpaceDetailVO();
				updateSpaceDetail.setSpaceDetailId(spaceDetailId);
				updateSpaceDetail.setSpaceId(spaceId);
				updateSpaceDetail.setSpaceDetailFreeDate(spaceDetailFreeDate);
				updateSpaceDetail.setSpaceDetailFreeTimeStart(spaceDetailFreeTimeStart);
				updateSpaceDetail.setSpaceDetailFreeTimeEnd(spaceDetailFreeTimeEnd);
				updateSpaceDetail.setSpaceDetailCharge(spaceDetailCharge);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateSpaceDetail); 
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spaceDetail/updateSpaceDetail.jsp");
					failureView.forward(req, res);
					return;
				}

				SpaceDetailServiceB spaceDetailServ = new SpaceDetailServiceB();
				updateSpaceDetail = spaceDetailServ.updateSapceDetail(updateSpaceDetail);
				req.setAttribute("updateSpaceDetail", updateSpaceDetail);

				String url = "/frontend/spaceDetail/selectAllSpaceDetail.jsp";
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
