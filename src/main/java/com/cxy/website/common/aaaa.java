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
        String oldPath = "M:\\迅雷下载";
        File oldFile = new File(oldPath);
        for (File file : oldFile.listFiles()) {
            if(!file.isDirectory()){
                continue;
            }
            /*long size = file.length() / 1024 / 1024 / 1024;
            if(size<1){
                continue;
            }*/

            for (File listFile : file.listFiles()) {
                String name = listFile.getName();
                long fileSize = listFile.length() / 1024 / 1024 / 1024;
                if((name.endsWith(".mp4")||name.endsWith(".avi")||name.endsWith(".mpg"))&&fileSize>1){
                    listFile.renameTo(new File("M:\\新建文件夹 (2)/"+name));
                }
            }
        }
    }
}
