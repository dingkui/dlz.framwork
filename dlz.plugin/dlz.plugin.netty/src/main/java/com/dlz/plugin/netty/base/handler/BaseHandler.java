package com.dlz.plugin.netty.base.handler;

import com.dlz.plugin.netty.base.codec.ICoder;
import com.dlz.plugin.netty.base.listener.ISocketListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BaseHandler extends ChannelInboundHandlerAdapter {

    protected ISocketListener lisner;
    protected ICoder coder;

    public BaseHandler(ISocketListener lisner, ICoder coder) {
        this.lisner = lisner;
        this.coder = coder;
    }

    /**
     * 处理客户端websocket请求的核心方法
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object o = coder.mkOut(lisner.deal(coder.getInfo(msg), ctx.channel().id().asLongText()));
        if(o!=null){
            ctx.writeAndFlush(o);
        }
    }
}