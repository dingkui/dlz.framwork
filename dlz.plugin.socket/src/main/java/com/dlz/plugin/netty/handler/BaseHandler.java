package com.dlz.plugin.netty.handler;

import com.dlz.plugin.netty.bean.RequestDto;
import com.dlz.plugin.socket.interfaces.ISocketListener;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BaseHandler extends ChannelInboundHandlerAdapter {
//	private static MyLogger logger = MyLogger.getLogger(BaseHandler.class);

	protected ISocketListener lisner;

	public BaseHandler(ISocketListener lisner) {
		this.lisner = lisner;
	}

	/**
	 * 服务端处理客户端websocket请求的核心方法，这里接收了客户端发来的信息
	 */
	@Override
	public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
		RequestDto requestInfo = (RequestDto) msg;
		RequestDto responseInfo = new RequestDto();
		String info = requestInfo.getInfo();
		byte bt = requestInfo.getType();
		switch (bt) {
		case 1:// 异步请求
//			logger.debug("服务器端接收到消息：" + info);
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
//			logger.debug("服务器端接收到消息：" + info);
			responseInfo.setType((byte) 3);
			responseInfo.setInfo(lisner.deal(info));
			channelHandlerContext.writeAndFlush(responseInfo);
			channelHandlerContext.close();
			break;
		case 3:// 同步返回
//			logger.debug("客户端接收到消息：" + info);
			lisner.deal(info);
			break;
		case 5:// 异步返回
		case 4:// 服务器端广播返回
//			logger.debug("客户端接收到消息：" + info);
			new Thread(new Runnable() {
				@Override
				public void run() {
					lisner.deal(info);
				}
			}).start();
			break;
		default:
			responseInfo.setType((byte) 5);
			responseInfo.setInfo("无效访问");
			channelHandlerContext.writeAndFlush(responseInfo);
			channelHandlerContext.close();
			break;
		}
	}
}