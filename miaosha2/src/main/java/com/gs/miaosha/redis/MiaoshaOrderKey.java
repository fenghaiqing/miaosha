package com.gs.miaosha.redis;

public class MiaoshaOrderKey extends BasePreFix {

	public MiaoshaOrderKey(int expireSeconds, String prefix, String suffix) {
		super(expireSeconds, prefix, suffix);
	}

	public MiaoshaOrderKey(String prefix) {
		super(0, prefix, "");
	}

	public MiaoshaOrderKey(int secondes, String prefix) {
		super(secondes, prefix, "");
	}

	public MiaoshaOrderKey(String prefix, String suffix) {
		super(0, prefix, suffix);
	}

	public static MiaoshaOrderKey getKey(String prefix, String suffix) {
		return new MiaoshaOrderKey(prefix, suffix);
	}

	public static MiaoshaOrderKey getKey(int secondes, String prefix) {
		return new MiaoshaOrderKey(secondes, prefix, "");
	}

	public static MiaoshaOrderKey getKey(int secondes, String prefix, String suffix) {
		return new MiaoshaOrderKey(secondes, prefix, suffix);
	}

	public static MiaoshaOrderKey getKey(String prefix) {
		return new MiaoshaOrderKey(prefix);
	}
}
