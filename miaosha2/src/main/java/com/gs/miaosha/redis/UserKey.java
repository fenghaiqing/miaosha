package com.gs.miaosha.redis;

public class UserKey extends BasePreFix {

	public UserKey(int expireSeconds, String prefix, String suffix) {
		super(expireSeconds, prefix, suffix);
	}

	public UserKey(String prefix) {
		super(0, prefix, "");
	}

	public UserKey(int secondes, String prefix) {
		super(secondes, prefix, "");
	}

	public UserKey(String prefix, String suffix) {
		super(0, prefix, suffix);
	}

	public static UserKey getKey(String prefix, String suffix) {
		return new UserKey(prefix, suffix);
	}

	public static UserKey getKey(int secondes, String prefix) {
		return new UserKey(secondes, prefix, "");
	}

	public static UserKey getKey(int secondes, String prefix, String suffix) {
		return new UserKey(secondes, prefix, suffix);
	}

	public static UserKey getKey(String prefix) {
		return new UserKey(prefix);
	}
}
