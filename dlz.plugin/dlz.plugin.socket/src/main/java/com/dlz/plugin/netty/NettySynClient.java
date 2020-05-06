package com.dlz.plugin.netty;

import org.slf4j.Logger;
import com.dlz.plugin.netty.codec.ICoder;
import com.dlz.plugin.netty.codec.MessageDecoder;
import com.dlz.plugin.netty.codec.MessageEncoder;
import com.dlz.plugin.netty.codec.impl.DefaultCoder;
import com.dlz.plugin.netty.handler.ClientSynHandler;
import com.dlz.plugin.netty.listener.NettySynClientListener;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 同步消息客户端 为短连接操作  每次发送消息等待服务器相应，相应完成后释放连接
 * @author dingkui
 *
 */
class NettySynClient {
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(NettySynClient.class);
	private int port;
	private String host;
	private ICoder coder;

	public NettySynClient(int port, String host) {
		this(port, host, new DefaultCoder());
	}
	public NettySynClient(int port, String host,ICoder coder) {
		this.host = host;
		this.port = port;
		this.coder=coder;
	}

	public String send(String msg) {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			NettySynClientListener lisner = new NettySynClientListener();
			ClientSynHandler clientSynHandler = new ClientSynHandler(lisner, msg);
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
					p.addLast(clientSynHandler);
				}
			});

			// Start the client.
			/**
			 * await()方法：Waits for this future to be completed. Waits for this
			 * future until it is done, and rethrows the cause of the failure if
			 * this future failed.
			 */
			Channel channel = b.connect(host, port).channel();
			channel.closeFuture().await();
			return lisner.getResult();
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		} finally {
			workerGroup.shutdownGracefully();
		}
		return null;
	}
}