package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.demo.dao.ExamMapper;
import com.example.demo.entity.Exam;

import com.example.demo.entity.Message;
import com.example.demo.entity.Paper;
import com.example.demo.entity.Question;
import com.example.demo.entity.Result;
import com.example.demo.entity.Result1;
import com.example.demo.entity.Type;



@Service
//@CacheConfig(cacheNames="redisCache")
public class ExamService {

	@Autowired
	ExamMapper examdao=null;
	
	public Exam getExamById(long id) {
	return examdao.getExamById(id);
	}

	//@CachePut (key = "#result.id")
	public Exam insertExam(Exam exam) {
		examdao.insertExam(exam);
		return  exam;
		
	}
	//@CachePut(key = "#id")
	//public Exam updateExam(Exam exam) {
	//	examdao.updateExam(exam);
	//	 return exam;
		
//	}	

	public Question insertQuestion(Question question) {
		examdao.insertQuestion(question);
		return question;	
	}
	
	public Question getQuestionById(long id) {
		return examdao.getQuestionById(id);
		}
	
	public Paper insertPaper(Paper paper) {
		examdao.insertPaper(paper);
		return paper;
		
	}
	
	public Paper getPaperById(long id) {
		return examdao.getPaperById(id);
		}
	
	public Type getTypeById(long id) {
		return examdao.getTypeById(id);
		}
	public Type insertType(Type type) {
		examdao.insertType(type);
		return type;	
	}
	
	public ArrayList<Message> getAllMessage() {
		return examdao.getAllMessage();
		}
	public Message insertMessage(Message message){
		examdao.insertMessage(message);
		return message;
		
	}
	
	public Result1 getResultById(long id) {
		 return examdao.getResultById(id);
		}
	
	public int insertResult(Result Result) {	
		
		return examdao.insertResult(changeResult(Result));	
	}
	
	public Result1 changeResult(Result result) {
		String questionIds,results;
		questionIds=result.getResultList().get(0).getQuestionId()+"";
		results=result.getResultList().get(0).getAnswer();
		if(result.getResultList().size()>1) {
		for (int i=1;i<result.getResultList().size();i++) {
			questionIds=questionIds+","+result.getResultList().get(i).getQuestionId()+"";
			results=results+"#"+result.getResultList().get(i).getAnswer();
		}}
		Result1 result1=new Result1(1,result.getUserName(),result.getExamId(),questionIds,results);
		return result1;
	}
}
