package com.dlz.netty.longconn;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MessageDecoder extends ByteToMessageDecoder {  
  
     
    @Override  
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {  
        //标记开始读取位置  
        in.markReaderIndex();  
        //判断协议类型  
        byte infoType = in.readByte();  
        RequestInfo requestInfo = new RequestInfo();  
        System.out.println(infoType);  
        requestInfo.setType(infoType);  
        //in.readableBytes()即为剩下的字节数  
        byte[] info = new byte[in.readableBytes()];  
        in.readBytes(info);  
        requestInfo.setInfo(new String(info, "utf-8"));  
        System.out.println(info.length + " : " + requestInfo.getInfo());  
        //最后把你想要交由ServerHandler的数据添加进去，就可以了  
        out.add(requestInfo);  
    }  
}  