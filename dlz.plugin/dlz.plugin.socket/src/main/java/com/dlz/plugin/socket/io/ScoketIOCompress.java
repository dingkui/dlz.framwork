package com.dlz.plugin.socket.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import com.dlz.plugin.socket.interfaces.ASocketIO;
import com.dlz.plugin.socket.util.StringCompress;

/**
 * 用定长的方式读写(传输数据经过压缩)
 * @author dingkui
 *
 */
public class ScoketIOCompress extends ASocketIO {

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(ScoketIOCompress.class);
	private static int headerLength=8;
	
	@Override
	public String read(InputStream socketIn) throws IOException {
		try{
			return StringCompress.decompress(readByte(socketIn, headerLength));
		}catch(Exception e){
			logger.error("数据读取失败："+e.getMessage());
			return null;
		}
	}
	
	/**
	 * 用定长的方式取值
	 * @param s
	 * @return
	 * @throws IOException
	 */
	@Override
	public void write(OutputStream socketOut,String ret) throws IOException{
		write(socketOut, StringCompress.compress(ret), headerLength);
	}
	
	@Override
	public String client(OutputStream socketOut, String input, InputStream socketIn) throws IOException {
		write(socketOut, input);
		return read(socketIn);
	}

	@Override
	public void server(OutputStream socketOut, String ret) throws IOException {
		write(socketOut, ret);
	}
}
