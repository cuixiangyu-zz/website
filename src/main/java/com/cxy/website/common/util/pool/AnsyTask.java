package com.cxy.website.common.util.pool;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class AnsyTask {

    private ExecutorService executorService = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(10));

    //"http://127.0.0.1:801/?"Func=Sound_Play&Info=请2019051710002640号，蔡培坤，到磁共振室就诊。请2019052000000100号，陈水文，到磁共振室准备就诊。&zsName=
    public void executeTask(String urlstr) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url;
                    URLConnection urlConn;
                    DataInputStream dis;
                    url = new URL(urlstr);
                    urlConn = url.openConnection();
                    urlConn.setRequestProperty("Accept-Charset", "GBK");
                    urlConn.setConnectTimeout(2000);
                    urlConn.setReadTimeout(1000);
                    urlConn.setDoInput(true);
                    urlConn.setUseCaches(false);
                    dis = new DataInputStream(urlConn.getInputStream());
                    byte[] buffer = new byte[2048];
                    int length = 0;
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    while ((length = (dis.read(buffer))) != -1)
                    {
                        outStream.write(buffer, 0, length);
                    }
                    outStream.close();
                    dis.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
