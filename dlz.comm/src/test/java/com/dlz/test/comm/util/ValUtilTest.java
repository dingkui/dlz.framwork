package com.dlz.test.comm.util;

import com.dlz.comm.util.ValUtil;
import org.junit.Test;

public class ValUtilTest {
	@Test
	public void coverString(){
		Double aDouble = ValUtil.getDouble("3.35");
		System.out.println(aDouble.toString());
		System.out.println((ValUtil.getBigDecimal(aDouble).toString()));
	}


}
