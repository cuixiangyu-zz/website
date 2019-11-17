package com.cxy.website.service.impl;

import com.cxy.website.service.DownloadPic;
import org.springframework.scheduling.annotation.Async;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @program: website
 * @description: 下载图片
 * @author: CuiXiangYu
 * @create: 2019-11-14 16:45
 **/
public class DownloadPicImpl implements DownloadPic {

    private String urlPath;
    private String localAddress;
    private String fileName;

    public DownloadPicImpl(String urlPath, String localAddress, String fileName){
        this.fileName = fileName;
        this.localAddress = localAddress;
        this.urlPath = urlPath;
    }

    @Override
    @Async("asyncServiceExecutor")
    public void run() {
        String virtualPath = "";
        try {
            // 构造URL
            URL url = new URL(urlPath);
            // 打开连接

            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //获取输入流
            InputStream input = httpUrlConn.getInputStream();
            //将字节输入流转换为字符输入流
            InputStreamReader read = new InputStreamReader(input, "utf-8");

            // 输入流
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File sf = new File(localAddress);
            if (!sf.exists()) {
                sf.mkdirs();
            }
            String filename = urlPath.substring(urlPath.lastIndexOf("/") + 1, urlPath.length());//获取服务器上图片的名称
            String index = filename.substring(filename.indexOf("."), filename.length());

            OutputStream os = new FileOutputStream(sf.getPath() + "\\" + fileName + index);
            virtualPath =  fileName + index;//存入数据库的虚拟路径
            // 开始读取
            while ((len = input.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
            return ;
        }
    }
}
