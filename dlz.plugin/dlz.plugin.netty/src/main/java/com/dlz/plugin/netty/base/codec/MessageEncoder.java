package com.dlz.plugin.netty.base.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder<T>  extends MessageToByteEncoder<T> {

	private ICoder<?,T> coder;
	public MessageEncoder(ICoder<?,T> coder){
		this.coder=coder;
	}
    @Override
    protected void encode(ChannelHandlerContext ctx, T msg, ByteBuf out) throws Exception {
        coder.writeObj(new ByteBufOutputStream(out), msg);
    }
}