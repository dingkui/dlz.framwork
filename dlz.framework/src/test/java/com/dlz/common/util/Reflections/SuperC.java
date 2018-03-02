package com.dlz.common.util.Reflections;

import java.util.Map;

import com.dlz.framework.bean.JSONMap;

public class SuperC {

	public boolean a(JSONMap a){
		System.out.println("SuperC.a");
		return true;
	}
	
	private boolean b(Map a){
		System.out.println("SuperC.b");
		System.out.println(a.toString());
		return true;
	}
}
