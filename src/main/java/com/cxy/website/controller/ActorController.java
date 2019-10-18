package com.cxy.website.controller;

import com.cxy.website.model.Actor;
import com.cxy.website.service.ActorService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: website
 * @description: 演员页面
 * @author: CuiXiangYu
 * @create: 2019-10-17 09:42
 **/
@RequestMapping("/actor")
@Controller
public class ActorController {

    @Autowired
    ActorService actorService;

    @RequestMapping("/add")
    @ResponseBody
    public int add(Actor actor){
        int count = actorService.add(actor);
        return count;
    }

    @RequestMapping("/update")
    @ResponseBody
    public int update(Actor actor){
        int count = actorService.update(actor);
        return count;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int delete(Integer id){
        int count = actorService.delete(id);
        return count;
    }

    @RequestMapping("/findByid")
    @ResponseBody
    public Actor findByid(Integer id){
        Actor actor = actorService.findByid(id);
        return actor;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public PageInfo<Actor> findAll(int pageNum, int pageSize){
        PageInfo<Actor> actors = actorService.findAll(pageNum, pageSize);
        return actors;
    }

    @RequestMapping("/findBycountry")
    @ResponseBody
    public PageInfo<Actor> findBycountry(int pageNum, int pageSize,String country){
        PageInfo<Actor> actors = actorService.findByCountry(pageNum, pageSize,country);
        return actors;
    }

    @RequestMapping("/findByType")
    @ResponseBody
    public PageInfo<Actor> findByType(int pageNum, int pageSize,int type){
        PageInfo<Actor> actors = actorService.findByType(pageNum, pageSize,type);
        return actors;
    }

    @RequestMapping("/findByLevel")
    @ResponseBody
    public PageInfo<Actor> findByLevel(int pageNum, int pageSize,int level){
        PageInfo<Actor> actors = actorService.findByLevel(pageNum, pageSize,level);
        return actors;
    }

    @RequestMapping("/findByName")
    @ResponseBody
    public Actor findByName(String name){
        Actor actors = actorService.findByName(name);
        return actors;
    }

    @RequestMapping("/findByChineseName")
    @ResponseBody
    public Actor findByChineseName(String chineseName){
        Actor actors = actorService.findByChineseName(chineseName);
        return actors;
    }
}
