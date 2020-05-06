package com.dlz.netty.rel;

import java.util.Scanner;

import org.slf4j.Logger;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(Client.class);
	private EventLoopGroup loop = new NioEventLoopGroup();

	public static void main(String[] args) {
		Client client = new Client();
		client.run();
		Scanner input = new Scanner(System.in);
        while (true){  
            String nextLine = input.nextLine();
            if("quit".equals(nextLine)){
            	break;
            }
			client.sendMessage(nextLine);    
        }  
	}
	SocketChannel socketChannel;

	public Bootstrap createBootstrap(Bootstrap bootstrap, EventLoopGroup eventLoop) {
		if (bootstrap != null) {
			final MyInboundHandler handler = new MyInboundHandler(this);
			bootstrap.group(eventLoop).remoteAddress("localhost", 8081);
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.option(ChannelOption.TCP_NODELAY, true);

			bootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					socketChannel.pipeline().addLast(handler);
				}
			});
			try {
				ChannelFuture channelFuture = bootstrap.connect().addListener(new ConnectionListener(this));
				if (channelFuture.isSuccess()) {
					socketChannel = (SocketChannel) channelFuture.channel();
					channelFuture.sync();
					socketChannel.closeFuture().sync();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				eventLoop.shutdownGracefully();
			}
		}
		return bootstrap;
	}

	public void run() {
		createBootstrap(new Bootstrap(), loop);
	}
	
	public void sendMessage(Object msg) {
		if (socketChannel != null) {
			socketChannel.writeAndFlush(msg);
		}
	}
}