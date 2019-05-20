package com.example.demo.entity;

public class SingleResult {
int questionId;
String answer;
public SingleResult(int questionId, String answer) {
	super();
	this.questionId = questionId;
	this.answer = answer;
}
public int getQuestionId() {
	return questionId;
}
public void setQuestionId(int questionId) {
	this.questionId = questionId;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
}
