package com.dlz.plugin.socket.deal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * 服务器端业务处理测试1
 * @author dk
 */
public class SetTest{
	@Test
	public void testSet(){
		Set<A> a=new HashSet<A>();
		a.add(new A());
		a.add(new A());
		a.add(new A());  
		for(A d : a){
			d=null;
		}
		System.out.println(a);
	}
	private class A{
		;
	}
	private class link{
		List<String> a=new ArrayList<String>();
	}
}
