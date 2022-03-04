package com.dlz.plugin.netty.base.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder { 
	private ICoder coder;

	public MessageDecoder(ICoder coder){
		this.coder=coder;
	}
	
    @Override  
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    	Object decode = coder.decode(in);
    	if(decode!=null){
    		out.add(decode);
    	}
    }  
}  