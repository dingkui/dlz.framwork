package com.dlz.common.util;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.nosql.modal.Delete;
import com.dlz.framework.db.nosql.modal.Find;
import com.dlz.framework.db.nosql.modal.Insert;
import com.dlz.framework.db.nosql.modal.Update;
import com.dlz.framework.db.nosql.service.INosqlService;
import com.dlz.framework.holder.SpringHolder;
 

/**
 * 单元测试支撑类<br>
 * @author dk
 */
public class DbUtilTest {
	INosqlService cs;
	
	@Before
	public void setUp() throws Exception {
		SpringHolder.init("ApplicationMongo");
		cs=SpringHolder.getBean(INosqlService.class);
	}
	@Test
	public void FindTest1(){
		Find ump=new Find("find.article");
		Page setPageSize = ump.createPage().setPageSize(100);
		setPageSize.setSortField("age");
		setPageSize.setSortOrder("asc");
		ump.addFilterPara("key", "查询");
		ump.addFilterPara("id", 7941);
		long t=new Date().getTime();
		List<ResultMap> mapList = cs.getMapList(ump);
		System.out.println(new Date().getTime()-t);
		System.out.println(mapList);
	}
	@Test
	public void FindTest(){
		Find ump=new Find("find.comm.searchTable");
		Page setPageSize = ump.createPage().setPageSize(100);
		setPageSize.setSortField("age");
		setPageSize.setSortOrder("asc");
		ump.addFilterPara("min", 12);
		List<ResultMap> mapList = cs.getMapList(ump);
		System.out.println(mapList);
	}
	
	@Test
	public void InsertTest(){
		Delete del=new Delete("delete.comm.deleteTest");
//		del.addFilterPara("min", 12);
		cs.del(del);
		Insert ump=new Insert("insert.comm.insertTest");
		ump.addData(new JSONMap().add("age", 12).add("name", "asd1").add("time", new Date()));
		ump.addData(new JSONMap().add("age", 11).add("name", "asd2"));
		ump.addData(new JSONMap().add("age", 13).add("name", "asd3"));
		ump.addData(new JSONMap().add("age", 14).add("name", "asd4"));
		ump.addData(new JSONMap().add("age", 15).add("name", "asd5"));
		cs.insert(ump);
	}
	
	@Test
	public void UpdateTest(){
		Update del=new Update("update.comm.updateTest");
		del.addFilterPara("min", 12);
		del.addData("new","999");
		cs.update(del);
	}
	
	@Test
	public void DelTest(){
		Delete ump=new Delete("delete.comm.deleteTest");
		ump.addFilterPara("min", 14);
		cs.del(ump);
	}
}
