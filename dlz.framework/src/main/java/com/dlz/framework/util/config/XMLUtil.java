package com.dlz.framework.util.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.dlz.framework.logger.MyLogger;
@SuppressWarnings({"unchecked","rawtypes"})
public class XMLUtil {
	private static final MyLogger logger = MyLogger.getLogger(XMLUtil.class);

	/**
	 * 
	 * @param obj 
	 * @param encoding 编码
	 * @param needFormat 是否需要格式化
	 * @return
	 * @throws IOException
	 */
	public static String createDoc(Object obj,String encoding,boolean needFormat) throws IOException {
		return createDoc(obj, "root", encoding, needFormat);
	}
	/**
	 * 
	 * @param obj 
	 * @param encoding 编码
	 * @return
	 * @throws IOException
	 */
	public static String createDoc(Object obj,String encoding) throws IOException {
		return createDoc(obj, "root", encoding, false);
	}
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static String createDoc(Object obj) throws IOException {
		return createDoc(obj, "root", "UTF-8", false);
	}
	/**
	 * 
	 * @param obj
	 * @param rootName xml根目录
	 * @param encoding 编码
	 * @param needFormat 是否需要格式化
	 * @return
	 * @throws IOException
	 */
	public static String createDoc(Object obj,String rootName,String encoding,boolean needFormat) throws IOException {
		Document doc = DocumentFactory.getInstance().createDocument();
		doc.setXMLEncoding(encoding);
		Element root = doc.addElement(rootName);
		if(obj instanceof Map){
			Map m = (Map)obj;
			for (String h : (Set<String>)m.keySet()) {
				root.addElement(h).setText(m.get(h).toString());
			}
		}else{
			Field[] fileds = obj.getClass().getDeclaredFields();
			String methodtitle;
			String value;
			for (int j = 0; j < fileds.length; j++) {
				String title = fileds[j].getName();
				Class type = fileds[j].getType();
				if (type.equals(boolean.class)) {
					methodtitle = "is";
				} else {
					methodtitle = "get";
				}
				methodtitle += title.substring(0, 1).toUpperCase() + title.substring(1, title.length());
				Method method;
				try {
					method = obj.getClass().getMethod(methodtitle, (Class<?>)null);
					if (method.invoke(obj) == null) {
						value = "";
					} else {
						value = method.invoke(obj).toString();
					}
				} catch (Exception e) {
					value="";
				} 
				root.addElement(title).setText(changeStr(value));
			}
		}
		StringWriter out = new StringWriter();
		XMLWriter writer = null;
		if(needFormat){
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("GBK");
			writer = new XMLWriter(out, format);
		}else{
			writer = new XMLWriter(out);
		}
		writer.write(doc);
		writer.flush();
		return out.toString();
	}
	
	public static Document readXmlAsDoc(String path) {
		Document doc = null;
		try {
			File file = new File(path);
			SAXReader reader = new SAXReader();
			doc = reader.read(file);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return doc;
	}

	public static String readXmlAsStr(String path) {
		return readXmlAsDoc(path).asXML();
	}

	

	public static Document createDoc() {
		Document doc = DocumentFactory.getInstance().createDocument();
		return doc;
	}

	public static void saveXml(String path, Document document) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				File p = new File(file.getParent());
				if (!p.exists()) {
					p.mkdirs();
				}
				file.createNewFile();
			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("GBK");
			XMLWriter output = new XMLWriter(new FileWriter(file), format);
			output.write(document);
			output.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		}
	}

	/**
	 * 将elment的属性 Attribute List或者子节点List转换成Map
	 * 
	 * @param list
	 * @return
	 */
	public static Map getMapFromDocList(List list) {
		Map m = new LinkedHashMap();
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			if (o instanceof Attribute) {
				Attribute a = (Attribute) list.get(i);
				m.put(a.getName(), revertStr(a.getText()));
			} else if (o instanceof Element) {
				Element a = (Element) list.get(i);
				m.put(a.getName(), revertStr(a.getText()));
			}
		}
		return m;
	}

	// 转换特殊字符
	public static String changeStr(String str) {
		if (str == null) {
			str = "";
		}
		return str.replaceAll("\n", "＼ｎ").replaceAll("\r", "＼ｒ").replaceAll(" ", "&nbsp;");
	}

	// 还原特殊字符
	public static String revertStr(String str) {
		if (str == null) {
			str = "";
		}
		return str.replaceAll("＼ｎ", "\n").replaceAll("＼ｒ", "\r").replaceAll("&nbsp;", " ");
	}
}
