package com.dlz.plugin.netty.handler;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.netty.conf.NettyConfig;
import com.dlz.plugin.netty.listener.INettyListener;

import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends BaseHandler {
	public ServerHandler(INettyListener lisner) {
		super(lisner);
	}

	private static MyLogger logger = MyLogger.getLogger(ServerHandler.class);
    @Override
    public boolean isSharable() {
        logger.debug("handler-sharable");
        return super.isSharable();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.debug("channelRegistered "+ctx.name());
        super.channelRegistered(ctx);
    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        logger.debug("channelUnregistered");
        super.channelUnregistered(ctx);
    }
	/**
	 * 客户端与服务端创建连接的时候调用
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.debug("客户端与服务端连接开始channelActive...");
		NettyConfig.group.add(ctx.channel());
	}

	/**
	 * 客户端与服务端断开连接时调用
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.debug("客户端与服务端连接关闭channelActive...");
		NettyConfig.group.remove(ctx.channel());
	}

	/**
	 * 服务端接收客户端发送过来的数据结束之后调用
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
		logger.debug("信息接收完毕channelReadComplete...");
	}

	/**
	 * 工程出现异常的时候调用
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

//	/**
//	 * 服务端处理客户端websocket请求的核心方法，这里接收了客户端发来的信息
//	 */
//	@Override
//	public void channelRead(ChannelHandlerContext channelHandlerContext, Object info) throws Exception {
//		RequestInfo requestInfo = (RequestInfo) info;
//		String info2 = requestInfo.getInfo();
//		logger.debug("服务端，接受到：" + info2);
//		// 单独回复客户端信息
//		requestInfo.setInfo("单独回复："+info2);
//		channelHandlerContext.writeAndFlush(requestInfo);
//		// 服务端使用这个就能向 每个连接上来的客户端群发消息
//		requestInfo.setInfo("群发回复："+info2);
//		NettyConfig.group.writeAndFlush(requestInfo);
//		Iterator<Channel> iterator = NettyConfig.group.iterator();
//		while (iterator.hasNext()) {
//			// 打印出所有客户端的远程地址
//			logger.debug((iterator.next()).remoteAddress());
//		}
//	}
}