package com.dlz.plugin.netty.codec.impl;

import com.dlz.plugin.netty.bean.RequestDto;
import com.dlz.plugin.netty.codec.ICoder;
import com.dlz.plugin.socket.util.StringCompress;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;

public class CompressCoder implements ICoder{


	@Override
	public Object decode(ByteBuf in) throws Exception {
		//标记开始读取位置  
        in.markReaderIndex();  
        //判断协议类型  
        byte infoType = in.readByte();  
        //in.readableBytes()即为剩下的字节数  
        byte[] info = new byte[in.readableBytes()];  
        in.readBytes(info);  
        String decompress = StringCompress.decompress(info);
		RequestDto requestInfo = new RequestDto(infoType,decompress);  
		return requestInfo;
	}

	@Override
	public void writeObj(ByteBufOutputStream writer,Object m) throws Exception {
		RequestDto msg=(RequestDto)m;
		writer.writeByte(msg.getType());
        byte[] info = null;
        if (msg != null &&msg.getInfo() != null && msg.getInfo() != "") {
        	info = StringCompress.compress(msg.getInfo());
        	writer.write(info);
        }
	}

}
