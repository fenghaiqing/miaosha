package com.gs.miaosha.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="redis")
public class RedisConfig {
	
	private String host;
	
	private int port;
	
	private String password;

	private int poolMaxTotal;
	
	private int poolMaxWait;
	
	private int poolMaxdle;
	
	private int timeout;

	
	
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPoolMaxTotal() {
		return poolMaxTotal;
	}

	public void setPoolMaxTotal(int poolMaxTotal) {
		this.poolMaxTotal = poolMaxTotal;
	}

	public int getPoolMaxWait() {
		return poolMaxWait;
	}

	public void setPoolMaxWait(int poolMaxWait) {
		this.poolMaxWait = poolMaxWait;
	}

	public int getPoolMaxdle() {
		return poolMaxdle;
	}

	public void setPoolMaxdle(int poolMaxdle) {
		this.poolMaxdle = poolMaxdle;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	
	
}
