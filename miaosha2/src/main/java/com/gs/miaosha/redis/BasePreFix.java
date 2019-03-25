package com.gs.miaosha.redis;

public  abstract class BasePreFix implements KeyPrefix {

	private int expireSeconds;
	private String prefix;
	private String suffix;
	public BasePreFix(int expireSeconds,String prefix,String suffix){
		this.expireSeconds=expireSeconds;
		this.prefix = prefix;
		this.suffix=suffix;
	}
	@Override
	public int expireSeconds() {
		return expireSeconds;
	}

	@Override
	public String getPrefix() {
		String clazz = this.getClass().getSimpleName();
		return clazz+":"+prefix+suffix;
	}

}
