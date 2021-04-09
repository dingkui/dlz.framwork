package com.dlz.test.framework.db;

import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.test.framework.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 单元测试支撑类<br>
 * @author dk
 */
public class DbUtilContitionTest extends BaseTest {
	@Autowired
	ICommService commService;

	@Test
	public void ConditionTest(){
//		ParaMap ump=new ParaMap("select 1 from dual");
//		ump.setPage(new Page(1, 1));
//		cs.getMap(ump);
		ParaMap ump2=new ParaMap("select t.* from PTN t where t.id=${key.comm.cntSql} and t.cc=${a} and c=${b} and ccc");
		ump2.addPara("a", "a${b}");
		ump2.addPara("b", "b${c}");
		ump2.addPara("_sql", "_sql${a}");
//		ump2.setPage(new Page<>(1, 2,"id","asc"));
		commService.getMap(ump2);
	}
	@Test
	public void ConditionTest2(){
		String sql = "key.sqlTest.sqlUtil";
		ParaMap ump2=new ParaMap(sql);
		ump2.addPara("a", "a1");
		ump2.addPara("b", "b1");
		ump2.addPara("d", "d1");
		ump2.addPara("c", "c1");
		ump2.addPara("_sql", "_sql${a}");
		ump2.setPage(new Page<>(1, 2,"id","asc"));
		commService.getMap(ump2);
		System.out.println(ump2.getSqlItem().getSqlRun());
	}
}
