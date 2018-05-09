package com.dlz.plugin.netty;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.netty.bean.RequestDto;
import com.dlz.plugin.netty.codec.MessageDecoder;
import com.dlz.plugin.netty.codec.MessageEncoder;
import com.dlz.plugin.netty.handler.ClientHandler;
import com.dlz.plugin.netty.handler.ClientSynHandler;
import com.dlz.plugin.netty.listener.INettyListener;
import com.dlz.plugin.netty.listener.impl.NettySynClientListenerImpl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	private static MyLogger logger = MyLogger.getLogger(NettyClient.class);
	private static int port;
	private static String host;
	private static SocketChannel socketChannel;
	
	private static boolean start=false;

	public static void init(int port, String host, INettyListener lisner) {
		NettyClient.host = host;
		NettyClient.port = port;
		if(!start && lisner!=null){
			start=true;
			start(new ClientHandler(lisner));
		}
	}
	public static void send(String msg){
		if (socketChannel != null) {
			RequestDto req = new RequestDto();
			req.setType((byte) 1);
			req.setInfo(msg);
			socketChannel.writeAndFlush(req);
		}
	}

	public static String synSend(String msg) {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			NettySynClientListenerImpl lisner=new NettySynClientListenerImpl();
			ClientSynHandler clientSynHandler = new ClientSynHandler(lisner, msg);
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			// b.option(ChannelOption.AUTO_READ, false);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();
					p.addLast(new MessageDecoder());
					p.addLast(new MessageEncoder());
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

	private static void start(ClientHandler clientHandler) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
				Bootstrap bootstrap = new Bootstrap();
				bootstrap.channel(NioSocketChannel.class);
				// 保持连接
				bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
				// 有数据立即发送
				bootstrap.option(ChannelOption.TCP_NODELAY, true);
				// 绑定处理group
				bootstrap.group(eventLoopGroup).remoteAddress(host, port);
				bootstrap.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						// 增加任务处理
						// 初始化编码器，解码器，处理器
						ChannelPipeline p = socketChannel.pipeline();
						p.addLast(new MessageDecoder());
						p.addLast(new MessageEncoder());
						p.addLast(clientHandler);
					}
				});
				// 进行连接
				ChannelFuture future;
				try {
					future = bootstrap.connect().sync();
					// 判断是否连接成功
					if (future.isSuccess()) {
						// 得到管道，便于通信
						socketChannel = (SocketChannel) future.channel();
						logger.debug("客户端开启成功...");
					} else {
						logger.debug("客户端开启失败...");
					}
					// 等待客户端链路关闭，就是由于这里会将线程阻塞，导致无法发送信息，所以我这里开了线程
					future.channel().closeFuture().sync();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					// 优雅地退出，释放相关资源
					eventLoopGroup.shutdownGracefully();
				}
			}
		});

		thread.start();
	}

//	public static void main(String[] args) {
//		Scanner input = new Scanner(System.in);
//		ClientUtil bootstrap = new ClientUtil(8080, "127.0.0.1");
//
//		String infoString = "";
//		while (true) {
//			infoString = input.nextLine();
//			RequestInfo req = new RequestInfo();
//			req.setType((byte) 1);
//			req.setInfo(infoString);
//			bootstrap.sendMessage(req);
//		}
//	}
}