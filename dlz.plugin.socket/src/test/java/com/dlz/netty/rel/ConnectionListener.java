package com.dlz.netty.rel;

import java.util.concurrent.TimeUnit;

import com.dlz.framework.logger.MyLogger;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;
import io.netty.channel.socket.SocketChannel;

public class ConnectionListener implements ChannelFutureListener {  
	private static MyLogger logger = MyLogger.getLogger(ConnectionListener.class);
  private Client client;  
  private SocketChannel socketChannel;
  public ConnectionListener(Client client) {  
    this.client = client;  
  }  
  @Override  
  public void operationComplete(ChannelFuture channelFuture) throws Exception {  
    if (!channelFuture.isSuccess()) {  
      logger.debug("Reconnect");  
      final EventLoop loop = channelFuture.channel().eventLoop();  
      loop.schedule(new Runnable() {  
        @Override  
        public void run() {  
          client.createBootstrap(new Bootstrap(), loop);  
        }  
      }, 1L, TimeUnit.SECONDS);  
    }else{
    	socketChannel=(SocketChannel)channelFuture.channel();
    	try{
	    	channelFuture.sync();
	    	socketChannel.closeFuture().sync();
    	}catch (Exception e) {
    		socketChannel.eventLoop().shutdownGracefully();
		}
    }
  } 
}