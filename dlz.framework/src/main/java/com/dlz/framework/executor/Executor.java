package com.dlz.framework.executor;

@FunctionalInterface
public interface Executor<T, R> {
    R excute(T t);
}