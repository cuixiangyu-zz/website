package com.cxy.website.common.config;

import com.cxy.website.common.CommonStatus;
import com.cxy.website.service.SysUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @program: website
 * @description: 定时任务
 * @author: CuiXiangYu
 * @create: 2021-12-22 19:57
 **/
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
public class ScheduledUtil {

    @Autowired
    SysUtilService sysUtilService;

    //3.添加定时任务
    @Scheduled(cron="0 */15 * * * ?")
    public void configureTasks() {
        if(CommonStatus.SYS_TYPE.equals("remote")){
            return;
        }
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        sysUtilService.sendNewData();
    }

}
