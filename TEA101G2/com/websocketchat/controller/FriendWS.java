package com.websocketchat.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.websocketchat.jedis.JedisHandleMessage;
import com.websocketchat.model.ChatMessage;
import com.websocketchat.model.State;

@ServerEndpoint("/FriendWS/{userName}")
public class FriendWS {
	private static Map<String, Session> chatSessionsMap = new ConcurrentHashMap<>();  
						//並行概念，與HashTable比較，HashTable封鎖整個集合，ConcurrentHashMap封鎖集合元素
	Gson gson = new Gson();
	//Google Json

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		/* save the new user in the map */
		chatSessionsMap.put(userName, userSession);
//		System.out.println("chatUserName: " + userName);
//		System.out.println("chatUserSession: " + userSession);
		/* Sends all the connected users to the new user */
		Set<String> userNames = chatSessionsMap.keySet();
//		for(String userNamefuck : userNames) {
//			System.out.println("userNamefuck:"+userNamefuck);
//		}
		State stateMessage = new State("open", userName, userNames);
		String stateMessageJson = gson.toJson(stateMessage);  //轉換JSON
		Collection<Session> sessions = chatSessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

//		String text = String.format("ChatSession ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
//				userName, userNames);
//		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
//		System.out.println("Message received: " + message);
		String[] messages = message.split(",");
		String type = messages[0].substring(messages[0].lastIndexOf(":") + 2, messages[0].length()-1);
//		System.out.println("type:"+type);
//		System.out.println("message type:"+ type.equals("chat"));
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
//		System.out.println(chatMessage);
		String sender = chatMessage.getSender();
//		System.out.println(sender);
		String receiver = chatMessage.getReceiver();
//		System.out.println(receiver);
		String time = chatMessage.getTime();
//		System.out.println(time);
		
		if ("history".equals(chatMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
//			System.out.println("historyMsg:" + historyMsg);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg, time);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
//				System.out.println("history = " + gson.toJson(cmHistory));
				return;
			}
		}
		
		
		Session receiverSession = chatSessionsMap.get(receiver);
//		System.out.println("receiverSession:"+receiverSession);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
			userSession.getAsyncRemote().sendText(message);
			JedisHandleMessage.saveChatMessage(sender, receiver, message);
//			System.out.println("saveChatMessage:"+message+time);
		}
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = chatSessionsMap.keySet();
		for (String userName : userNames) {
			if (chatSessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				chatSessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = chatSessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

//		String text = String.format("ChatSession ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
//				reason.getCloseCode().getCode(), userNames);
//		System.out.println(text);
	}
}
