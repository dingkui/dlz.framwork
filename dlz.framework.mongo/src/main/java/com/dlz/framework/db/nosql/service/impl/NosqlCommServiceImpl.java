package com.dlz.framework.db.nosql.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.db.DbCoverUtil;
import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.nosql.BsonUtil;
import com.dlz.framework.db.nosql.dao.IDaoOperator;
import com.dlz.framework.db.nosql.modal.Delete;
import com.dlz.framework.db.nosql.modal.Find;
import com.dlz.framework.db.nosql.modal.Insert;
import com.dlz.framework.db.nosql.modal.Update;
import com.dlz.framework.db.nosql.service.INosqlService;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.ValUtil;

@SuppressWarnings("unchecked")
public class NosqlCommServiceImpl implements INosqlService {
	private static MyLogger logger = MyLogger.getLogger(NosqlCommServiceImpl.class);
	@Autowired
	private IDaoOperator daoOperator;
	
	@Override
	public int insert(Insert insert) {
		BsonUtil.dealParm(insert);
		logger.info("insert:name="+ insert.getName() + " data=" + insert.getDataBson());
		try {
			return ValUtil.getInt(daoOperator.insert(insert));
		} catch (Exception e) {
			throw new DbException("insert:name="+ insert.getName()+ " data=" + insert.getDataBson() , e);
		}
	}

	@Override
	public int update(Update paraMap) {
		BsonUtil.dealParm(paraMap);
		logger.info("update:key="+paraMap.getKey()+ " name="+ paraMap.getName() + " data=" + paraMap.getDataBson()+ " filter=" + paraMap.getFilterBson());
		try {
			return ValUtil.getInt(daoOperator.update(paraMap));
		} catch (Exception e) {
			throw new DbException("update:key="+paraMap.getKey()+ " name:"+ paraMap.getName() + "data=" + paraMap.getDataBson()+ " filter=" + paraMap.getFilterBson() + " para=" + paraMap.getPara(), e);
		}
	}

	@Override
	public int del(Delete paraMap) {
		BsonUtil.dealParm(paraMap);
		logger.info("del:key="+paraMap.getKey() + " name="+ paraMap.getName()+ " filter=" + paraMap.getFilterBson());
		try {
			return ValUtil.getInt(daoOperator.del(paraMap));
		} catch (Exception e) {
			throw new DbException(paraMap.getKey()+ "name:"+ paraMap.getName() + ":" + paraMap.getFilterBson() + " para:" + paraMap.getPara(), e);
		}
	}
	
	@Override
	public int getCnt(Find paraMap) {
		paraMap = BsonUtil.dealParm(paraMap);
		logger.info("getCnt:"+paraMap.getKey()+ "name:"+ paraMap.getName() + "[" + paraMap.getFilterBson()+ "]para:[" + paraMap.getPara()+"]");
		try {
			return ValUtil.getInt(daoOperator.getCnt(paraMap));
		} catch (Exception e) {
			throw new DbException(paraMap.getKey() + "name:"+ paraMap.getName()+ ":" + paraMap.getFilterBson() + " para:" + paraMap.getPara(), e);
		}
	}
	
	private List<ResultMap> getList(Find paraMap) {
		paraMap = BsonUtil.dealParm(paraMap);
		logger.info("getList:"+paraMap.getKey() + "name:"+ paraMap.getName() + "[" + paraMap.getFilterBson()+ "]para:[" + paraMap.getPara()+"]");
		try {
			return daoOperator.getList(paraMap);
		} catch (Exception e) {
			throw new DbException(e.getMessage()+" "+paraMap.getKey()+ "name:"+ paraMap.getName() + ":" + paraMap.getFilterBson() + " para:" + paraMap.getPara(), e);
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
	public <T> T getBean(Find paraMap,Class<T> t){
		try {
			return JacksonUtil.coverObj(getMap(paraMap), t);
		} catch (Exception e) {
			throw new DbException(e.getMessage(),e);
		}
	}
	@Override
	public <T> List<T> getBeanList(Find paraMap,Class<T> t){
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
	public Page<ResultMap> getPage(Find paraMap){
		return getPage(paraMap, ResultMap.class);
	}
	
	@Override
	public ResultMap getMap(Find paraMap){
		try{
			return getOne(getList(paraMap));
		}catch (Exception e) {
			throw new DbException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<ResultMap> getMapList(Find paraMap){
		return getList(paraMap);
	}
	
	@Override
	public Object getColum(Find paraMap){
		return DbCoverUtil.getFistClumn(getOne(getList(paraMap)));
	}
	@Override
	public String getStr(Find paraMap){
		return ValUtil.getStr(getColum( paraMap));
	}
	@Override
	public Long getLong(Find paraMap){
		return ValUtil.getLong(getColum( paraMap));
	}
	@Override
	public Integer getInt(Find paraMap){
		return ValUtil.getInt(getColum( paraMap));
	}
	@Override
	public Float getFloat(Find paraMap){
		return ValUtil.getFloat(getColum(paraMap));
	}
	@Override
	public BigDecimal getBigDecimal(Find paraMap){
		return ValUtil.getBigDecimal(getColum( paraMap));
	}
	@Override
	public List<Object> getColumList(Find paraMap){
		List<ResultMap> r = getList(paraMap);
		List<Object> l = new ArrayList<Object>();
		for(ResultMap m : r){
			l.add(DbCoverUtil.getFistClumn(m));
		}
		return l;
	}
	public <T> List<T> getColumList(Find paraMap, Class<T> t){
		List<ResultMap> r = getList(paraMap);
		List<T> l = new ArrayList<T>();
		for(ResultMap m : r){
			l.add(ValUtil.getObj(DbCoverUtil.getFistClumn(m), t));
		}
		return l;
	}
	@Override
	public List<String> getStrList(Find paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<String> l = new ArrayList<String>();
		for(ResultMap m : r){
			l.add(ValUtil.getStr(DbCoverUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public List<BigDecimal> getBigDecimalList(Find paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<BigDecimal> l = new ArrayList<BigDecimal>();
		for(ResultMap m : r){
			l.add(ValUtil.getBigDecimal(DbCoverUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public List<Float> getFloatList(Find paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<Float> l = new ArrayList<Float>();
		for(ResultMap m : r){
			l.add(ValUtil.getFloat(DbCoverUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public List<Integer> getIntList(Find paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<Integer> l = new ArrayList<Integer>();
		for(ResultMap m : r){
			l.add(ValUtil.getInt(DbCoverUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public List<Long> getLongList(Find paraMap) {
		List<ResultMap> r = getList(paraMap);
		List<Long> l = new ArrayList<Long>();
		for(ResultMap m : r){
			l.add(ValUtil.getLong(DbCoverUtil.getFistClumn(m)));
		}
		return l;
	}
	@Override
	public <T> Page<T> getPage(Find paraMap, Class<T> t) {
		Page<T> page= paraMap.getPage();
		if(page==null){
			page=new Page<T>();
		}
		paraMap.setPage(page);
		page.setCount(getCnt(paraMap));
		if(page.getCount()>0){
			if(t==ResultMap.class){
				page.setData((List<T>) getMapList(paraMap));
			}else{
				page.setData(getBeanList(paraMap,t));
			}
		}else{
			page.setData(new ArrayList<T>());
		}
		return page;
	}
}
