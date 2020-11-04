package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberDAO;
import com.member.model.MemberDAO_interface;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/LoginHandler.do")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected MemberVO allowUser(String account, String password) {
		MemberService memSvc = new MemberService();
		MemberVO memberVO = memSvc.LoginAuthenticate(account, password);
		return memberVO;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);

		String account = req.getParameter("account");
		String password = req.getParameter("password");
		MemberVO userVO = allowUser(account, password);

		try {
			if (userVO != null) {
				HttpSession session = req.getSession();
				session.setAttribute("userVO", userVO);
				MemberService ms = new MemberService();
				ms.updateMemberOnline(userVO, "Y");

				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location");
						res.sendRedirect(location);
						return;
					}
				} catch (Exception e) {
				}
				res.sendRedirect(req.getContextPath() + "/frontend/home.jsp");
			} else {
				errorMsgs.put("error", "帳號密碼錯誤，請再次登入");
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/login.jsp");
				failureView.forward(req, res);
			}
		} catch (Exception e) {
			errorMsgs.put("error", "錯誤，請再次登入");
			RequestDispatcher failureView = req.getRequestDispatcher("/frontend/login.jsp");
			failureView.forward(req, res);
		}
	}
}