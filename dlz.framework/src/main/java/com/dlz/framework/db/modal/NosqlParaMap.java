package com.dlz.framework.db.modal;

import java.io.Serializable;
import java.util.Map;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.conver.Convert;
import com.dlz.framework.db.conver.impl.DateConverter;
import com.dlz.framework.db.enums.DateFormatEnum;
import com.dlz.framework.db.enums.ParaTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("rawtypes")
public class NosqlParaMap implements Serializable{
	private static final long serialVersionUID = 8374167270612933157L;
	@JsonIgnore
	private Convert convert;
	private String key;
	private Page page;
	private JSONMap para = new JSONMap();

	protected NosqlParaMap(String key,Page page){
		this.key=key;
		this.setPage(page);
		this.addDefualtConverter();
	}
	protected NosqlParaMap(String key){
		this.key=key;
		this.addDefualtConverter();
	}
	public NosqlParaMap addParas(Map<String, Object> map) {
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
	public NosqlParaMap addPara(String key,Object value){
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
	public NosqlParaMap addPara(String key,String value,ParaTypeEnum pte){
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
