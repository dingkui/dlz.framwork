package com.dlz.netty.syn;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.netty.codec.DefaultCoder;
import com.dlz.plugin.netty.codec.ICoder;
import com.dlz.plugin.netty.codec.MessageDecoder;
import com.dlz.plugin.netty.codec.MessageEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 1、Client向Server发送消息：Are you ok? 2、Server接收客户端发送的消息，并打印出来。 3、Server端向客户端发送消息：I
 * am ok! 4、Client接收Server端发送的消息，并打印出来，通讯结束。
 */

public class HelloClient {
	private static MyLogger logger = MyLogger.getLogger(HelloClient.class);

	StringBuffer message = new StringBuffer();

	public String connect(String host, int port,ICoder coder) throws Exception {

		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			// b.option(ChannelOption.AUTO_READ, false);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();
					p.addLast(new MessageDecoder(coder));
					p.addLast(new MessageEncoder(coder));
					p.addLast(new HelloClientIntHandler(message));
				}
			});

			// Start the client.
			/**
			 * await()方法：Waits for this future to be completed. Waits for this
			 * future until it is done, and rethrows the cause of the failure if
			 * this future failed.
			 */
			Channel channel = b.connect(host, port).channel();
			ChannelFuture f = channel.closeFuture().await();
			channel.closeFuture().await();
			return message.toString();

		} finally {
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		HelloClient client = new HelloClient();
		logger.debug(">>>>>>>>>>" + client.connect("127.0.0.1", 9090,new DefaultCoder()).toString());
	}
}
