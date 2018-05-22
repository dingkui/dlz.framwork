package com.dlz.plugin.netty.handler;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.netty.bean.RequestDto;
import com.dlz.plugin.socket.interfaces.ISocketListener;

import io.netty.channel.ChannelHandlerContext;

public class ClientSynHandler extends BaseHandler { 
	private static MyLogger logger = MyLogger.getLogger(ClientSynHandler.class);
	private String msg;
	public ClientSynHandler(ISocketListener lisner,String msg) {
		super(lisner);
		this.msg=msg;
	}
    
    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RequestDto req = new RequestDto();    
        req.setType((byte) 2);    
        req.setInfo(msg);  
        ctx.writeAndFlush(req);
    }
    
	/**
	 * 服务端处理客户端websocket请求的核心方法，这里接收了客户端发来的信息
	 */
	@Override
	public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
		RequestDto requestInfo = (RequestDto) msg;
		String info = requestInfo.getInfo();
		int bt = requestInfo.getType();
		switch (bt) {
		case 3:// 同步返回
			lisner.deal(info);
			break;
		default:
			logger.error("同步客户端接收到无效信息：{0}",requestInfo);
			break;
		}
	}
} 