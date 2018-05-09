package com.dlz.plugin.netty.handler;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.netty.listener.INettyListener;

import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends BaseHandler { 
	public ClientHandler(INettyListener lisner) {
		super(lisner);
	}
	private static MyLogger logger = MyLogger.getLogger(ClientHandler.class);
    @Override  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        logger.debug("客户端通道读取完毕！channelReadComplete");  
    }  
      
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        if(null != cause) cause.printStackTrace();  
        if(null != ctx) ctx.close();  
    }
    
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.debug("channel--register");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        logger.debug("channel--unregistered");
    }
	  
} 