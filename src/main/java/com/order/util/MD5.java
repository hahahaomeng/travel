package com.order.util;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class MD5 {
	/**
	 * MD5加密
	 * @param str 需要加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String encoderByMd5(String str) throws Exception {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
	
	/**
	 * 判断密码是否相同
	 * @param newpasswd 未加密的密码
	 * @param oldpasswd 已加密的密码
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean checkpassword(String newpasswd,String oldpasswd) throws Exception {
        if(encoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }
}