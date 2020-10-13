package com.websocketchat.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/TogetherWS/{userName}")
public class TogetherWS {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	//Collections.synchronizedXXX()回傳一個同步過的XXX集合

	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	//連線建立時啟動
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		connectedSessions.add(userSession);
		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);
	}
	
	//接收訊息時啟動
	@OnMessage
	public void onMessage(Session userSession, String message) {  //String message = JSON傳遞的資料
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
			//考慮同步問題來選擇方法
		}
		System.out.println("Message received: " + message);
	}

	//連線中斷時啟動
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
