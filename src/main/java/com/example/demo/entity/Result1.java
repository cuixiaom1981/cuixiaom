package com.example.demo.entity;

import java.util.ArrayList;

public class Result1 {

	private long id;
	private String userName;
	private int examId;
	private String questionIds;
	private String results;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getQuestionIds() {
		return questionIds;
	}
	public void setQuestionIds(String questionIds) {
		this.questionIds = questionIds;
	}
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
	public Result1(long id, String userName, int examId, String questionIds, String results) {
		super();
		this.id = id;
		this.userName = userName;
		this.examId = examId;
		this.questionIds = questionIds;
		this.results = results;
	}


}
