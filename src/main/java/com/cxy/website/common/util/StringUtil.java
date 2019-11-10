package com.cxy.website.common.util;

/**
 * @Author: 石文静
 * @Description: 字符串工具类
 * @CreateDate: 2019/9/27  17:22
 * @UpdateDate: 2019/9/27  17:22
 * @Version: V1
 */
public class StringUtil {

    /*
     * @Author 石文静
     * @Description 判断是否是空字符串
     * @CreateDate 2019/9/27 17:23
     * @Version V1
    */
    public static boolean checkStr(String str){
        return (str == null) || (str.length() == 0);
    }

    /**
     * 判断多个字符串全部是否为空
     * @param strings
     * @return
     */
    public static boolean isAllEmpty(String... strings) {
        if (strings == null) {
            return true;
        }
        for (String str : strings) {
            if (!(str == null) || (str.length() == 0)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较字符相等
     * @param value
     * @param equals
     * @return
     */
    public static boolean equals(String value, String equals) {
        if (isAllEmpty(value, equals)) {
            return true;
        }
        return value.equals(equals);
    }

    /**
     * 消除转义字符
     * @param str
     * @return
     */
    public static String escapeXML(String str) {
        if (str == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            switch (c) {
                case '\u00FF':
                case '\u0024':
                    break;
                case '&':
                    sb.append("&");
                    break;
                case '<':
                    sb.append("<");
                    break;
                case '>':
                    sb.append(">");
                    break;
                case '\"':
                    sb.append("");
                    break;
                case '\'':
                    sb.append("'");
                    break;
                default:
                    if (c >= '\u0000' && c <= '\u001F'){
                        break;
                    }
                    if (c >= '\uE000' && c <= '\uF8FF'){
                        break;
                    }
                    if (c >= '\uFFF0' && c <= '\uFFFF'){
                        break;
                    }
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

}
