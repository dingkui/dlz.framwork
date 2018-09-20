package com.dlz.framework.db.service.impl;

import java.util.List;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.annotation.AnnoTable;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.SearchParaMap;
import com.dlz.framework.db.service.IBaseBeanService;
/**
 * 服务基本接口 增删改查 添加对应的bean定义
 * @author dingkui 2018-06-27
 *
 */
public abstract class BaseBeanServiceImpl<T> extends BaseServiceImpl implements IBaseBeanService<T> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@SuppressWarnings("unchecked")
	private Class<T> getBeanClass(){
		return (Class<T>)this.getClass().getAnnotatedInterfaces()[0].getClass();
	}
	
	@Override
	public List<T> searchList(JSONMap para) {
		AnnoTable anno = getAnno();
		ParaMap pm=new ParaMap(anno.sqlKey());
		pm.addParas(para);
		return commService.getBeanList(pm, getBeanClass());
	}
	@Override
	public T searchBean(JSONMap para) {
		AnnoTable anno = getAnno();
		ParaMap pm=new ParaMap(anno.sqlKey());
		pm.addParas(para);
		return commService.getBean(pm, getBeanClass());
	}
	@Override
	public Page<T> pageByPara(Page<?> page, JSONMap para) {
		AnnoTable anno = getAnno();
		ParaMap pm=new ParaMap(anno.sqlKey(),page);
		pm.addParas(para);
		return commService.getPage(pm, getBeanClass());
	}
	
	@Override
	public T getByKey(Object pk) {
		AnnoTable anno = getAnno();
		SearchParaMap pm=new SearchParaMap(anno.value());
		pm.addEqCondition(anno.pk(), pk);
		return commService.getBean(pm, getBeanClass());
	}
}
