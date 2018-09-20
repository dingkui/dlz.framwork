package com.dlz.framework.task;

/**
 * 自动任务接口
 * @author dingkui
 *
 */
public interface AutoTask {
	default void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	public void start();
}