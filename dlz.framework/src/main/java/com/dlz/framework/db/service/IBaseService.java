package com.dlz.framework.db.service;

import java.util.List;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;

/**
 * 服务基本接口 增删改查（数据类型为JSONMap）
 * @author dingkui 2018-06-27
 *
 */
public interface IBaseService {
	/**
	 * 根据主键删除
	 */
	int delByKey(Object pk);

	/**
	 * 根据多个主键删除
	 */
	int delByKeys(String keys);
	/**
	 * 根据主键查询
	 */
	ResultMap getMapByKey(Object key);

	/**
	 * 查询数据列表返回ResultMap
	 */
	List<ResultMap> searchMapList(JSONMap para);

	/**
	 * 条件查询单条ResultMap对象
	 * 
	 * @return 查询结果为0条则返回null，查询条数大于1则抛出异常
	 */
	ResultMap searchMap(JSONMap para);

	/**
	 * 添加或更新数据
	 */
	JSONMap addOrUpdate(JSONMap para);

	/**
	 * 构造查询条件查询数据列表JSONMap（带分页信息）
	 */
	Page<ResultMap> mapPageByPara(Page<?> page, JSONMap para);

	/**
	 * 查询数据列表返回ResultMap
	 */
	List<ResultMap> searchMapList(String sqlKey, JSONMap para);

	/**
	 * 条件查询单条ResultMap对象
	 * 
	 * @return 查询结果为0条则返回null，查询条数大于1则抛出异常
	 */
	ResultMap searchMap(String sqlKey, JSONMap para);

	/**
	 * 构造查询条件查询数据列表JSONMap（带分页信息）
	 */
	Page<ResultMap> mapPageByPara(String sqlKey, Page<?> page, JSONMap para);
}
