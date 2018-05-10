package com.dlz.plugin.netty.codec;

import com.dlz.plugin.netty.bean.RequestDto;
import com.dlz.plugin.socket.util.StringCompress;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder  extends MessageToByteEncoder<RequestDto> {
    @Override
    protected void encode(ChannelHandlerContext ctx, RequestDto msg, ByteBuf out) throws Exception {
        ByteBufOutputStream writer = new ByteBufOutputStream(out);
        writer.writeByte(msg.getType());
        byte[] info = null;
        if (msg != null &&msg.getInfo() != null && msg.getInfo() != "") {
//        	info = msg.getInfo().getBytes("utf-8");
        	info = StringCompress.compress(msg.getInfo());
        	writer.write(info);
        }
    }

}