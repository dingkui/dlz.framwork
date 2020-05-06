package com.dlz.netty.syn;

import com.dlz.plugin.netty.codec.ICoder;
import com.dlz.plugin.netty.codec.MessageDecoder;
import com.dlz.plugin.netty.codec.MessageEncoder;
import com.dlz.plugin.netty.codec.impl.SimpleCoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServer {


	public void start(int port,ICoder coder) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					// 注册handler
					ChannelPipeline p = ch.pipeline();
					p.addLast(new MessageDecoder(coder));
                    p.addLast(new MessageEncoder(coder));
                    p.addLast(new HelloServerInHandler());
				}
			}).option(ChannelOption.SO_BACKLOG, 128);
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		HelloServer server = new HelloServer();
		server.start(9090,new SimpleCoder());
	}
}
