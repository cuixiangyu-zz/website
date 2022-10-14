package com.cxy.website.service;

import java.util.HashSet;
import java.util.Map;

/**
 * 网页工具
 * @author 65797
 */
public interface WebSiteToolsService {

    /**
     * 根据url下载图片并保存到本地
     * @param url   图片url
     * @param localAddress  保存地址
     * @param fileName  图片名
     * @return 是否下载成功
     */
    String downloadPics(String url,String localAddress , String fileName);

    /**
     * 根据url地址和正则表达式返回复合的字符串
     * @param url 网页地址
     * @param regex 正则表达式
     * @return  符合的字符串
     */
    HashSet<String> getHtmlbyRegex(String url, String regex);

    /**
     * 根据影片id查找影片基本信息
     * @param id 影片id
     * @param type
     * @return 影片基本信息
     */
    Map<String,Object> getvideoinfo(String id, String type);

    /**
     * 重命名文件
     * @param filePath 文件夹路径
     */
    void renameFiles(String filePath);

    /**
     * 移动文件
     * @param oldPath   源文件地址
     * @param newPath   目标文件地址
     */
    void moveFiles(String oldPath, String newPath);
}
