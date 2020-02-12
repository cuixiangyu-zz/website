package com.cxy.website.common;

/**
 * @program: website
 * @description: 常量类
 * @author: CuiXiangYu
 * @create: 2019-11-08 10:16
 **/
public class CommonStatus {

    public static final Integer ACTOR_TYPE_JAPAN = 1;
    public static final Integer ACTOR_TYPE_AMERICAN = 2;
    public static final Integer ACTOR_TYPE_PICTURE = 3;

    public static final Integer PICTURE_EXIST_EXIST = 1;
    public static final Integer PICTURE_EXIST_DELETE = 2;
    public static final Integer PICTURE_EXIST_NOTINDATABASE = 3;

    public static final Integer IS_VALID_NO = 0;
    public static final Integer IS_VALID_TES = 1;

    public static final Integer IS_DEL_NO = 0;
    public static final Integer IS_DEL_YES = 1;

    public static final Integer VIDEO_TYPE_JAPAN = 1;
    public static final Integer VIDEO_TYPE_AMERICAN = 2;
    public static final Integer VIDEO_TYPE_COMIC = 3;

    public static final Integer VIDEO_EXIST_EXIST = 1;
    public static final Integer VIDEO_EXIST_DELETE = 2;
    public static final Integer VIDEO_EXIST_NOTINDATABASE = 3;

    public static final Integer TYPE_TYPE_JAPAN = 1;
    public static final Integer TYPE_TYPE_AMERICAN = 2;
    public static final Integer TYPE_TYPE_PICTURE = 3;
    public static final Integer TYPE_TYPE_COMIC = 4;
    public static final Integer TYPE_TYPE_ARTIST = 5;

    public static final Integer FAVORITE_HISTORY_TYPE_JAPAN = 1;
    public static final Integer FAVORITE_HISTORY_TYPE_AMERICAN = 2;
    public static final Integer FAVORITE_HISTORY_TYPE_ANIMATE = 3;
    public static final Integer FAVORITE_HISTORY_TYPE_SMALL_VIDEO = 4;
    public static final Integer FAVORITE_HISTORY_TYPE_PICTURE = 5;

    public static final String FILE_ADDRESS_PREFIX = "K:\\resources";
    public static final String FILE_URL_PREFIX = "http://192.168.1.8:5002/website/resources";
//public static final String FILE_URL_PREFIX = "http://www.caesium.top:5002/website/resources";
    public static final String FILE_COVER_PREFIX = "G:/COVER";
}
