package com.dlz.plugin.netty.codec.impl;

import com.dlz.plugin.netty.bean.RequestDto;
import com.dlz.plugin.netty.codec.ICoder;
import com.dlz.plugin.netty.consts.ConstantValue;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;

/**
 * 默认编码器
 * 传递数据编码成utf-8
 * @author dingkui
 *
 */
public class DefaultCoder implements ICoder{

	private static String cataName="UTF-8";
	@Override
	public Object decode(ByteBuf buffer) throws Exception {
		// 可读长度必须大于基本长度  
        if (buffer.readableBytes() >= ConstantValue.BASE_LENGTH) {  
            // 防止socket字节流攻击  
            // 防止，客户端传来的数据过大  
            // 因为，太大的数据，是不合理的  
            if (buffer.readableBytes() > 2048*1024) {  
                buffer.skipBytes(buffer.readableBytes());  
            }  
  
            // 记录包头开始的index  
            int beginReader;  
  
            while (true) {  
                // 获取包头开始的index  
                beginReader = buffer.readerIndex();  
                // 标记包头开始的index  
                buffer.markReaderIndex();  
                // 读到了协议的开始标志，结束while循环  
                if (buffer.readInt() == ConstantValue.HEAD_DATA) {  
                    break;  
                }  
  
                // 未读到包头，略过一个字节  
                // 每次略过，一个字节，去读取，包头信息的开始标记  
                buffer.resetReaderIndex();  
                buffer.readByte();  
  
                // 当略过，一个字节之后，  
                // 数据包的长度，又变得不满足  
                // 此时，应该结束。等待后面的数据到达  
                if (buffer.readableBytes() < ConstantValue.BASE_LENGTH) {  
                    return null;  
                }  
            }  
  
            // 消息的长度  
  
            int length = buffer.readInt();  
            // 判断请求数据包数据是否到齐  
            if (buffer.readableBytes() < length) {  
                // 还原读指针  
                buffer.readerIndex(beginReader);  
                return null;  
            }  
  
            // 读取data数据  
            byte[] data = new byte[length];  
            buffer.readBytes(data); 
            
            String infos = new String(data,cataName);
            return new RequestDto(Byte.parseByte(infos.substring(0, 1)),infos.substring(1));
        }
        return null;
	}

	@Override
	public void writeObj(ByteBufOutputStream writer,Object m) throws Exception {
		RequestDto msg=(RequestDto)m;
		// 写入消息SmartCar的具体内容  
		byte[] bytes = (msg.getType()+msg.getInfo()).getBytes(cataName);
		
        // 1.写入消息的开头的信息标志(int类型)  
		writer.writeInt(ConstantValue.HEAD_DATA);  
        // 2.写入消息的长度(int 类型)  
		writer.writeInt(bytes.length);  
        // 3.写入消息的内容(byte[]类型)  
		writer.write(bytes);
	}
}
