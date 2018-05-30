package com.dlz.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.service.ICommService;

@Component
public class Test1 {
	@Autowired
	ICommService service;
	
	public void run(){
		System.out.println("run Without para:"+service);
	}
	
	public void run(JSONMap m){
		System.out.println("run With para:"+m+" "+service);
	}
	
	public void deal(){
		System.out.println("deal Without para:"+service);
	}
	
	public void deal(JSONMap m){
		System.out.println("deal With para:"+m+" "+service);
	}
}
