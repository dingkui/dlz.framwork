package com.dlz.test.framework.db;

import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.UpdateParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.test.framework.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 单元测试支撑类<br>
 * @author dk
 */
public class UpdateParaTest extends BaseTest {
	@Autowired
	ICommService commService;

	@Test
	public void UpdateParaMapTest(){
		UpdateParaMap ump=new UpdateParaMap("dh_room");
		ump.addEqCondition("equipment_id", 1);
		ump.addSetValue("room_id", 1);
		commService.excuteSql(ump);
	}


}
