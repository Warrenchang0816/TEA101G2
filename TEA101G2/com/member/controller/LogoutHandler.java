package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/LogoutHandler.do")
public class LogoutHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberService ms = new MemberService();
		MemberVO userVO = (MemberVO)session.getAttribute("userVO");
		ms.updateMemberOnline(userVO, "N");
		session.invalidate();
		String url = req.getContextPath() + "/frontend/home.jsp"; 
		res.sendRedirect(url);
	}
}
