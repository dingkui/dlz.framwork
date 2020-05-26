package com.dlz.framework.db.service.impl;

import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.db.DbCoverUtil;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.cache.DbOprationCache;
import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.db.modal.*;
import com.dlz.framework.db.mySequence.ISequenceMaker;
import com.dlz.framework.db.service.ICommService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings("unchecked")
@DependsOn({"dbInfo"})
@Lazy
@Slf4j
public class CommServiceImpl implements ICommService {
	@Autowired
	private DaoOperator daoOperator;
	
	@Autowired(required = false)
	private DbOprationCache dbOprationCache;
	
	@Autowired(required = false)
	ISequenceMaker sequenceMaker;
	
	@Override
	public int excuteSql(BaseParaMap paraMap) {
		paraMap = SqlUtil.dealParm(paraMap);
		if(paraMap.getSqlInput()!=null && log.isInfoEnabled()){
			log.info("SQL:"+paraMap.getSqlInput() + "[" + paraMap.getSqlRun()+ "]para:[" + paraMap.getPara()+"]");
		}
		try {
			int r=daoOperator.updateSql(paraMap);
			if(log.isInfoEnabled()){
				log.info("result:"+r);
			}
			return r;
		} catch (Exception e) {
			if(e instanceof DbException) {
				throw e;
			}
			throw new DbException(paraMap.getSqlInput() + ":" + paraMap.getSqlRun() + " para:" + paraMap.getPara(),1003, e);
		}
	}
	@Override
	public long getSeq(String seqName) {
		if (sequenceMaker != null && sequenceMaker.isInited()) {
			return sequenceMaker.nextVal(seqName);
		}
		return daoOperator.getSeq(seqName);
	}
	
	@Override
	public int getCnt(BaseParaMap paraMap) {
		String key=null;
		if(dbOprationCache !=null && paraMap.getCacheTime()!=0){
			key="cnt"+paraMap.key();
			Page<ResultMap> page=dbOprationCache.getFromCache(key);
			if(page!=null){
				return page.getCount();
			}
		}
		
		paraMap = SqlUtil.dealParm(paraMap);
		SqlUtil.createCntSql(paraMap);
		if(paraMap.getSqlInput()!=null && log.isInfoEnabled()){
			log.info("SQL:"+paraMap.getSqlInput() + "[" + paraMap.getSql_cnt()+ "]para:[" + paraMap.getPara()+"]");
		}
		try {
			int cnt = daoOperator.getCnt(paraMap);
			if(key!=null){
				dbOprationCache.put(key, new Page<ResultMap>(cnt,null), paraMap.getCacheTime());
			}
			return cnt;
		} catch (Exception e) {
			if(e instanceof DbException) {
				throw e;
			}
			throw new DbException(paraMap.getSqlInput() + ":" + paraMap.getSql_cnt() + " para:" + paraMap.getPara(),1003, e);
		}
	}
	
	/**
	* 从数据库中取得map类型列表如：[{AD_ENDDATE=2015-04-08 13:47:12.0}]
	* sql语句，可以带参数如：select AD_ENDDATE from JOB_AD t where ad_id=#{ad_id}
	* @param paraMap ：Map<String,Object> m=new HashMap<String,Object>();m.put("ad_id", "47");
	* @return
	* @throws Exception
	*/
	private List<ResultMap> getList(BaseParaMap paraMap) {
		String key=null;
		if(dbOprationCache !=null && paraMap.getCacheTime()!=0){
			key="list"+paraMap.key();
			Page<ResultMap> page=dbOprationCache.getFromCache(key);
			if(page!=null){
				return page.getData();
			}
		}
		
		paraMap = SqlUtil.dealParm(paraMap);
		SqlUtil.createPageSql(paraMap);
		if(paraMap.getSqlInput()!=null && log.isInfoEnabled()){
			log.info("SQL:"+paraMap.getSqlInput() + "[" + paraMap.getSql_page()+ "]para:[" + paraMap.getPara()+"]");
		}
		try {
			List<ResultMap> list = daoOperator.getList(paraMap);
			List<ResultMap> list2=new ArrayList<ResultMap>();
			for(ResultMap r: list){
				list2.add(DbCoverUtil.converResultMap(r,paraMap.getConvert()));
			}
			if(key!=null){
				dbOprationCache.put(key, new Page<ResultMap>(0,list2), paraMap.getCacheTime());
			}
			return list2;
		} catch (Exception e) {
			if(e instanceof DbException) {
				throw e;
			}
			throw new DbException(e.getMessage()+" "+paraMap.getSqlInput() + ":" + paraMap.getSql_page() + " para:" + paraMap.getPara(),1003, e);
		}
	}
	
	/**
	 * 从List中取得一条Map，多条报异常
	 * @param list
	 * @author dk 2015-04-09
	 * @return
	 * @throws Exception
	 */
	private <T> T getOne(List<T> list, boolean throwEx){
		if(list.size()==0){
			return null;
		}else if(list.size()>1 && throwEx){
			throw new DbException("查询结果为多条",1004);
		}else{
			return list.get(0);
		}
	}
	
	@Override
	public <T> T getBean(BaseParaMap paraMap,Class<T> t, boolean throwEx){
		try {
			return JacksonUtil.coverObj(getMap(paraMap,throwEx), t);
		} catch (Exception e) {
			if(e instanceof DbException){
				throw e;
			}
			throw new DbException(e.getMessage(),1004,e);
		}
	}
	@Override
	public <T> List<T> getBeanList(BaseParaMap paraMap,Class<T> t){
		List<ResultMap> list = getList(paraMap);
		List<T> l = new ArrayList<T>();
		for(ResultMap r: list){
			try{
				l.add(JacksonUtil.coverObj(r, t));
			}catch(Exception e){
				log.error(e.getMessage());
			}
		}
		return l;
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
			log.error(clazz.toString()+"未设置tableName，取得通用sequence！");
		}
		if (seqName != null) {
			seqName = "seq_" + seqName;
		} 
		return getSeq(seqName);
	}
	@Override
	public Page<ResultMap> getPage(BaseParaMap paraMap){
		return getPage(paraMap, ResultMap.class);
	}
	
	@Override
	public ResultMap getMap(BaseParaMap paraMap, boolean throwEx){
		try{
			return getOne(getList(paraMap),throwEx);
		}catch (Exception e) {
			if(e instanceof DbException) {
				throw e;
			}
			throw new DbException(paraMap.getSqlInput() + ":" + paraMap.getSqlRun() + " para:" + paraMap.getPara(),1004, e);
		}
	}
	
	@Override
	public List<ResultMap> getMapList(BaseParaMap paraMap){
		return getList(paraMap);
	}
	
	@Override
	public Object getColum(BaseParaMap paraMap){
		return DbCoverUtil.getFistClumn(getOne(getList(paraMap),true));
	}

	@Override
	public List<Object> getColumList(BaseParaMap paraMap){
		List<ResultMap> r = getList(paraMap);
		List<Object> l = new ArrayList<Object>();
		for(ResultMap m : r){
			l.add(DbCoverUtil.getFistClumn(m));
		}
		return l;
	}
	public <T> List<T> getColumList(BaseParaMap paraMap, Class<T> t){
		List<ResultMap> r = getList(paraMap);
		List<T> l = new ArrayList<T>();
		for(ResultMap m : r){
			l.add(ValUtil.getObj(DbCoverUtil.getFistClumn(m), t));
		}
		return l;
	}
	@Override
	public List<String> getStrList(BaseParaMap paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<String> l = new ArrayList<String>();
		for(ResultMap m : r){
			l.add(ValUtil.getStr(DbCoverUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public List<BigDecimal> getBigDecimalList(BaseParaMap paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<BigDecimal> l = new ArrayList<BigDecimal>();
		for(ResultMap m : r){
			l.add(ValUtil.getBigDecimal(DbCoverUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public List<Float> getFloatList(BaseParaMap paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<Float> l = new ArrayList<Float>();
		for(ResultMap m : r){
			l.add(ValUtil.getFloat(DbCoverUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public List<Integer> getIntList(BaseParaMap paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<Integer> l = new ArrayList<Integer>();
		for(ResultMap m : r){
			l.add(ValUtil.getInt(DbCoverUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public List<Long> getLongList(BaseParaMap paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<Long> l = new ArrayList<Long>();
		for(ResultMap m : r){
			l.add(ValUtil.getLong(DbCoverUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public <T> Page<T> getPage(BaseParaMap paraMap, Class<T> t) {
		Page<T> page= paraMap.getPage();
		if(page==null){
			page=new Page<T>();
		}
		paraMap.setPage(page);
		
		String key=null;
		if(dbOprationCache !=null && paraMap.getCacheTime()!=0){
			key="page"+paraMap.key();
			Page<T> page2=dbOprationCache.getFromCache(key);
			if(page2!=null){
				return page2;
			}
		}
		//pageNow==0的情况，不统计条数
		boolean needCount=page.getPageNow()>0;
		//是否需要查询列表（需要统计条数并且条数是0的情况不查询，直接返回空列表）
		boolean needList=true;
		
		if(needCount){
			page.setCount(getCnt(paraMap));
			if(page.getCount()==0){
				needList=false;
			}
		}
		
		if(needList){
			if(t==ResultMap.class){
				page.setData((List<T>) getMapList(paraMap));
			}else{
				page.setData(getBeanList(paraMap,t));
			}
		}else{
			page.setData(new ArrayList<T>());
		}
		
		if(key!=null){
			dbOprationCache.put(key, page, paraMap.getCacheTime());
		}
		return page;
	}

	@Override
	public int update(String tableName, Object bean, String where) {
		UpdateParaMap paraMap = new UpdateParaMap(tableName);
		paraMap.addSetValues(new JSONMap(bean));
		paraMap.setWhere(" where "+where);
		return excuteSql(paraMap);
	}
	@Override
	public int insert(String tableName, Object bean) {
		InsertParaMap paraMap = new InsertParaMap(tableName);
		paraMap.addValues(new JSONMap(bean));
		return excuteSql(paraMap);
	}
}
