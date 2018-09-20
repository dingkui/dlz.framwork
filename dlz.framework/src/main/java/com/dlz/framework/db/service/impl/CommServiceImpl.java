package com.dlz.framework.db.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.DbCoverUtil;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.cache.DbOprationCache;
import com.dlz.framework.db.dao.IDaoOperator;
import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.db.modal.BaseModel;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.InsertParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.modal.UpdateParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.ValUtil;

@Service 
@SuppressWarnings("unchecked")
@DependsOn("dbInfo")
public class CommServiceImpl implements ICommService {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static MyLogger logger = MyLogger.getLogger(CommServiceImpl.class);
	@Autowired
	private IDaoOperator daoOperator;
	@Autowired
	private DbOprationCache dbOprationCache;
	
	@Override
	public int excuteSql(BaseParaMap paraMap) {
		paraMap = SqlUtil.dealParm(paraMap);
		if(paraMap.getSqlInput()!=null && logger.isInfoEnabled()){
			logger.info("SQL:"+paraMap.getSqlInput() + "[" + paraMap.getSqlRun()+ "]para:[" + paraMap.getPara()+"]");
		}
		try {
			int r=daoOperator.updateSql(paraMap);
			if(logger.isInfoEnabled()){
				logger.info("result:"+r);
			}
			return r;
		} catch (Exception e) {
			throw new DbException(paraMap.getSqlInput() + ":" + paraMap.getSqlRun() + " para:" + paraMap.getPara(), e);
		}
	}
	@Override
	public long getSeq(String seqName) {
		return daoOperator.getSeq(seqName);
	}
	
	@Override
	public long getSeqWithTime(String seqName) {
		return daoOperator.getSeqWithTime(seqName);
	}
	@Override
	public int getCnt(BaseParaMap paraMap) {
		String key=null;
		if(paraMap.getCacheTime()!=0){
			key="cnt"+paraMap.key();
			Page<ResultMap> page=dbOprationCache.getFromCache(key);
			if(page!=null){
				return page.getCount();
			}
		}
		
		paraMap = SqlUtil.dealParm(paraMap);
		SqlUtil.createCntSql(paraMap);
		if(paraMap.getSqlInput()!=null && logger.isInfoEnabled()){
			logger.info("SQL:"+paraMap.getSqlInput() + "[" + paraMap.getSql_cnt()+ "]para:[" + paraMap.getPara()+"]");
		}
		try {
			int cnt = daoOperator.getCnt(paraMap);
			if(key!=null){
				dbOprationCache.put(key, new Page<ResultMap>(cnt,null), paraMap.getCacheTime());
			}
			return cnt;
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
		String key=null;
		if(paraMap.getCacheTime()!=0){
			key="list"+paraMap.key();
			Page<ResultMap> page=dbOprationCache.getFromCache(key);
			if(page!=null){
				return page.getData();
			}
		}
		
		paraMap = SqlUtil.dealParm(paraMap);
		SqlUtil.createPageSql(paraMap);
		if(paraMap.getSqlInput()!=null && logger.isInfoEnabled()){
			logger.info("SQL:"+paraMap.getSqlInput() + "[" + paraMap.getSql_page()+ "]para:[" + paraMap.getPara()+"]");
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
			return JacksonUtil.coverObj(getMap(paraMap), t);
		} catch (Exception e) {
			throw new DbException(e.getMessage(),e);
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
				logger.error(e.getMessage());
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
			logger.error(clazz.toString()+"未设置tableName，取得通用sequence！");
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
	public ResultMap getMap(BaseParaMap paraMap){
		try{
			return getOne(getList(paraMap));
		}catch (Exception e) {
			throw new DbException(paraMap.getSqlInput() + ":" + paraMap.getSqlRun() + " para:" + paraMap.getPara(), e);
		}
	}
	
	@Override
	public List<ResultMap> getMapList(BaseParaMap paraMap){
		return getList(paraMap);
	}
	
	@Override
	public Object getColum(BaseParaMap paraMap){
		return DbCoverUtil.getFistClumn(getOne(getList(paraMap)));
	}
	@Override
	public String getStr(BaseParaMap paraMap){
		return ValUtil.getStr(getColum( paraMap));
	}
	@Override
	public Long getLong(BaseParaMap paraMap){
		return ValUtil.getLong(getColum( paraMap));
	}
	@Override
	public Integer getInt(BaseParaMap paraMap){
		return ValUtil.getInt(getColum( paraMap));
	}
	@Override
	public Float getFloat(BaseParaMap paraMap){
		return ValUtil.getFloat(getColum(paraMap));
	}
	@Override
	public BigDecimal getBigDecimal(BaseParaMap paraMap){
		return ValUtil.getBigDecimal(getColum( paraMap));
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
		if(paraMap.getCacheTime()!=0){
			key="page"+paraMap.key();
			@SuppressWarnings("rawtypes")
			Page page2=dbOprationCache.getFromCache(key);
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
	public int excuteSql(String sql, Object... para) {
		return excuteSql(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public Object getColum(String sql, Object... para) {
		return getColum(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public String getStr(String sql, Object... para) {
		return getStr(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public BigDecimal getBigDecimal(String sql, Object... para) {
		return getBigDecimal(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public Float getFloat(String sql, Object... para) {
		return getFloat(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public Integer getInt(String sql, Object... para) {
		return getInt(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public Long getLong(String sql, Object... para) {
		return getLong(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public List<Object> getColumList(String sql, Object... para) {
		return getColumList(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public List<String> getStrList(String sql, Object... para) {
		return getStrList(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public List<BigDecimal> getBigDecimalList(String sql, Object... para) {
		return getBigDecimalList(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public List<Float> getFloatList(String sql, Object... para) {
		return getFloatList(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public List<Integer> getIntList(String sql, Object... para) {
		return getIntList(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public List<Long> getLongList(String sql, Object... para) {
		return getLongList(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public ResultMap getMap(String sql, Object... para) {
		return getMap(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public List<ResultMap> getMapList(String sql, Object... para) {
		return getMapList(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public <T> T getBean(String sql, Class<T> t, Object... para) {
		return getBean(SqlUtil.getParmMap(sql, para),t);
	}
	@Override
	public <T> List<T> getBeanList(String sql, Class<T> t, Object... para) {
		return getBeanList(SqlUtil.getParmMap(sql, para),t);
	}
	@Override
	public int getCnt(String sql, Object... para) {
		return getCnt(SqlUtil.getParmMap(sql, para));
	}
	@Override
	public <T> Page<T> getPage(String sql, Class<T> t, int pageSize, int pageIndex, Object... para) {
		BaseParaMap paraMap = SqlUtil.getParmMap(sql, para);
		paraMap.setPage(new Page<T>(pageIndex,pageSize));
		return getPage(paraMap, t);
	}
	@Override
	public Page<ResultMap> getPage(String sql, int pageSize, int pageIndex, Object... para) {
		BaseParaMap paraMap = SqlUtil.getParmMap(sql, para);
		paraMap.setPage(new Page<ResultMap>(pageIndex,pageSize));
		return getPage(paraMap);
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
