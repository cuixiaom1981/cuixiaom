package com.example.demo.entity;

public class Type {
	private long id;
	private String name;
	private String introduce;
	private int score;
	public Type(long id, String name, String introduce, int score) {
		super();
		this.id = id;
		this.name = name;
		this.introduce = introduce;
		this.score = score;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
