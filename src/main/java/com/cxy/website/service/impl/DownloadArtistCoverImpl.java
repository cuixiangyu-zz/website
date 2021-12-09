package com.cxy.website.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cxy.website.service.DownloadArtistCover;
import com.cxy.website.service.WebSiteToolsService;
import com.cxy.website.service.rabbitMqService.MsgProducer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * @program: website
 * @description: 下载演员封面图
 * @author: CuiXiangYu
 * @create: 2020-09-02 13:04
 **/
@Service
public class DownloadArtistCoverImpl implements DownloadArtistCover {

    @Autowired
    WebSiteToolsService webSiteToolsService;

    @Autowired
    MsgProducer msgProducer;

    private String baseUrl = "https://www.busjav.cam/actresses/";

    /**
     * 影片演员
     */
    private static final String VIDEO_ACTOR_PATTERN = "<img src=\"https://pics.javcdn.pw/actress/.*.jpg\" title=\".*\">";

    public void getCoverUrl() {
        for (int i = 1; i < 50; i++) {
            String url = baseUrl + i;
            HashSet<String> coverUrls = webSiteToolsService.getHtmlbyRegex(url, VIDEO_ACTOR_PATTERN);
            for (String coverUrl : coverUrls) {
                if(StringUtils.isEmpty(coverUrl)){
                    continue;
                }
                String artistName = coverUrl.substring(coverUrl.indexOf("title")+7,coverUrl.length()-2);
                String picUrl = coverUrl.substring(10,coverUrl.indexOf("title")-2);
                JSONObject picUrlJson = new JSONObject();
                picUrlJson.put("picUrl",picUrl);
                picUrlJson.put("localAddress","H:\\COVER\\japanArtistCover");
                picUrlJson.put("filename",artistName);

                msgProducer.sendToCoverUrl(picUrlJson);
            }
        }

    }
}
