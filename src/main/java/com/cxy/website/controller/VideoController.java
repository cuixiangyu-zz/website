package com.cxy.website.controller;

import com.alibaba.fastjson.JSON;
import com.cxy.website.common.CommonStatus;
import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.*;
import com.cxy.website.model.sys.SysUser;
import com.cxy.website.service.ActorService;
import com.cxy.website.service.TypeService;
import com.cxy.website.service.UserFavoriteService;
import com.cxy.website.service.VideoService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 根据影片id获取影片详细信息：影片基本信息，演员，分类
     * @param id    影片id
     * @return  影片详细信息
     */
    @RequestMapping(value = "/getDetile",method = RequestMethod.GET)
    @ResponseBody
    public JsonData getDetile(@RequestParam Integer id){
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        Video videos = videoService.findByid(id);
        videos = videoService.getVideo(videos, id);
        return JsonData.buildSuccess(videos);
    }

    /**
     * 根据条件查询分页信息
     * @param queryData 查询条件实体类
     * @return  分页信息
     */
    @RequestMapping(value = "/getPageList" , method = RequestMethod.POST)
    @ResponseBody
    public JsonData getPageList(@RequestBody QueryData queryData){
        JsonData jsonData = videoService.findPageList(queryData.getPageNum(),queryData.getPageSize(),queryData.getActorName(),queryData.getPictureName(),queryData.getLanguage(),queryData.getTypes(),queryData.getVideoType());
        return jsonData;
    }

    /**
     * 查找目录下所有文件，并提供建议文件名
     * @param source    文件夹路径
     * @return  文件名list
     */
    @RequestMapping(value = "/selectfile" , method = RequestMethod.GET)
    @ResponseBody
    public JsonData selectfile(@RequestParam String source){
        /*File file = new File(source);
        for (File listFile : file.listFiles()) {
            for (File file1 : listFile.listFiles()) {
                file1.renameTo(new File("L:\\resources"+File.separator+file1.getName()));
            }
        }*/
        List<UpdateFileName> updateFileNames = videoService.selectFileForJapan(source);
        return JsonData.buildSuccess(updateFileNames);
    }

    /**
     * 查找目录下所有文件，并提供建议文件名
     * @param source    文件夹路径
     * @return  文件名list
     */
    @RequestMapping(value = "/selectFileForAmerican" , method = RequestMethod.GET)
    @ResponseBody
    public JsonData selectFileForAmerican(@RequestParam String source){
        /*File file = new File(source);
        for (File listFile : file.listFiles()) {
            for (File file1 : listFile.listFiles()) {
                file1.renameTo(new File("L:\\resources"+File.separator+file1.getName()));
            }
        }*/
        List<UpdateFileName> updateFileNames = videoService.selectFileForAmerican(source);
        return JsonData.buildSuccess(updateFileNames);
    }


    /**
     * 将页面信息保存到数据库
     * @param request   request
     * @param title     作品名
     * @param picurl    封面url
     * @param id    id
     * @param arrayurl  分类和演员
     * @return
     */
    @CrossOrigin
    @RequestMapping("/saveinfo")
    @ResponseBody
    public JsonData saveinfo(HttpServletRequest request, @RequestParam String title,
                           @RequestParam String picurl, @RequestParam String id, @RequestParam String arrayurl){

        videoService.saveNotDownloadInfo(title, picurl, id, arrayurl);
        return JsonData.buildSuccess();
    }

    /**
     * 将本地文件信息保存到数据库
     * @param updatefile    来源文件夹，目标文件夹，文件类型
     * @return JsonData
     */
    @RequestMapping(value = "/updatefile" , method = RequestMethod.POST)
    @ResponseBody
    public JsonData updatefile(@RequestBody Updatefile updatefile){

        videoService.updateVideoFromLocal(updatefile.getSource(),updatefile.getTarget()
                ,updatefile.getFilemap(),updatefile.getType());
        return JsonData.buildSuccess();
    }

    /**
     * 更新作品分数
     * @param id    作品id
     * @param level 作品分数
     * @return JsonData
     */
    @RequestMapping(value = "/changelevel" , method = RequestMethod.GET)
    @ResponseBody
    public JsonData changeLevel(@RequestParam String id,@RequestParam String level,@RequestParam Integer type){
        videoService.changeLevel(id,level, type);
        return JsonData.buildSuccess();
    }

    /**
     * 保存浏览记录
     * @param type  类型
     * @param id    id
     * @param startData 开始时间
     * @param watchTime 观看时间
     * @return  null
     */
    @RequestMapping(value = "/saveViewHistory" , method = RequestMethod.POST)
    @ResponseBody
    public JsonData saveViewHistory(@RequestBody Integer type,@RequestBody Integer id,@RequestBody Integer startData,@RequestBody Integer watchTime){
        videoService.saveViewHistory(type,id,startData,watchTime);
        return null;
    }

    @GetMapping("/getWatchList")
    @ResponseBody
    public JsonData getWatchList(@RequestParam String idList){
        //List<Integer> idList = videoAndIdList.getIdList();
        List<Integer> parse = (List<Integer>) JSON.parse(idList);
        return videoService.getWatchList(parse);
    }

    /**
     * 分页查询条件实体类
     */
    static class QueryData {
        Integer pageNum;
        Integer pageSize;
        String actorName;
        String pictureName;
        String language;
        List<List<Object>> types;
        Integer videoType;

        public Integer getVideoType() {
            return videoType;
        }

        public void setVideoType(Integer videoType) {
            this.videoType = videoType;
        }

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

    /**
     * 更新文件实体类
     */
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

    static class VideoAndIdList{
        List<Integer> idList;
        List<Video> videoList;

        public List<Video> getVideoList() {
            return videoList;
        }

        public void setVideoList(List<Video> videoList) {
            this.videoList = videoList;
        }

        public List<Integer> getIdList() {
            return idList;
        }

        public void setIdList(List<Integer> idList) {
            this.idList = idList;
        }
    }
}
