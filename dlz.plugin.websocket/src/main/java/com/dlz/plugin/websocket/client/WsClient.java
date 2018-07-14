package com.dlz.plugin.websocket.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.core.codec.CodecException;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.websocket.handler.IWsHandler;

/**
 * Created by dk 2018-07-14
 */
public class WsClient {
	static MyLogger logger=MyLogger.getLogger(WsClient.class);
	public static WebSocketClient client;
	static Timer timer = new Timer();
	
	IWsHandler handler;
	URI serverUri;
	Map<String, String> httpHeaders=new HashMap<>();
	int connectTimeout=0;//连接超时时间
	long retryTime;//断线重连间隔时间
	
	
	
	public WsClient(String url,IWsHandler handler){
		try {
			serverUri = new URI(url);
		} catch (URISyntaxException e) {
			throw new CodecException("WsClient 初始化有误 url:"+url, e);
		}
		this.handler=handler;
	}
	
	/**
	 * 客户端开始连接
	 */
	private void initClient(){
		httpHeaders.clear();
		httpHeaders.put("Connection", "Upgrade");
		httpHeaders.put("Upgrade", "websocket");
		String sessionId=handler.getSessionId();
		if(sessionId!=null){
			httpHeaders.put("Cookie", "JSESSIONID="+sessionId);
		}
		final Map<String, String> headers = handler.getHeaders();
		if(headers!=null && !headers.isEmpty()){
			httpHeaders.putAll(headers);
		}
		client=new WebSockeC();
		status=1;
		client.connect();
	}
	
	private class WebSockeC extends WebSocketClient{
		private WebSockeC() {
			super(serverUri, new Draft_6455(), httpHeaders, connectTimeout);
		}
		@Override
		public void onOpen(ServerHandshake serverHandshake) {
			status=2;
			try{
				handler.onOpen();
			}catch(Exception e){
				logger.error(e.getMessage(), e);
			}
		}
		@Override
		public void onMessage(String s) {
			System.out.println("收到消息" + s);
			try{
				handler.onMessage(s);
			}catch(Exception e){
				logger.error(e.getMessage(), e);
			}
		}

		@Override
		public void onClose(int i, String s, boolean b) {
			System.out.println("链接已关闭");
			client=null;
			if(status!=-1){
				timer.schedule(new TimerTask(){
					@Override
					public void run() {
						if(status!=-1){
							System.out.println("重新连接。。。");
							initClient();
						}
					}
				}, retryTime);
			}
		}
		@Override
		public void onError(Exception e) {
			try{
				handler.onError(e);
			}catch(Exception e2){
				logger.error(e2.getMessage(), e2);
			}
		}
	}

	/**
	 * 发送消息，连接成功时才能执行
	 * @param bytes
	 */
	public void send(byte[] bytes) {
		if(status!=2){
			throw new CodecException("发送失败，status="+status);
		}
		client.send(bytes);
	}
	/**
	 * 客户端连接，初始状态0和关闭状态-1可以连接
	 * @param retryTime 断线重连时间
	 * @param connectTimeout 连接超时时间
	 */
	public void connect(long retryTime,int connectTimeout) {
		if(status!=0 && status!=-1){
			throw new CodecException("连接失败，status="+status);
		}
		this.retryTime=retryTime;
		this.connectTimeout=connectTimeout;
		initClient();
	}
	/**
	 * 客户端连接，初始状态0和关闭状态-1可以连接
	 */
	public void connect() {
		connect(10000,0);
	}
	/**
	 * 重新连接，连接成功时才能执行
	 */
	public void reset() {
		if(status!=2){
			throw new CodecException("重置失败，status="+status);
		}
		client.close();
	}
	//运行状态 0初始 1启动中 2连接成功 -1连接销毁
	int status=0;
	
	/**
	 * 客户端关闭,只有在客户端启动中和连接成功时才能执行
	 */
	public void close() {
		if(status!=1 && status!=2){
			throw new CodecException("关闭失败，status="+status);
		}
		status=-1;
		client.close();
	}
}