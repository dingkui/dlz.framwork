package com.dlz.plugin.netty;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import com.dlz.plugin.netty.bean.RequestDto;
import com.dlz.plugin.netty.codec.ICoder;
import com.dlz.plugin.netty.codec.MessageDecoder;
import com.dlz.plugin.netty.codec.MessageEncoder;
import com.dlz.plugin.netty.codec.impl.DefaultCoder;
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

/**
 * 异步客户端 采用长连接
 * @author dingkui
 *
 */
public class NettyClient {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(NettyClient.class);
	private int port;
	private String host;
	private SocketChannel socketChannel;
	private ISocketListener lisner;
	private ICoder coder;
	
	NettyClient(int port, String host, ISocketListener lisner) {
		this(port, host, lisner, new DefaultCoder());
	}
	
	NettyClient(int port, String host, ISocketListener lisner,ICoder coder) {
		this.host = host;
		this.port = port;
		this.lisner=lisner;
		this.coder=coder;
		if(lisner==null){
			throw new RuntimeException("lisner 不能为空");
		}
		if(coder==null){
			throw new RuntimeException("编码器 不能为空");
		}
		start();
	}

	 void send(String msg) {
		if (socketChannel != null) {
			if(retryTimes>0){
				throw new RuntimeException("连接已中断，发送失败。。");
			}
			RequestDto req = new RequestDto((byte) 1 , msg);
			synchronized (a) {
				socketChannel.writeAndFlush(req);
			}
		}
	}
	 
	static byte[] a=new byte[0];

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
//						p.addLast(new LineBasedFrameDecoder(1024));
//						p.addLast(new StringDecoder());
//						p.addLast(new TimeClientHandler());
						p.addLast(new MessageDecoder(coder));
						p.addLast(new MessageEncoder(coder));
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
						String message = "接服务器("+host+":"+port+")成功...";
						System.out.println(message);
						logger.info(message);
						retryTimes=0;
					} else {
						logger.error("连接服务器失败...");
					}
					// 等待客户端链路关闭，就是由于这里会将线程阻塞，导致无法发送信息，所以我这里开了线程
					future.channel().closeFuture().sync();
				} catch (Exception e) {
					shutdownAndRetry(e.getMessage());
				} finally {
					// 优雅地退出，释放相关资源
					eventLoopGroup.shutdownGracefully();
				}
			}
		});
		thread.start();
	}

	int retryTimes=0;
	boolean retrying=false;
	Timer timer = new Timer();
	/**
	 * 断线重连服务器
	 * 第一次立即重连，在后面每一次重连失败，重连时间加10秒，达到120秒后，每120秒重试一次
	 * @param msg
	 */
	public void shutdownAndRetry(String msg) {
		if(retrying){
			logger.error("重连中。。。");
			return;
		}
		retrying=true;
		
		retryTimes++;
		long times=retryTimes>12?120:(retryTimes-1)*10;
		logger.error("出现异常：["+msg+"],"+(times>0?times+"秒后将":"")+"重连。。。");
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				retrying=false;
				logger.warn("客户端重连服务器("+host+":"+port+")第"+retryTimes+"次。。。");
				start();
			}
		}, times*1000);
	}
}