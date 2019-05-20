package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Audio;
import com.example.demo.entity.Image;

@Mapper
public interface ImageMapper {
String getImageByName(String name);
int insertImage(Image image);
int updateImage(Image image);
int insertAudio(Audio audio);
 Audio getAudioById(long id);
}
