package com.cxy.website.common.util;

import ws.schild.jave.*;
import java.io.File;
import ws.schild.jave.AudioAttributes;
import ws.schild.jave.VideoAttributes;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;


/**
 * @program: website
 * @description: 视频格式转换
 * @author: CuiXiangYu
 * @create: 2019-11-19 20:35
 **/
public class Transvideo {

    public  void AviToMp4(String oldPath,String newPath) {
//		File source = new File("C:\\shiping\\1.avi");
//	    File target = new File("C:\\shiping\\2019-06-27333333测试.mp4");
        File source = new File(oldPath);
        File target = new File(newPath);

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame"); //音频编码格式
        audio.setBitRate(new Integer(800000));
        audio.setChannels(new Integer(1));
        //audio.setSamplingRate(new Integer(22050));
        VideoAttributes video = new VideoAttributes();
        video.setCodec("libx264");//视频编码格式
        video.setBitRate(new Integer(3200000));
        video.setFrameRate(new Integer(15));//数字设置小了，视频会卡顿
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp4");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        MultimediaObject multimediaObject = new MultimediaObject(source);
        try {

            encoder.encode(multimediaObject, target, attrs);

        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InputFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (EncoderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
