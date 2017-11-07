package com.dlz.common.util;

import org.junit.Before;
import org.junit.Test;

import com.dlz.framework.db.DbInfo;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.modal.UpdateParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.db.service.impl.ColumnMapperToLower;
import com.dlz.framework.holder.SpringHolder;
 

/**
 * 单元测试支撑类<br>
 * @author dk
 */
public class DbUtilTest {
	ICommService cs;
	
	@Before
	public void setUp() throws Exception {
		SpringHolder.init();
		/**
		 * DB功能初始化
		 */
		new DbInfo(new ColumnMapperToLower());
		cs=SpringHolder.getBean(ICommService.class);
	}
	@Test
	public void UpdateParaMapTest(){
		UpdateParaMap ump=new UpdateParaMap("dh_room");
		ump.addEqCondition("equipment_id", 1);
		ump.addSetValue("room_id", 1);
		cs.excuteSql(ump);
	}
}
