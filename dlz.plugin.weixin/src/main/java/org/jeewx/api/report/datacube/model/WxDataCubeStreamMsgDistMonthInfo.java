package org.jeewx.api.report.datacube.model;

/**
 * 结果类--获取消息发送分布月数据
 * @author luweichao
 *
 * 2015年1月27日
 */
public class WxDataCubeStreamMsgDistMonthInfo extends WxDataCubeStreamMsgInfo{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
 
	private String  count_interval;

	public String getCount_interval() {
		return count_interval;
	}

	public void setCount_interval(String count_interval) {
		this.count_interval = count_interval;
	}
	
}
