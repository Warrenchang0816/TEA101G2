package com.login.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpVO;

public class backendIndexFilter implements Filter{
	private FilterConfig config;
	
	public void init(FilterConfig config) {
		this.config = config;
	}
	
	public void destory() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		EmpVO loginEmp = (EmpVO)session.getAttribute("loginEmp");
		Integer loginEmpAuth = loginEmp.getEmpAuth();
		
		if(loginEmpAuth < 2) {
				res.sendRedirect(req.getContextPath() + "/backend/empIndex.jsp");
				return;
		}else {
			chain.doFilter(req, res);
		}
		
	}
	
}
