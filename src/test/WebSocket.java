package com.example.demo.websocket;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;







	@Component
	@ServerEndpoint("/websocket/student/{username}")
	public   class WebSocket {
	  // private Logger logger = lombok.extern.log4j.Log4j.Logger. ;
    	protected org.apache.commons.logging.Log log = LogFactory.getLog(getClass());
	    /**
	     * 在线人数
	     */
	    public static int onlineNumber = 0;
	    /**
	     * 以用户的姓名为key，WebSocket为对象保存起来
	     */
	    private static Map<String, WebSocket> clients = new HashMap<String, WebSocket>();
	    /**
	     * 会话
	     */
	    private Session session;
	    /**
	     * 用户名称
	     */
	    private String username;
	    /**
	     * 建立连接
	     *
	     * @param session
	     */
	    @OnOpen
	    public void onOpen(@PathParam("username") String username, Session session)
	    {
	        onlineNumber++;
	      log.info("现在来连接的客户id："+session.getId()+"用户名："+username);
	        this.username = username;
	        this.session = session;
	       //messageType 1代表文字消息 2代表语音消息 3代表在线名单 
			//把自己的信息加入到map当中去
			clients.put(username, this);            
			//给所有人发一条消息：更新在线名单
		    try {
				refreshUserList();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	 
	    @OnError
	    public void onError(Session session, Throwable error) {
	        log.info("服务端发生了错误"+error.getMessage());
	        //error.printStackTrace();
	    }
	    /**
	     * 连接关闭
	     */
	    @OnClose
	    public void onClose()
	    {
	        onlineNumber--;
	        //webSockets.remove(this);
	        clients.remove(username);
	        try {
	        	refreshUserList(); 
	        }
	        catch (IOException e){
	            log.info(username+"下线的时候通知所有人发生了错误");
	        }
	        log.info("有连接关闭！ 当前在线人数" + onlineNumber);
	    }
	 
	    /**
	     * 收到客户端的消息
	     *
	     * @param message 消息
	     * @param session 会话
	     */
	    @OnMessage
	    public void onMessage(String message, Session session)
	    {
	        try {
	            log.info("来自客户端消息：" + message+"客户端的id是："+session.getId());
	            JSONObject json = JSON.parseObject(message);
	          
	            //String fromusername = json.getString("fromUser");
	            String tousername = json.getString("toUser");
	            //如果不是发给所有，那么就发给某一个人
	            json.put("timestamp", new Timestamp(new Date().getTime()));
	           
	          
	            if(tousername.equals("All")){
	                
	            	sendMessageAll(json.toString());
	            }
	            else{
	                
	                sendMessageTo(json.toString(),tousername);
	            }
	        }
	        catch (Exception e){
	            log.info("发生了错误了");
	        }
	 
	    }
	 
	 
	    public void sendMessageTo(String message, String ToUserName) throws IOException {
	        for (WebSocket item : clients.values()) {
	        	//WebSocket item1=clients.get(ToUserName);
	        	//item1.session.getAsyncRemote().sendText(message);
	            if (item.username.equals(ToUserName) ) {
	                item.session.getAsyncRemote().sendText(message);
	                break;
	            }
	        }
	    }
	 
	    public void sendMessageAll(String message) throws IOException {
	        for (WebSocket item : clients.values()) {
	                item.session.getAsyncRemote().sendText(message);
	        }
	    }
	    public void refreshUserList() throws IOException {
	    	 JSONObject json=new JSONObject();
	            json.put("type", 3);
	            Set<String> set = clients.keySet();
	            json.put("userList", set.toArray());
	            sendMessageAll(json.toString());
	        
	    }
	    public static synchronized int getOnlineCount() {
	        return onlineNumber;
	    }
	}
	
	
	
	
	
	
	
