package com.example.demo;

import static org.junit.Assume.assumeTrue;

import java.io.IOException;

import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONException;
import com.example.demo.identify.face;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	protected org.apache.commons.logging.Log log = LogFactory.getLog(getClass());
	@Test
	public void contextLoads() throws JSONException, IOException {
	
	}

}
