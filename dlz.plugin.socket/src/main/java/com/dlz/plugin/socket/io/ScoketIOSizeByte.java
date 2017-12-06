package com.dlz.plugin.socket.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.socket.interfaces.ASocketIO;

/**
 * 用定长的方式读写（传输数据用utf-8编码）
 * @author dingkui
 *
 */
public class ScoketIOSizeByte  extends ASocketIO {
	private static MyLogger logger = MyLogger.getLogger(ScoketIOSizeByte.class);
	private static int headerLength=8;
	private static String charsetName="UTF-8";
	
	@Override
	public String read(InputStream socketIn) throws IOException {
		try{
			byte[] bytes=readByte(socketIn, headerLength);
			return bytes==null?null:(new String(bytes,charsetName)).trim();
		}catch(IOException e){
			throw e;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
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
		write(socketOut, ret.getBytes(charsetName), headerLength);
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
