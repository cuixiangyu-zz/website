package com.cxy.website.controller;

import com.cxy.website.common.CommonStatus;
import com.cxy.website.common.util.VideoUtil;
import com.cxy.website.common.util.web.JsonData;
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
import java.util.Date;

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

    @GetMapping("/savePornVideo")
    public JsonData savePornVideo(){
        String videoPath = "N:\\newFile/";
        String picPath = "G:\\COVER\\pornHub/";
        String newPath = "N:\\resources\\pornHub/";
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
}
