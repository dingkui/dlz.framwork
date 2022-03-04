package com.dlz.plugin.netty.base.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;

public interface ICoder<IN,OUT> {

	IN decode(ByteBuf in) throws Exception;
	
	void writeObj(ByteBufOutputStream writer, OUT m) throws Exception;

	String getInfo(IN in);

	OUT mkOut(String result);
}
