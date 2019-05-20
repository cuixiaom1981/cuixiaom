package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;



@SpringBootApplication

@MapperScan(basePackages="com.example.demo.*",
	annotationClass=Mapper.class)
//@EnableCaching

public class DemoApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		
	}
	protected SpringApplicationBuilder configure(
          SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
