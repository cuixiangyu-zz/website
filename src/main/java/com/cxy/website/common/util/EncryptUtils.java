package com.cxy.website.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 *@Description 加密工具类
 *@Author 侯森林
 *@Date 2019-9-18
 */
public class EncryptUtils {

    public static String encrypt(String str,String salt, String algorithmName, int hashIterations)
    {
        return new SimpleHash(algorithmName,str, ByteSource.Util.bytes(salt),hashIterations).toString();
    }

}
