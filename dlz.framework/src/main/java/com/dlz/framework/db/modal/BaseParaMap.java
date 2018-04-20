package com.dlz.framework.db.modal;

import java.io.Serializable;
import java.util.Map;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.conver.Convert;
import com.dlz.framework.db.conver.impl.DateConverter;
import com.dlz.framework.db.enums.DateFormatEnum;
import com.dlz.framework.db.enums.ParaTypeEnum;
import com.dlz.framework.util.EncryptUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("rawtypes")
public class BaseParaMap implements Serializable,IPara{
	private static final long serialVersionUID = 8374167270612933157L;
	@JsonIgnore
	private Convert convert;
	private String sqlInput;
	private String sqlRun;
	private int cacheTime=0;//缓存时间
	public int getCacheTime() {
		return cacheTime;
	}
	public void setCacheTime(int cacheTime) {
		this.cacheTime = cacheTime;
	}
	private String sql_page;
	private String sql_cnt;
	private String sqlJdbc;
	private Object[] sqlJdbcPara;

	private Page page;
	
	private JSONMap para = new JSONMap();

	protected BaseParaMap(String sqlInput,Page page){
		this.sqlInput=sqlInput;
		this.setPage(page);
		this.addDefualtConverter();
	}
	public String key(){
		StringBuffer sb=new StringBuffer(sqlInput);
		if(page!=null){
			sb.append(page.getBegin()).append(page.getEnd()).append(page.getOrderBy());
		}
		sb.append(para);
		return EncryptUtil.md5(sb.toString());
	}
	protected BaseParaMap(String sqlInput){
		this.sqlInput=sqlInput;
		this.addDefualtConverter();
	}
	
	public IPara addParas(Map<String, Object> map) {
		for (String key : map.keySet()) {
			Object v=map.get(key);
			if(v instanceof String[]){
				String[] paras=(String[])map.get(key);
				if(paras.length==1){
					para.put(key, paras[0]);
				}else{
					para.put(key, paras);
				}
			}else{
				para.put(key, v);
			}
		}
		return this;
	}
	/**
	 * 添加参数
	 * @param key
	 * @param value
	 * @return
	 */
	public IPara addPara(String key,Object value){
		para.put(key, value==null?"":value);
		return this;
	}

	private void addDefualtConverter(){
		if(convert==null){
			convert = new Convert();
		}
		convert.addClassConvert(new DateConverter(DateFormatEnum.DateTimeStr));
	}

	/**
	 * 添加指定类型的参数（根据类型自动转换）
	 * @param key
	 * @param value
	 * @param pte
	 * @return
	 */
	public BaseParaMap addPara(String key,String value,ParaTypeEnum pte){
		para.put(key, SqlUtil.coverString2Object(value, pte));
		return this;
	}
	
	/**  
	 * 获取convert
	 * @return convert convert  
	 */
	public Convert getConvert() {
		return convert;
	}
	/**  
	 * 获取sql
	 * @return sql sql  
	 */
	public String getSqlInput() {
		return sqlInput;
	}
	/** 
	 * 设置sql 
	 * @param sql sql 
	 */
	public void setSqlInput(String sqlInput) {
		this.sqlInput = sqlInput;
		this.sqlRun=null;
		this.sql_page=null;
		this.sql_cnt=null;
	}
	/**  
	 * 获取sqlRun
	 * @return sqlRun sqlRun  
	 */
	public String getSqlRun() {
		return sqlRun;
	}
	/** 
	 * 设置sqlRun 
	 * @param sqlRun sqlRun 
	 */
	public void setSqlRun(String sqlRun) {
		this.sqlRun = sqlRun;
	}
	public String getSql_page() {
		return sql_page;
	}
	public void setSql_page(String sql_page) {
		this.sql_page = sql_page;
	}
	public String getSql_cnt() {
		return sql_cnt;
	}
	public void setSql_cnt(String sql_cnt) {
		this.sql_cnt = sql_cnt;
	}
	/**  
	 * 创建Page
	 * @return page page  
	 */
	public Page createPage() {
		if(page==null){
			page=new Page();
		}
		return page;
	}
	/**  
	 * 获取page
	 * @return page page  
	 */
	public Page getPage() {
		return page;
	}
	/** 
	 * 设置page 
	 * @param page page 
	 */
	public void setPage(Page page) {
		this.page = page;
	}
	/**  
	 * 获取para
	 * @return para para  
	 */
	public JSONMap getPara() {
		return para;
	}
	public String getSqlJdbc() {
		return sqlJdbc;
	}
	public void setSqlJdbc(String sqlJdbc) {
		this.sqlJdbc = sqlJdbc;
	}
	public Object[] getSqlJdbcPara() {
		return sqlJdbcPara;
	}
	public void setSqlJdbcPara(Object[] sqlJdbcPara) {
		this.sqlJdbcPara = sqlJdbcPara;
	}
}
