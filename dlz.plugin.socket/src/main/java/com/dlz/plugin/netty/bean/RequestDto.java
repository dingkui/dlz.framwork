package com.dlz.plugin.netty.bean;

public class RequestDto {
    
    public RequestDto(byte type,String info){
    	this.type=type;
    	this.info = info;
    }
    
	private String info;

	// case 1://异步请求
	// case 2://同步请求
	// case 3://同步返回
	// case 4://服务器端广播返回
	private byte type;

	public String getInfo() {
		return info;
	}

	public byte getType() {
		return type;
	}
}