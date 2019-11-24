package com.cxy.website.controller;

import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.Type;
import com.cxy.website.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: website
 * @description: 类型页面
 * @author: CuiXiangYu
 * @create: 2019-10-17 11:59
 **/
@Controller
@RequestMapping("/type")
@ResponseBody
public class TypeController {
    @Autowired
    TypeService typeService;

    @RequestMapping("/add")
    public int add(Type type){
        int count = typeService.add(type);
        return count;
    }

    @RequestMapping("/update")
    public int update(Type type){
        int count = typeService.update(type);
        return count;
    }

    @RequestMapping("/delete")
    public int delete(int id){
        int count = typeService.delete(id);
        return count;
    }

    @RequestMapping("/getByType")
    public PageInfo<Type> getByType(int pageNum, int pageSize,int type){
        PageInfo<Type> pageInfo = typeService.findByType(pageNum,pageSize,type);
        return pageInfo;
    }

    @RequestMapping("/updateType")
    public JsonData updateType(@RequestParam String url,@RequestParam Integer type){
        typeService.updateType(url,type);
        return JsonData.buildSuccess();
    }
}
