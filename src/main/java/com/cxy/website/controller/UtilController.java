package com.cxy.website.controller;

import com.cxy.website.common.CommonStatus;
import com.cxy.website.common.util.VideoUtil;
import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.dao.PictureMapper;
import com.cxy.website.dao.VideoMapper;
import com.cxy.website.model.Picture;
import com.cxy.website.model.Video;
import com.cxy.website.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: website
 * @description: 工具类
 * @author: CuiXiangYu
 * @create: 2020-02-25 14:04
 **/
@RequestMapping("/util")
@Controller
@ResponseBody
public class UtilController {

    @Autowired
    VideoService videoService;

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    PictureMapper   pictureMapper;

    @GetMapping("/savePornVideo")
    public JsonData savePornVideo(){
        String videoPath = "J:\\新建文件夹";
        String picPath = "H:\\COVER\\pornHub/";
        String newPath = "J:\\resources\\pornHub/";
        File videos = new File(videoPath);
        for (File file : videos.listFiles()) {
            String videoName = file.getName().substring(0,file.getName().length()-4);
            String picDir = VideoUtil.creatPic(file.getPath(), picPath + videoName + ".jpg");
            Video video = new Video();
            video.setName(videoName);
            video.setType(CommonStatus.VIDEO_TYPE_PORN);
            video.setExist(1);
            video.setCreatTime(new Date());
            video.setVideoUrl(File.separator+"pornHub"+File.separator+file.getName());
            video.setCoverUrl(File.separator+"pornHub"+File.separator+videoName + ".jpg");
            videoService.add(video);
            file.renameTo(new File(newPath+file.getName()));
        }

        return JsonData.buildSuccess();
    }

    @GetMapping("/checkVideo")
    public JsonData checkVideo(){
        String prefix = "/mnt/";
        List<Video> videoList =  videoMapper.findAll();
        File mnt = new File(prefix);
        List<String> addrs = new ArrayList<>();
        for (File file : mnt.listFiles()) {
            addrs.add(file.getName());
        }
        for (Video video : videoList) {
            boolean exist = false;
            String fileAddr = null;
            for (String addr : addrs) {
                String address =prefix + addr + "/resources" + video.getVideoUrl();
                System.out.println(address);
                File file = new File(address);
                if(file.exists()){
                    exist = true;
                    break;
                }
            }
            if(exist){
                video.setExist(1);
            }else{
                video.setExist(2);
            }
            videoMapper.updateByPrimaryKey(video);
        }

        /*List<Picture> pictureList = pictureMapper.findAll();
        for (Picture picture : pictureList) {
            boolean exist = false;
            String fileAddr = null;
            String picAddr = null;
            for (String addr : CommonStatus.addrs) {
                String address =prefix + addr + "/resources" + picture.getPictureUrl();
                address = address.replaceAll("\\\\","|");
                address = address.replaceAll("\\|","/");
                File file = new File(address);
                if(file.exists()){
                    fileAddr = File.separator+addr + "/resources" + picture.getPictureUrl();
                    fileAddr = fileAddr.replaceAll("\\\\","|");
                    fileAddr = fileAddr.replaceAll("\\|","/");
                    picAddr = File.separator+addr + "/resources" + picture.getCoverUrl();
                    picAddr = picAddr.replaceAll("\\\\","|");
                    picAddr = picAddr.replaceAll("\\|","/");
                    exist = true;
                    break;
                }
            }
            if(exist){
                picture.setExist(1);
                picture.setCoverUrl(picAddr);
                picture.setPictureUrl(fileAddr);
            }else{
                picture.setExist(0);
            }
            pictureMapper.updateByPrimaryKey(picture);
        }*/

        return JsonData.buildSuccess();
    }
}
