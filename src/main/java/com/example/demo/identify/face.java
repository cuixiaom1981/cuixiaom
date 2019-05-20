package com.example.demo.identify;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.ImageMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.LoginUser;
import com.example.demo.entity.User;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;

@Component
public class face {
@Autowired
protected ImageService imageService;
public static face fa;
@PostConstruct
public void init() {  
    fa = this;  
    fa.imageService = this.imageService;        
    // 初使化时将已静态化的testService实例化
} 

	// webapi 接口地址
				private static final String WEBWFV_URL = "http://api.xfyun.cn/v1/service/v1/image_identify/face_verification";
				// 应用ID
				private static final String APPID = "5cd132c0";
				// 接口密钥
				private static final String API_KEY = "704eee497d62635cd36f4728ac1252fe";
				protected org.apache.commons.logging.Log log = LogFactory.getLog(getClass());
				
				// 图片地址
				private static final String FILE_PATH1 = "/Users/cuixiaom/Downloads/WechatIMG36.jpeg";
				private static final String FILE_PATH2 = "/Users/cuixiaom/Downloads/WechatIMG35.jpeg";
				/**
				 * OCR WebAPI 调用示例程序
				 * 
				 * @param args
				 * @throws IOException
				 * @throws JSONException 
				 */
		
				
				public Boolean getImageResult(String image1,String image2) throws IOException, JSONException {
					Map<String, String> header = buildHttpHeader();
					
				/*	byte[] imageByteArray1 = FileUtil.read(FILE_PATH1);
					String imageBase641 = new String(Base64.encodeBase64(imageByteArray1), "UTF-8");
					
					byte[] imageByteArray2 = FileUtil.read(FILE_PATH2);
					String imageBase642 = new String(Base64.encodeBase64(imageByteArray2), "UTF-8");
					*/
					String result = HttpUtil.doPost1(WEBWFV_URL, header, "first_image=" + URLEncoder.encode(image1, "UTF-8") + "&" + "second_image="+ URLEncoder.encode(image2, "UTF-8"));
					log.info("人脸比对接口调用结果：" + result);
					//JSONObject.parseObject(result).getFloatValue("data");
					return JSONObject.parseObject(result).getFloatValue("data")>0.75;
				}
                public static Boolean faceId(LoginUser loginuser) throws JSONException, IOException {
                	String image=fa.imageService.getImageByName(loginuser.getName());
	            return   fa.getImageResult(loginuser.getImage(), image);
               }
				public String toBase64(String path) throws IOException{
					byte[] imageByteArray2 = FileUtil.read(path);
					return new String(Base64.encodeBase64(imageByteArray2), "UTF-8");
				}
				/**
				 * 组装http请求头
				 * @throws JSONException 
				 */
				private static Map<String, String> buildHttpHeader() throws UnsupportedEncodingException, JSONException {
					String curTime = System.currentTimeMillis() / 1000L + "";
					
					JSONObject param = new JSONObject();
					param.put("get_image", true);
					String params = param.toString();
					String paramBase64 = new String(Base64.encodeBase64(params.getBytes("UTF-8")));
					
					String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
					Map<String, String> header = new HashMap<String, String>();
					header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
					header.put("X-Param", paramBase64);
					header.put("X-CurTime", curTime);
					header.put("X-CheckSum", checkSum);
					header.put("X-Appid", APPID);
					return header;
				}
	}
