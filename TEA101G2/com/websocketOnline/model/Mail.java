package com.websocketOnline.model;

import java.util.Set;

public class Mail {
	private String type;
	private String sender;
	private String receivers;
	private String title;
	private String time;
	
	
	public Mail(String type, String sender, String receivers, String title, String time) {
		super();
		this.type = type;
		this.sender = sender;
		this.receivers = receivers;
		this.title = title;
		this.time = time;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getReceivers() {
		return receivers;
	}


	public void setReceivers(String receivers) {
		this.receivers = receivers;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
	
	
}
