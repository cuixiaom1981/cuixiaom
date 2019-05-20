package com.example.demo.entity;

public class Grade {
	private int id;
	private String userName;
	private int examId;
	private int grade;	
	private String dateTime;
	public Grade(int id, String userName, int examId, int grade, String dateTime) {
		super();
		this.id = id;
		this.userName = userName;
		this.examId = examId;
		this.grade = grade;
		this.dateTime = dateTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
