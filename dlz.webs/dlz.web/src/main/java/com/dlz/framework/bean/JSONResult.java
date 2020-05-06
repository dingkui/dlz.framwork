package com.dlz.framework.bean;

import com.dlz.comm.json.JSONMap;

/**
 * JSONResult
 * @author dk 2017-06-15
 *
 */
public class JSONResult extends JSONMap {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2143967262379436967L;
	private static final String FLAG = "flag";
	private static final String MSG = "msg";
	private static final String DATA = "data";
	private static final int defualtFlag=1;
	public JSONResult(){
		super();
	}
	public JSONResult(Object o){
		super(o);
	}
	public static JSONResult createResult(){
		JSONResult r=new JSONResult();
		r.put(FLAG,defualtFlag);
		return r;
	}
	public static JSONResult createErrResult(String msg){
		JSONResult r=new JSONResult();
		r.addErr(msg);
		return r;
	}
	public static JSONResult createResult(Object o){
		JSONResult r=new JSONResult(o);
		r.put(FLAG,defualtFlag);
		return r;
	}
	
	public boolean isError(){
		return getFlag()<defualtFlag;
	}
	public JSONResult addErr(String msg) {
		addErr(-1,msg);
		return this;
	}
	public JSONResult addErr(int flag,String msg) {
		put(FLAG,flag);
		if (msg == null) {
			return this;
		}
		addMsg(msg);
		return this;
	}
	public JSONResult addMsg(String msg) {
		put(MSG,msg);
		return this;
	}
	public JSONResult addMsg(int flag,String msg) {
		put(MSG,msg);
		put(FLAG,flag);
		return this;
	}
	
	public String getMsg() {
		return getStr(MSG);
	}
	public JSONResult addData(Object data) {
		put(DATA,data);
		return this;
	}
	public int getFlag() {
		return getInt(FLAG);
	}
	public JSONMap getData() {
		return getMap(DATA);
	}
	public <T> T getData(Class<T> classs) {
		return getObj(DATA,classs);
	}

}