package com.memberFavorite.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.memberFavorite.model.MemberFavoriteService;
import com.memberFavorite.model.MemberFavoriteVO;

@WebServlet("/MemberFavoriteServlet.do")
public class MemberFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("addMemberFavorite".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String memberId = req.getParameter("memberId").trim();
				if (memberId == null || memberId.isEmpty()) {
					errorMsgs.put("memberId", "請勿空白");
				}

				String spaceId = req.getParameter("spaceId").trim();
				if (spaceId == null || spaceId.isEmpty()) {
					errorMsgs.put("spaceId", "請勿空白");
				}

				MemberFavoriteVO memberFavoriteVO = new MemberFavoriteVO();
				memberFavoriteVO.setMemberId(memberId);
				memberFavoriteVO.setSpaceId(spaceId);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberFavoriteVO", memberFavoriteVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/memberFavorite/addMemberFavorite.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberFavoriteService memberFavoriteSvc = new MemberFavoriteService();
				memberFavoriteSvc.addMemberFavorite(memberFavoriteVO);

				String url = "/frontend/memberFavorite/getAllMemberFavorite.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/frontend/memberFavorite/addMemberFavorite.jsp");
				failView.forward(req, res);
			}
		}

		if ("getOneUpdate".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String memberFavoriteId = req.getParameter("memberFavoriteId").trim();

				MemberFavoriteService memberFavoriteSvc = new MemberFavoriteService();
				MemberFavoriteVO memberFavoriteVO = memberFavoriteSvc.getOneMemberFavorite(memberFavoriteId);

				req.setAttribute("memberFavoriteVO", memberFavoriteVO);

				String url = "/frontend/memberFavorite/updateMemberFavorite.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("//TODO");
				failView.forward(req, res);
			}
		}

		if ("updateMemberFavorite".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String memberFavoriteId = req.getParameter("memberFavoriteId").trim();

				String memberId = req.getParameter("memberId").trim();
				if (memberId == null || memberId.isEmpty()) {
					errorMsgs.put("memberId", "請勿空白");
				}

				String spaceId = req.getParameter("spaceId").trim();
				if (spaceId == null || spaceId.isEmpty()) {
					errorMsgs.put("spaceId", "請勿空白");
				}

				MemberFavoriteVO memberFavoriteVO = new MemberFavoriteVO();
				memberFavoriteVO.setMemberFavoriteId(memberFavoriteId);
				memberFavoriteVO.setMemberId(memberId);
				memberFavoriteVO.setSpaceId(spaceId);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberFavoriteVO", memberFavoriteVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/memberFavorite/updateMemberFavorite.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberFavoriteService memberFavoriteSvc = new MemberFavoriteService();
				memberFavoriteSvc.updateMemberFavorite(memberFavoriteVO);

				String url = "/frontend/memberFavorite/getAllMemberFavorite.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/frontend/memberFavorite/updateMemberFavorite.jsp");
				failView.forward(req, res);
			}
		}
		
		if ("deleteMemberFavorite".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberFavoriteId = req.getParameter("memberFavoriteId").trim();
				System.out.println("memberFavoriteId="+memberFavoriteId);
				MemberFavoriteService memberFavoriteSvc = new MemberFavoriteService();
				memberFavoriteSvc.deleteMemberFavorite(memberFavoriteId);

				String url = "/frontend/memberFavorite/memberFavoriteList.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("//TODO");
				failView.forward(req, res);
			}
		}
		
		if ("getOneMemberFavorite".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberFavoriteId = req.getParameter("memberFavoriteId").trim();
				if(memberFavoriteId == null || memberFavoriteId.isEmpty()) {
					errorMsgs.put("memberFavoriteId", "請勿空白");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/memberFavorite/MemberFavoriteHome.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberFavoriteService memberFavoriteSvc = new MemberFavoriteService();
				MemberFavoriteVO memberFavoriteVO = memberFavoriteSvc.getOneMemberFavorite(memberFavoriteId);
				
				if (memberFavoriteVO == null) {
					errorMsgs.put("memberFavoriteId", "查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/memberFavorite/MemberFavoriteHome.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("memberFavoriteVO", memberFavoriteVO);

				String url = "/frontend/memberFavorite/getOneMemberFavorite.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("//TODO");
				failView.forward(req, res);
			}
		}	
	}
}
