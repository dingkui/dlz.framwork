package com.dlz.plugin.netty;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.netty.bean.RequestDto;
import com.dlz.plugin.netty.codec.ICoder;
import com.dlz.plugin.netty.codec.MessageDecoder;
import com.dlz.plugin.netty.codec.MessageEncoder;
import com.dlz.plugin.netty.codec.impl.DefaultCoder;
import com.dlz.plugin.netty.conf.NettyConfig;
import com.dlz.plugin.netty.handler.ServerHandler;
import com.dlz.plugin.socket.interfaces.ISocketListener;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务器端
 * @author dingkui
 *
 */
public class NettyServer {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static MyLogger logger = MyLogger.getLogger(NettyServer.class);
	private ServerSocketChannel serverSocketChannel;

	ServerHandler serverHandler;
	ISocketListener listener;
	private ICoder coder;
	private static NettyServer instace;
	public static void broad(String msg){
		if(instace!=null){
			instace.broadMsg(msg);
		}
	}

	/**
	 * @param serverPort 端口
	 * @param listener 服务器监听器（客户端发送的消息监听并处理）
	 * 默认编码器为长度标识
	 */
	public NettyServer(int serverPort, ISocketListener listener) {
		this(serverPort, listener, new DefaultCoder());
	}
	/**
	 * 
	 * @param serverPort 端口
	 * @param listener 服务器监听器（客户端发送的消息监听并处理）
	 * @param coder 编码器
	 */
	public NettyServer(int serverPort, ISocketListener listener,ICoder coder) {
		if(instace==null){
			instace=this;
			this.listener = listener;
			this.coder=coder;
			if(listener==null){
				throw new RuntimeException("lisner 不能为空");
			}
			if(coder==null){
				throw new RuntimeException("编码器不能为空");
			}
			bind(serverPort);
		}
	}

	private void bind(int serverPort) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// 服务端要建立两个group，一个负责接收客户端的连接，一个负责处理数据传输
				// 连接处理group
				EventLoopGroup boss = new NioEventLoopGroup();
				// 事件处理group
				EventLoopGroup worker = new NioEventLoopGroup();
				ServerBootstrap bootstrap = new ServerBootstrap();
				// 绑定处理group
				bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
						// 保持连接数
						.option(ChannelOption.SO_BACKLOG, 128)
						// 有数据立即发送
						.option(ChannelOption.TCP_NODELAY, true)
						// 保持连接
						.childOption(ChannelOption.SO_KEEPALIVE, true)
						// 处理新连接
						.childHandler(new ChannelInitializer<SocketChannel>() {
							@Override
							protected void initChannel(SocketChannel sc) throws Exception {
								// 增加任务处理
								ChannelPipeline p = sc.pipeline();
//								p.addLast(new LineBasedFrameDecoder(1024));
//								p.addLast(new StringDecoder());
//								p.addLast(new TimeServerHandler());
								p.addLast(new MessageDecoder(coder));
								p.addLast(new MessageEncoder(coder));
								p.addLast(new ServerHandler(listener));
							}
						});

				// 绑定端口，同步等待成功
				ChannelFuture future;
				try {
					future = bootstrap.bind(serverPort).sync();
					if (future.isSuccess()) {
						serverSocketChannel = (ServerSocketChannel) future.channel();
						logger.info("服务端开启成功");
					} else {
						logger.warn("服务端开启失败");
					}

					// 等待服务监听端口关闭,就是由于这里会将线程阻塞，导致无法发送信息，所以我这里开了线程
					future.channel().closeFuture().sync();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// 优雅地退出，释放线程池资源
					boss.shutdownGracefully();
					worker.shutdownGracefully();
				}
			}
		});
		thread.start();
	}

	/**
	 * 给所有连接中的客户端发消息
	 * @param msg
	 */
	public void broadMsg(String msg) {
		if (serverSocketChannel != null) {
			synchronized (a) {
				RequestDto req = new RequestDto((byte) 4,msg);
				NettyConfig.group.writeAndFlush(req);
//				ByteBuf message = null;
//				byte[] reqs=req.toString().getBytes();
//		        for (int i = 0; i < 100; i++) {
//		            message = Unpooled.buffer(reqs.length);
//		            message.writeBytes(reqs);
//		            NettyConfig.group.writeAndFlush(message);
//		        }
			}
		}
	}
	static byte[] a=new byte[0];
}