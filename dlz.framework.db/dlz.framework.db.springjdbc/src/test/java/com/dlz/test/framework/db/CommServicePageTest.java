package com.dlz.test.framework.db;

import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.test.framework.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommServicePageTest extends BaseTest {
	@Autowired
	ICommService commService;

	@Test
	public void PageTest(){
		ParaMap pm=new ParaMap("select t.* from Goods t where t.goods_id=310");
		pm.setPage(new Page<>(1, 2,"id","asc"));
		Page<ResultMap> page = commService.getPage(pm);
		int count = page.getCount();//总条数
		List<ResultMap> data = page.getData();//查询结果
	}

	@Test
	public void PageTest2(){
		ParaMap ump2=new ParaMap("select t.* from PTN_GOODS_PRICE t where t.goods_id=#{goodsId}");
		ump2.setPage(new Page<>(1, 2,"id","asc"));
		ump2.addPara("goodsId",123);
		commService.getMap(ump2);
	}
}
