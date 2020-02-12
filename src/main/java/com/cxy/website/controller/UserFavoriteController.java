package com.cxy.website.controller;

import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.UserFavorite;
import com.cxy.website.service.UserFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: website
 * @description: 用户收藏
 * @author: CuiXiangYu
 * @create: 2020-02-01 10:40
 **/
@RestController
@RequestMapping("/userFavorite")
public class UserFavoriteController {

    @Autowired
    UserFavoriteService userFavoriteService;

    @PostMapping("/addFavorite")
    public JsonData addFavorite(@RequestBody UserFavorite userFavorite){
        UserFavorite favorite = userFavoriteService.addFavorite(userFavorite);
        return JsonData.buildSuccess(favorite);
    }

    @GetMapping("/deleteFavorite")
    public JsonData deleteFavorite(@RequestParam Integer id){
        userFavoriteService.deleteFavorite(id);
        return JsonData.buildSuccess();
    }

    @GetMapping("/getList")
    public JsonData getList(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam Integer type,@RequestParam Integer level){
        return userFavoriteService.getList(pageNum,pageSize,type,level);
    }
}
