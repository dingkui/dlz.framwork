package com.dlz.test.db.mybatis;

import org.junit.Test;

import com.dlz.framework.db.dao.IDaoOperator;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.ssme.db.dao.UserMapper;
import com.dlz.framework.ssme.db.model.UserCriteria;
import com.dlz.framework.ssme.db.model.UserCriteria.GeneratedCriteria;

public class DaoOperatorTest {

	@Test
	public void test1(){
		IDaoOperator createMapper = MapperFactory.HD.createMapper(IDaoOperator.class);
		ParaMap paraMap=new ParaMap("select * from dual where 1=#{a}");
		paraMap.setSqlRun("select * from dual where 1=#{a}");
		paraMap.addPara("a", 1);
		createMapper.getList(paraMap);
	}
	
	@Test
	public void test2(){
		UserMapper createMapper = MapperFactory.HD.createMapper(UserMapper.class);
		UserCriteria userCriteria = new UserCriteria();
		Page<Object> page = new Page<>(12, 10);
//		page.setNeedFy(false);
//		page.setPageSize(20);
		userCriteria.setPage(page);
		userCriteria.createCriteria().andLoginIdEqualTo("111");
		createMapper.selectByExample(userCriteria);
	}
}
