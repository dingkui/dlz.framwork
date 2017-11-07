package com.dlz.plugin.socket.interfaces.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.socket.interfaces.ISocketIO;
import com.dlz.plugin.socket.util.StringCompress;

/**
 * 用定长的方式读写(传输数据经过压缩)
 * @author dingkui
 *
 */
public class ScoketIOCompress implements ISocketIO {
	private static MyLogger logger = MyLogger.getLogger(ScoketIOCompress.class);
	private static int headerLength=8;
	
	@Override
	public String read(InputStream socketIn) throws IOException {
		byte[] b=new byte[1];
		byte[] l = new byte[headerLength];
		for(int i=0;i<headerLength;i++){
			socketIn.read(b);
			l[i]=b[0];
		}
		try{
			int length = 0;
			try{
				length=Integer.parseInt(new String(l));
			}catch(Exception e){
				logger.error("socket input error:"+new String(l));
				return null;
			}
			byte[] strBytes= new byte[length];
			for(int i=0;i<length;i++){
				socketIn.read(b);
				strBytes[i]=b[0];
			}
			return StringCompress.decompress(strBytes);
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
		byte[] out=StringCompress.compress(ret);
		String l = addZeroBefor(out.length + 1, headerLength);
		socketOut.write(l.getBytes());
		socketOut.write(out);
		socketOut.write("\r\n".getBytes());
	}
	
	/**
	 * 补0成指定长度的字符串
	 * @param i
	 * @param length
	 * @return
	 */
	static String addZeroBefor(long i,int length){
		String s = String.valueOf(i);
		while(length>s.length()){
			s="0"+s;
		}
		return s;
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
