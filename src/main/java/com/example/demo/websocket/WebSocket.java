package com.example.demo.websocket;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.sql.Timestamp;
import java.util.Date;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.apache.commons.logging.LogFactory;


	@Component
	@ServerEndpoint("/websocket/{username}")
	public   class WebSocket {
		protected org.apache.commons.logging.Log log = LogFactory.getLog(getClass());
		public static int onlineNumber = 0;
		
	    /**
	     * 以用户的姓名为key，WebSocket为对象保存起来
	     */
	    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
	    
	    public WebSocket getWebsocket(String user) {
	    	return clients.get(user);
	    }
	    public boolean getSet(String user) {
	    	return clients.keySet().contains(user);
	    }
	    /**
	     * 会话
	     */
	    private Session session;
	    /**
	     * 用户名称
	     */
	    private String username;

	    /**
	     * OnOpen 表示有浏览器链接过来的时候被调用
	     * OnClose 表示浏览器发出关闭请求的时候被调用
	     * OnMessage 表示浏览器发消息的时候被调用
	     * OnError 表示有错误发生，比如网络断开了等等
	     */

	    /**
	     * 建立连接
	     *
	     * @param session
	     */
	    @OnOpen
	    public void onOpen(@PathParam("username") String username, Session session) {
	        onlineNumber++;
	        log.info("现在来连接的客户id：" + session.getId() + "用户名：" + username);
	        this.username = username;
	        this.session = session;
	        log.info("有新连接加入！ 当前在线人数" + onlineNumber);
	        try {
	            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
	            //先给所有人发送通知，说我上线了
	            Map<String, Object> map1 = new HashMap<>();	         	           
	            clients.put(username, this);
	            map1.put("type", 3);
	            //移除掉自己
	            Set<String> set = clients.keySet();
	            map1.put("userList", set);
	            sendMessageAll(JSON.toJSONString(map1));
	        } catch (IOException e) {
	            log.info(username + "上线的时候通知所有人发生了错误");
	        }


	    }

	    @OnError
	    public void onError(Session session, Throwable error) {
	        log.info("服务端发生了错误" + error.getMessage());
	        //error.printStackTrace();
	    }

	    /**
	     * 连接关闭
	     */
	    @OnClose
	    public void onClose() {
	        onlineNumber--;
	        //webSockets.remove(this);
	        clients.remove(username);
	        try {
	            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
	            Map<String, Object> map1 = new HashMap<>();
	            map1.put("type", 3);
	            map1.put("userList", clients.keySet());	          
	            sendMessageAll(JSON.toJSONString(map1));
	        } catch (IOException e) {
	            log.info(username + "下线的时候通知所有人发生了错误");
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
	    public void onMessage(String message , Session session) {
	        try { 
	            log.info("来自客户端消息：" + message + "客户端的id是：" + session.getId());
	            JSONObject jsonObject = JSON.parseObject(message);
	            int type = jsonObject.getIntValue("type");
	            String fromusername = jsonObject.getString("fromUser");
	            String tousername = jsonObject.getString("toUser");        
	            //如果不是发给所有，那么就发给某一个人
	            //messageType 1代表文字消息 2代表语音 3代表在线名单  
	         /*   Map<String, Object> map1 = new HashMap<>();
	            map1.put("type", 4);
	            map1.put("message", textMessage);
	            map1.put("fromUser", fromusername);  */
	            jsonObject.put("timestamp",new Timestamp(new Date().getTime()));
	            if (tousername.equals("All")) {	         
	                sendMessageAll(jsonObject.toString());
	            } else {	               
	                sendMessageTo(jsonObject.toString(), tousername);
	            
	            }  
	        
	        } catch (Exception e) {
	            log.info("发生了错误了");
	        }
	    }

	    public void sendMessageTo(String message, String ToUserName) throws IOException {
	        for (WebSocket item : clients.values()) {
	            if (item.username.equals(ToUserName)) {
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

	    public static synchronized int getOnlineCount() {
	        return onlineNumber;
	    }

	}
	
	
	
	
	
	
	
