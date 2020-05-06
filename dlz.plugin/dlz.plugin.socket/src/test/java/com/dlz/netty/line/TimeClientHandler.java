package com.dlz.netty.line;
import java.util.logging.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter{

    
    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());
    
    private int counter;
    
    private byte[] req;
    
    
    public TimeClientHandler(){
        req = ("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
        
    }
    
    //当客户端和服务端TCP链路建立成功之后，Netty的NIO线程会调用channelActive方法，发送查询时间的指令给服务端
    //调用ChannelHandlerContext的writeAndFlush方法将请求消息发送给客户端
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }
    
    //当客户端返回应答消息，channelRead方法被调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //拿到的msg已经是解码成字符串之后的应答消息了。
        String body = (String)msg;
        System.out.println("Now is :" + body + " ; the counter is : " + ++counter);
    }
    
    //发生异常时，释放客户端资源
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
    }
    
}