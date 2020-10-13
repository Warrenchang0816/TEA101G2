package com.login.controller;

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
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutHandler")@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class LogoutHandler extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public LogoutHandler() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		try {
			if(session.getAttribute("loginEmp") != null) {
				System.out.println("YAYAYAYAY");
				session.invalidate();
				res.sendRedirect(req.getContextPath() + "/backend/backendLogin.jsp");
			}else {
				System.out.println("YAYAYAYAY222");
				res.sendRedirect(req.getContextPath() + "/backend/backendLogin.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
			exceptionView.forward(req, res);
		}
	}
}