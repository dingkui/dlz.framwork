package com.dlz.plugin.netty.test.server;


import com.dlz.plugin.netty.server.NettyServer;
import com.dlz.plugin.netty.test.codec.WaterStationCoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务器端
 * @author dingkui
 *
 */
@Slf4j
public class RunNettyServer {
	public static void main(String[] args) {
		NettyServer server=new NettyServer(89,  new NettyServerListener(), new WaterStationCoder());
		server.start();
	}

}