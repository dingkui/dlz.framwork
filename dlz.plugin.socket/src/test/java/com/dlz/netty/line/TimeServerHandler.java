package com.dlz.netty.line;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

//TimeServerHandler 继承自ChannelHandlerAdapter，它用于对网络事件进行读写操作
public class TimeServerHandler extends ChannelInboundHandlerAdapter{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    
    private int counter;
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String)msg;
        System.out.println("The time server receive order : " + body
                + "; the counter is : "+ ++counter);
        //对请求消息进行判断，如果是QUERY TIME ORDER则创建应答消息
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? 
                new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        //通过ChannelHandlerContext的write方法异步发送应答消息给客户端
        ctx.writeAndFlush(resp);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //当发生异常时，关闭ChannelHandlerContext，释放和ChannelHandlerContext相关联的句柄等资源
        ctx.close();
    }
}