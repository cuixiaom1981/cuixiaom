package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.alibaba.fastjson.JSONException;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.JwtUser;
import com.example.demo.entity.LoginUser;
import com.example.demo.entity.User;
import com.example.demo.identify.face;
@Service
@CacheConfig(cacheNames="redisCache")
public class UserService implements UserDetailsService{

	@Autowired
	UserMapper userdao=null;
	face fa;
	@Override
	   public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	        User user = userdao.getUserByName(userName);
	        return new JwtUser(user);
	    }
	public JwtUser changeUser(User user){
		return new JwtUser(user);
		
	}
	
	public User getUserByName(String userName) {
	return userdao.getUserByName(userName);
	}
    public Boolean faceId(LoginUser loginuser) throws JSONException, IOException {
    	return fa.getImageResult(loginuser.getImage(), getUserByName(loginuser.getName()).getImage());
    }


	//@Cacheable(key = "#id")
	public User getUserById(long id) {
		return userdao.getUserById(id);
	}
	//@CachePut (key = "#result.id")
	public User insertUser(User user) {
		userdao.insertUser(user);
		return  user;
		
	}
	//@CachePut(key = "#id")
	public User updateUser(User user) {
		 userdao.updateUser(user);
		 return user;
		
	}
	//@CachePut(key = "#id")
	public int deleteUser(long id) {
		return userdao.deleteUser(id);
		 
		
	}
	public ArrayList<User> getAllUser() {
        //调用dao层
        return userdao.getAllUser();
   }
}
