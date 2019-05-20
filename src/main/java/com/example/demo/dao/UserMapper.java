package com.example.demo.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.entity.User;
@Mapper

public interface UserMapper {
User getUserByName(String name);
User getUserById(long id);	
ArrayList<User>getAllUser();
int insertUser(User user);
int updateUser(User user);
int deleteUser(long id);
}
