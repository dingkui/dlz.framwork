package com.dlz.plugin.weixin.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import org.slf4j.Logger;
import com.dlz.plugin.weixin.message.req.VideoMessage;
import com.dlz.plugin.weixin.message.resp.Article;
import com.dlz.plugin.weixin.message.resp.ImageMessage;
import com.dlz.plugin.weixin.message.resp.MusicMessage;
import com.dlz.plugin.weixin.message.resp.NewsMessage;
import com.dlz.plugin.weixin.message.resp.TextMessage;
import com.dlz.plugin.weixin.message.resp.VoiceMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class MessageUtil {

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(MessageUtil.class);
	/**
	 * 响应消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 响应消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	
	/**
	 * 响应消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	
	/**
	 * 响应消息类型：图片
	 */
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	
	/**
	 * 响应消息类型：视频
	 */
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	
	
	
	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	
	
	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	
	
	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	
	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	
	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	
	/**
	 * 请求消息类型： 视频
	 */
	public static final String REQ_MESSAGE_TYPE_VIDEO = "voideo";
	
	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event"; 
	
	/**
	 * 事件类型：subscribe(订阅) 
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	
	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	
	/**
	 * 事件类型：scan (关注用户搜啊秒带参数的二维码)
	 */
	public static final String EVENT_TYPE_SCAN = "SCAN";
	
	/**
	 * 事件类型：LOCATION(上报地理位置)
	 */
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	
	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	 public static final String EVENT_TYPE_CLICK = "CLICK";
	 
	 
	 
	
	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * 
	 * doPost方法有两个参数，request中封装了请求相关的所有内容，可以从request中取出微信服务器发来的消息；
	 * 而通过response我们可以对接收到的消息进行响应，即发送消息
	 * 
	 * 那么如何解析请求消息的问题也就转化为如何从request中得到微信服务器发送给我们的xml格式的消息了。
	 * 这里我们借助于开源框架dom4j去解析xml（这里使用的是dom4j-1.6.1.jar），然后将解析得到的结果存入HashMap
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();  
		
		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();  
		// 读取输入流 
		SAXReader reader = new SAXReader();  
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();  
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();  
		// 遍历所有子节点
		for (Element e : elementList){
			map.put(e.getName(), e.getText()); 
		}
		
		// 释放资源
		inputStream.close();  
		inputStream = null;
		
		return map;
	}
	
//	/**
//	 * 解析微信发来的请求（XML）
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 * doPost方法有两个参数，request中封装了请求相关的所有内容，可以从request中取出微信服务器发来的消息；
//	 * 而通过response我们可以对接收到的消息进行响应，即发送消息
//	 * 那么如何解析请求消息的问题也就转化为如何从request中得到微信服务器发送给我们的xml格式的消息了。
//	 * 
//	 */
//	public static JSONMap parseXml(HttpServletRequest request) throws Exception {
//		// 将解析结果存储在HashMap中
//		JSONMap map = new JSONMap();  
//		// 从request中取得输入流
//		InputStream inputStream = request.getInputStream(); 
//		try{
//			Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
//			Element documentElement = doc.getDocumentElement();
//			NodeList childNodes = documentElement.getChildNodes();
//			for (int i=0;i<childNodes.getLength();i++) {
//				Node e = childNodes.item(i);
//				map.put(e.getNodeName(), e.getTextContent()); 
//			}
//		}finally{
//			// 释放资源
//			inputStream.close();  
//			inputStream = null;
//		}
//		return map;
//	}
	
	/**
	 * 扩展xstream，使其支持CDATA块
	 * @date 2014-06-26
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out){
			return new PrettyPrintWriter(out){
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;
				
				public void startNode(String name, Class clazz){
					super.startNode(name, clazz);
				}
				
				protected void writeText(QuickWriter writer, String text){
					if (cdata){
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					}else{
						writer.write(text);
					}
				}
				
			};
		}
	});

	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessage 文本消息对象
	 * @return
	 * 
	 * 我们先前已经将响应消息封装成了Java类，方便我们在代码中使用。
	 * 那么，请求接收成功、处理完成后，该如何将消息返回呢？
	 * 这里就涉及到如何将响应消息转换成xml返回的问题，
	 * 这里我们将采用开源框架xstream来实现Java类到xml的转换（这里使用的是xstream-1.3.1.jar）
	 * 
	 */
	public static String messageToXml(TextMessage textMessage){
		xstream.alias("xml", textMessage.getClass());  
		return xstream.toXML(textMessage);  

	}
	
	/**
	 * 图文消息对象转换成xml 
	 * 
	 * @param newsMessage
	 * @return xml
	 */
	public static String messageToXml(NewsMessage newsMessage){
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
	
	/**
	 * 图片消息对象转换成 XML
	 * 
	 * @param imageMessage
	 * @return
	 */
	public static String messageToXml(ImageMessage imageMessage){
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	
	/**
	 * 语言消息对象转换成 XML
	 * 
	 * @param voiceMessage
	 * @return
	 */
	public static String messageToXml(VoiceMessage voiceMessage){
		xstream.alias("xml", voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}
	
	/**
	 * 视频消息对象转换成 XML
	 * 
	 * @param videoMessage
	 * @return
	 */
	public static String messageToXml(VideoMessage videoMessage){
		xstream.alias("xml", videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}
	
	/**
	 * 音乐消息对象转换成 XML
	 * 
	 * @param musicMessage
	 * @return
	 */
	public static String messageToXml(MusicMessage musicMessage){
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
	
	
	
	
	
	/**
	 * 计算采用utf-8编码方式时字符串所占字节数
	 * 
	 * @param content
	 * @return
	 */
	public static int getByteSize(String content){
		int size = 0;
		if(content != null){
			try{
				//获取字符长度
				size = content.getBytes().length;
			}catch(Exception e){
				logger.error(e.getMessage(),e);
			}
		}
		return size;
	}
	
	/**
	 * URL 编码(UTF-8)
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source){
		String result = source;
		try{
			result = java.net.URLEncoder.encode(source,"utf-8");
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return result;
	}
	
	
	public static void main (String[] args){
		MessageUtil mu  = new MessageUtil();
		String oauthUrl="http://hebiao.duapp.com/oauthServlet";
		logger.debug(mu.urlEncodeUTF8(urlEncodeUTF8(oauthUrl)));
	}
}
