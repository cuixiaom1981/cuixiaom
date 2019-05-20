package com.example.demo.entity;

public class Exam {
private long id;
public Exam(long id, String courseName, String classId, String stuIds, String paperIds, int time,String dateTime,
		String teacher) {
	super();
	this.id = id;
	this.courseName = courseName;
	this.classId = classId;
	this.stuIds = stuIds;
	this.paperIds = paperIds;
	this.time = time;
	this.dateTime=dateTime;
	this.teacher = teacher;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String coureseName) {
	this.courseName = coureseName;
}
public String getClassId() {
	return classId;
}
public void setClassId(String classId) {
	this.classId = classId;
}
public String getStuIds() {
	return stuIds;
}
public void setStuIds(String stuIds) {
	this.stuIds = stuIds;
}


public String getPaperIds() {
	return paperIds;
}
public void setPaperIds(String paperIds) {
	this.paperIds = paperIds;
}
public int getTime() {
	return time;
}
public void setTime(int time) {
	this.time = time;
}
public String getTeacher() {
	return teacher;
}
public void setTeacher(String teather) {
	this.teacher = teather;
}
private String courseName;
private String classId;
private String stuIds;
private String paperIds;
private int time;
private String dateTime;
public String getDateTime() {
	return dateTime;
}
public void setDateTime(String datetime) {
	this.dateTime = datetime;
}
private String teacher;
}
