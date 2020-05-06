package com.dlz.netty.line;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeServer {


    public void bind(int port) throws Exception{
        //配置服务端的NIO线程组
        //NioEventLoopGroup是个线程组，它包含了一组NIO线程，专门用于网络事件的处理，实际上它们就是Reactor线程组
        //bossGroup用于服务端接受客户端的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup进行SocketChannel的网络读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //Netty用于启动NIO服务端的辅助启动类，目的是降低服务端的开发复杂度
            ServerBootstrap bootstrap = new ServerBootstrap();
            //将两个NIO线程组当作入参传递到ServerBootstrap
            bootstrap.group(bossGroup, workerGroup)
                //设置创建的Channel为NioServerSocketChannel,它的功能对应于JDK NIO类库中的ServerSocketChannel类。
                .channel(NioServerSocketChannel.class)
                //配置NioServerSocketChannel的TCP参数，此处将它的backlog设置为1024
                .option(ChannelOption.SO_BACKLOG, 1024)
                //绑定I/O事件的处理类ChildChannelHandler，它的作用类似于Reactor模式中的Handler类，主要用于处理网络I/O事件，例如记录日志、对消息进行编解码等
                .childHandler(new ChildChannelHandler());
            //调用bind方法绑定监听端口，随后，调用它的同步阻塞方法sync等待绑定操作完成。
            //完成之后Netty会返回一个ChannelFuture，它的功能类似于JDK的java.util.concurrent.Future，主要用于异步操作的通知回调
            ChannelFuture future = bootstrap.bind(port).sync();
            //等待服务端监听端口关闭，等待服务端链路关闭之后main函数才退出
            future.channel().closeFuture().sync();
        } finally {
            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            //在原来的TimeServerHandler之前新增了两个解码器LineBasedFrameDecoder、StringDecoder
            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
            ch.pipeline().addLast(new StringDecoder());
            ch.pipeline().addLast(new TimeServerHandler());
        }
    }
    
    public static void main(String[] args) throws Exception {
        int port = 9090;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        new TimeServer().bind(port);
    }
}