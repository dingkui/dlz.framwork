package com.dlz.netty.rel;

import java.util.Iterator;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.netty.conf.NettyConfig;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
	private static MyLogger logger = MyLogger.getLogger(ServerHandler.class);     
    
    /** 
     * 客户端与服务端创建连接的时候调用 
     */  
    @Override  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
        logger.debug("客户端与服务端连接开始...");  
        NettyConfig.group.add(ctx.channel());  
    }  
  
    /** 
     * 客户端与服务端断开连接时调用 
     */  
    @Override  
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {  
        logger.debug("客户端与服务端连接关闭...");  
        NettyConfig.group.remove(ctx.channel());  
    }  
  
    /** 
     * 服务端接收客户端发送过来的数据结束之后调用 
     */  
    @Override  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        ctx.flush();  
        logger.debug("信息接收完毕...");  
    }  
  
    /** 
     * 工程出现异常的时候调用 
     */  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        cause.printStackTrace();  
        ctx.close();  
    }  
  
    /** 
     * 服务端处理客户端websocket请求的核心方法，这里接收了客户端发来的信息 
     */  
    @Override  
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object info) throws Exception {  
        logger.debug("我是服务端，我接受到了：" + ((String)info));  
        //服务端使用这个就能向 每个连接上来的客户端群发消息  
        NettyConfig.group.writeAndFlush(info);  
        Iterator<Channel> iterator = NettyConfig.group.iterator();  
        while(iterator.hasNext()){  
            //打印出所有客户端的远程地址  
            logger.debug((iterator.next()).remoteAddress());  
        }  
//      //单独回复客户端信息  
//      channelHandlerContext.writeAndFlush(info);  
    }  
}  