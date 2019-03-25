package com.gs.miaosha.redis;

public class GoodsKey extends BasePreFix {

	public GoodsKey(int expireSeconds, String prefix, String suffix) {
		super(expireSeconds, prefix, suffix);
	}

	public GoodsKey(String prefix) {
		super(0, prefix, "");
	}

	public GoodsKey(int secondes, String prefix) {
		super(secondes, prefix, "");
	}

	public GoodsKey(String prefix, String suffix) {
		super(0, prefix, suffix);
	}

	public static GoodsKey getKey(String prefix, String suffix) {
		return new GoodsKey(prefix, suffix);
	}

	public static GoodsKey getKey(int secondes, String prefix) {
		return new GoodsKey(secondes, prefix, "");
	}

	public static GoodsKey getKey(int secondes, String prefix, String suffix) {
		return new GoodsKey(secondes, prefix, suffix);
	}

	public static GoodsKey getKey(String prefix) {
		return new GoodsKey(prefix);
	}
}
