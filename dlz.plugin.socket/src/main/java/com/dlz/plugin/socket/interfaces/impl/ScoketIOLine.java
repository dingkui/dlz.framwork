package com.dlz.plugin.socket.interfaces.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.socket.interfaces.ISocketIO;

/**
 * 用换行符做结束
 * @author dingkui
 */
public class ScoketIOLine implements ISocketIO {
	private static MyLogger logger = MyLogger.getLogger(ScoketIOLine.class);
	private static String charsetName="UTF-8";
	@Override
	public String read(InputStream socketIn) throws IOException {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(socketIn, charsetName));
			String b= br.readLine();
			return b;
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	@Override
	public void write(OutputStream socketOut, String ret) throws IOException {
		PrintWriter output = new PrintWriter(new OutputStreamWriter(socketOut, charsetName), true);
		output.println(ret);
	}

	@Override
	public String client(OutputStream socketOut, String input, InputStream socketIn) throws IOException {
		logger.info("client write begin:"+input);
		write(socketOut, input);
		logger.info("client write end,read begin");
		String ret= read(socketIn);
		logger.info("client write begin:"+ret);
		return ret;
	}

	@Override
	public void server(OutputStream socketOut, String ret) throws IOException {
		write(socketOut, ret);
	}
}
