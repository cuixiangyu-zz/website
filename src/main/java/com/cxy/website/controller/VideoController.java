package com.cxy.website.controller;

import com.cxy.website.model.Actor;
import com.cxy.website.model.Type;
import com.cxy.website.model.Video;
import com.cxy.website.model.VideoDetile;
import com.cxy.website.service.ActorService;
import com.cxy.website.service.TypeService;
import com.cxy.website.service.VideoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: website
 * @description: 电影页面
 * @author: CuiXiangYu
 * @create: 2019-10-16 16:06
 **/
@RequestMapping("/video")
@Controller
public class VideoController {
    @Autowired
    VideoService videoService;

    @Autowired
    TypeService typeService;

    @Autowired
    ActorService actorService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public int add(@RequestBody Video video){
        int count = videoService.add(video);
        return count;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public int update(@RequestBody Video video){
        int count = videoService.update(video);
        return count;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public int delete(@RequestBody Integer id){
        int count = videoService.delete(id);
        return count;
    }

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public Video getById(@RequestParam Integer id){
        Video video = videoService.findByid(id);
        return video;
    }

    @RequestMapping(value = "/getByName",method = RequestMethod.GET)
    @ResponseBody
    public Video getByName(@RequestParam String name){
        Video video = videoService.findByName(name);
        return video;
    }

    @RequestMapping(value = "/getByType",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Video> getByType(@RequestParam Integer pageNum,@RequestParam Integer pageSize ,@RequestParam Integer type){
        PageInfo<Video> videos = videoService.findByType(pageNum,pageSize,type);
        return videos;
    }

    @RequestMapping(value = "/getByActor",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Video> getByActor(@RequestParam Integer pageNum,@RequestParam Integer pageSize ,@RequestParam Integer actorid){
        PageInfo<Video> videos = videoService.findByActor(pageNum,pageSize,actorid);
        return videos;
    }

    @RequestMapping(value = "/getDetile",method = RequestMethod.GET)
    @ResponseBody
    public VideoDetile getDetile(@RequestParam Integer id){
        VideoDetile videoDetile = new VideoDetile();
        Video videos = videoService.findByid(id);
        List<Type> typeList =  typeService.findByVideoId(id);
        List<Actor> actorList = actorService.findByVideoid(id);
        videoDetile.setTypeList(typeList);
        videoDetile.setActorList(actorList);
        videoDetile.setVideo(videos);
        return videoDetile;
    }
}
