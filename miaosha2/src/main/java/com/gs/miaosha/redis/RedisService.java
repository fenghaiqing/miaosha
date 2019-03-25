package com.gs.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {

	@Autowired
	private JedisPool jedisPool;

	private static final String OK = "OK";

	private Jedis jedis = null;

	public <T> T get(KeyPrefix prefix, Class<T> clazz) {
		try {
			jedis = jedisPool.getResource();
			String str = jedis.get(prefix.getPrefix());
			return (T) stringBean(str, clazz);
		} finally {
			returnToPool(jedis);
		}

	}

	public <T> boolean set(KeyPrefix prefix, T value) {
		try {
			jedis = jedisPool.getResource();
			String str = null;
			String t = beanToString(value);
			if (prefix.expireSeconds() <= 0) {
				str = jedis.set(prefix.getPrefix(), t);
			} else {
				str = jedis.setex(prefix.getPrefix(), prefix.expireSeconds(), t);
			}

			if (OK.equals(str)) {
				return true;
			} else {
				return false;
			}
		} finally {
			returnToPool(jedis);
		}
	}

	public <T> boolean exists(String key) {
		try {
			jedis = jedisPool.getResource();
			return jedis.exists(key);
		} finally {
			returnToPool(jedis);
		}
	}

	public <T> Long incr(String key) {
		try {
			jedis = jedisPool.getResource();
			return jedis.incr(key);
		} finally {
			returnToPool(jedis);
		}

	}
	
	public <T> Long decr(String key) {
		try {
			jedis = jedisPool.getResource();
			return jedis.decr(key);
		} finally {
			returnToPool(jedis);
		}
	}
	
	private <T> String beanToString(T value) {
		if (value == null) {
			return null;
		}
		Class<?> clazz = value.getClass();
		if (clazz == int.class || clazz == Integer.class) {
			return String.valueOf(value);
		} else if (clazz == String.class) {
			return value.toString();
		} else if (clazz == long.class || clazz == Long.class) {
			return String.valueOf(value);
		} else {
			return JSON.toJSONString(value);
		}

	}

	@SuppressWarnings("unchecked")
	private <T> T stringBean(String value, Class<T> clazz) {

		if (value == null || value.length() == 0 || clazz == null) {
			return null;
		} else if (clazz == int.class || clazz == Integer.class) {
			return (T) Integer.valueOf(value);
		} else if (clazz == String.class) {
			return (T) value;
		} else if (clazz == long.class || clazz == Long.class) {
			return (T) Long.valueOf(value);
		} else {
			return JSON.toJavaObject(JSON.parseObject(value), clazz);
		}

	}

	private void returnToPool(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}

	}

	
}
