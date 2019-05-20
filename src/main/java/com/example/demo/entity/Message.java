package com.example.demo.entity;

public class Message {
	private int id;
	private String topic;
	public Message(int id, String topic, String message, String from, String timestamp) {
		super();
		this.id = id;
		this.topic = topic;
		this.message = message;
		this.fromUser = from;
		this.timeStamp = timestamp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFrom() {
		return fromUser;
	}
	public void setFromUser(String from) {
		this.fromUser = from;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timestamp) {
		this.timeStamp = timestamp;
	}
	private String message;
	private String fromUser;	
	private String timeStamp;
}
