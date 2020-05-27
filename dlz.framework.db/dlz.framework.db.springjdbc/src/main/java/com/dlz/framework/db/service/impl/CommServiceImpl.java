package com.dlz.framework.db.service.impl;

import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.db.convertor.ConvertUtil;
import com.dlz.framework.db.SqlUtil;
import com.dlz.comm.exception.DbException;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.InsertParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.modal.UpdateParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.items.SqlItem;
import com.dlz.framework.db.service.ICommService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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



	@Override
	public int excuteSql(BaseParaMap paraMap) {
		SqlItem sqlItem = paraMap.getSqlItem();
		if (sqlItem.getSqlKey() != null){
			sqlItem = SqlUtil.dealParm(paraMap);
			sqlItem.setSqlRun(sqlItem.getSqlDeal());
			if(log.isInfoEnabled()){
				log.info("SQL:"+sqlItem.getSqlKey() + "[" + sqlItem.getSqlRun()+ "]para:[" + paraMap.getPara()+"]");
			}
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
			throw new DbException(sqlItem.getSqlKey() + ":" + sqlItem.getSqlRun() + " para:" + paraMap.getPara(),1003, e);
		}
	}
	@Override
	public long getSeq(String seqName) {
		return daoOperator.getSeq(seqName);
	}
	
	@Override
	public int getCnt(BaseParaMap paraMap) {
		Page cache = paraMap.getCacheItem().getCache("cnt", paraMap);
		if(cache!=null){
			return cache.getCount();
		}
		SqlItem sqlItem = paraMap.getSqlItem();
		if (sqlItem.getSqlKey() != null){
			sqlItem = SqlUtil.dealParm(paraMap);
			SqlUtil.createCntSql(sqlItem);
			if(log.isInfoEnabled()){
				log.info("SQL:"+sqlItem.getSqlKey() + "[" + sqlItem.getSqlCnt()+ "]para:[" + paraMap.getPara()+"]");
			}
		}
		try {
			int cnt = daoOperator.getCnt(paraMap);
			paraMap.getCacheItem().saveCache(cnt);
			return cnt;
		} catch (Exception e) {
			if(e instanceof DbException) {
				throw e;
			}
			throw new DbException(sqlItem.getSqlKey() + ":" + sqlItem.getSqlCnt() + " para:" + paraMap.getPara(),1003, e);
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
		Page cache = paraMap.getCacheItem().getCache("list", paraMap);
		if (cache != null) {
			return cache.getData();
		}
		SqlItem sqlItem = paraMap.getSqlItem();
		if (sqlItem.getSqlKey() != null){
			sqlItem = SqlUtil.dealParm(paraMap);
			SqlUtil.createPageSql(paraMap);
			if (log.isInfoEnabled()) {
				log.info("SQL:" + sqlItem.getSqlKey() + "[" + sqlItem.getSqlRun() + "]para:[" + paraMap.getPara() + "]");
			}
		}
		try {
			List<ResultMap> list = daoOperator.getList(paraMap);
			List<ResultMap> list2=new ArrayList<ResultMap>();
			for(ResultMap r: list){
				list2.add(ConvertUtil.converResultMap(r,paraMap.getConvert()));
			}
			paraMap.getCacheItem().saveCache(list2);
			return list2;
		} catch (Exception e) {
			if(e instanceof DbException) {
				throw e;
			}
			throw new DbException(e.getMessage()+" "+sqlItem.getSqlKey() + ":" + sqlItem.getSqlPage() + " para:" + paraMap.getPara(),1003, e);
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
	public Page<ResultMap> getPage(BaseParaMap paraMap){
		return getPage(paraMap, ResultMap.class);
	}
	
	@Override
	public ResultMap getMap(BaseParaMap paraMap, boolean throwEx){
		SqlItem sqlItem = paraMap.getSqlItem();
		try{
			return getOne(getList(paraMap),throwEx);
		}catch (Exception e) {
			if(e instanceof DbException) {
				throw e;
			}
			throw new DbException(sqlItem.getSqlKey() + ":" + sqlItem.getSqlRun() + " para:" + paraMap.getPara(),1004, e);
		}
	}
	
	@Override
	public List<ResultMap> getMapList(BaseParaMap paraMap){
		return getList(paraMap);
	}
	
	@Override
	public Object getColum(BaseParaMap paraMap){
		return ConvertUtil.getFistClumn(getOne(getList(paraMap),true));
	}

	@Override
	public List<Object> getColumList(BaseParaMap paraMap){
		List<ResultMap> r = getList(paraMap);
		List<Object> l = new ArrayList<Object>();
		for(ResultMap m : r){
			l.add(ConvertUtil.getFistClumn(m));
		}
		return l;
	}
	public <T> List<T> getColumList(BaseParaMap paraMap, Class<T> t){
		List<ResultMap> r = getList(paraMap);
		List<T> l = new ArrayList<T>();
		for(ResultMap m : r){
			l.add(ValUtil.getObj(ConvertUtil.getFistClumn(m), t));
		}
		return l;
	}
	@Override
	public List<String> getStrList(BaseParaMap paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<String> l = new ArrayList<String>();
		for(ResultMap m : r){
			l.add(ValUtil.getStr(ConvertUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public List<BigDecimal> getBigDecimalList(BaseParaMap paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<BigDecimal> l = new ArrayList<BigDecimal>();
		for(ResultMap m : r){
			l.add(ValUtil.getBigDecimal(ConvertUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public List<Float> getFloatList(BaseParaMap paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<Float> l = new ArrayList<Float>();
		for(ResultMap m : r){
			l.add(ValUtil.getFloat(ConvertUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public List<Integer> getIntList(BaseParaMap paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<Integer> l = new ArrayList<Integer>();
		for(ResultMap m : r){
			l.add(ValUtil.getInt(ConvertUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public List<Long> getLongList(BaseParaMap paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<Long> l = new ArrayList<Long>();
		for(ResultMap m : r){
			l.add(ValUtil.getLong(ConvertUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public <T> Page<T> getPage(BaseParaMap paraMap, Class<T> t) {
		Page cache = paraMap.getCacheItem().getCache("page", paraMap);
		if(cache!=null){
			return cache;
		}

		Page<T> page= paraMap.getPage();
		//是否需要查询列表（需要统计条数并且条数是0的情况不查询，直接返回空列表）
		boolean needList=true;
		
		page.setCount(getCnt(paraMap));
		if(page.getCount()==0){
			needList=false;
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
		paraMap.getCacheItem().saveCache(page);
		
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
