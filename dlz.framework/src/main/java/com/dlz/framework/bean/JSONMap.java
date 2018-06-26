package com.dlz.framework.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dlz.framework.exception.CodeException;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.StringUtils;
import com.dlz.framework.util.ValUtil;

/**
 * JSONMap
 * @author dk 2017-06-15
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JSONMap extends HashMap<String,Object> implements IUniversalVals{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7554800764909179290L;
	
	public JSONMap(){
		super();
	}
	public JSONMap(Object obj){
		super();
		if(obj==null){
			return;
		}
		if(obj instanceof Map){
			putAll((Map)obj);
		}else {
			String string=null;
			if(obj instanceof CharSequence){
				string=obj.toString().trim();
			}else{
				string=JacksonUtil.getJson(obj);
			}
			if(string==null){
				return;
			}
			if(string.startsWith("{") && string.endsWith("}")){
				putAll(JacksonUtil.readValue(string, JSONMap.class));
			}else{
				throw new CodeException("参数不能转换成JSONMap:"+string);
			}
		}
	}
	public static JSONMap createJsonMap(Object json){
		return new JSONMap(json);
	}
	public JSONMap clearEmptyProp(){
		List<String> emputyKeys=new ArrayList<String>();
		for(Entry<String,Object> entry:this.entrySet()){
			if(StringUtils.isEmpty(entry.getValue())){
				emputyKeys.add(entry.getKey());
			}
		}
		for(String key:emputyKeys){
			this.remove(key);
		}
		return this;
	}
	/**
	 * 
	 * @param key
	 * @param obj
	 * @param joinMethod 
	 * 		0:替换原有信息
	 * 		1：加入到原有数组中
	 * 		2：合并到原有数组中
	 * 		3：把原有数据跟新数据构造新数组 
	 * @return
	 */
	public JSONMap add(String key,Object obj,int joinMethod){
		Object o=this.get(key);
		if(o==null){
			put(key, obj);
			return this;
		}
		switch(joinMethod){
			case 0:
				put(key, obj);
				break;
			case 1:
				if(o instanceof Collection||o instanceof Object[]){
					List list = ValUtil.getList(o);
					list.add(obj);
					put(key, list);
				}
				break;
			case 2:
				List list;
				if(o instanceof Collection||o instanceof Object[]){
					list = ValUtil.getList(o);
				}else{
					list = new ArrayList();
					list.add(o);
				}
				if(obj instanceof Collection||obj instanceof Object[]){
					list.addAll(ValUtil.getList(obj));
				}else{
					list.add(obj);
				}
				put(key, list);
				break;
		}
		return this;
	}
	public JSONMap add(String key,Object obj){
		return add(key, obj, 2);
	}
	public JSONMap getObj(String key){
		return getObj(key,JSONMap.class);
	}
	public String toString(){
		return JacksonUtil.getJson(this);
	}
	@Override
	public Object getInfoObject() {
		return this;
	}
}