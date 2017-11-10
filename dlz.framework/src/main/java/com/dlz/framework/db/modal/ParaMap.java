package com.dlz.framework.db.modal;

import java.util.Map;

import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.enums.ParaTypeEnum;
import com.dlz.framework.util.StringUtils;

public class ParaMap extends BaseParaMap{
	private static final long serialVersionUID = 8374167270612933157L;
	
	@SuppressWarnings("rawtypes")
	public ParaMap(String sql,Page page){
		super(sql, page);
	}
	public ParaMap(String sql){
		super(sql);
	}
	
	public ParaMap setLikePara(String paraName) {
		if(!StringUtils.isEmpty(super.getPara().get(paraName))){
			super.addPara(paraName, "%"+super.getPara().get(paraName)+"%");
		}
		return this;
	}
	public ParaMap addDefaultPara(String paraName,String defaultValue) {
		if(StringUtils.isEmpty(super.getPara().get(paraName))){
			super.addPara(paraName, defaultValue);
		}
		return this;
	}
	/**
	 * 添加参数
	 * @param key
	 * @param value
	 * @return
	 */
	public ParaMap addPara(String key,Object value){
		super.addPara(key, value);
		return this;
	}
	/**
	 * 添加指定类型的参数（根据类型自动转换）
	 * @param key
	 * @param value
	 * @param pte
	 * @return
	 */
	public ParaMap addPara(String key,String value,ParaTypeEnum pte){
		super.addPara(key, SqlUtil.coverString2Object(value, pte));
		return this;
	}
	
	public ParaMap addRequestPara(Map<String,?> requestParaMap){
		for(String key:requestParaMap.keySet()){
			if(key.startsWith("search_")){
				String[] paras=(String[])requestParaMap.get(key);
				if(paras.length==1){
					addPara(key.substring("search_".length()), paras[0]);
				}else{
					addPara(key.substring("search_".length()), paras);
				}
			}
		}
		return this;
	}
}
