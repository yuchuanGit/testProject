package com.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	private static JedisPool jedisPool = null;
	
	public static JedisPool getJedisPoolInstance(){
		if(null==jedisPool){
			synchronized (JedisPoolUtil.class) {
				if(null==jedisPool){
					JedisPoolConfig poolConfig = new JedisPoolConfig();
					poolConfig.setMaxActive(1000);//可用连接实例的最大数目，默认值为8
					poolConfig.setMaxIdle(32);//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8
					poolConfig.setMaxWait(100*1000);//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；  
					poolConfig.setTestOnBorrow(true);//在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
					jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
				}
			}
		}
		return jedisPool;
	}
	
	public static void release(JedisPool jedisPool,Jedis jedis){
		if(jedis!=null){
			jedisPool.returnResourceObject(jedis);
		}
	}
}
