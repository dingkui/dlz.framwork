package com.dlz.plugin.netty.handler;

import com.dlz.plugin.socket.interfaces.ISocketListener;

import io.netty.channel.ChannelInboundHandlerAdapter;

public class BaseHandler extends ChannelInboundHandlerAdapter {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	protected ISocketListener lisner;

	public BaseHandler(ISocketListener lisner) {
		this.lisner = lisner;
	}
}