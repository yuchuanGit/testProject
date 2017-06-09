package com.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Test {

	public static void main(String[] args) {
//		Jedis jedis = new Jedis("127.0.0.1",6379);
//		jedis.set("l1", "aaannn");
//		System.out.println(jedis.get("l1"));
		JedisPool jedisPoolUtil = JedisPoolUtil.getJedisPoolInstance();
		Jedis jedis = jedisPoolUtil.getResource();
		jedis.set("l1", "aaannn");
		jedis.set("l2", "bbb");
		System.out.println("你好");
		JedisPoolUtil.release(jedisPoolUtil, jedis);
	}

}
