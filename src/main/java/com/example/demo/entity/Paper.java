package com.example.demo.entity;

public class Paper {
	private long id;
	public Paper(long id, int typeId,  String questionIds, int targetId) {
		super();
		this.id = id;
		this.typeId = typeId;		
		this.questionIds = questionIds;
		this.targetId = targetId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getQuestionIds() {
		return questionIds;
	}
	public void setQuestionIds(String questionIds) {
		this.questionIds = questionIds;
	}
	public int getTargetId() {
		return targetId;
	}
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	private int typeId;

	private String questionIds;
	private int targetId;	

	
	
	
}
