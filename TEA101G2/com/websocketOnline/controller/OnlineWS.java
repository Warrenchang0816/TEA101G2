package com.websocketOnline.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.emp.model.EmpService;
import com.google.gson.Gson;
import com.websocketOnline.model.Mail;
import com.websocketOnline.model.OnlineState;


@ServerEndpoint(value = "/OnlineWS/{userName}", configurator = ServletAwareConfig.class)
public class OnlineWS {
	private static Map<String, Session> onlineSessionsMap = new ConcurrentHashMap<>(); 
	private static Map<String, Session> onlineMemberSessionsMap = new ConcurrentHashMap<>();
//	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	//Collections.synchronizedXXX()回傳一個同步過的XXX集合
	Gson gson = new Gson();
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
//		System.out.println("httpSession:"+httpSession);
        ServletContext servletContext = httpSession.getServletContext();
//        System.out.println("servletContext:"+servletContext);
        
//        Map<String, Session> sessionsMap = (Map<String, Session>)servletContext.getAttribute("TEA101G2DV_OnlineList");
//        sessionsMap.put(userName, userSession);
//        Set<String> set = sessionsMap.keySet();
//        for(String name: set) {
//        	System.out.println(name);
//        }
//	    servletContext.setAttribute("TEA101G2DV_OnlineList", sessionsMap);
        
        
        onlineSessionsMap.put(userName, userSession);
        System.out.println("onlineUserName: " + userName);
        Set<String> userNames = onlineSessionsMap.keySet();
		OnlineState onlineList = new OnlineState("online", userName, userNames);
		String onlineListJson = gson.toJson(onlineList); 
		Collection<Session> sessions = onlineSessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(onlineListJson);
			}
		}
        
		String text = String.format("OnlineSession ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);
	}
	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		Set<String> userSessionNames = onlineSessionsMap.keySet();
		String[] messagesplit = message.split(",");
		String type = messagesplit[0].substring(messagesplit[0].lastIndexOf(":") + 2, messagesplit[0].length()-1);
		System.out.println("type:"+type);
		System.out.println("message type:"+ type.equals("mail"));
		
		if("mail".equals(type)) {
			EmpService es = new EmpService();
			Map<String, String> empNames = es.selectAllEmpIdName();
			Mail mail = gson.fromJson(message, Mail.class);
			String sender = mail.getSender();
			String senderName = empNames.get(sender);
			System.out.println("sender"+sender);
			System.out.println("sender"+empNames.get(sender));
			String receivers = mail.getReceivers();
			System.out.println("receivers:"+receivers);
			String title = mail.getTitle();
			System.out.println("title:"+title);
			String time = mail.getTime();
			System.out.println("time:"+time);
			
			String[] emps = receivers.split(";");
			if (userSession != null && userSession.isOpen()) {
				for(int i = 0; i < emps.length; i++) {
					String emp = emps[i];
					if(userSessionNames.stream().anyMatch(name -> name.equals(emp))) {
						Mail mailInfo = new Mail("mailJSON", sender, emp, title, time);
						userSession.getAsyncRemote().sendText(gson.toJson(mailInfo));
						return;
					}
				}
			}
		}
	}
	
	@OnClose
	public void offLine(Session userSession, CloseReason reason) {
		HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
		System.out.println(userSession.getId()+"close");
//		System.out.println("httpSession:"+httpSession);
        ServletContext servletContext = httpSession.getServletContext();
//        System.out.println("servletContext:"+servletContext);
        
//        Map<String, Session> sessionsMap = (Map<String, Session>)servletContext.getAttribute("TEA101G2DV_OnlineList");
//		String userNameClose = null;
//        Set<String> userNames = null;
//        if(!sessionsMap.isEmpty()) {
//        Set<String> userNames = sessionsMap.keySet();
//		}
//		for (String userName : userNames) {
//			if (sessionsMap.get(userName).equals(userSession)) {
//				sessionsMap.remove(userName);
//				System.out.println(userName + "offline");
//				break;
//			}
//		}
//	    servletContext.setAttribute("TEA101G2DV_OnlineList", sessionsMap);
	    
        String userNameClose = null;
		Set<String> userNames = onlineSessionsMap.keySet();
		for (String userName : userNames) {
			if (onlineSessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				onlineSessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			OnlineState stateMessage = new OnlineState("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = onlineSessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}
        
        
        
        
		String text = String.format("OnlineCloseSession ID = %s, disconnected; close code = %d%nusers:", userSession.getId(),
				reason.getCloseCode().getCode());
		System.out.println(text);
	}

}
