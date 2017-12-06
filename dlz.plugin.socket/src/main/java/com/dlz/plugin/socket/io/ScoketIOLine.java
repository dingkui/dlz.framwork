package com.dlz.plugin.socket.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.socket.interfaces.ASocketIO;

/**
 * 用换行符做结束
 * @author dingkui
 */
public class ScoketIOLine  extends ASocketIO{
	private static MyLogger logger = MyLogger.getLogger(ScoketIOLine.class);
	private static String charsetName="UTF-8";
	public static String separatorReplace="<separatorReplace>";
	@Override
	public String read(InputStream socketIn) throws IOException {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(socketIn, charsetName));
			return br.readLine().replaceAll(separatorReplace, separatorStr);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	@Override
	public void write(OutputStream socketOut, String ret) throws IOException {
		socketOut.write(ret.replaceAll(separatorStr, separatorReplace).getBytes(charsetName));
		socketOut.write(separatorByte);
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
