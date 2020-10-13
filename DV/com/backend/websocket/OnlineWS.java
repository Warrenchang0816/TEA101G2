package com.backend.websocket;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/OnlineWS/{userName}", configurator = ServletAwareConfig.class)
public class OnlineWS {
	
//	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	//Collections.synchronizedXXX()回傳一個同步過的XXX集合

	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	//連線建立時啟動
	
	private EndpointConfig config;
	
	@OnOpen
	public void onLine(@PathParam("userName") String userName, Session userSession, EndpointConfig config) throws IOException {
		this.config = config;
		HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
		System.out.println("httpSession:"+httpSession);
        ServletContext servletContext = httpSession.getServletContext();
        System.out.println("servletContext:"+servletContext);
        
        Map<String, Session> sessionsMap = (Map<String, Session>)servletContext.getAttribute("TEA101G2DV_OnlineList");
        sessionsMap.put(userName, userSession);
        Set<String> set = sessionsMap.keySet();
        for(String name: set) {
        	System.out.println(name);
        }
	        
	    servletContext.setAttribute("TEA101G2DV_OnlineList", sessionsMap);
		String text = String.format("OnlineSession ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);
	}
	
	@OnClose
	public void offLine(Session userSession, CloseReason reason) {
		HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
		System.out.println("httpSession:"+httpSession);
        ServletContext servletContext = httpSession.getServletContext();
        System.out.println("servletContext:"+servletContext);
        
        Map<String, Session> sessionsMap = (Map<String, Session>)servletContext.getAttribute("TEA101G2DV_OnlineList");
//		String userNameClose = null;
//        Set<String> userNames = null;
//        if(!sessionsMap.isEmpty()) {
        Set<String> userNames = sessionsMap.keySet();
//		}
//		for (String userName : userNames) {
//			if (sessionsMap.get(userName).equals(userSession)) {
//				sessionsMap.remove(userName);
//				System.out.println(userName + "offline");
//				break;
//			}
//		}
        
	    servletContext.setAttribute("TEA101G2DV_OnlineList", sessionsMap);
	    
		String text = String.format("OnlineSession ID = %s, disconnected; close code = %d%nusers:", userSession.getId(),
				reason.getCloseCode().getCode());
		System.out.println(text);
	}

}
