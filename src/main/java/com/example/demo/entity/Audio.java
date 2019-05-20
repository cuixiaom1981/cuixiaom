package com.example.demo.entity;

public class Audio {
	long id;
	public Audio(long id, String toUser, String fromUser, String audio) {
		super();
		this.id = id;
		this.toUser = toUser;
		this.fromUser = fromUser;
		this.audio = audio;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	String toUser;
    String fromUser;
    String audio;


}
