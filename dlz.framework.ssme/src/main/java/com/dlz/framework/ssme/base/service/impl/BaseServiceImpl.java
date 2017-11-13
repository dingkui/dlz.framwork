package com.dlz.framework.ssme.base.service.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.ssme.base.dao.BaseMapper;
import com.dlz.framework.ssme.base.service.BaseService;
import com.dlz.framework.util.system.Reflections;
import com.google.common.collect.Lists;

public abstract class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {
	private static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
	protected BaseMapper<T, PK> mapper;
	@Autowired
	protected ICommService commService;
	
	@SuppressWarnings("unchecked")
	private Class<T> getBeanClass(){
		return (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	@Override
   public List<T> getBeanList(BaseParaMap pm) throws Exception{
	   return commService.getBeanList(pm, getBeanClass());
   }
   @Override
   public T getBean(BaseParaMap pm) throws Exception{
	   return commService.getBean(pm, getBeanClass());
   }
   @Override
   public Page<T> getPage(BaseParaMap pm) throws Exception{
	   return commService.getPage(pm,getBeanClass());
   }
   @Override
   public int excute(BaseParaMap pm) throws Exception{
	   return commService.excuteSql(pm);
   }
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
	public Page<T> pageByExample(Object example)throws Exception {
		if(example == null){
			return new Page<T>(0, Lists.newArrayListWithExpectedSize(0));
		}else{
			Page<T> page = null;
			try{
				Method m1=Reflections.getAccessibleMethodByName(example, "getPage");
				page = (Page<T>) m1.invoke(example);
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
			if(page==null){
				return new Page<T>(mapper.countByExample(example), mapper.selectByExample(example));
			}else{
				return getPageByExample(example, page);
			}
		}
	}

	@Override
	public Page<T> pageByExampleWithBlobs(Object example) throws Exception {
		if(example == null){
			return new Page<T>(0, Lists.newArrayListWithExpectedSize(0));
		}else{
			Page<T> page = null;
			try{
				Method m1=Reflections.getAccessibleMethodByName(example, "getPage");
				page = (Page<T>) m1.invoke(example);
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
			if(page==null){
				return new Page<T>(mapper.countByExample(example), mapper.selectByExampleWithBLOBs(example));
			}else{
				page.setCount(mapper.countByExample(example));
				page.setData(mapper.selectByExampleWithBLOBs(example));
				return page;
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<T> getPageByExample(Object example,Page page)throws Exception {
		if(example != null){
			page.setCount(mapper.countByExample(example));
			page.setData(mapper.selectByExample(example)) ;
			return page;
		}else{
			return null;
		}
	}
}
