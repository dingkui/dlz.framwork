package com.dlz.framework.db.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlz.framework.db.DbCoverUtil;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.dao.IDaoOperator;
import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.db.modal.BaseModel;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.logger.MyLogger;

@Service 
public class CommServiceImpl implements ICommService {
	private static MyLogger logger = MyLogger.getLogger(CommServiceImpl.class);
	@Autowired
	private IDaoOperator commMapper;
	
	@Override
	public int excuteSql(BaseParaMap paraMap) {
		paraMap = SqlUtil.dealParm(paraMap);
		logger.info("SQL:"+paraMap.getSqlInput() + "[" + paraMap.getSqlRun()+ "]para:[" + paraMap.getPara()+"]");
		try {
			int r=commMapper.updateSql(paraMap);
			logger.info("result:"+r);
			return r;
		} catch (Exception e) {
			throw new DbException(paraMap.getSqlInput() + ":" + paraMap.getSqlRun() + " para:" + paraMap.getPara(), e);
		}
	}
	@Override
	public long getSeq(String seqName) {
		return commMapper.getSeq(seqName);
	}
	
	@Override
	public long getSeqWithTime(String seqName) {
		return commMapper.getSeqWithTime(seqName);
	}
	@Override
	public int getCnt(BaseParaMap paraMap) {
		paraMap = SqlUtil.dealParm(paraMap);
		SqlUtil.createCntSql(paraMap);
		logger.info("SQL:"+paraMap.getSqlInput() + "[" + paraMap.getSql_cnt()+ "]para:[" + paraMap.getPara()+"]");
		try {
			return commMapper.getPageCnt(paraMap);
		} catch (Exception e) {
			throw new DbException(paraMap.getSqlInput() + ":" + paraMap.getSql_cnt() + " para:" + paraMap.getPara(), e);
		}
	}
	
	/**
	* 从数据库中取得map类型列表如：[{AD_ENDDATE=2015-04-08 13:47:12.0}]
	* @param sql sql语句，可以带参数如：select AD_ENDDATE from JOB_AD t where ad_id=#{ad_id}
	* @param paraMap ：Map<String,Object> m=new HashMap<String,Object>();m.put("ad_id", "47");
	* @return
	* @throws Exception
	*/
	private List<ResultMap> getList(BaseParaMap paraMap) {
		paraMap = SqlUtil.dealParm(paraMap);
		SqlUtil.createPageSql(paraMap);
		logger.info("SQL:"+paraMap.getSqlInput() + "[" + paraMap.getSql_page()+ "]para:[" + paraMap.getPara()+"]");
		try {
			return commMapper.getList(paraMap);
		} catch (Exception e) {
			throw new DbException(e.getMessage()+" "+paraMap.getSqlInput() + ":" + paraMap.getSql_page() + " para:" + paraMap.getPara(), e);
		}
	}
	
	/**
	 * 从List中取得一条Map，多条报异常
	 * @param list
	 * @author dk 2015-04-09
	 * @return
	 * @throws Exception
	 */
	private <T> T getOne(List<T> list){
		if(list.size()==0){
			return null;
		}else if(list.size()>1){
			throw new DbException("查询结果为多条");
		}else{
			return list.get(0);
		}
	}
	
	@Override
	public <T> T getBean(BaseParaMap paraMap,Class<T> t){
		try {
			return DbCoverUtil.converObjWithJackson(getMap(paraMap),t);
		} catch (Exception e) {
			throw new DbException(e.getMessage(),e);
		}
	}
	@Override
	public <T> List<T> getBeanList(BaseParaMap paraMap,Class<T> t){
		return DbCoverUtil.converList(getList(paraMap),t);
	}
	
	@Override
	public long getSeq(Class<?> clazz) {
		String seqName = null;
		try {
			Object o = clazz.newInstance();
			if (o instanceof BaseModel) {
				Field field = clazz.getField("tableName");
				seqName=field.get(o).toString();
			}
		} catch (Exception e) {
			logger.error(clazz.toString()+"未设置tableName，取得通用sequence！");
		}
		if (seqName != null) {
			seqName = "seq_" + seqName;
		} 
		return getSeq(seqName);
	}

	@Override
	public Page getPage(BaseParaMap paraMap){
		Page page= paraMap.getPage();
		if(page==null){
			page=new Page();
		}
		page.setCount(getCnt(paraMap));
		if(page.getCount()>0){
			page.setData(getMapList(paraMap));
		}else{
			page.setData(new ArrayList<ResultMap>());
		}
		return page;
	}
	
	@Override
	public ResultMap getMap(BaseParaMap paraMap){
		try{
			return DbCoverUtil.doMyCover(getOne(getList(paraMap)), paraMap.getConvert());
		}catch (Exception e) {
			throw new DbException(paraMap.getSqlInput() + ":" + paraMap.getSqlRun() + " prar:" + paraMap.getPara(), e);
		}
	}
	
	@Override
	public List<ResultMap> getMapList(BaseParaMap paraMap){
		return DbCoverUtil.doMyCover(getList(paraMap), paraMap.getConvert());
	}
	
	@Override
	public Object getColum(BaseParaMap paraMap){
		return DbCoverUtil.getStr(getMap( paraMap));
	}
	@Override
	public <T> T getColum(BaseParaMap paraMap,Class<T> t){
		return DbCoverUtil.converObjWithJackson(getColum(paraMap),t);
	}
	@Override
	public List<Object> getColumList(BaseParaMap paraMap){
		List<ResultMap> r = getMapList( paraMap);
		List<Object> l = new ArrayList<Object>();
		for(ResultMap m : r){
			l.add(DbCoverUtil.getStr(m));
		}
		return l;
	}
	public <T> List<T> getColumList(BaseParaMap paraMap, Class<T> t){
		List<Object> r = getColumList(paraMap);
		List<T> l = new ArrayList<T>();
		for(Object m : r){
			l.add(DbCoverUtil.converObjWithJackson(m,t));
		}
		return l;
	}
}
