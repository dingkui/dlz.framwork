package com.dlz.common.util;

import org.junit.Before;
import org.junit.Test;

import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.UpdateParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.holder.SpringHolder;
 

/**
 * 单元测试支撑类<br>
 * @author dk
 */
public class DbTest {
	ICommService cs;
	
	@Before
	public void setUp() throws Exception {
		try {
			SpringHolder.init();
			
			cs=SpringHolder.getBean(ICommService.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	@Test
	public void UpdateParaMapTest(){
		UpdateParaMap ump=new UpdateParaMap("dh_room");
		ump.addEqCondition("equipment_id", 1);
		ump.addSetValue("room_id", 1);
		cs.excuteSql(ump);
	}
	
	@Test
	public void PageTest(){
//		ParaMap ump=new ParaMap("select 1 from dual");
//		ump.setPage(new Page(1, 1));
//		cs.getMap(ump);
		ParaMap ump2=new ParaMap("select t.* from PTN_GOODS_PRICE t where t.goods_id=310");
//		ump2.addPara("gid", 310);
		ump2.setPage(new Page<>(1, 2,"id","asc"));
		cs.getMap(ump2);
	}
	
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
		cs.getMap(ump2);
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
		cs.getMap(ump2);
		System.out.println(ump2.getSqlRun());
	}
	@Test
	public void conntest(){
		ParaMap ump2=new ParaMap("select #{a}||'xxx' from dual");
		ump2.addPara("a", "a222");
		
		System.out.println(cs.getStr(ump2));
	}
	@Test
	public void seq(){
		cs.getSeq("xxx");
	}
}
