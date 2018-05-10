package com.dlz.plugin.netty.handler;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.netty.NettyClient;
import com.dlz.plugin.socket.interfaces.ISocketListener;

import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends BaseHandler { 
	NettyClient client;
	public ClientHandler(ISocketListener lisner,NettyClient client) {
		super(lisner);
		this.client=client;
	}
	public ClientHandler(ISocketListener lisner) {
		super(lisner);
	}
	private static MyLogger logger = MyLogger.getLogger(ClientHandler.class);

      
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	if(cause!=null) {
    		logger.error(cause.getMessage());
    		logger.error(ctx.channel().remoteAddress());
    	}
        if(null != ctx) ctx.close(); 
        if(null != client) client.shutdownAndRetry();
    }
//  @Override  
//  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
//      logger.debug("客户端通道读取完毕！channelReadComplete");  
//  }     
//    @Override
//    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        logger.debug("channel--register");
//    }
//
//    @Override
//    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        logger.debug("channel--unregistered");
//    }
	  
} 