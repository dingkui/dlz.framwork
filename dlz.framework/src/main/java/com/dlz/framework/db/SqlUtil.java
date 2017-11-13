package com.dlz.framework.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.enums.ParaTypeEnum;
import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.service.IColumnMapperService;
import com.dlz.framework.db.service.impl.ColumnMapperToLower;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.DateUtil;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.StringUtils;


/**
 * sql操作Util
 * 
 * @author ding_kui 2010-12-14
 * 
 */
public class SqlUtil{
	private static MyLogger logger = MyLogger.getLogger(SqlUtil.class);
	private final static String BLOB_CHARSETNAME = "dbset.blob_charsetname";
	public final static String SU_STR_TABLE_NM = "SU_STR_TABLE_NM";
	public final static String SU_STR_UPDATE_KEYS = "SU_STR_UPDATE_KEYS";
	
	private static Pattern sqlPattern0 = Pattern.compile("\\^#\\{(\\w[\\.\\w]*)\\}");
	private static Pattern sqlPattern = Pattern.compile("\\$\\{(\\w[\\.\\w]*)\\}");
	private static Pattern sqlPattern1 = Pattern.compile("#\\{(\\w+[\\.\\w]*)\\}");
	private static Pattern sqlPattern2 = Pattern.compile("\\[([^\\^][^\\[\\]]*)\\]");
	
	
	private static IColumnMapperService columnMapper=new ColumnMapperToLower();
	
	static void setMapper(IColumnMapperService mapper){
		columnMapper=mapper;
	}
	
	public static String getBlobCharsetname(){
		return DbInfo.getDbset(BLOB_CHARSETNAME);
	}
	
	private static JSONMap getParaMap(Object para){
		return JacksonUtil.coverObj(para,JSONMap.class);
	}
	/**
	 * 转换参数
	 * @param paraMap
	 * @author dk 2015-04-09
	 * @return
	 * @throws Exception
	 */
	public static BaseParaMap dealParmToJdbc(BaseParaMap paraMap,String sql){
		List<Object> paraList=new ArrayList<Object>();
		if(sql!=null){
			StringBuffer sb = new StringBuffer();
			int beginIndex=0;
			Matcher mat = sqlPattern1.matcher(sql);
			JSONMap para=getParaMap(paraMap);
			while(mat.find()){
		  		sb.append(sql.substring(beginIndex, mat.start()));
		  		sb.append("?");
		  		paraList.add(JacksonUtil.at(para, mat.group(1)));
		  		beginIndex=mat.end();
		  	}
			sb.append(sql.substring(beginIndex));
			paraMap.setSqlJdbc(sb.toString());
			paraMap.setSqlJdbcPara(paraList.toArray());
		}
		return paraMap;
	}
	
	/**
	 * 转换参数
	 * @param paraMap
	 * @author dk 2015-04-09
	 * @return
	 * @throws Exception
	 */
	public static BaseParaMap dealParm(BaseParaMap paraMap){
		String sql=paraMap.getSqlRun();
		if(sql==null){
			sql=createSqlRun(paraMap.getPara(), paraMap.getSqlInput());
			sql=sqlPattern1.matcher(sql).replaceAll("#{para.$1}");
			paraMap.setSqlRun(sql);
			
//			if(sql.matches("[\\s]*(?i)select.*") && !sql.matches("[\\s]*(?i)select count(\\*).*") ){
//				paraMap.creatPage();
//			}
		}
		return paraMap;
	}
	
	/**
	 * 创建执行sql(带替换符)
	 * @param paraMap
	 * @author dk 2015-04-09
	 * @return
	 * @throws Exception
	 */
	public static String createSqlRun(Map<String,Object> para,String sql){
		sql=DbInfo.getSql(sql);
		sql=getConditionStr(sql, para);
		sql=replaceSql(sql, para);
		sql=sql.replaceAll("[\\s]+", " ");
		return sql;
	}
	
	/**
	 * 转换成翻页sql
	 * @return
	 * @throws Exception
	 */
	public static String createPageSql(BaseParaMap paraMap){
		String sql=paraMap.getSql_page();
		if(sql==null){
			if(paraMap.getPage()!=null){
				Map<String,Object> p=new HashMap<String,Object>();
				p.put("_sql", paraMap.getSqlRun());
				p.put("page", getParaMap(paraMap.getPage()));
				sql=createSqlRun(p, "key.comm.pageSql");
				paraMap.setSql_page(sql);
			}else{
				paraMap.setSql_page(paraMap.getSqlRun());
			}
		}
		return sql;
	}
	/**
	 * 转换成查询条数sql
	 * @return
	 * @throws Exception
	 */
	public static String createCntSql(BaseParaMap paraMap){
		String sql=paraMap.getSql_cnt();
		if(sql==null){
			Map<String,Object> p=new HashMap<String,Object>();
			p.put("_sql", paraMap.getSqlRun());
			sql=createSqlRun(p, "key.comm.cntSql");
			paraMap.setSql_cnt(sql);
		}
		return sql;
	}
	
 
	
	public static String replaceSql(String sql,Map<String,Object> m){
		if(sql.length()>10000){
			throw new DbException("sql过长或出现引用死循环！");
		}
		Matcher mat = sqlPattern.matcher(sql);
	  	while(mat.find()){
	  		String key=mat.group(1);
	  		Object o = JacksonUtil.at(m,key);
	  		if(o==null && key.startsWith("key.")){
	  			o=getConditionStr(DbInfo.getSql(key),m);
	  		}
	  		String matStr = "";
	  		if(o instanceof Object[]){
	  			matStr = "'"+StringUtils.join((Object[])o, "','")+"'";
	  		}else if(o instanceof Collection){
	  			matStr = "'"+StringUtils.join((Collection<?>)o, "','")+"'";
	  		}else{
	  			matStr=String.valueOf(o==null?"":o);
	  		}
	  		matStr=StringUtils.NVL(matStr);
	  		if(sqlPattern.matcher(matStr).find()){
	  			matStr=replaceSql(matStr,m);
	  		}
	  		sql = sql.replaceAll("\\$\\{"+key+"\\}", matStr);
	  	}
		if(sqlPattern.matcher(sql).find()){
			return replaceSql(sql,m);
		}else{
			return sql;
		}
	}
	
	public static String getConditionStr(String sql,Map<String,Object> m){
		Matcher mat = sqlPattern2.matcher(sql);
		StringBuffer sb = new StringBuffer();
		while(mat.find()){
			Matcher mat2 = sqlPattern1.matcher(mat.group(1));
			boolean append = false;
			while(mat2.find()){
				if(isNotEmpty(m,mat2.group(1))){
					append = true;
					break;
				}
			}
			if(!append){
				Matcher mat3 = sqlPattern.matcher(mat.group(1));
				while(mat3.find()){
					if(isNotEmpty(m,mat3.group(1))){
						append = true;
						break;
					}
				}
			}
			if(append){
				mat.appendReplacement(sb, "$1");
			}else{
				mat.appendReplacement(sb, "");
			}
		}
		mat.appendTail(sb);
		sql = sb.toString();
		if(sqlPattern2.matcher(sql).find()){
			return getConditionStr(sql,m);
		}else{
			return sqlPattern0.matcher(sql).replaceAll("");
		}
	}
	
	private static boolean isNotEmpty(Map<String,Object> m,String key){
		Object o=JacksonUtil.at(m,key);
		return o!=null&&!"".equals(o);
	}
	
	/**
	 * 将数据库查询对象转换成String
	 * @param m
	 * @author dk 2015-04-09
	 * @return
	 */
//	public static Object coverObject2String(Object o){
//		if(o==null){
//			return null;
//		}
////		if(o instanceof BLOB){
////			try {
////				BLOB blob = (BLOB)o;
////				return  new String(blob.getBytes((long)1, (int)blob.length()),getBlobCharsetname());
////			} catch (SQLException e) {
////				logger.error(e.getMessage(),e);
////				return null;
////			} catch (UnsupportedEncodingException e) {
////				logger.error(e.getMessage(),e);
////				return null;
////			}
////		} 
//		return o;
//	}
	/**
	 * 将参数转换成对应的Object
	 * @param m
	 * @author dk 2015-04-09
	 * @return
	 */
	public static Object coverString2Object(String value, ParaTypeEnum pte) {
		try {
			switch (pte) {
			case Blob:
				return value.getBytes(SqlUtil.getBlobCharsetname());
			case Date:
				value = value.replaceAll("/", "-");
				if (value.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.*")) {
					return DateUtil.parseDateTime(value.substring(0, 18));
				} else if (value.matches("^\\d{4}年\\d{2}月\\d{2}日 \\d{2}时\\d{2}分\\d{2}秒")) {
					return DateUtil.parseDateTimeC(value);
				} else if (value.matches("^\\d{4}-\\d{2}-\\d{2}")) {
					return DateUtil.parseDate(value);
				}
			default:
				return value;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return value;
	}
	
	
	/**
	 * 创建表插入语句
	 * @param p
	 * @param paraList
	 * @return
	 * @throws SQLException
	 */
	public static String createInsertSql(Map<String,Object> p) throws SQLException {
		String tableNm = (String)p.get(SU_STR_TABLE_NM);
		if(tableNm==null){
			throw new SQLException("创建sql出错，参数中缺少表名："+SU_STR_TABLE_NM);
		}
		tableNm.toUpperCase();
		p.remove(SU_STR_TABLE_NM);
		
		StringBuffer sb1 = new StringBuffer("insert into " + tableNm + "(");
		StringBuffer sb2 = new StringBuffer(" values (");
		Iterator<Entry<String,Object>> it = p.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String,Object> entry = it.next();
			String columeNm = entry.getKey();
			String valueStr = "#{"+ columeNm+"}";
			sb1.append((String) entry.getKey() + ",");
			sb2.append(valueStr + ",");
		}
		return sb1.substring(0, sb1.length() - 1) + ")" + sb2.substring(0, sb2.length() - 1) + ")";
	}
	
	/**
	 * 创建表更新语句
	 * @param p
	 * @param endSql
	 * @param paraList
	 * @return
	 * @throws SQLException
	 */
	public static String createUpdateSql(Map<String,Object> p,String endSql) throws SQLException {
		String tableNm = (String)p.get(SU_STR_TABLE_NM);
		if(tableNm==null){
			throw new SQLException("创建sql出错，参数中缺少表名："+SU_STR_TABLE_NM);
		}
		p.remove(SU_STR_TABLE_NM);
		
		String keys = (String)p.get(SU_STR_UPDATE_KEYS);
		if(keys==null){
			throw new SQLException("创建sql出错，参数中缺少检索条件："+SU_STR_UPDATE_KEYS);
		}
		p.remove(SU_STR_UPDATE_KEYS);
		// where语句
		StringBuffer sb2 = new StringBuffer(" where 1=1");
		for (String key : keys.split(",")) {
			if (p.containsKey(key)) {
				String valueStr= "#{"+ key+"}";
				sb2.append(" and " + key + "=" + valueStr);
				p.remove(key);
			} else{
				throw new SQLException("参数中键值未设定："+key);
			}
		}
		if(endSql != null){
			sb2.append(endSql);
		}
		if (sb2.toString().equals(" where 1=1 ")) {
			throw new SQLException("更新条件为空："+p);
		}
		// update语句
		StringBuffer sb1 = new StringBuffer("update " + tableNm + " set ");
		Iterator<Entry<String, Object>> it = p.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry =  it.next();
			String columeNm = String.valueOf(entry.getKey());
			String valueStr= "#{"+ columeNm+"}";
			sb1.append((String) entry.getKey() + "=");
			sb1.append(valueStr + ",");
		}
		// sql语句拼接
		return sb1.substring(0, sb1.length() - 1) + sb2.toString();
	}
	
	
	/**
	 * 创建单表查询语句
	 * @param p
	 * @param endSql
	 * @return list 
	 * @throws SQLException 
	 */
	public static String createSelectSql(Map<String,Object> p,String endSql) throws SQLException{
		String tableNm = (String)p.get(SU_STR_TABLE_NM);
		if(tableNm==null){
			throw new SQLException("创建sql出错，参数中缺少表名："+SU_STR_TABLE_NM);
		}
		tableNm = tableNm.toUpperCase();
		p.remove(SU_STR_TABLE_NM);
		
		StringBuilder sql = new StringBuilder("SELECT *");
		sql.append(",'" + tableNm + "' "+SU_STR_TABLE_NM);
		sql.append(" FROM " + tableNm);
		sql.append(" WHERE 1=1 ");
		
		Iterator<Entry<String, Object>> it = p.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			String columeNm = String.valueOf(entry.getKey());
			String value = String.valueOf(entry.getValue());
			String valueStr="#{"+ columeNm+"}";
			if(value == null){
				sql.append(" and " + entry.getKey() + " is null");
			}else{
				sql.append(" and " + entry.getKey() + "=" + valueStr);
			}
		}
		if (endSql != null) {
			sql.append(endSql);
		}
		return sql.toString();
	}

	public static String converStr2ClumnStr(String beanKey) {
		return columnMapper.converStr2ClumnStr(beanKey);
	}
	public static String converClumnStr2Str(String beanKey) {
		return columnMapper.converClumnStr2Str(beanKey);
	}
}
