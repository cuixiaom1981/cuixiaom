package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ImageMapper;
import com.example.demo.entity.Audio;
import com.example.demo.entity.Image;



@Service
//@CacheConfig(cacheNames="redisCache")
public class ImageService {

	@Autowired
	ImageMapper imagedao=null;
	
	public String getImageByName(String name) {
	return imagedao.getImageByName(name);
	}



	
	//@CachePut (key = "#result.id")
	public Image insertImage(Image image) {
		imagedao.insertImage(image);
		return  image;
		
	}
	//@CachePut(key = "#id")
	public Image updateImage(Image image) {
		imagedao.updateImage(image);
		 return image;
		
	}	
	
	
	
	public Audio insertAudio(Audio audio) {
		imagedao.insertAudio(audio);
		return audio;
		
	}
	
	public Audio getAudioById(long id) {
		return imagedao.getAudioById(id);
		}
}
