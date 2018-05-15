package com.dlz.plugin.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;

public interface ICoder {
	
	public Object decode(ByteBuf in) throws Exception;
	
	void writeObj(ByteBufOutputStream writer, Object m) throws Exception;
}
