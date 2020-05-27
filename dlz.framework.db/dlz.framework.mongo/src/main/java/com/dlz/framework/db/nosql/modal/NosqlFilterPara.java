package com.dlz.framework.db.nosql.modal;

import com.dlz.comm.json.JSONList;
import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.StringUtils;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.enums.ParaTypeEnum;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class NosqlFilterPara extends NosqlBasePara {

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
	public NosqlFilterPara addParas(Map<String, Object> map) {
		if(map!=null){
			Set<Entry<String, Object>> entrySet = map.entrySet();
			for(Entry<String, Object> e:entrySet){
				addPara(e.getKey(),e.getValue());
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
	public NosqlFilterPara addPara(String key,Object value){
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
			if(key.startsWith("2i_")){
				String[] vals=(String.valueOf(value)).split(",");
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
				para.put(key, value);
			}
			
			
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
