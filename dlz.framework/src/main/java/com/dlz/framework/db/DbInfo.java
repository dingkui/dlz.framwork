package com.dlz.framework.db;

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
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.db.service.IColumnMapperService;
import org.slf4j.Logger;

/**
 * 数据库配置信息
 * 
 * @author dingkui 2011-08-12 v1.0 
 * history dingkui 2012-05-07 v1.1
 * 修改sql文件路径取得方式，以便执行init时可以刷新内存
 * 
 */
@SuppressWarnings("unchecked")
@Component
public class DbInfo {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(DbInfo.class);
	private static ResourceBundle dbConfig;
	private final static String NAME_DB_CONFIG = "sqllist";
	private final static String STR_SQL_JAR = "sqllist.sql.jar.";
	private final static String STR_SQL_FILE = "sqllist.sql.file.";
	private final static String STR_SQL_FOLDER = "sqllist.sql.folder.";
	private final static String STR_DBTYPE = "dbtype";
	private final static String STR_COLUMNMAPPER = "columnmapper";
	private static Map<String, String> m_dbset = new HashMap<String, String>();
	private static Map<String, String> m_sqlList = new HashMap<String, String>();
	private static SqlDialect dialect = SqlDialect.ORACLE;
	private static String dbtype = null;
	private enum SqlDialect {
		MYSQL(".mysql"), ORACLE(""), MSSQL(".sqlserver");
		private String end;
		private SqlDialect(String end) {
			this.end = end;
		}
		public String getEnd() {
			return end;
		}
	}
	
	public static String getDbtype(){
		return dbtype;
	}
	
	private static boolean initIng = false;
	public DbInfo(){
		try {
			init();
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
		loadRsources("framework/*");
		loadRsources("common/*");
		loadRsources("service/*/*");
		for (Enumeration<String> enums = dbConfig.getKeys(); enums.hasMoreElements();) {
			String name = enums.nextElement();
			String str = dbConfig.getString(name).trim();
			if(STR_DBTYPE.equals(name)){
				dbtype=str.toUpperCase();
				dialect=SqlDialect.valueOf(dbtype);
				continue;
			}
			if(STR_COLUMNMAPPER.equals(name)){
				try {
					SqlUtil.setMapper((IColumnMapperService)Class.forName(str).newInstance());
				} catch (Exception e) {
					throw new DbException("字段转换类型设置无效："+name+"="+str+"\n"+e.getMessage(), e);
				}
				continue;
			}
			if("1".equals(str)){
				if (name.startsWith(STR_SQL_FILE)) {
					String path="/sql/"+name.substring(STR_SQL_FILE.length()-1).replaceAll("\\.", "/")+".sql";
					readSqlPath(new File(DbInfo.class.getClassLoader().getResource("").getPath()+path));
				}else if (name.startsWith(STR_SQL_JAR)) {
					loadRsources(name.substring(STR_SQL_JAR.length()).replaceAll("\\.", "/"));
				}else if (name.startsWith(STR_SQL_FOLDER)) {
					String path="/sql/"+name.substring(STR_SQL_FOLDER.length()-1).replaceAll("\\.", "/");
					readSqlPath(new File(DbInfo.class.getClassLoader().getResource("").getPath()+path)); 
				}
			}
			m_dbset.put(name, str);
		}
		logger.info("dbsettinhs:" + m_dbset);
		logger.info("sqlList:" + m_sqlList);
		initIng = false;
	}
	
	private static void readSqlPath(File file){
		try {
			if(file.isDirectory()){
				File[] files=file.listFiles();
				for(File fi:files){
					readSqlPath(fi);
				}
			}else{
				if(file.getAbsolutePath().endsWith(".sql")){
					readSqlXml(new FileInputStream(file));
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(file.getAbsolutePath()+" 文件找不到！", e);
		} catch (Exception e) {
			logger.error(file.getAbsolutePath()+" 加载异常！", e);
		}
	}	
	
//	private static void readSqlXml(String filePath){
//		try {
//			readSqlXml(ClassLoader.getSystemResourceAsStream(filePath));
////			readSqlXml(DbInfo.class.getResourceAsStream(filePath));
//		} catch (Exception e) {
//			logger.error(filePath+" 加载异常！", e);
//		}
//	}
	
//	private static void readSqlXml(InputStream is){
//		try {
//			Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
//			Element documentElement = doc.getDocumentElement();
//			NodeList childNodes = documentElement.getElementsByTagName("sql");
//			for (int i=0;i<childNodes.getLength();i++) {
//				Node sql = childNodes.item(i);
//				String sqlStr = sql.getTextContent();
//				String sqlid= sql.getAttributes().getNamedItem("sqlId").getTextContent();
//				sqlStr = sqlStr.replaceAll("--.*", "");
//				sqlStr = sqlStr.replaceAll("[\\s]+", " ");
//				m_sqlList.put(sqlid,sqlStr);
//				logger.info(sqlid + ":" + sqlStr);
//			}
//		} catch (SAXException e) {
//			logger.error(" 文件读取异常！", e);
//		} catch (IOException e) {
//			logger.error(" 文件读取异常！", e);
//		} catch (ParserConfigurationException e) {
//			logger.error(" 文件读取异常！", e);
//		} finally{
//			if(is !=null){
//				try {
//					is.close();
//				} catch (IOException e) {
//					logger.error(" 文件关闭异常！", e);
//				}
//			}
//		}
//	}
	private static void readSqlXml(InputStream is){
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			for (Element sql : (List<Element>) doc.getRootElement().elements()) {
				String sqlStr = sql.getData().toString();
				sqlStr = sqlStr.replaceAll("--.*", "");
				sqlStr = sqlStr.replaceAll("[\\s]+", " ");
				m_sqlList.put(sql.attributeValue("sqlId"), sqlStr);
				logger.debug(sql.attributeValue("sqlId") + ":" + sqlStr);
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
	
	public static void main(String[] args) throws IOException {
//		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//		Resource[] rs=resourcePatternResolver.getResources("classpath:sql/common/*.sql");
//		for(Resource r:rs){
//			System.out.println(r.getFile().getAbsolutePath());
//			readSqlXml(r.getInputStream());
//		}
		loadRsources("common/*");
	}
	
	public static void loadRsources(String path) throws IOException {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		Resource[] rs=resourcePatternResolver.getResources("classpath*:sql/"+path+".sql");
		for(Resource r:rs){
			logger.info(r.getURI().toString());
			readSqlXml(r.getInputStream());
		}
	}

	public static String getSql(String key){
		if (key == null) {
			throw new DbException("输入的sql为空！");
		}
		String sql = key;
		if (key.startsWith("key.")) {
			sql = m_sqlList.get(key+dialect.getEnd());
			if(sql==null){
				sql = m_sqlList.get(key);
			}
		}
		if (sql == null) {
			throw new DbException("输入的sqlKey找不到相应的sql语句！key=" + key);
		}
		return sql;
	}
	
	public static void appendInfoToSql(String key,String appendInfo){
		if (key == null) {
			throw new DbException("输入的sql为空！");
		}
		String sql = key;
		if (key.startsWith("key.")) {
			sql = m_sqlList.get(key+dialect.getEnd());
			if(sql==null){
				sql = m_sqlList.get(key);
			}
		}
		if (sql == null) {
			throw new DbException("输入的sqlKey找不到相应的sql语句！key=" + key);
		}
		if(sql.indexOf(appendInfo)==-1){
			m_sqlList.put(key+dialect.getEnd(), sql+" "+appendInfo);
		}
	}
	
	public static String getDbset(String key) {
		if (key == null) {
			return null;
		}
		String value = key;
		value = m_dbset.get(key.trim());
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
