package com.dlz.plugin.socket.interfaces;

/**
 * 业务处理接口
 * @author dingkui
 *
 */
public interface IDealService {
	/**
	 * 处理传入数据，并返回信息
	 * @param postStr 传入数据
	 * @return
	 */
	public String getResStr(String postStr);
}
