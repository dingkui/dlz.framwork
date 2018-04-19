package com.dlz.framework.db.nosql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.db.nosql.modal.Delete;
import com.dlz.framework.db.nosql.modal.Find;
import com.dlz.framework.db.nosql.modal.Insert;
import com.dlz.framework.db.nosql.modal.Update;
import com.dlz.framework.util.JacksonUtil;


/**
 * sql操作Util
 * 
 * @author ding_kui 2010-12-14
 * 
 */
public class BsonUtil{
	private static Pattern strPattern0 = Pattern.compile("\\^\\$\\{(\\w[\\.\\w]*)\\}");
	private static Pattern strPattern1 = Pattern.compile("\\$\\{(\\w+[\\.\\w]*)\\}");
	private static Pattern strPattern2 = Pattern.compile("【([^【】]*)】");
	
	public static Find dealParm(Find upt){
		BsonInfo bsonInfo = NosqlDbInfo.getBsonInfo(upt.getKey());
		upt.setName(bsonInfo.getName());
		upt.setClumns(bsonInfo.getClumns());
		upt.setFilterBson(createBson(upt.getPara(),NosqlDbInfo.getBsonInfo(bsonInfo.getFilter()).getBson()));
		return upt;
	}
	public static Insert dealParm(Insert insert){
//		System.out.println(222);
		BsonInfo bsonInfo = NosqlDbInfo.getBsonInfo(insert.getKey());
//		System.out.println(3333);
		insert.setName(bsonInfo.getName());
//		System.out.println(bsonInfo.getName());
		return insert;
	}
	public static Update dealParm(Update upt){
		BsonInfo bsonInfo = NosqlDbInfo.getBsonInfo(upt.getKey());
		upt.setName(bsonInfo.getName());
		upt.setFilterBson(createBson(upt.getPara(),NosqlDbInfo.getBsonInfo(bsonInfo.getFilter()).getBson()));
		return upt;
	}
	public static Delete dealParm(Delete upt){
		BsonInfo bsonInfo = NosqlDbInfo.getBsonInfo(upt.getKey());
		upt.setName(bsonInfo.getName());
		upt.setFilterBson(createBson(upt.getPara(),NosqlDbInfo.getBsonInfo(bsonInfo.getFilter()).getBson()));
		return upt;
	}
	
	/**
	 * 创建执行sql(带替换符)
	 * @param paraMap
	 * @author dk 2015-04-09
	 * @return
	 * @throws Exception
	 */
	private static String createBson(JSONMap para,String fileterBson){
		fileterBson=getConditionStr(fileterBson, para);
		fileterBson=replaceSql(fileterBson, para);
		return fileterBson.replaceAll(",\\s*\\}", "}").replaceAll(",\\s*\\]", "]").replaceAll("\\{\\s*", "{").replaceAll("\\[\\s*", "[").replaceAll("\"", "");
	}
	
	private static String replaceSql(String sql,JSONMap m){
		if(sql.length()>10000){
			throw new DbException("解析过长或出现引用死循环！");
		}
		Matcher mat = strPattern1.matcher(sql);
		while(mat.find()){
	  		String key=mat.group(1);
	  		Object o = JacksonUtil.at(m,key);
	  		String matStr = "";
	  		if(o==null){
	  			if(key.startsWith("filter.")){
	  				matStr=createBson(m,NosqlDbInfo.getBsonInfo(key).getBson());
	  			}
	  		}else{
	  			matStr = JacksonUtil.getJson(o);
	  		}
	  		if(strPattern1.matcher(matStr).find()){
	  			matStr=replaceSql(matStr,m);
	  		}
	  		sql= sql.replaceAll("\\$\\{"+key+"\\}", matStr);
	  	}
		return sql;
	}
	
	private static String getConditionStr(String sql,JSONMap m){
		Matcher mat = strPattern2.matcher(sql);
		StringBuffer sb = new StringBuffer();
		while(mat.find()){
			Matcher mat2 = strPattern1.matcher(mat.group(1));
			boolean append = false;
			while(mat2.find()){
				if(isNotEmpty(m,mat2.group(1))){
					append = true;
					break;
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
		if(strPattern2.matcher(sql).find()){
			return getConditionStr(sql,m);
		}else{
			return strPattern0.matcher(sql).replaceAll("");
		}
	}
	
	private static boolean isNotEmpty(JSONMap m,String key){
		Object o=JacksonUtil.at(m,key);
		return o!=null&&!"".equals(o);
	}
}
