package com.dlz.framework.rpc;

public interface IRpcServer {
	default void doNothingL(){new java.util.ArrayList<>().forEach(a->{});}
	public void init();
}
