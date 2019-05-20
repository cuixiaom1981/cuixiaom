package com.example.demo.entity;

import java.util.ArrayList;

public class Result {

	private int id;
	private String userName;
	private int examId;
	private ArrayList<SingleResult> resultList ;
	public Result(int id, String userName, int examId, ArrayList<SingleResult> resultList) {
		super();
		this.id = id;
		this.userName = userName;
		this.examId = examId;
		this.resultList = resultList;
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
	public ArrayList<SingleResult> getResultList() {
		return resultList;
	}
	public void setResultList(ArrayList<SingleResult> resultList) {
		this.resultList = resultList;
	}	
	;
}
