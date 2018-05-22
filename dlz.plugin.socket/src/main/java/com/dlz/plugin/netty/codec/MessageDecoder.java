package com.dlz.plugin.netty.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MessageDecoder extends ByteToMessageDecoder { 
//	private static Logger logger=Logger.getLogger(MessageDecoder.class);
	
	
	private ICoder coder;

	public MessageDecoder(ICoder coder){
		this.coder=coder;
	}
	
    @Override  
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {  
//        //标记开始读取位置  
//        in.markReaderIndex();  
//        //判断协议类型  
//        byte infoType = in.readByte();  
//        RequestDto requestInfo = new RequestDto();  
//        requestInfo.setType(infoType);  
//        //in.readableBytes()即为剩下的字节数  
//        byte[] info = new byte[in.readableBytes()];  
//        in.readBytes(info);  
////        requestInfo.setInfo(new String(info, "utf-8"));  
////        String decompress = StringCompress.decompress(info);
//        String decompress = new String(info, "utf-8");
//		requestInfo.setInfo(decompress);
////        logger.debug("MessageDecoder：type="+infoType+" msg=" + requestInfo.getInfo());  
//        //最后把你想要交由ServerHandler的数据添加进去，就可以了  
//        out.add(requestInfo);
    	
    	Object decode = coder.decode(in);
    	if(decode!=null){
    		out.add(decode);
    	}
    }  
}  