package com.dlz.plugin.netty;

import java.util.Timer;
import java.util.TimerTask;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.netty.bean.RequestDto;
import com.dlz.plugin.netty.codec.MessageDecoder;
import com.dlz.plugin.netty.codec.MessageEncoder;
import com.dlz.plugin.netty.handler.ClientHandler;
import com.dlz.plugin.socket.interfaces.ISocketListener;

import io.netty.bootstrap.Bootstrap;
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
	private int port;
	private String host;
	private SocketChannel socketChannel;
	private ISocketListener lisner;
	
	/**
	 * 运行状态
	 * -1：运行错误
	 * 0：初始
	 * 1：开始初始化
	 * 2：运行中
	 */

	NettyClient(int port, String host, ISocketListener lisner) {
		this.host = host;
		this.port = port;
		this.lisner=lisner;
		if(lisner==null){
			throw new RuntimeException("lisner 不能为空");
		}
		start();
	}

	 void send(String msg) {
		if (socketChannel != null) {
			if(retryTimes>0){
				throw new RuntimeException("连接已中断，发送失败。。");
			}
			RequestDto req = new RequestDto();
			req.setType((byte) 1);
			req.setInfo(msg);
			socketChannel.writeAndFlush(req);
		}
	}

	private void start() {
		ClientHandler clientHandler=new ClientHandler(lisner,this);
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
						logger.debug("客户端连接成功...");
						retryTimes=0;
					} else {
						logger.debug("客户端开启失败...");
					}
					// 等待客户端链路关闭，就是由于这里会将线程阻塞，导致无法发送信息，所以我这里开了线程
					future.channel().closeFuture().sync();
				} catch (Exception e) {
					logger.error(e.getMessage());
				} finally {
					// 优雅地退出，释放相关资源
					eventLoopGroup.shutdownGracefully();
					shutdownAndRetry();
				}
			}
		});
		thread.start();
	}

	int retryTimes=0;
	boolean retrying=false;
	public void shutdownAndRetry() {
		if(retrying){
			logger.error("客户端重启第"+retryTimes+"次中。。。");
			return;
		}
		retryTimes++;
		long times=retryTimes>6?60:(retryTimes-1)*10;
		logger.error("出现异常，"+times+"秒后将重连。。。");
		if(socketChannel!=null){
			socketChannel.eventLoop().shutdownGracefully();
		}
		retrying=true;
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				logger.error("客户端重启第"+retryTimes+"次。。。");
				start();
				retrying=false;
			}
		}, times*1000);
		
	}
}