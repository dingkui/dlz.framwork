package com.dlz.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import com.dlz.framework.util.config.ConfUtil;
import com.dlz.web.util.HttpUtil.HttpPostUtil;

/**
 * Created by jack on 2017/10/25.
 */
public class WebsocketClient2 {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	public static WebSocketClient client;
	static Timer timer = new Timer();
	
	public static class WebSockeC extends WebSocketClient{
		URI serverUri;
		Map<String, String> httpHeaders;
		int connectTimeout;
		public WebSockeC(URI serverUri, Map<String, String> httpHeaders, int connectTimeout) {
			super(serverUri, new Draft_6455(), httpHeaders, connectTimeout);
			this.serverUri=serverUri;
			this.httpHeaders=httpHeaders;
			this.connectTimeout=connectTimeout;
		}

		@Override
		public void onOpen(ServerHandshake serverHandshake) {
			System.out.println("打开链接");
		}

		@Override
		public void onMessage(String s) {
			System.out.println("收到消息" + s);
		}

		@Override
		public void onClose(int i, String s, boolean b) {
			System.out.println("链接已关闭");
			timer.schedule(new TimerTask(){
				@Override
				public void run() {
					System.out.println("重新连接。。。");
					client=new WebSockeC(serverUri, httpHeaders, connectTimeout);
					client.connect();
				}
			}, 5000);
		}

		@Override
		public void onError(Exception e) {
			System.out.println("发生错误已关闭");
			e.printStackTrace();
		}
	
	}

//	public static void main(String[] args) throws URISyntaxException {
//		final URI serverUri = new URI("ws://127.0.0.1:2222/vcenter/websocket");
//		client = new WebSockeC(serverUri, null,0);
//		client.connect();
//	}
	public static void main(String[] args) throws URISyntaxException {
		
		
		final Map<String, String> map = new HashMap<>();
		
		CookieStore cookieStore = new BasicCookieStore();
		HttpClientContext localContext = new HttpClientContext();
		localContext.setCookieStore(cookieStore);
		try {
			HttpPostUtil.post(ConfUtil.getConfig("cas.url.auth"), ConfUtil.getMap("cas.admin"), localContext);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		final Header[] allHeaders = localContext.getResponse().getAllHeaders();
		//System.out.println(localContext.getResponse().toString());
		for (Header c : allHeaders) {
			System.out.println(c.getName()+" : "+c.getValue());
			if(c.getName().equals("Set-Cookie")){
				map.put("Cookie", c.getValue());
			}
		}
		
		
		
		final URI serverUri = new URI("ws://192.168.1.213:8080/cas/websocket");
		
		
		map.put("Connection", "Upgrade");
		map.put("Upgrade", "websocket");
		
		client = new WebSockeC(serverUri, map,0);
		client.connect();
	}
	
	
	

	public static void send(byte[] bytes) {
		client.send(bytes);
	}

}