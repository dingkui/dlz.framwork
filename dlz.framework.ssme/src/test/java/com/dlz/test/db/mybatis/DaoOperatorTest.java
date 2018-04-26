package com.dlz.test.db.mybatis;

import org.junit.Test;

import com.dlz.framework.db.dao.IDaoOperator;
import com.dlz.framework.db.modal.ParaMap;

public class DaoOperatorTest {

	@Test
	public void test1(){
		IDaoOperator createMapper = MapperFactory.HD.createMapper(IDaoOperator.class);
		ParaMap paraMap=new ParaMap("");
		paraMap.setSqlRun("select * from dual where 1=?");
		paraMap.addPara("a", 1);
		createMapper.getList(paraMap);
	}
}
