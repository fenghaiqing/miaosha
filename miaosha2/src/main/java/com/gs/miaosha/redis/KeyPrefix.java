package com.gs.miaosha.redis;

public interface KeyPrefix {

	int expireSeconds();
	
	String getPrefix();
}
