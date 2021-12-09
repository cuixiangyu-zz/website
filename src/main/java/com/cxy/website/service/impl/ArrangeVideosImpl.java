package com.cxy.website.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cxy.website.common.CommonStatus;
import com.cxy.website.model.Actor;
import com.cxy.website.model.Level;
import com.cxy.website.model.UpdateFileName;
import com.cxy.website.model.Video;
import com.cxy.website.service.*;
import com.cxy.website.service.rabbitMqService.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2020-08-31 18:08
 **/
@Service
public class ArrangeVideosImpl implements ArrangeVideos {

    @Autowired
    private MsgProducer msgProducer;

    public void update(String source, String target, String type, UpdateFileName updateFileName) {

        JSONObject videoNameQueue = new JSONObject();
        videoNameQueue.put("source",source);
        videoNameQueue.put("target",target);
        videoNameQueue.put("type",type);
        videoNameQueue.put("updateFileName",updateFileName);
        msgProducer.sendToVideoName(videoNameQueue);
    }
}
