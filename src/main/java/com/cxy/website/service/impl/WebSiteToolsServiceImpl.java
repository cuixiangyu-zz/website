package com.cxy.website.service.impl;

import com.cxy.website.common.CommonStatus;
import com.cxy.website.common.util.StringUtil;
import com.cxy.website.service.DownloadPic;
import com.cxy.website.service.WebSiteToolsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2019-11-12 14:03
 **/
@Service
public class WebSiteToolsServiceImpl implements WebSiteToolsService {


    /**
     * 影片分类
     */
    private static final Pattern VIDEO_TYPE_PATTERN = Pattern.compile("href=\"https://www.*/genre/[A-Za-z0-9-]*");

    /**
     * 影片演员
     */
    private static final Pattern VIDEO_ACTOR_PATTERN = Pattern.compile("<a href=\"https://www.*/star/.*\" title=\".*\">");

    /**
     * 影片图片
     */
    //private static final Pattern VIDEO_COVER_PATTERN = Pattern.compile("img src=\"https://.*/cover/.*.jpg");
    private static final Pattern VIDEO_COVER_PATTERN = Pattern.compile("img src=\".*/cover/.*.jpg");

    /**
     * 影片标题
     */
    private static final Pattern VIDEO_TITLE_PATTERN = Pattern.compile("<title>.*</title>");




    /**
     * 根据url下载图片并保存到本地
     *
     * @param urlPath          图片url
     * @param localAddress 保存地址
     * @param fileName     图片名
     * @return 图片名
     */
    @Override
    public String  downloadPics(String urlPath, String localAddress, String fileName) {
        DownloadPic downloadPic= new DownloadPicImpl(urlPath,localAddress,fileName);
        downloadPic.run();
        return null;
    }

    /**
     * 根据url地址和正则表达式返回复合的字符串
     *
     * @param weburl 网页地址
     * @param regex  正则表达式
     * @return 符合的字符串
     */
    @Override
    public HashSet<String> getHtmlbyRegex(String weburl, String regex) {
        HashSet<String> coversrc = new HashSet<String>();
        try {
            //建立连接
            URL url = new URL(weburl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //获取输入流
            InputStream input = httpUrlConn.getInputStream();
            //将字节输入流转换为字符输入流
            InputStreamReader read = new InputStreamReader(input, "utf-8");
            //为字符输入流添加缓冲
            BufferedReader br = null;
            br = new BufferedReader(read);
            // 读取返回结果
            String data = "";
            while ((data = br.readLine()) != null) {

                Pattern pattern = null;

                Matcher matcher = null;
                pattern = Pattern
                        .compile(regex);
                matcher = pattern.matcher(data);

                while (matcher.find()) {
                    matcher.group().length();

                    String newLink = matcher.group(0).trim(); // 链接
                    coversrc.add(newLink);
                }
            }
            // 释放资源
            br.close();
            read.close();
            input.close();
            httpUrlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coversrc;
    }

    /**
     * 根据影片id查找影片基本信息
     *
     * @param id 影片id
     * @param type
     * @return 影片基本信息
     */
    @Override
    public Map<String, Object> getvideoinfo(String id, String type) {
        Map<String, Object> coversrc = new HashMap<String, Object>();
        List<String> category = new ArrayList<>();
        List<String> artists = new ArrayList<>();
        String title = "";
        String picurl = "";
        try {
            //建立连接
            URL url;
            if(StringUtils.isEmpty(type)||type.contains("japan")){
                url = new URL(CommonStatus.JAPAN_URL + id);
            }else{
                url = new URL(CommonStatus.AMERICAN_URL + id);
            }

            // URL url = new URL("https://www.seedmm.in/" + id);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
            httpUrlConn.setRequestProperty("cookie",CommonStatus.COOKIE);
            //获取输入流
            InputStream input = httpUrlConn.getInputStream();
            //将字节输入流转换为字符输入流
            InputStreamReader read = new InputStreamReader(input, "utf-8");
            //为字符输入流添加缓冲
            BufferedReader br = null;
            br = new BufferedReader(read);
            // 读取返回结果
            String data = "";
            while ((data = br.readLine()) != null) {
                //System.out.println(data);
                //匹配分类
                Matcher matcher = null;
                matcher = VIDEO_TYPE_PATTERN.matcher(data);
                while (matcher.find()) {
                    matcher.group().length();
                    String newLink = matcher.group(0).trim(); // 链接
                    category.add(newLink.substring(newLink.indexOf("genre/") + 6, newLink.length()));
                }

                //匹配演员
                Matcher matcher1 = null;
                matcher1 = VIDEO_ACTOR_PATTERN.matcher(data);
                while (matcher1.find()) {
                    matcher1.group().length();
                    String newLink = matcher1.group(0).trim(); // 链接
                    String artist = newLink.substring(newLink.indexOf("title=") + 7, newLink.length() - 2);
                    if (artists.indexOf(artist) < 0) {
                        artists.add(artist);
                    }
                }

                //匹配标题
                Matcher matcher2 = null;
                matcher2 = VIDEO_TITLE_PATTERN.matcher(data);
                while (matcher2.find()) {
                    matcher2.group().length();
                    String newLink = matcher2.group(0).trim(); // 链接
                    title = newLink.substring(newLink.indexOf("<title>") + 7, newLink.indexOf("</title>"));
                }

                //匹配图片
                Matcher matcher3 = null;
                matcher3 = VIDEO_COVER_PATTERN.matcher(data);
                while (matcher3.find()) {
                    matcher3.group().length();
                    String newLink = matcher3.group(0).trim(); // 链接
                    picurl = newLink.substring(newLink.indexOf("src") + 5, newLink.length());
                }
            }
            // 释放资源
            br.close();
            read.close();
            input.close();
            httpUrlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        coversrc.put("category", category);
        coversrc.put("artists", artists);
        coversrc.put("title", title);
        if(picurl!=null&&!picurl.contains("https://www.")){
            coversrc.put("picurl", CommonStatus.COVER_URL_PREFIX_NET+picurl);
        }else{
            coversrc.put("picurl", picurl);
        }
        return coversrc;
    }

    /**
     * 重命名文件
     *
     * @param filePath 文件夹路径
     */
    @Override
    public void renameFiles(String filePath) {
        File file = new File(filePath);
        File[] listFiles = file.listFiles();
        for (File file2 : listFiles) {
            String filename = file2.getName();
            String index = filename.substring(filename.length()-4,filename.length());
            String name = filename.substring(0,filename.length()-4);
            if(name.indexOf("[44x.me]")>=0||name.indexOf("[88q.me]")>=0||name.indexOf("[99u.me]")>=0){
                name=name.substring(8, name.length());
            }
            if(name.indexOf("[ThZu.Cc]")>=0){
                name=name.substring(9, name.length());
            }
            if(name.indexOf("HD-")>=0){
                name=name.substring(3, name.length());
            }
            if(name.indexOf("hjd2048.com")>=0){
                name=name.substring(16, name.length());
            }
            if(name.indexOf("shjd2048.com")>=0){
                name=name.substring(17, name.length());
            }
            if(name.indexOf("-h264")>=0){
                name=name.substring(0, name.length()-5);
            }
            if(name.indexOf("mp4")>=0){
                name=name.substring(0, name.length()-3);
            }
            name.trim();
            if(name.indexOf("-")<0){
                name=name.substring(0, name.length()-3)+"-"+name.substring(name.length()-3, name.length());
            }
            name=name.toUpperCase();
            new File(filePath+File.separator+filename).renameTo(new File(filePath+File.separator+name+index));
            System.out.println("重命名文件："+filePath+"-----"+filename+"-------"+name);
        }
    }

    /**
     * 移动文件
     * @param oldPath   源文件地址
     * @param newPath   目标文件地址
     */
    @Override
    public void moveFiles(String oldPath, String newPath) {
        File oldFile = new File(oldPath);
        File targetFile = new File(newPath);
        if(oldFile.exists()&&oldFile.isFile()){
            if(targetFile.isDirectory()){
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                oldFile.renameTo(new File(newPath+File.separator+oldFile.getName()));
            }else if(!targetFile.exists()){
                if(!targetFile.getParentFile().exists()){
                    targetFile.getParentFile().mkdirs();
                }
                oldFile.renameTo(targetFile);
            }
        }else if(oldFile.isDirectory()){
            if(targetFile.isFile()){
                return;
            }
            File[] listFiles = oldFile.listFiles();
            for(File file:listFiles){
                moveFiles(file.getPath(),newPath);
            }
        }
    }
}
