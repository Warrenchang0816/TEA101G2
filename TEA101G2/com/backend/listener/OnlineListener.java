package com.backend.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.websocket.Session;

public class OnlineListener implements ServletContextListener{
	
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		context.setAttribute("TEA101G2DV_OnlineList", sessionsMap);
		System.out.println("FFFFFUCKTEA101G2DV_OnlineList");
		
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		context.removeAttribute("TEA101G2DV_OnlineList");
		System.out.println("FUCKKKKKTEA101G2DV_OnlineList");
	}
}
