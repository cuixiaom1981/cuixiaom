package com.example.demo.controller;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Audio;
import com.example.demo.entity.Exam;
import com.example.demo.entity.LoginUser;
import com.example.demo.entity.Message;
import com.example.demo.entity.Paper;
import com.example.demo.entity.Question;
import com.example.demo.entity.Result;
import com.example.demo.entity.Result1;
import com.example.demo.entity.Type;
import com.example.demo.entity.User;
import com.example.demo.identify.WebIAT;
import com.example.demo.identify.face;
import com.example.demo.service.ExamService;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;
import com.example.demo.websocket.WebSocket;
@RestController
@ComponentScan
public class UserController {
	@Autowired
	   private UserService userService=null;
		@Autowired
		   private ImageService imageService=null;

		@Autowired
		   private ExamService examService=null;
		@Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;
		
		  @RequestMapping(value="/user/{name}")
		public User getuserByName(@PathVariable("name") String name) {
		        //调用dao层
		        return userService.getUserByName(name);
		  }
		   @RequestMapping(value="/user/id/{id}")
			 public User getuserById(@PathVariable("id") long id) {
			        //调用dao层
			        return userService.getUserById(id);
			   }
		   
		   @PostMapping(value="/user/registe")
		   @ResponseStatus(HttpStatus.CREATED)
		   public User insertuser(@RequestBody User user  ) {
		        //调用dao层
			//User user1=new User(5,"lee","000","stu");
			  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		       return userService.insertUser(user);
		   }
		   @PutMapping(value="/user/update")
		   public User updateuser(@RequestBody User user
				   ) {
		        //调用dao层
			   user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		       return userService.updateUser(user);
		   }
		   @DeleteMapping(value="/user/delete/{id}")
		   public int deleteuser(@PathVariable("id") long id
				   ) {
		        //调用dao层
			
		       return userService.deleteUser(id);
		   }
		   @RequestMapping(value="/users")
		   public ArrayList<User> getAllUser() {
		        //调用dao层
			  
		        return userService.getAllUser();
		   }
		   
		   
		   @PostMapping(value="/audio")
		   @ResponseStatus(HttpStatus.CREATED)
		   public int insertAudio(@RequestBody Audio audio  ) throws IOException {
		        WebSocket item=new WebSocket();
		        String user=audio.getToUser();
		        int id=0;
		       if( item.getSet(user) ){	
		    	   id=(int)imageService.insertAudio(audio).getId();
		    	  JSONObject json=new JSONObject();
		    	  json.put("id", id);
		    	  json.put("type", 2);
		    	  json.put("fromUser", audio.getFromUser());
		    	if(user.equals("All")) {item.sendMessageAll(json.toString());
		    	}else{item.sendMessageTo(json.toString(), user);
		    	}
			   //调用dao层
			return id;}
		       else {
		    	   return 0;
		       }
		   }
		   
		   //audio
		   @GetMapping(value="/audio/{id}")
				 public Audio getAudioById(@PathVariable("id") long id) {
				        //调用dao层
				        return imageService.getAudioById(id);
				   }
		   @GetMapping(value="/audioId/{id}")
			 public String getAudioIdById(@PathVariable("id") long id) throws UnsupportedEncodingException {
			        //调用dao层
			   WebIAT we=new WebIAT();
			 return  we.audioId(imageService.getAudioById(id).getAudio());
			        
			   }
		   //exam
		   @GetMapping(value="/exam/{id}")
			 public Exam getExamById(@PathVariable("id") long id) {
			        //调用dao层
			        return examService.getExamById(id);
			   }
		   @PostMapping(value="/exam")
		   public Exam insertExam(@RequestBody Exam exam  ) {
		        //调用dao层
			
		       return examService.insertExam(exam);
		   }
		   @GetMapping(value="/question/{id}")
			 public Question getQuestonById(@PathVariable("id") long id) {
			        //调用dao层
			        return examService.getQuestionById(id);
			   }
		   @GetMapping(value="/paper/{id}")
			 public Map<String,String[]> getPaperByExamId(@PathVariable("id") long id) {
			        //调用dao层
				 Map<String,String[]> map=new HashMap<>();
				String[]ids= examService.getExamById(id).getPaperIds().split(",");
			 for(String s:ids ) {
				 Paper paper= examService.getPaperById( Long.parseLong(s));
				 
				 map.put(paper.getTypeId()+"", paper.getQuestionIds().split(","));
			 }
			   return map;
			   }

			   @PostMapping(value="/paper")
			   public Paper insertPaper(@RequestBody Paper paper  ) {
			        //调用dao层
				
			       return examService.insertPaper(paper);
			   }
			 @GetMapping(value="/type/{id}")
			 public Type getTypeById(@PathVariable("id") long id) {
			        //调用dao层
			        return examService.getTypeById(id);
			   }
		   
			  @PostMapping(value="/message")
			   public Message insertMessage(@RequestBody Message message  ) {
			        //调用dao层
				
			       return examService.insertMessage(message);
			   }
			 @GetMapping(value="/message")
			 public ArrayList<Message> getAllMessage() {
			        //调用dao层
			        return examService.getAllMessage();
			   }
			 
			  @PostMapping(value="/result")
			   public int insertResult(@RequestBody Result result  ) {
			        //调用dao层
			
			       return examService.insertResult(result);
			   }
			 @GetMapping(value="/result/{id}")
			 public Result1 getResultById(@PathVariable("id") long id) {
			        //调用dao层
				
			        return examService.getResultById(1);
			   }
		   @RequestMapping(value="/test")
		   public Boolean test(@RequestBody LoginUser loginuser
				   ) throws JSONException, IOException {
		        //调用dao层
			   face fa=new face();	
			   //return fa.getImageResult(fa.toBase64("/Users/cuixiaom/Downloads/WechatIMG36.jpeg"), fa.toBase64("/Users/cuixiaom/Downloads/WechatIMG35.jpeg"));
			 //  return fa.getImageResult(loginuser.getImage(), userService.getUserByName(loginuser.getName()).getImage());
			   return fa.faceId(loginuser);
		   }
	
	
	
}
