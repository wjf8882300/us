/*
 *  * Copyright (c) 2018 runxs.com. All Rights Reserved.
 */

package com.tonggu.provider.us.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jeff.hou
 * @ClassName: MD5
 * @Description: MD5 工具类
 * @date: 2018年04月21日  01:39
 **/

public class MD5Util {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * J 转换byte到16进制
     *
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * J 编码
     *
     * @param origin 需加密的字符串
     * @return String 加密后的字符串
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }

    /**
     * MessageDigest 为 JDK 提供的加密类
     *
     * @param bytes 需加密的字节数组
     * @return String 加密后的字符串
     */
    public static String MD5Encode(byte[] bytes) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(bytes));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
    
    /** 
     * MessageDigest 为 JDK 提供的加密类
     *
     * @Title: MD5EncodeBytes 
     * @Description: TODO
     * @param @param binaryData
     * @param @return 
     * @return byte[] 
     * @throws 
     */
    public static byte[] MD5EncodeBytes(byte[] binaryData) {
        MessageDigest messageDigest = null;
        try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        messageDigest.update(binaryData);
        return messageDigest.digest();
    }

    /**
     * 通过特定编码格式加密字符串
     *
     * @param origin      需加密的字符串
     * @param charsetName 编码格式
     * @return String 加密后的字符串
     */
    public static String MD5Encode(String origin, String charsetName) {
        origin = origin.trim();
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetName)));
        } catch (Exception ex) {
        }
        return resultString;
    }
}