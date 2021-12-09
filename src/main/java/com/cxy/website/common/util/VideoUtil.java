package com.cxy.website.common.util;

import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @program: website
 * @description: 视频工具
 * @author: CuiXiangYu
 * @create: 2020-02-25 13:34
 **/
public class VideoUtil {
    /**
     * 获取视频时长，单位为秒
     *
     * @param video 源视频文件
     * @return 时长（s）
     */
    private static long getVideoDuration(File video) {
        long duration = 0L;
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        try {
            ff.start();
            duration = ff.getLengthInTime() / (1000 * 1000);
            ff.stop();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
        return duration;
    }

    /**
     * 截取视频获得指定帧的图片
     *
     * @param video  源视频文件
     * @param picPath 截图存放路径
     */
    private static void getVideoPic(File video, String picPath) {
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        try {
            ff.start();
            // 截取中间帧图片(具体依实际情况而定)
            int i = 0;
            int length = ff.getLengthInFrames();
            int middleFrame = length / 2;
            Frame frame = null;
            while (i < length) {
                frame = ff.grabFrame();
                if ((i > middleFrame) && (frame.image != null)) {
                    break;
                }
                i++;
            }
            // 截取的帧图片
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage srcImage = converter.getBufferedImage(frame);
            int srcImageWidth = srcImage.getWidth();
            int srcImageHeight = srcImage.getHeight();
            // 对截图进行等比例缩放(缩略图)
            int width = 480;
            int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
            BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            File picFile = new File(picPath);
            ImageIO.write(thumbnailImage, "jpg", picFile);
            ff.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        String videoPath = "N:/pornhub/0001.mp4";
        File video = null;
        video = new File(videoPath);
        String picPath = "N:/pornhub/0001.jpg";
        getVideoPic(video, picPath);

        long duration = getVideoDuration(video);
        System.out.println("videoDuration = " + duration);
    }
*/
    /**
     * 创建视频缩略图
     * @param videoPath 视频地址
     * @param picPath   图片地址
     * @return  图片地址
     */
    public static String creatPic(String videoPath,String picPath){
        File video = new File(videoPath);
        if(video.exists()&&video.isFile()){
            getVideoPic(video, picPath);
        }
        return picPath;
    }

}
