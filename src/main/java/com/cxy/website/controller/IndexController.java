package com.cxy.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: website
 * @description: 入口
 * @author: CuiXiangYu
 * @create: 2020-02-27 16:29
 **/
@Controller
public class IndexController {

    @GetMapping("/web")
    public String web(){
        return "/web/index";
    }

    @GetMapping("/phone")
    public String phone(){
        return "/phone/index";
    }
}
