package com.runxsports.provider.cs.cms.common.util;

/**
 * @author wangjf
 * @date: 2018年08月03日
 */
public class RandomUtil {

    public static enum TYPE{
        STRING_NUM,
        NUM
    }

    public static String randomStr(int n, TYPE type) {
        String str1 ="";
        if(type .equals(TYPE.STRING_NUM)){
            str1  = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        }else if(type.equals(TYPE.NUM)){
            str1 = "1234567890";
        }
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }
}
