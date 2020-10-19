package com.login.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

@WebServlet("/backendLoginHandler")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class backendLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public backendLoginHandler() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		Queue<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		EmpService empService = new EmpService();
		
		try {
			String empAccount = req.getParameter("empAccount");
			req.setAttribute("empAccount", empAccount);
//			System.out.println(empAccount);
				
			String empPassword = req.getParameter("empPassword");
			req.setAttribute("empPassword", empPassword);
//			System.out.println(empPassword);
			
			if(empAccount != null || !empAccount.isEmpty()) {
				if(empService.isEmpAccountLogin(empAccount)) {
					if(empService.isEmpPasswordLogin(empPassword)) {
						EmpVO loginEmp = empService.selectAllEmpByAccount(empAccount);
						empService.updateEmpOnline(loginEmp, "Y");
						session.setAttribute("loginEmp", loginEmp);
							try {
								String location = (String)session.getAttribute("backendLocation");
								if(location != null)
									res.sendRedirect(location);
								else
									res.sendRedirect(req.getContextPath() + "/backend/index.jsp");
								return;
							}catch (Exception e){
								RequestDispatcher exceptionView = req.getRequestDispatcher(req.getContextPath() + "/backend/error.jsp");
								exceptionView.forward(req, res);
							}
					}else {
						RequestDispatcher failVeiw = req.getRequestDispatcher("/backend/backendLogin.jsp");
						failVeiw.forward(req, res);
					}
				}else {
					errorMsgs.add("帳號不存在");
					RequestDispatcher failVeiw = req.getRequestDispatcher("/backend/backendLogin.jsp");
					failVeiw.forward(req, res);
				}
			}else {
				RequestDispatcher failVeiw = req.getRequestDispatcher("/backend/backendLogin.jsp");
				failVeiw.forward(req, res);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
			exceptionView.forward(req, res);
		}
		
		
		
		
	}

}
