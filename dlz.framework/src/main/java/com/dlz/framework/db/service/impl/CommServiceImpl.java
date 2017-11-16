package com.dlz.framework.db.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
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
import com.dlz.framework.util.ValUtil;

@Service 
@SuppressWarnings("unchecked")
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
	public Page<ResultMap> getPage(BaseParaMap paraMap){
		Page<ResultMap> page= paraMap.getPage();
		if(page==null){
			page=new Page<ResultMap>();
		}
		paraMap.setPage(page);
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
			throw new DbException(paraMap.getSqlInput() + ":" + paraMap.getSqlRun() + " para:" + paraMap.getPara(), e);
		}
	}
	
	@Override
	public List<ResultMap> getMapList(BaseParaMap paraMap){
		return DbCoverUtil.doMyCover(getList(paraMap), paraMap.getConvert());
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
			l.add(DbCoverUtil.converObjWithJackson(DbCoverUtil.getFistClumn(m),t));
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
		page.setCount(getCnt(paraMap));
		if(page.getCount()>0){
			page.setData(getBeanList(paraMap,t));
		}else{
			page.setData(new ArrayList<T>());
		}
		return page;
	}
}
