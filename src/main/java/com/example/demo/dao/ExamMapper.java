package com.example.demo.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Exam;
import com.example.demo.entity.Message;
import com.example.demo.entity.Paper;
import com.example.demo.entity.Question;
import com.example.demo.entity.Result1;
import com.example.demo.entity.Type;
@Mapper
public interface ExamMapper {
	
	Exam getExamById(long id);
	Paper getPaperById(long id);	
	Question getQuestionById(long id);	
	int insertExam(Exam exam);
	//int updateExam(Exam exam);
	int insertQuestion(Question exam);
	int insertPaper(Paper paper);
	Type getTypeById(long id);
	int insertType(Type type);
	ArrayList<Message> getAllMessage();
	int insertMessage(Message message);
	Result1 getResultById(long id);
	int insertResult(Result1 result);
}
