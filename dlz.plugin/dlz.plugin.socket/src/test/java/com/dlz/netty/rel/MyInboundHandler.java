package com.dlz.netty.rel;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;

public class MyInboundHandler extends ChannelInboundHandlerAdapter {

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(MyInboundHandler.class);
	private Client client;

	public MyInboundHandler(Client client) {
		this.client = client;
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		final EventLoop eventLoop = ctx.channel().eventLoop();
		eventLoop.schedule(new Runnable() {
			@Override
			public void run() {
				client.createBootstrap(new Bootstrap(), eventLoop);
			}
		}, 1L, TimeUnit.SECONDS);
		super.channelInactive(ctx);
	}

	  @Override  
	  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
	      logger.debug("我是客户端：" +msg);  
	  }  
	    
	  @Override  
	  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
	      logger.debug("通道读取完毕！");  
	  }
	    
	  @Override  
	  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
	      if(null != cause) cause.printStackTrace();  
	      if(null != ctx) ctx.close();  
	  }
}