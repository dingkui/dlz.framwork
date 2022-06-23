package com.dlz.comm.json;

import com.dlz.comm.exception.SystemException;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.StringUtils;
import com.dlz.comm.util.ValUtil;

import java.util.*;

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
			if(JacksonUtil.isJsonObj(string)){
				putAll(JacksonUtil.readValue(string, JSONMap.class));
			}else{
				throw new RuntimeException("参数不能转换成JSONMap:"+string);
			}
		}
	}
	public JSONMap(String key,Object... value){
		super();
		final int length = value.length;
		if(length%2==0){
			throw new RuntimeException("参数个数只能是偶数");
		}
		put(key, value[0]);
		int keys = length/2-1;
		for(;keys>=0;keys--){
			if(!(value[keys*2+1] instanceof String)){
				throw new RuntimeException("键名必须为String");
			}
			put((String)value[keys*2+1], value[keys*2+2]);
		}
	}
	public static JSONMap createJsonMap(Object json){
		return new JSONMap(json);
	}
	
	public <T> Map<String,T> asMap(Class<T> objectClass){
		return (Map<String,T>)(Object)this;
	}
	public Map<String,JSONMap> asMap(){
		return (Map<String,JSONMap>)(Object)this;
	}
	public Map<String,JSONList> asMapList(){
		return (Map<String,JSONList>)(Object)this;
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
	 * 层次替换
	 * @param key
	 * @param obj
	 * @return
	 */
	public JSONMap set(String key,Object obj){
		return set(key, obj,1);
	}

	/**
	 * 按层次设定值 设定的对象需要是 JSONMap对象
	 * @param key  如：a.b.c.d
	 * @param value 要设定的值
	 * @param joinMethod
	 * 		0: 替换
	 * 		1：合并
	 * @return this
	 */
	public JSONMap set(String key,Object value,int joinMethod){
		if(value==null){
			return this;
		}
		int i = key.indexOf(".");
		if(i>-1){
			String key0 = key.substring(0, i);
			String key1 = key.substring(i + 1);
			Object o = this.get(key0);
			if(o==null){
				o = new JSONMap();
				put(key0,o);
			}
			if (!(o instanceof JSONMap)) {
				throw new SystemException("不支持的设定信息:" + o.getClass() + key.substring(i));
			}
			this.put(key0,((JSONMap)o).set(key1,value));
		}else{
			Object o = this.get(key);
			if(o instanceof JSONMap){
				if(!Map.class.isAssignableFrom(value.getClass())){
					throw new SystemException("设定类型不一致:"+value.getClass()+"→"+o.getClass());
				}else{
					if(joinMethod==0){
						put(key,value);
					}else{
						((JSONMap) o).putAll((Map)value);
					}
				}
			}else{
				put(key,value);
			}
		}
		return this;
	}
	/**
	 * 将值添加到JSONMap子值中
	 * @param key 设定的key
	 * @param obj 设定对象
	 * @param joinMethod 
	 * 		0:替换原有信息
	 * 		1：加入到原有数组中
	 * 		2：合并到原有数组中
	 * 		3：把原有数据跟新数据构造新数组 
	 * @return this
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
	public JSONMap add2List(String key,Object obj){
		List list=this.getList(key);
		if(list==null){
			list=new ArrayList();
		}
		if(obj instanceof Collection||obj instanceof Object[]){
			list = ValUtil.getList(obj);
		}else{
			list.add(obj);
		}
		put(key, list);
		return this;
	}
	@Override
	public JSONMap put(String key,Object value){
		super.put(key, value);
		return this;
	}
	@Override
	public String toString(){
		return JacksonUtil.getJson(this);
	}
	@Override
	public Object getInfoObject() {
		return this;
	}
}