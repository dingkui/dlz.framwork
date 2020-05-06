package com.dlz.plugin.netty.codec.impl;

import com.dlz.plugin.netty.bean.RequestDto;
import com.dlz.plugin.netty.codec.ICoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;

/**
 * 默认编码器
 * 传递数据编码成utf-8
 * @author dingkui
 *
 */
public class SimpleCoder implements ICoder{

	private static String cataName="UTF-8";
	@Override
	public Object decode(ByteBuf in) throws Exception {
		//标记开始读取位置  
        in.markReaderIndex();  
        //判断协议类型  
        byte infoType = in.readByte();  
        //in.readableBytes()即为剩下的字节数  
        byte[] info = new byte[in.readableBytes()];  
        in.readBytes(info);  
        String decompress = new String(info, cataName);
		return new RequestDto(infoType,decompress);
	}

	@Override
	public void writeObj(ByteBufOutputStream writer,Object m) throws Exception {
		RequestDto msg=(RequestDto)m;
		writer.writeByte(msg.getType());
        byte[] info = null;
        if (msg != null &&msg.getInfo() != null && msg.getInfo() != "") {
        	info = msg.getInfo().getBytes(cataName);
        	writer.write(info);
        }
	}
}
