package com.cxy.website.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.website.service.DownloadArtistCover;
import com.cxy.website.service.rabbitMqService.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: website
 * @description: 测试类
 * @author: CuiXiangYu
 * @create: 2020-08-28 16:47
 **/
@RestController
@RequestMapping("/rabbitMq")
public class TestController {
    @Autowired
    MsgProducer msgProducer;
    @Autowired
    DownloadArtistCover downloadArtistCover;

    @GetMapping("/testVideoName")
    public String testVideoName(){
        /*for(int i=0;i<10;i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","cxy"+i);
            msgProducer.sendToVideoName(jsonObject);
        }*/
        downloadArtistCover.getCoverUrl();
        return null;
    }
}
