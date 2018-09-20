package com.dlz.plugin.weixin.message.req;

/**
 * 音乐消息
 * @author Administrator
 *
 */
public class MusicMessage extends BaseMessage {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
	
	
}
