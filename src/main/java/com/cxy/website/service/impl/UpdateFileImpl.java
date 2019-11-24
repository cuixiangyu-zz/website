package com.cxy.website.service.impl;

import com.cxy.website.common.CommonStatus;
import com.cxy.website.model.Actor;
import com.cxy.website.model.Level;
import com.cxy.website.model.UpdateFileName;
import com.cxy.website.model.Video;
import com.cxy.website.service.*;
import org.springframework.scheduling.annotation.Async;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2019-11-16 22:21
 **/
public class UpdateFileImpl implements UpdateFile {
    private String source;
    private String target;
    private String type;
    private UpdateFileName updateFileName;
    private WebSiteToolsService webSiteToolsService;
    private VideoService videoService;
    private TypeService typeService;
    private ActorService actorService;
    private LevelService levelService;

    public UpdateFileImpl(String source, String target, String type, UpdateFileName updateFileName, WebSiteToolsService webSiteToolsService
            , VideoService videoService, TypeService typeService, ActorService actorService, LevelService levelService) {
        this.source = source;
        this.target = target;
        this.type = type;
        this.updateFileName = updateFileName;
        this.webSiteToolsService = webSiteToolsService;
        this.videoService = videoService;
        this.typeService = typeService;
        this.actorService = actorService;
        this.levelService = levelService;
    }


    private synchronized void update(Video video, Map<String, Object> videoinfo) {
        videoService.add(video);
        Video video1 = videoService.findByName(video.getName());
        if (videoinfo.get("category") != null && ((List) videoinfo.get("category")).size() > 0) {
            typeService.updateVideoType(video1.getId(), (List<String>) videoinfo.get("category"));
        }
        if (videoinfo.get("artists") != null && ((List) videoinfo.get("artists")).size() > 0) {
            actorService.updateVideoActor(video1.getId(), (List<String>) videoinfo.get("artists"));
        }
    }

    @Async("asyncServiceExecutor")
    public void update() {
        String fileaddress = source + File.separator + updateFileName.getFilename();
        String suggestname = updateFileName.getSuggestname();

        File file = new File(fileaddress);
        Video video = new Video();
        String filename = suggestname.substring(0, suggestname.indexOf("."));

        //从网页获取视频基本信息
        Map<String, Object> videoinfo = webSiteToolsService.getvideoinfo(filename);

        if (videoinfo == null || videoinfo.get("title") == null || videoinfo.get("title") == "") {
            return;
        }
        Video oldvideo = videoService.findByName(videoinfo.get("title").toString());
        if (oldvideo != null && oldvideo.getExist() == 1) {
            List<Actor> actorList = actorService.findByVideoid(oldvideo.getId());
            if (actorList.size() == 1) {
                String targetPath = target + File.separator + type + File.separator +
                        actorList.get(0) + File.separator + suggestname;
                webSiteToolsService.moveFiles(fileaddress, targetPath);
                oldvideo.setVideoUrl(File.separator + type + File.separator +
                        actorList.get(0) + File.separator + suggestname);
            } else {
                String targetPath = target + File.separator + type + File.separator +
                        "多作者" + File.separator + suggestname;
                webSiteToolsService.moveFiles(fileaddress, targetPath);
                oldvideo.setVideoUrl(File.separator + type + File.separator +
                        "多作者" + File.separator + suggestname);
            }
            oldvideo.setExist(1);
            videoService.update(oldvideo);
            return;
        } else if (oldvideo != null && oldvideo.getExist() == 1) {
            return;
        }
        List<String> artists = (List) videoinfo.get("artists");

        if (artists != null && artists.size() > 0) {
            for (String artist : artists) {
                Actor actor = actorService.findByName(artist);
                if (actor == null) {
                    Actor actor1 = new Actor();
                    actor1.setName(artist);
                    actor1.setChineseName(artist);
                    if (this.type.equals("english")) {
                        actor1.setType(CommonStatus.ACTOR_TYPE_AMERICAN);
                    } else if (this.type.equals("japanvideo")) {
                        actor1.setType(CommonStatus.ACTOR_TYPE_JAPAN);
                    }

                    actor1.setCreatTime(new Date());
                    actorService.add(actor1);
                    Actor actor2 = actorService.findByName(artist);
                    Level level = new Level();
                    level.setProductionId(actor2.getId());
                    level.setLevel("0");
                    level.setUserId(0);
                    level.setProductionType(5);
                    levelService.add(level);
                }
            }
        }
        //移动视频文件到对应演员的文件夹
        if (artists.size() == 1) {
            String targetPath = target + File.separator + type + File.separator +
                    artists.get(0) + File.separator + suggestname;
            webSiteToolsService.moveFiles(fileaddress, targetPath);
            video.setVideoUrl(File.separator + type + File.separator +
                    artists.get(0) + File.separator + suggestname);
        } else {
            String targetPath = target + File.separator + type + File.separator +
                    "多作者" + File.separator + file.getName();
            webSiteToolsService.moveFiles(fileaddress, targetPath);
            video.setVideoUrl(File.separator + type + File.separator +
                    "多作者" + File.separator + suggestname);
        }

        //下载视频封面
        if (videoinfo.get("picurl") != null) {
            webSiteToolsService.downloadPics(videoinfo.get("picurl").toString(), CommonStatus.FILE_COVER_PREFIX + File.separator +
                    "japanVideoCover", filename);
            video.setCoverUrl(File.separator +
                    "japanVideoCover" + File.separator + filename + ".jpg");
        }
        if (this.type.equals("english")) {
            video.setType(CommonStatus.VIDEO_TYPE_AMERICAN);
        } else if (this.type.equals("japanvideo")) {
            video.setType(CommonStatus.VIDEO_TYPE_JAPAN);
        }
        video.setExist(CommonStatus.VIDEO_EXIST_EXIST);
        video.setName(videoinfo.get("title") == null ? filename : videoinfo.get("title").toString());
        video.setCreatTime(new Date());
        update(video, videoinfo);
    }
}
