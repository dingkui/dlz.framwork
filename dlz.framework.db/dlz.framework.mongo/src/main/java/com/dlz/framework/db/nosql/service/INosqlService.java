package com.dlz.framework.db.nosql.service;

import java.util.List;

import com.dlz.comm.json.JSONMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.nosql.modal.Delete;
import com.dlz.framework.db.nosql.modal.Find;
import com.dlz.framework.db.nosql.modal.Insert;
import com.dlz.framework.db.nosql.modal.Update;

/**
 * 从数据库中取得单条map类型数据：{adEnddate=2015-04-08 13:47:12.0}
 * @param sql sql语句，可以带参数如：select AD_ENDDATE from JOB_AD t where ad_id=#{ad_id}
 * @param paraMap ：Map<String,Object> m=new HashMap<String,Object>();m.put("ad_id", "47");
 * @return
 * @throws Exception
 */
public interface INosqlService {
	public int insert(Insert insert);
	/**
	 * 插入数据，不需要配置bson
	 * @param name 表名
	 * @param datas 
	 * @return
	 */
	public int insert(String name,List<JSONMap> datas);
	/**
	 * 插入数据，不需要配置bson
	 * @param name 表名
	 * @param datas 
	 * @return
	 */
	public int insert(String name,JSONMap data);
	
	public int update(Update paraMap);
	/**
	 * 根据ID更新数据，不需要配置bson
	 * @param name 表名
	 * @param id 
	 * @param data 
	 * @return
	 */
	public int update(String name,long id,JSONMap data);
	/**
	 * 根据条件更新数据，bson语句中需要有filter  name.byfilter
	 * @param name 表名
	 * @param para 
	 * @param data 
	 * @return
	 */
	public int update(String name,JSONMap para,JSONMap data);
	
	public int del(Delete paraMap);
	/**
	 * 根据ID删除数据，不需要配置bson
	 * @param name 表名
	 * @param id 
	 * @return
	 */
	public int del(String name,long id);
	/**
	 * 根据条件删除数据，bson语句中需要有filter  name.byfilter
	 * @param name 表名
	 * @param para 
	 * @return
	 */
	public int del(String name,JSONMap para);
	
	/**
	 * 从数据库中取得集合
	 */
	public ResultMap getMap(Find paraMap);
	/**
	 * 根据ID查询数据，不需要配置bson
	 * @param name 表名
	 * @param id 
	 * @return
	 */
	public ResultMap getMap(String name,long id);
	public List<ResultMap> getMapList(Find paraMap);
	public List<ResultMap> getMapList(String name,JSONMap para);
	public <T> T getBean(Find paraMap, Class<T> t);
	/**
	 * 根据ID取得对象，不需要配置bson语句
	 * @param name 表名
	 * @param id _id
	 * @param t 类型
	 * @return
	 */
	public <T> T getBean(String name,long id, Class<T> t);
	/**
	 * 根据参数查询取得对象列表，不需要配置bson语句
	 * @param paraMap 表名
	 * @param id _id
	 * @param t 类型
	 * @return
	 */
	public <T> List<T> getBeanList(Find paraMap, Class<T> t);
	/**
	 * 根据参数查询取得对象列表，bson语句中需要有filter  name.byfilter
	 * @param name 表名
	 * @param para 构建filter需要的参数
	 * @param t 类型
	 * @return
	 */
	public <T> List<T> getBeanList(String name,JSONMap para, Class<T> t);
	
	/**
	 * 取得分页数据
	 * @return
	 * @throws Exception
	 */
	Page<ResultMap> getPage(Find paraMap);
	<T> Page<T> getPage(Find paraMap, Class<T> t);
	
	public int getCnt(Find paraMap);
	public long getSeq(String seqName);
}
