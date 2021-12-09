package com.cxy.website.controller;

import com.cxy.website.common.util.Transvideo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: website
 * @description: 获取电影地址
 * @author: CuiXiangYu
 * @create: 2019-11-07 20:30
 **/

@Controller
public class Getvideo {

    @RequestMapping("/get")
    @ResponseBody
    public Map<String,List<String>> get(){

        List<String> japan = new ArrayList<>();
        List<String> american = new ArrayList<>();
        List<String> suggest = new ArrayList<>();
        Map<String,List<String>> videos = new HashMap<>();

        File file = new File("H:\\电视剧");
        File[] files = file.listFiles();
        for (File file1 : files) {
            if("japan".equals(file1.getName())){
                File[] files1 = file1.listFiles();
                for (int i = 0; i < files1.length; i++) {
                    japan.add(files1[i].getName());
                }
            }
            if("american".equals(file1.getName())){
                File[] files1 = file1.listFiles();
                for (int i = 0; i < files1.length; i++) {
                    american.add(files1[i].getName());
                }
            }
            if("suggest".equals(file1.getName())){
                File[] files1 = file1.listFiles();
                for (int i = 0; i < files1.length; i++) {
                    suggest.add(files1[i].getName());
                }
            }
        }
        videos.put("japan",japan);
        videos.put("american",american);
        videos.put("suggest",suggest);
        return videos;
    }

    @RequestMapping("/videos")
    public String video(){
        /*Transvideo transvideo = new Transvideo();
        transvideo.AviToMp4("I:\\japan/EBOD-478.AVI","I:\\japan/EBOD-478.mp4");*/
        return "/index";
    }
}
