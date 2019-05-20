package com.example.demo.entity;

public class Question {
	private long id;	
	public Question(long id, String question, String answer,String selection, int objective, int typeId) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.selection=selection;
		this.objective = objective;
		this.typeId = typeId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getObjective() {
		return objective;
	}
	public void setObjective(int objective) {
		this.objective = objective;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	private String question;
	private String answer;
	private String selection;
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	private int objective;	
	private int typeId;
}
