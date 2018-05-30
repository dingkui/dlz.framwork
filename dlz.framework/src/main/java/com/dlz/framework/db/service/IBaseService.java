package com.dlz.framework.db.service;

import java.util.List;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.Page;

public interface IBaseService<T,PK> {
	/**
	 * 根据主键删除数据
	 */
	int delByKey(PK key);
	/**
	 * 根据主键删除数据
	 */
	int delByKeys(String keys);

	/**
	 * 查询数据列表
	 */
	List<T> searchList(JSONMap para);

	/**
	 * 条件查询单条对象
	 * @return 查询结果为0条则返回null，查询条数大于1则抛出异常
	 */
	T searchBean(JSONMap para);

	/**
	 * 根据主键查询单条数据
	 */
	T getByKey(PK pk);

	/**
	 * 添加或更新数据
	 */
	JSONMap addOrUpdate(JSONMap para);

	/**
	 * 构造查询条件查询数据列表（带分页信息）
	 */
	Page<T> pageByPara(Page<?> page, JSONMap para);
}
