package com.cxy.website.controller;

import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.*;
import com.cxy.website.service.ActorService;
import com.cxy.website.service.TypeService;
import com.cxy.website.service.VideoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
    public JsonData getDetile(@RequestParam Integer id){

        Video videos = videoService.findByid(id);
        videos = videoService.getVideo(videos, id);
        return JsonData.buildSuccess(videos);
    }

    @RequestMapping(value = "/getPageList" , method = RequestMethod.POST)
    @ResponseBody
    public JsonData getPageList(@RequestBody PictureController.QueryData queryData){
        JsonData jsonData = videoService.findPageList(queryData.getPageNum(),queryData.getPageSize(),queryData.getActorName(),queryData.getPictureName(),queryData.getLanguage(),queryData.getTypes());
        return jsonData;
    }

    @RequestMapping(value = "/selectfile" , method = RequestMethod.GET)
    @ResponseBody
    public JsonData selectfile(@RequestParam String source){
        /*File file = new File(source);
        for (File listFile : file.listFiles()) {
            for (File file1 : listFile.listFiles()) {
                file1.renameTo(new File("L:\\resources"+File.separator+file1.getName()));
            }
        }*/
        List<UpdateFileName> updateFileNames = videoService.selectfile(source);
        return JsonData.buildSuccess(updateFileNames);
    }

    @CrossOrigin
    @RequestMapping("/saveinfo")
    @ResponseBody
    public JsonData saveinfo(HttpServletRequest request, @RequestParam String title,
                           @RequestParam String picurl, @RequestParam String id, @RequestParam String arrayurl){

        videoService.saveNotDownloadInfo(title, picurl, id, arrayurl);
        return JsonData.buildSuccess();
    }

    @RequestMapping(value = "/updatefile" , method = RequestMethod.POST)
    @ResponseBody
    public JsonData updatefile(@RequestBody Updatefile updatefile){

        videoService.updateVideoFromLocal(updatefile.getSource(),updatefile.getTarget()
                ,updatefile.getFilemap(),updatefile.getType());
        return JsonData.buildSuccess();
    }

    static class QueryData {
        Integer pageNum;
        Integer pageSize;
        String actorName;
        String pictureName;
        String language;
        List<List<Object>> types;
        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public Integer getPageNum() {
            return pageNum;
        }

        public void setPageNum(Integer pageNum) {
            this.pageNum = pageNum;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public String getActorName() {
            return actorName;
        }

        public void setActorName(String actorName) {
            this.actorName = actorName;
        }

        public String getPictureName() {
            return pictureName;
        }

        public void setPictureName(String pictureName) {
            this.pictureName = pictureName;
        }

        public List<List<Object>> getTypes() {
            return types;
        }

        public void setTypes(List<List<Object>> types) {
            this.types = types;
        }
    }

    static class Updatefile{
        String  target;
        String  source;
        String  type;
        List<UpdateFileName> filemap;

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<UpdateFileName> getFilemap() {
            return filemap;
        }

        public void setFilemap(List<UpdateFileName> filemap) {
            this.filemap = filemap;
        }
    }
}
