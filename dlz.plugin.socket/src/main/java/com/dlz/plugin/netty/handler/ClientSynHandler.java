package com.dlz.plugin.netty.handler;

import com.dlz.plugin.netty.bean.RequestDto;
import com.dlz.plugin.netty.listener.INettyListener;

import io.netty.channel.ChannelHandlerContext;

public class ClientSynHandler extends BaseHandler { 
	private String msg;
	public ClientSynHandler(INettyListener lisner,String msg) {
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
} 