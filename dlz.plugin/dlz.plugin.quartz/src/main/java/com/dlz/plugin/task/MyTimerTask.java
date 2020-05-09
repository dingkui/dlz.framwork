package com.dlz.plugin.task;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.TimerTask;

import org.slf4j.Logger;

/**
 * 定时任务
 * 
 * @author dingkui
 *
 * @param <T>
 */
public abstract class MyTimerTask<T> extends TimerTask {
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(TimerTask.class);

	private Set<String> sets = new HashSet<String>();
	private Stack<T> paraStack = new Stack<T>();
	private Stack<T> paraStackTmp = new Stack<T>();
	private String key;

	public void run() {
		while (!paraStack.isEmpty()) {
			T t = paraStack.pop();
			try {
				done(t);
				sets.remove(t.toString());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		if (!paraStackTmp.isEmpty()) {
			Stack<T> t = paraStack;
			paraStack = paraStackTmp;
			paraStackTmp = t;
			run();
		} else {
			TimerDispacher.completeTask(key);
		}
	}

	public void addPara(T t) {

		if (!sets.contains(t.toString())) {
			sets.add(t.toString());
			paraStackTmp.push(t);
		}
	}

	public abstract void done(T t);

	public void setKey(String key) {
		this.key = key;
	}

}