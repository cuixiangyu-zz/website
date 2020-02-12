package com.cxy.website.common.aop;

import java.io.File;

/**
 * @program: website
 * @description: huoquwenjianming
 * @author: CuiXiangYu
 * @create: 2020-02-07 19:42
 **/
public class GetFileName {
    public static void main(String[] args){
        File file = new File("J:\\迅雷下载");
        File[] files = file.listFiles();
        for (File file1 : files) {
            //System.out.println(file1.getName());
            if(file1.isFile()&&file1.getName().indexOf(".mp4")>0){
                file1.renameTo(new File("N:\\newFile\\"+file1.getName()));
                System.out.println("aaaaaaaaaa");
            }
        }
        System.out.println("123456");
    }
}
