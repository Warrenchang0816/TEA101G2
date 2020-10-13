package com.memberFavorite.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.memberFavorite.model.MemberFavoriteServiceB;
import com.memberFavorite.model.MemberFavoriteVO;
import com.spacePhoto.model.SpacePhotoServiceB;
import com.spacePhoto.model.SpacePhotoVO;

@WebServlet("/MemberFavoriteServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberFavoriteServletB extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public MemberFavoriteServletB() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("backend_AddMemberFavorite".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberId = req.getParameter("memberId").trim();
				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				String spaceId = req.getParameter("spaceId").trim();
				if(spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");

				MemberFavoriteVO addMemberFavorite = new MemberFavoriteVO();
				addMemberFavorite.setMemberId(memberId);
				addMemberFavorite.setSpaceId(spaceId);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addMemberFavorite", addMemberFavorite);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/memberFavorite/addMemberFavorite.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberFavoriteServiceB memberFavoriteServ = new MemberFavoriteServiceB();
				addMemberFavorite = memberFavoriteServ.addMemberFavorite(addMemberFavorite);

				String url = "/backend/memberFavorite/selectAllMemberFavorite.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_SelectOneMemberFavorite".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberFavoriteId = req.getParameter("memberFavoriteId").trim();
				if(memberFavoriteId == null || memberFavoriteId.isEmpty()) errorMsgs.add("會員最愛編號: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/memberFavorite/memberFavorite.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberFavoriteServiceB memberFavoriteServ = new MemberFavoriteServiceB();
				MemberFavoriteVO selectOneMemberFavorite = new MemberFavoriteVO();
				selectOneMemberFavorite = memberFavoriteServ.selectOneMemberFavorite(memberFavoriteId);
				
				if (selectOneMemberFavorite == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/memberFavorite/memberFavorite.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneMemberFavorite", selectOneMemberFavorite);

				String url = "/backend/memberFavorite/selectOneMemberFavorite.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_DeleteMemberFavorite".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberFavoriteId = req.getParameter("memberFavoriteId").trim();

				MemberFavoriteServiceB memberFavoriteServ = new MemberFavoriteServiceB();
				MemberFavoriteVO deleteMemberFavorite = new MemberFavoriteVO();
				memberFavoriteServ.deleteMemberFavorite(memberFavoriteId);

				String url = "/backend/memberFavorite/selectAllmemberFavorite.jsp";
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
				String memberFavoriteId = req.getParameter("memberFavoriteId").trim();

				MemberFavoriteServiceB memberFavoriteServ = new MemberFavoriteServiceB();
				MemberFavoriteVO selectOneUpdate = new MemberFavoriteVO();
				selectOneUpdate = memberFavoriteServ.selectOneMemberFavorite(memberFavoriteId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/backend/memberFavorite/updateMemberFavorite.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_UpdateMemberFavorite".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberFavoriteId = req.getParameter("memberFavoriteId").trim();
				
				String memberId = req.getParameter("memberId").trim();
				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				String spaceId = req.getParameter("spaceId").trim();
				if(spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");

				MemberFavoriteVO updateMemberFavorite = new MemberFavoriteVO();
				updateMemberFavorite.setMemberFavoriteId(memberFavoriteId);
				updateMemberFavorite.setMemberId(memberId);
				updateMemberFavorite.setSpaceId(spaceId);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateMemberFavorite);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/memberFavorite/updateMemberFavorite.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberFavoriteServiceB memberFavoriteServ = new MemberFavoriteServiceB();
				updateMemberFavorite = memberFavoriteServ.updateMemberFavorite(updateMemberFavorite);
//				req.setAttribute("updateSpacePhoto", updateSpacePhoto);

				String url = "/backend/memberFavorite/selectAllMemberFavorite.jsp";
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

		
		
		if ("addMemberFavorite".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberId = req.getParameter("memberId").trim();
				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				String spaceId = req.getParameter("spaceId").trim();
				if(spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");

				MemberFavoriteVO addMemberFavorite = new MemberFavoriteVO();
				addMemberFavorite.setMemberId(memberId);
				addMemberFavorite.setSpaceId(spaceId);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addMemberFavorite", addMemberFavorite);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/memberFavorite/addMemberFavorite.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberFavoriteServiceB memberFavoriteServ = new MemberFavoriteServiceB();
				addMemberFavorite = memberFavoriteServ.addMemberFavorite(addMemberFavorite);

				String url = "/frontend/memberFavorite/selectAllMemberFavorite.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("selectOneMemberFavorite".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberFavoriteId = req.getParameter("memberFavoriteId").trim();
				if(memberFavoriteId == null || memberFavoriteId.isEmpty()) errorMsgs.add("會員最愛編號: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/memberFavorite/memberFavorite.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberFavoriteServiceB memberFavoriteServ = new MemberFavoriteServiceB();
				MemberFavoriteVO selectOneMemberFavorite = new MemberFavoriteVO();
				selectOneMemberFavorite = memberFavoriteServ.selectOneMemberFavorite(memberFavoriteId);
				
				if (selectOneMemberFavorite == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/memberFavorite/memberFavorite.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneMemberFavorite", selectOneMemberFavorite);

				String url = "/frontend/memberFavorite/selectOneMemberFavorite.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("deleteMemberFavorite".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberFavoriteId = req.getParameter("memberFavoriteId").trim();

				MemberFavoriteServiceB memberFavoriteServ = new MemberFavoriteServiceB();
				MemberFavoriteVO deleteMemberFavorite = new MemberFavoriteVO();
				memberFavoriteServ.deleteMemberFavorite(memberFavoriteId);

				String url = "/frontend/memberFavorite/selectAllmemberFavorite.jsp";
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
				String memberFavoriteId = req.getParameter("memberFavoriteId").trim();

				MemberFavoriteServiceB memberFavoriteServ = new MemberFavoriteServiceB();
				MemberFavoriteVO selectOneUpdate = new MemberFavoriteVO();
				selectOneUpdate = memberFavoriteServ.selectOneMemberFavorite(memberFavoriteId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/frontend/memberFavorite/updateMemberFavorite.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("updateMemberFavorite".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberFavoriteId = req.getParameter("memberFavoriteId").trim();
				
				String memberId = req.getParameter("memberId").trim();
				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				String spaceId = req.getParameter("spaceId").trim();
				if(spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");

				MemberFavoriteVO updateMemberFavorite = new MemberFavoriteVO();
				updateMemberFavorite.setMemberFavoriteId(memberFavoriteId);
				updateMemberFavorite.setMemberId(memberId);
				updateMemberFavorite.setSpaceId(spaceId);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateMemberFavorite);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/memberFavorite/updateMemberFavorite.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberFavoriteServiceB memberFavoriteServ = new MemberFavoriteServiceB();
				updateMemberFavorite = memberFavoriteServ.updateMemberFavorite(updateMemberFavorite);
//				req.setAttribute("updateSpacePhoto", updateSpacePhoto);

				String url = "/frontend/memberFavorite/selectAllMemberFavorite.jsp";
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
