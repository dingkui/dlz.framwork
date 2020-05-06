package com.dlz.netty.reconn;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client  
 {  
   private EventLoopGroup loop = new NioEventLoopGroup();  
   public static void main( String[] args )  
   {  
     new Client().run();  
   }  
   public Bootstrap createBootstrap(Bootstrap bootstrap, EventLoopGroup eventLoop) {  
     if (bootstrap != null) {  
       final MyInboundHandler handler = new MyInboundHandler(this);  
       bootstrap.group(eventLoop);  
       bootstrap.channel(NioSocketChannel.class);  
       bootstrap.option(ChannelOption.SO_KEEPALIVE, true);  
       bootstrap.handler(new ChannelInitializer<SocketChannel>() {  
         @Override  
         protected void initChannel(SocketChannel socketChannel) throws Exception {  
           socketChannel.pipeline().addLast(handler);  
         }  
       });  
       bootstrap.remoteAddress("localhost", 8080);
       bootstrap.connect().addListener(new ConnectionListener(this)); 
     }  
     return bootstrap;  
   }  
   public void run() {  
     createBootstrap(new Bootstrap(), loop);
   }  
 }