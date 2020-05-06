package com.dlz.plugin.netty.consts;

public class ConstantValue {

	 /** 
     * 消息的开头的信息标志 
     */  
	public static int HEAD_DATA = 0x76;  
	
    /** 
     * <pre> 
     * 协议开始的标准head_data，int类型，占据4个字节. 
     * 表示数据的长度contentLength，int类型，占据4个字节. 
     * </pre> 
     */  
    public static int BASE_LENGTH = 4 + 4; 
}

