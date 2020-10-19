package com.memberComment.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.member.model.MemberService;
import com.memberComment.model.MemberCommentService;
import com.memberComment.model.MemberCommentVO;

@WebServlet("/MemberCommentServlet.do")
public class MemberCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("addMemberComment".equals(action)) {
			System.out.println("star success");
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String memberAId = req.getParameter("memberAId");
				if (memberAId == null || memberAId.trim().length() == 0) {
					System.out.println("fail Aid");
					errorMsgs.put("memberAId", "請勿空白");
				}

				String memberBId = req.getParameter("memberBId");
				if (memberBId == null || memberBId.trim().length() == 0) {
					System.out.println("fail Bid");
					errorMsgs.put("memberBId", "請勿空白");
				}

				String memberCommentContent = req.getParameter("memberCommentContent");
				if (memberCommentContent == null || memberCommentContent.trim().length() == 0) {
					System.out.println("fail Comment");
					errorMsgs.put("memberCommentContent", "請勿空白");
				}

				Double memberCommentLevel = Double.parseDouble(req.getParameter("memberCommentLevel"));
				if (memberCommentLevel == null || memberCommentLevel < 1 || memberCommentLevel > 5) {
					System.out.println("fail Level");
					errorMsgs.put("memberCommentLevel", "分數錯誤");
				}

				Date memberCommentDate = null;
				try {
					memberCommentDate = new java.sql.Date(System.currentTimeMillis());
				} catch (IllegalArgumentException e) {
					memberCommentDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("memberCommentDate", "格式錯誤");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/frontend/memberComment/addMemberComment.jsp");
					failView.forward(req, res);
					return;
				}

				MemberCommentVO memberCommentVO = new MemberCommentVO();
				memberCommentVO.setMemberAId(memberAId);
				memberCommentVO.setMemberBId(memberBId);
				memberCommentVO.setMemberCommentContent(memberCommentContent);
				memberCommentVO.setMemberCommentLevel(memberCommentLevel);
				memberCommentVO.setMemberCommentDate(memberCommentDate);
				
				MemberCommentService memberCommentSvc = new MemberCommentService();
				memberCommentSvc.addMemberComment(memberCommentVO);
				
				Gson gson = new Gson();
				String jsonString = gson.toJson(memberCommentVO);
				res.getWriter().write(jsonString);
				System.out.println(jsonString);


			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/frontend/memberComment/addMemberComment.jsp");
				failView.forward(req, res);
			}
		}
		
		if ("getOneUpdate".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String memberCommentId = req.getParameter("memberCommentId").trim();

				MemberCommentService memCommentSvc = new MemberCommentService();
				MemberCommentVO memberCommentVO = memCommentSvc.getOneMemberComment(memberCommentId);

				req.setAttribute("memberCommentVO", memberCommentVO);

				String url = "/frontend/memberComment/updateMemberComment.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("//TODO");
				failView.forward(req, res);
			}
		}

		if ("updateMemberComment".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String memberCommentId = req.getParameter("memberCommentId").trim();

				String memberAId = req.getParameter("memberAId").trim();
				if (memberAId == null || memberAId.isEmpty()) {
					errorMsgs.put("memberAId", "請勿空白");
				}

				String memberBId = req.getParameter("memberBId").trim();
				if (memberBId == null || memberBId.isEmpty()) {
					errorMsgs.put("memberBId", "請勿空白");
				}

				String memberCommentContent = req.getParameter("memberCommentContent").trim();
				if (memberCommentContent == null || memberCommentContent.isEmpty()) {
					errorMsgs.put("memberCommentContent", "請勿空白");
				}
				
				
				Double memberCommentLevel = null;
				try {
					memberCommentLevel = Double.parseDouble(req.getParameter("memberCommentLevel").trim());
					if(memberCommentLevel < 1 || memberCommentLevel > 5) {
						errorMsgs.put("memberCommentLevel", "分數錯誤");
					}
				} catch (NumberFormatException e) {
					memberCommentLevel = 0.0;
					errorMsgs.put("memberCommentLevel", "分數錯誤");
				}

				java.sql.Date memberCommentDate = null;
				try {
					memberCommentDate = new java.sql.Date(System.currentTimeMillis());
				} catch (IllegalArgumentException e) {
					memberCommentDate = new java.sql.Date(System.currentTimeMillis());
				}

				MemberCommentVO memberCommentVO = new MemberCommentVO();
				memberCommentVO.setMemberCommentId(memberCommentId);
				memberCommentVO.setMemberAId(memberAId);
				memberCommentVO.setMemberBId(memberBId);
				memberCommentVO.setMemberCommentContent(memberCommentContent);
				memberCommentVO.setMemberCommentLevel(memberCommentLevel);
				memberCommentVO.setMemberCommentDate(memberCommentDate);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberCommentVO", memberCommentVO);
					RequestDispatcher failView = req.getRequestDispatcher("/frontend/memberComment/updateMemberComment.jsp");
					failView.forward(req, res);
					return;
				}

				MemberCommentService memberCommentSvc = new MemberCommentService();
				memberCommentVO = memberCommentSvc.updateMemberComment(memberCommentVO);
				req.setAttribute("memberCommentVO", memberCommentVO);

				String url = "/frontend/memberComment/getAllMemberComment.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/frontend/memberComment/updateMemberComment.jsp");
				failView.forward(req, res);
			}
		}

		if ("deleteMemberComment".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String memberCommentId = req.getParameter("memberCommentId").trim();

				MemberCommentService memberCommentSvc = new MemberCommentService();
				memberCommentSvc.deleteMemberComment(memberCommentId);
				
				String url = "/frontend/memberComment/getAllMemberComment.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("//TODO");
				failView.forward(req, res);
			}
		}

		if ("getOneMemberComment".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String memberCommentId = req.getParameter("memberCommentId").trim();
				if (memberCommentId == null || memberCommentId.isEmpty()) {
					errorMsgs.put("memberCommentId", "請勿空白");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/frontend/memberComment/MemberCommentHome.jsp");
					failView.forward(req, res);
					return;
				}

				MemberCommentService memCommentSvc = new MemberCommentService();
				MemberCommentVO memberCommentVO = memCommentSvc.getOneMemberComment(memberCommentId);

				if (memberCommentVO == null) {
					errorMsgs.put("memberCommentId", "查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/frontend/memberComment/MemberCommentHome.jsp");
					failView.forward(req, res);
					return;
				}

				req.setAttribute("memberCommentVO", memberCommentVO);

				String url = "/frontend/memberComment/getOneMemberComment.jsp";
				RequestDispatcher sucessView = req.getRequestDispatcher(url);
				sucessView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("//TODO");
				failView.forward(req, res);
			}
		}
	}
}
