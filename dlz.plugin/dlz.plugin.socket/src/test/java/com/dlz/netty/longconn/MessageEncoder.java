package com.dlz.netty.longconn;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder  extends MessageToByteEncoder<RequestInfo> {  
  
  
    @SuppressWarnings("resource")
	@Override  
    protected void encode(ChannelHandlerContext ctx, RequestInfo msg, ByteBuf out) throws Exception {  
  
        ByteBufOutputStream writer = new ByteBufOutputStream(out);  
          
        writer.writeByte(msg.getType());  
        byte[] info = null;  
  
        if (msg != null &&msg.getInfo() != null && msg.getInfo() != "") {  
            info = msg.getInfo().getBytes("utf-8");  
            writer.write(info);  
        }  
    }  
  
} 