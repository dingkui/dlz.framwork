package com.dlz.plugin.socket.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * socket读写方式
 * @author dingkui
 *
 */
public interface ISocketIO {
	String read(InputStream socketIn) throws IOException;
	void write(OutputStream socketOut, String ret) throws IOException;
	

	String client(OutputStream socketOut, String input,InputStream socketIn) throws IOException;
	void server(OutputStream socketOut, String ret) throws IOException;
}