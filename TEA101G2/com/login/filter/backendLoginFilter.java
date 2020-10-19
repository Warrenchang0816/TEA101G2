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

public class backendLoginFilter implements Filter{
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
		
		Object loginEmp = session.getAttribute("loginEmp");
		String servletPath = req.getServletPath();
		
		if(isBackendLoginPage(servletPath)) {
			if(loginEmp == null) {
				session.setAttribute("backendLocation", req.getRequestURI());
				res.sendRedirect(req.getContextPath() + "/backend/backendLogin.jsp");
				return;
			}else {
				chain.doFilter(req, res);
			}
		}else {
			chain.doFilter(req, res);
		}
		
	}
	
	
	private boolean isBackendLoginPage(String servletPath) {
		if(!servletPath.equals("/backend/backendLogin.jsp") && !servletPath.equals("/LoginHandler")
				&& !servletPath.endsWith(".css") && !servletPath.endsWith(".js")
				&& !servletPath.endsWith(".png") && !servletPath.endsWith(".ico") && !servletPath.endsWith(".jpg")
				&& !servletPath.endsWith(".woff") && !servletPath.endsWith(".ttf"))
		return true;
		else
		return false;
	}
	
	
}
