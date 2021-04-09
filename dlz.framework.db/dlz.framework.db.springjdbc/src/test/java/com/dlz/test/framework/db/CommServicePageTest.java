package com.dlz.test.framework.db;

import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.test.framework.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CommServicePageTest extends BaseTest {
	@Autowired
	ICommService commService;

	@Test
	public void PageTest(){
//		ParaMap ump=new ParaMap("select 1 from dual");
//		ump.setPage(new Page(1, 1));
//		cs.getMap(ump);
		ParaMap ump2=new ParaMap("select t.* from PTN_GOODS_PRICE t where t.goods_id=310");
//		ump2.addPara("gid", 310);
		ump2.setPage(new Page<>(1, 2,"id","asc"));
		commService.getMap(ump2);
	}
}
