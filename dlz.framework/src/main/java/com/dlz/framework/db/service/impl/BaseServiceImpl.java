package com.dlz.framework.db.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.comm.json.JSONMap;
import com.dlz.framework.db.anno.AnnoTable;
import com.dlz.framework.db.modal.DeleteParaMap;
import com.dlz.framework.db.modal.InsertParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.modal.UpdateParaMap;
import com.dlz.framework.db.service.IBaseService;
import com.dlz.framework.db.service.ICommService;
import com.dlz.comm.exception.CodeException;

/**
 * 服务基本接口 增删改查（数据类型为JSONMap）
 * @author dingkui 2018-06-27
 */
public abstract class BaseServiceImpl implements IBaseService {

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
	
	@Override
	public int delByKey(Object pk) {
		AnnoTable anno = getAnno();
		DeleteParaMap pm=new DeleteParaMap(anno.value());
		pm.addEqCondition(anno.pk(), pk);
		return commService.excuteSql(pm);
	}
	@Override
	public ResultMap getMapByKey(Object pk) {
		JSONMap para=new JSONMap(getAnno().pk(),pk);
		return searchMap(para);
	}
	@Override
	public int delByKeys(String keys) {
		AnnoTable anno = getAnno();
		DeleteParaMap pm=new DeleteParaMap(anno.value());
		pm.addCondition(anno.pk(),"in", keys);
		return commService.excuteSql(pm);
	}
	
	@Override
	public List<ResultMap> searchMapList(JSONMap para) {
		return searchMapList(getAnno().sqlKey(), para);
	}
	@Override
	public ResultMap searchMap(JSONMap para){
		return searchMap(getAnno().sqlKey(), para);
	};
	@Override
	public Page<ResultMap> mapPageByPara(Page<?> page, JSONMap para){
		return mapPageByPara(getAnno().sqlKey(),page, para);
	}
	@Override
	public List<ResultMap> searchMapList(String sqlKey,JSONMap para) {
		ParaMap pm=new ParaMap(sqlKey);
		pm.addParas(para);
		return commService.getMapList(pm);
	}
	@Override
	public Page<ResultMap> mapPageByPara(String sqlKey,Page<?> page, JSONMap para){
		ParaMap pm=new ParaMap(sqlKey,page);
		pm.addParas(para);
		return commService.getPage(pm);
	}	
	@Override
	public ResultMap searchMap(String sqlKey,JSONMap para){
		ParaMap pm=new ParaMap(sqlKey);
		pm.addParas(para);
		return commService.getMap(pm);
	};
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
}
