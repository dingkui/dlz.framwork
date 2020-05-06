package com.dlz.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

@SuppressWarnings({"resource","unused"})
public class RedisTest {

	@Test 
	public void test1Normal() { 
	    long start = System.currentTimeMillis(); 
	    for (int i = 0; i < 100; i++) { 
		    Jedis jedis = new Jedis("localhost"); 
	        String result = jedis.setex("n" + i,3, "n" + i); 
	        jedis.disconnect(); 
	    } 
	    long end = System.currentTimeMillis(); 
	    System.out.println("Simple SET: " + ((end - start)/1000.0) + " seconds"); 
	    
	} 
	@Test 
	public void test3Normal() { 
	    long start = System.currentTimeMillis(); 
	    for (int i = 0; i < 100; i++) { 
		    Jedis jedis = new Jedis("localhost"); 
	        String result = jedis.set("n" + i, "n" + i); 
	        jedis.disconnect(); 
	    } 
	    long end = System.currentTimeMillis(); 
	    System.out.println("Simple SET: " + ((end - start)/1000.0) + " seconds"); 
	    
	} 
	
	@Test 
	public void test2Normal() { 
	    long start = System.currentTimeMillis(); 
	    for (int i = 0; i < 100; i++) { 
		    Jedis jedis = new Jedis("localhost"); 
	        String result = jedis.get("n" + i); 
	        jedis.disconnect(); 
	        System.out.println(result);
	    } 
	    long end = System.currentTimeMillis(); 
	    System.out.println("Simple SET: " + ((end - start)/1000.0) + " seconds"); 
	    
	}
}

