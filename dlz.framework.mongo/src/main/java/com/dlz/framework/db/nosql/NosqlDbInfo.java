package com.dlz.framework.db.nosql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.db.nosql.operator.mongo.MongoManager;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.StringUtils;

/**
 * 数据库配置信息
 * 
 * @author dingkui 2011-08-12 v1.0 
 * history dingkui 2012-05-07 v1.1
 * history dingkui 2018-03-07 v1.2 添加bson配置文件支持
 * 修改sql文件路径取得方式，以便执行init时可以刷新内存
 * 
 */
@Component
@Lazy(false)
@SuppressWarnings("unchecked")
public class NosqlDbInfo {
	private static MyLogger logger = MyLogger.getLogger(NosqlDbInfo.class);
	private static ResourceBundle dbConfig;
	private final static String NAME_DB_CONFIG = "mongolist";
	private final static String STR_BSON_FOLDER = "bsonlist.";
	private static JSONMap m_dbset = new JSONMap();
	private static Map<String,BsonInfo> m_sqlList = new HashMap<String,BsonInfo>();
	
	private static boolean initIng = false;
	public NosqlDbInfo(){
		System.out.println("NosqlDbInfo init..");
		try {
			init();
			MongoManager.init(m_dbset);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);;
		}
	}
	private static void init() throws IOException {
		if (initIng || dbConfig != null) {
			return;
		}
		initIng = true;
		dbConfig = ResourceBundle.getBundle(NAME_DB_CONFIG);
		for (Enumeration<String> enums = dbConfig.getKeys(); enums.hasMoreElements();) {
			String name = enums.nextElement();
			String str = dbConfig.getString(name).trim();
			if("1".equals(str)&&name.startsWith(STR_BSON_FOLDER)){
				String path="/bson"+name.substring(STR_BSON_FOLDER.length()-1).replaceAll("\\.", "/");
				readPath(new File(NosqlDbInfo.class.getClassLoader().getResource("").getPath()+path));
			}
			m_dbset.put(name, str);
		}
		logger.debug("BsonSet:" + m_dbset);
		logger.debug("BsonList:" + m_sqlList);
		initIng = false;
	}
	
	private static void readPath(File file){
		try {
			if(file.isDirectory()){
				File[] files=file.listFiles();
				for(File fi:files){
					readPath(fi);
				}
			}else{
				if(file.getAbsolutePath().endsWith(".bson")){
					readBsonXml(new FileInputStream(file));
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(file.getAbsolutePath()+" 文件找不到！", e);
		} catch (Exception e) {
			logger.error(file.getAbsolutePath()+" 加载异常！", e);
		}
	}	
	
	
	
	private static void readBsonXml(InputStream is){
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			for (Element item : (List<Element>) doc.getRootElement().elements()) {
				BsonInfo iteminfo=new BsonInfo();
				String key = item.attributeValue("key");
				String name = item.attributeValue("name");
				String filter = item.attributeValue("filter");
				String clumns = item.attributeValue("clumns");
				String opt = item.getName();
				if("filter".equals(opt)||"update".equals(opt)){
					String bson = item.getData().toString();
					bson = bson.replaceAll("--.*", "");
					bson = bson.replaceAll("//.*", "");
					bson = bson.replaceAll("[\\s]+", " ");
					iteminfo.setBson(bson);
				}
				if(StringUtils.isNotEmpty(name)){
					iteminfo.setName(name);
				}
				if(StringUtils.isNotEmpty(clumns)){
					iteminfo.setClumns(clumns);
				}
				if(StringUtils.isNotEmpty(filter)){
					iteminfo.setFilter(filter);
				}
				m_sqlList.put(key, iteminfo);
				logger.info(key + ":" + iteminfo);
			}
		} catch (DocumentException e) {
			logger.error(" 文件读取异常！", e);
		} finally{
			if(is !=null){
				try {
					is.close();
				} catch (IOException e) {
					logger.error(" 文件关闭异常！", e);
				}
			}
		}
	}
	public static BsonInfo getBsonInfo(String key){
		if (key == null) {
			throw new DbException("输入的key为空！");
		}
		BsonInfo bson = m_sqlList.get(key);
		if (bson == null) {
			throw new DbException("输入的Key找不到相应的bson！key=" + key);
		}
		return bson;
	}
	
	public static String getDbset(String key) {
		if (key == null) {
			return null;
		}
		String value = key;
		value = m_dbset.getStr(key.trim());
		if (value != null) {
			return value.trim();
		} else {
			return null;
		}
	}

	public static void reload() {
		dbConfig = null;
		m_sqlList.clear();
		try {
			init();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);;
		}
	}
}
