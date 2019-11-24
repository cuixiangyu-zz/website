package com.cxy.website.controller;

import com.cxy.website.common.CommonStatus;
import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.*;
import com.cxy.website.service.ActorService;
import com.cxy.website.service.PictureService;
import com.cxy.website.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: website
 * @description: 漫画接口
 * @author: CuiXiangYu
 * @create: 2019-10-16 14:14
 **/
@RequestMapping("/picture")
@Controller
public class PictureController {

    @Autowired
    PictureService pictureService;

    @Autowired
    ActorService actorService;

    @Autowired
    TypeService typeService;

    /**
     * 新增
     * @param picture
     * @return
     */
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    @ResponseBody
    public int add(@RequestBody Picture picture){
        int count = pictureService.add(picture);
        return count;
    }

    /**
     * 更新
     * @param picture
     * @return
     */
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    @ResponseBody
    public int update(@RequestBody Picture picture){
        int count = pictureService.update(picture);
        return count;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    @ResponseBody
    public int update(@RequestBody Integer id){
        int count = pictureService.delete(id);
        return count;
    }

    /**
     * 根据id查找漫画
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById" , method = RequestMethod.GET)
    @ResponseBody
    public Picture getById(@RequestParam Integer id){
        Picture picture = pictureService.findById(id);
        return picture;
    }

    /**
     * 根据名字搜索漫画
     * @param name
     * @return
     */
    @RequestMapping(value = "/getByName" , method = RequestMethod.GET)
    @ResponseBody
    public Picture getByName(@RequestParam String name){
        Picture picture = pictureService.findByName(name);
        return picture;
    }

    /**
     * 根据作者搜索漫画
     * @param pageNum
     * @param pageSize
     * @param artist
     * @return
     */
    @RequestMapping(value = "/getByArtist" , method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Picture> getByArtist(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam Integer artist){
        PageInfo<Picture> pictures = pictureService.findByArtist(pageNum,pageSize,artist);
        return pictures;
    }

    /**
     * 根据语言搜索漫画
     * @param pageNum
     * @param pageSize
     * @param language
     * @return
     */
    @RequestMapping(value = "/getByLanguage" , method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Picture> getByLanguage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam String language){
        PageInfo<Picture> pictures = pictureService.findByLanguage(pageNum,pageSize,language);
        return pictures;
    }

    /**
     * 根据类型搜索漫画
     * @param pageNum
     * @param pageSize
     * @param type
     * @return
     */
    @RequestMapping(value = "/getByType" , method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Picture> getByType(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam Integer type){
        PageInfo<Picture> pictures = pictureService.findByType(pageNum,pageSize,type);
        return pictures;
    }

    /**
     * 根据id搜索漫画详细信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getDetil" , method = RequestMethod.GET)
    @ResponseBody
    public JsonData getDetil(@RequestParam Integer id){
        Picture picture = pictureService.findById(id);
        picture = pictureService.getPicture(picture,id);
        return JsonData.buildSuccess(picture);
    }



    @RequestMapping(value = "/getPageList" , method = RequestMethod.POST)
    @ResponseBody
    public JsonData getPageList(@RequestBody QueryData queryData){
        JsonData jsonData = pictureService.findPageList(queryData.getPageNum(),queryData.getPageSize(),queryData.getActorName(),queryData.getPictureName(),queryData.getLanguage(),queryData.getTypes());
        return jsonData;
    }

    /**
     * 根据地址获取文件夹下所有图片并重命名
     *
     * @param source 文件夹地址
     * @return 文件名和推荐名
     */
    @RequestMapping(value = "/getComicName" , method = RequestMethod.GET)
    @ResponseBody
    public JsonData getComicName(@RequestParam String source){
        return pictureService.getComicName(source);
    }

    /**
     * 将本地文件信息保存到数据库
     * @param updatefile    来源文件夹，目标文件夹，文件类型
     * @return JsonData
     */
    @RequestMapping(value = "/updatefile" , method = RequestMethod.POST)
    @ResponseBody
    public JsonData updatefile(@RequestBody Updatefile updatefile){

        pictureService.updatePicsFromLocal(updatefile.getSource(),updatefile.getTarget()
                ,updatefile.getFilemap(),updatefile.getType());
        return JsonData.buildSuccess();
    }

    static class QueryData {
        Integer pageNum;
        Integer pageSize;
        String actorName;
        String pictureName;
        String language;

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        List<List<Object>> types;

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
}
