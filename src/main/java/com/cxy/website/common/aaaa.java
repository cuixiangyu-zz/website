package com.cxy.website.common;

import java.io.File;

/**
 * @program: website
 * @description: aaa
 * @author: CuiXiangYu
 * @create: 2020-06-14 23:42
 **/
public class aaaa {
    public static void main(String[] args){
        String aab = "/mnt/1t-3/resources\\japanvideo\\黒川サリナ\\ABP-739.mp4";
        /*aab = aab.replaceAll("\\\\","|");
        aab = aab.replaceAll("\\|","/");*/
        aab = aab.replaceAll("\\\\","/");
        System.out.println(aab);
    }
}
