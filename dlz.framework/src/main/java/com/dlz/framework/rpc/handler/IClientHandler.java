package com.dlz.framework.rpc.handler;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;

public interface IClientHandler {
	default void doNothingL(){new java.util.ArrayList<>().forEach(a->{});}
	public JSONResult done(String server,String method,String string, JSONMap para);
	public JSONResult auth(String server,String id,String pwd);
}
