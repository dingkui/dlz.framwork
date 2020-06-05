package com.dlz.framework.db.service.impl;

import com.dlz.comm.exception.DbException;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.convertor.ConvertUtil;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.modal.items.SqlItem;
import com.dlz.framework.db.service.ICommService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
			int cnt = ValUtil.getInt(ConvertUtil.getFistClumn(daoOperator.getList(paraMap).get(0)));
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
	@Override
	public List<ResultMap> getMapList(BaseParaMap paraMap) {
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
	

}
