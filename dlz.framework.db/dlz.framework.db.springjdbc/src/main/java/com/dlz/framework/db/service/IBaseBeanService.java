package com.dlz.framework.db.service;

import com.dlz.comm.json.JSONMap;
import com.dlz.framework.db.modal.Page;

import java.util.List;


/**
 * 服务基本接口 增删改查 添加对应的bean定义
 * @author dingkui 2018-06-27
 *
 */
public interface IBaseBeanService<T> extends IBaseService{
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
	T getByKey(Object pk);

	/**
	 * 构造查询条件查询数据列表（带分页信息）
	 */
	Page<T> pageByPara(Page<?> page, JSONMap para);
}
