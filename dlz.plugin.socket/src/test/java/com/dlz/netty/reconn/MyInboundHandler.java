package com.dlz.netty.reconn;

import java.util.concurrent.TimeUnit;

import com.dlz.framework.logger.MyLogger;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyInboundHandler extends SimpleChannelInboundHandler {
	private static MyLogger logger = MyLogger.getLogger(MyInboundHandler.class);
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
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.debug("客户端：channelRead0" +msg);  
	}
	
      
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


    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("channel--active");
        String msg = "Are you oka啊飒飒大?";
        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
        encoded.writeBytes(msg.getBytes());
        ctx.write(encoded);
        Thread.sleep(10000);
        ctx.flush();
    }


    // 接收server端的消息，并打印出来
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.debug("客户端：channelRead");
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        logger.debug("Server said:" + new String(result1));
        result.release();
    }
}