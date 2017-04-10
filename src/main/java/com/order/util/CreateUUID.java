package com.order.util;

import java.util.UUID;

public class CreateUUID {
	/**
	 * 创建UUID标识
	 * @return
	 */
    public static String createUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}