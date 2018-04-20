package com.dlz.framework.rpc;

import java.util.HashMap;
import java.util.Map;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.bean.JSONResult;
import com.dlz.framework.rpc.handler.IClientHandler;

public class ARpcClient {
	private String server;
	private String id;
	private String pwd;
	private String token;
	private IClientHandler client;
	
	//可以执行的接口
	private Map<String,Object> clientMethod=new HashMap<String,Object>();
	public ARpcClient(String server,String id,String pwd,IClientHandler client){
		this.server=server;
		this.client=client;
		this.id=id;
		this.pwd=pwd;
	}
	private boolean auth(){
		JSONMap para = new JSONMap();
		para.put("id", id);
		para.put("pwd", pwd);
		JSONResult auth = client.auth(server,id, pwd);
		if(auth.isError()){
			token=null;
			clientMethod.clear();
			return false;
		}
		token=auth.getStr("token");
		clientMethod.putAll(auth.getData());
		return true;
	}
	
	public JSONResult done(String method,JSONMap para){
		if(token==null && !auth()){
			return JSONResult.createResult().addErr(0,"无远程调用权限");
		}
		if(!clientMethod.containsKey(method)){
			return JSONResult.createResult().addErr(-99,"无接口调用权限");
		}
		JSONResult done = client.done(server,token,"auth", para);
		if(done.isError() && done.getFlag()==0 && auth()){
			return client.done(server,token,"auth", para);
		}
		return done;
	}
}
