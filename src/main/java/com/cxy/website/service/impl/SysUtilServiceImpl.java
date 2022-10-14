package com.cxy.website.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cxy.website.common.CommonStatus;
import com.cxy.website.dao.VideoMapper;
import com.cxy.website.service.SysUtilService;
import com.cxy.website.service.rabbitMqService.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2021-12-16 16:45
 **/
@Service
public class SysUtilServiceImpl implements SysUtilService {

    @Autowired
    VideoMapper videoMapper;
    @Autowired
    MsgProducer msgProducer;
    @Autowired
    RestTemplate restTemplate;


    @Override
    public void refreshCover() {
        String localAddress = CommonStatus.FILE_COVER_PREFIX + File.separator +"japanVideoCover";
        List<String> coverAddrs = videoMapper.selectCover();
        JSONObject jsonObject =new JSONObject();
        for (String coverAddr : coverAddrs) {
            String addr = CommonStatus.FILE_COVER_PREFIX+coverAddr;
            File file = new File(addr);
            if(!file.exists()){
                String videoName = file.getName().substring(0, file.getName().indexOf("."));
                jsonObject.put("videoName",videoName);
                jsonObject.put("localAddress",localAddress);
                msgProducer.sendToRefreshCover(jsonObject);
            }
        }
    }

    @Override
    public void sendNewData(){
        /*JSONObject commonData = new JSONObject();
        commonData.put("COVER_URL_PREFIX",CommonStatus.COVER_URL_PREFIX);
        commonData.put("FILE_URL_PREFIX",CommonStatus.FILE_URL_PREFIX);
        commonData.put("FILE_COVER_PREFIX",CommonStatus.FILE_COVER_PREFIX);
        commonData.put("resourcesAddrs", JSONArray.parseArray(JSON.toJSONString(CommonStatus.resourcesAddrs)));
        restTemplate.postForObject(CommonStatus.REMOTE_SYS_URL+"/sysUtil/refreshIp",commonData,String.class);*/
    }
}
