package com.dlz.plugin.weixin.message.resp;


/**
 * 音乐消息
 * 
 * @author 陈孙亮（微信）
 * @date 2014-06-26
 *
 */
public class MusicMessage extends BaseMessage  {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
	
	
	
	
}
