package com.dlz.framework.db.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.annotation.AnnoTable;
import com.dlz.framework.db.modal.DeleteParaMap;
import com.dlz.framework.db.modal.InsertParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.modal.SearchParaMap;
import com.dlz.framework.db.modal.UpdateParaMap;
import com.dlz.framework.db.service.IBaseService;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.exception.CodeException;

public abstract class BaseServiceImpl<T,PK> implements IBaseService<T,PK> {
	@Autowired
	protected ICommService commService;
	protected AnnoTable getAnno(){
		AnnoTable annotation = this.getClass().getAnnotation(AnnoTable.class);
		if(annotation==null){
			throw new CodeException(this.getClass()+" AnnoTable 必须定义");
		}
		if(annotation.value().equals("")){
			throw new CodeException("AnnoTable value必须定义");
		}
		if(annotation.pk().equals("")){
			throw new CodeException("AnnoTable pk必须定义");
		}
		if(annotation.sqlKey().equals("")){
			throw new CodeException("AnnoTable sqlKey必须定义");
		}
		return annotation;
	}
	@SuppressWarnings("unchecked")
	protected Class<T> getBeanClass(){
		return (Class<T>)this.getClass().getAnnotatedInterfaces()[0].getClass();
	}
	@Override
	public int delByKey(PK pk) {
		AnnoTable anno = getAnno();
		DeleteParaMap pm=new DeleteParaMap(anno.value());
		pm.addEqCondition(anno.pk(), pk);
		return commService.excuteSql(pm);
	}
	@Override
	public int delByKeys(String keys) {
		AnnoTable anno = getAnno();
		DeleteParaMap pm=new DeleteParaMap(anno.value());
		pm.addCondition(anno.pk(),"in", keys);
		return commService.excuteSql(pm);
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
	public ResultMap searchMap(JSONMap para){
		AnnoTable anno = getAnno();
		ParaMap pm=new ParaMap(anno.sqlKey());
		pm.addParas(para);
		return commService.getBean(pm, ResultMap.class);
	};
	@Override
	public T getByKey(PK pk) {
		AnnoTable anno = getAnno();
		SearchParaMap pm=new SearchParaMap(anno.value());
		pm.addEqCondition(anno.pk(), pk);
		return commService.getBean(pm, getBeanClass());
	}
	@Override
	public JSONMap addOrUpdate(JSONMap para) {
		AnnoTable anno = getAnno();
		Long key=para.getLong(anno.pk());
		if(key==null){
			InsertParaMap pm=new InsertParaMap(anno.value());
			para.add(anno.pk(), commService.getSeq(anno.value()));
			pm.addValues(para);
			commService.excuteSql(pm);
		}else{
			UpdateParaMap pm=new UpdateParaMap(anno.value());
			pm.addSetValues(para);
			pm.addEqCondition(anno.pk(), key);
			commService.excuteSql(pm);
		}
		return para;
	}
	@Override
	public Page<T> pageByPara(Page<?> page, JSONMap para) {
		AnnoTable anno = getAnno();
		ParaMap pm=new ParaMap(anno.sqlKey(),page);
		pm.addParas(para);
		return commService.getPage(pm, getBeanClass());
	}
	@Override
	public Page<JSONMap> mapPageByPara(Page<?> page, JSONMap para){
		AnnoTable anno = getAnno();
		ParaMap pm=new ParaMap(anno.sqlKey(),page);
		pm.addParas(para);
		return commService.getPage(pm,JSONMap.class);
	}
	
	
}
