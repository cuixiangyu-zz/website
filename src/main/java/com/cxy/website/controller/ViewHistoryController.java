package com.cxy.website.controller;

import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.ViewHistory;
import com.cxy.website.service.ViewHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @program: website
 * @description: 浏览记录
 * @author: CuiXiangYu
 * @create: 2020-02-01 10:46
 **/
@RestController
@RequestMapping("/viewHistory")
public class ViewHistoryController {

    @Autowired
    ViewHistoryService  viewHistoryService;

    @PostMapping("/addHistory")
    public JsonData addHistory(@RequestBody ViewHistory viewHistory){
        viewHistoryService.addHistory(viewHistory);
        return JsonData.buildSuccess();
    }

    @GetMapping("/getHistory")
    public JsonData getHistory(@RequestParam Integer pageNum, @RequestParam Integer pageSize ,@RequestParam(required = false) Integer type,@RequestParam(required = false) String startTime,@RequestParam(required = false) String endTime){
        return viewHistoryService.getHistory(pageNum,pageSize,type,startTime,endTime);
    }

}
