package com.dlz.framework.db.mySequence.bean;

import java.util.concurrent.atomic.AtomicLong;

public class SequenceRange {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private final long min;
	private final long max;
	/** */
	private final AtomicLong value;
	/** 是否超限 */
	private volatile boolean over = false;

	/**
	 * 构造.
	 *
	 * @param min
	 * @param max
	 */
	public SequenceRange(long min, long max) {
		this.min = min;
		this.max = max;
		this.value = new AtomicLong(min);
	}

	/**
	 * <p>
	 * Gets and increment
	 * </p>
	 *
	 * @return
	 */
	public long getAndIncrement() {
		long currentValue = value.getAndIncrement();
		if (currentValue > max) {
			over = true;
			return -1;
		}

		return currentValue;
	}

	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public long getMin() {
		return min;
	}

	public long getMax() {
		return max;
	}

	public AtomicLong getValue() {
		return value;
	}
	
	

}