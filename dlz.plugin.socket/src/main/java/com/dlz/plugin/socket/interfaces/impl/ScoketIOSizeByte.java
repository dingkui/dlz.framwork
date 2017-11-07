package com.dlz.plugin.socket.interfaces.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.socket.interfaces.ISocketIO;

/**
 * 用定长的方式读写（传输数据用utf-8编码）
 * @author dingkui
 *
 */
public class ScoketIOSizeByte implements ISocketIO {
	private static MyLogger logger = MyLogger.getLogger(ScoketIOSizeByte.class);
	private static int headerLength=8;
	private static String charsetName="UTF-8";
	
	@Override
	public String read(InputStream socketIn) throws IOException {
		try{
			byte[] b=new byte[1];
			byte[] l = new byte[headerLength];
			for(int i=0;i<headerLength;i++){
				socketIn.read(b);
				l[i]=b[0];
			}
			int length = Integer.parseInt(new String(l));
			byte[] strBytes= new byte[length];
			for(int i=0;i<length;i++){
				socketIn.read(b);
				strBytes[i]=b[0];
			}
			return (new String(strBytes,charsetName)).trim();
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
		String l = addZeroBefor(ret.getBytes(charsetName).length + 1, headerLength);
		ret = l + ret;
		PrintWriter output = new PrintWriter(new OutputStreamWriter(socketOut, charsetName), true);
		output.println(ret);
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
