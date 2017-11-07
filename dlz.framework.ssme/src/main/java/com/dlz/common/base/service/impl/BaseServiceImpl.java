package com.dlz.common.base.service.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dlz.common.base.dao.BaseMapper;
import com.dlz.common.base.service.BaseService;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.util.system.Reflections;
import com.google.common.collect.Lists;

public abstract class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {
	private static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
	protected BaseMapper<T, PK> mapper;
	
	/**
	 * 
	 */
	@Override
	public int countByExample(Object example)throws Exception {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(Object example)throws Exception {
		return mapper.deleteByExample(example);
	}


	@Override
	public int deleteByPrimaryKey(PK pk)throws Exception {
		return mapper.deleteByPrimaryKey(pk);
	}
	
	@Override
	public int insert(T record)throws Exception {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(T record)throws Exception {
		return mapper.insertSelective(record);
	}

	@Override
	public List<T> selectByExample(Object example)throws Exception {
		return mapper.selectByExample(example);
	}
	
	@Override
	public List<T> selectByExampleWithBLOBs(Object example)throws Exception {
		return mapper.selectByExampleWithBLOBs(example);
	}

	
	@Override
	public T selectBeanByExample(Object example)throws Exception {
		List<T> list= mapper.selectByExample(example);
		if(list.isEmpty()){
			return null;
		}else if(list.size()>1){
			throw new RuntimeException("查询结果为多条："+example);
		}else{
			return list.get(0);
		}
	}

	@Override
	public T selectByPrimaryKey(PK pk)throws Exception {
		return mapper.selectByPrimaryKey(pk);
	}

	@Override
	public int updateByExampleSelective(T record, Object example)throws Exception {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(T record, Object example)throws Exception {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(T record)throws Exception {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record)throws Exception {
		return mapper.updateByPrimaryKey(record);
	}
	
	@Override
	public int updateByPrimaryKeyWithBLOBs(T record)throws Exception {
		return mapper.updateByPrimaryKeyWithBLOBs(record);
	}
	
	@Override
	public int updateByExampleWithBLOBs(T record,Object example)throws Exception {
		return mapper.updateByExampleWithBLOBs(record,example);
	}
	/**
	 * 分页查询所有
	 */
	@Override
	public Page pageByExample(Object example)throws Exception {
		if(example == null){
			return new Page(0, Lists.newArrayListWithExpectedSize(0));
		}else{
			Page page = null;
			try{
				Method m1=Reflections.getAccessibleMethodByName(example, "getPage");
				page = (Page) m1.invoke(example);
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
			if(page==null){
				return new Page(mapper.countByExample(example), mapper.selectByExample(example));
			}else{
				return getPageByExample(example, page);
			}
		}
	}

	@Override
	public Page pageByExampleWithBlobs(Object example) throws Exception {
		if(example == null){
			return new Page(0, Lists.newArrayListWithExpectedSize(0));
		}else{
			Page page = null;
			try{
				Method m1=Reflections.getAccessibleMethodByName(example, "getPage");
				page = (Page) m1.invoke(example);
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
			if(page==null){
				return new Page(mapper.countByExample(example), mapper.selectByExampleWithBLOBs(example));
			}else{
				page.setCount(mapper.countByExample(example));
				page.setData(mapper.selectByExampleWithBLOBs(example));
				return page;
			}
		}
	}

	@Override
	public Page getPageByExample(Object example,Page page)throws Exception {
		if(example != null){
			page.setCount(mapper.countByExample(example));
			page.setData(mapper.selectByExample(example)) ;
			return page;
		}else{
			return null;
		}
	}
}
