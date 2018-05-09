package com.dlz.plugin.netty.handler;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.netty.bean.RequestDto;
import com.dlz.plugin.netty.listener.INettyListener;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BaseHandler extends ChannelInboundHandlerAdapter {
	private static MyLogger logger = MyLogger.getLogger(BaseHandler.class);

	protected INettyListener lisner;

	public BaseHandler(INettyListener lisner) {
		this.lisner = lisner;
	}

	/**
	 * 服务端处理客户端websocket请求的核心方法，这里接收了客户端发来的信息
	 */
	@Override
	public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
		RequestDto requestInfo = (RequestDto) msg;
		String info = requestInfo.getInfo();
		byte bt = requestInfo.getType();
		switch (bt) {
		case 1:// 异步请求
			logger.debug("服务器端接收到消息：" + info);
			new Thread(new Runnable() {
				@Override
				public void run() {
					RequestDto responseInfo = new RequestDto();
					responseInfo.setType((byte) 5);
					responseInfo.setInfo(lisner.deal(info));
					channelHandlerContext.writeAndFlush(responseInfo);
				}
			}).start();
			break;
		case 2:// 同步请求
			logger.debug("服务器端接收到消息：" + info);
			RequestDto responseInfo = new RequestDto();
			responseInfo.setType((byte) 3);
			responseInfo.setInfo(lisner.deal(info));
			channelHandlerContext.writeAndFlush(responseInfo);
			channelHandlerContext.close();
			break;
		case 3:// 同步返回
			logger.debug("客户端接收到消息：" + info);
			lisner.recive(info);
			break;
		case 5:// 异步返回
		case 4:// 服务器端广播返回
			logger.debug("客户端接收到消息：" + info);
			new Thread(new Runnable() {
				@Override
				public void run() {
					lisner.recive(info);
				}
			}).start();
			break;
		default:
			break;
		}
	}
}