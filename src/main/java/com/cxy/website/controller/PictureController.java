package com.cxy.website.controller;

import com.cxy.website.model.Actor;
import com.cxy.website.model.Picture;
import com.cxy.website.model.PictureDetil;
import com.cxy.website.model.Type;
import com.cxy.website.service.ActorService;
import com.cxy.website.service.PictureService;
import com.cxy.website.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public PictureDetil getDetil(@RequestParam Integer id){
        PictureDetil pictureDetil= new PictureDetil();
        Picture picture = pictureService.findById(id);
        List<Actor> actors = actorService.findByPictureid(id);
        List<Type> types = typeService.findByPictureid(id);
        pictureDetil.setPicture(picture);
        pictureDetil.setActors(actors);
        pictureDetil.setTypes(types);
        return pictureDetil;
    }
}
