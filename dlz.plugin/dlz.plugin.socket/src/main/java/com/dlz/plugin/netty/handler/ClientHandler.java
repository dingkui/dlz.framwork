package com.dlz.plugin.netty.handler;

import java.io.IOException;

import org.slf4j.Logger;
import com.dlz.plugin.netty.NettyClient;
import com.dlz.plugin.netty.bean.RequestDto;
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
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(ClientHandler.class);

      
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//    	if(cause!=null) {
//    		logger.error(cause.getMessage());
//    		logger.error(ctx.channel().remoteAddress().toString());
//    	}
        if(null != ctx) ctx.close(); 
        if(null != client){
        	if(cause!=null) {
        		client.shutdownAndRetry(ctx.channel().remoteAddress().toString()+ cause.getMessage());
        		if(!(cause instanceof IOException)){
        			logger.error(cause.getMessage(),cause);
        		}
        	}else{
        		client.shutdownAndRetry("异常重连");
        	}
        }else{
        	if(!(cause instanceof IOException)){
    			logger.error(cause.getMessage(),cause);
    		}else{
    			logger.error(cause.getMessage());
        		logger.error(ctx.channel().remoteAddress().toString());
    		}
        }
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
    
    
	/**
	 * 异步客户端处理服务器websocket相应的核心方法，这里接收了服务端发来的信息
	 */
	@Override
	public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
		RequestDto requestInfo = (RequestDto) msg;
		String info = requestInfo.getInfo();
		int bt = requestInfo.getType();
		switch (bt) {
		case 5:// 异步返回
		case 4:// 服务器端广播返回
			new Thread(new Runnable() {
				@Override
				public void run() {
					lisner.deal(info);
				}
			}).start();
			break;
		default:
			logger.error("异步客户端接收到无效信息：{0}", requestInfo);
			break;
		}
	}
	  
} 