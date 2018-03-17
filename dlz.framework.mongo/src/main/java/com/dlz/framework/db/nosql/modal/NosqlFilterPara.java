package com.dlz.framework.db.nosql.modal;

import java.util.Map;

import com.dlz.framework.bean.JSONList;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.enums.ParaTypeEnum;
import com.dlz.framework.db.modal.IPara;
import com.dlz.framework.util.StringUtils;
import com.dlz.framework.util.ValUtil;

public class NosqlFilterPara extends NosqlBasePara implements IPara{
	private static final long serialVersionUID = 8374167270612933157L;
	private String filterBson;
	private JSONMap para = new JSONMap();
	public NosqlFilterPara(String key) {
		super(key);
	}
	public String getFilterBson() {
		return filterBson;
	}
	public void setFilterBson(String filterBson) {
		this.filterBson = filterBson;
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
		if(key==null){
			return this;
		}
		if(value==null){
			value="";
		}
		if (value instanceof String) {
			if(key.startsWith("2i_")){
				String[] vals=((String)value).split(",");
				JSONList list=new JSONList();
				for(String val:vals){
					if(StringUtils.isNumber(val)){
						list.add(ValUtil.getLong(val));
					}else{
						list.add(val);
					}
				}
				para.put(key.substring(3), list);
			}else{
				if(StringUtils.isNumber((String)value)){
					para.put(key,ValUtil.getLong(value));
				}else{
					para.put(key,value);
				}
			}
		}else{
			para.put(key, value);
		}
		
		return this;
	}
	
	public JSONMap getPara() {
		return para;
	}
	/**
	 * 添加指定类型的参数（根据类型自动转换）
	 * @param key
	 * @param value
	 * @param pte
	 * @return
	 */
	public NosqlFilterPara addPara(String key,String value,ParaTypeEnum pte){
		para.put(key, SqlUtil.coverString2Object(value, pte));
		return this;
	}
}
