package com.cxy.website.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cxy.website.common.CommonStatus;
import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.Picture;
import com.cxy.website.model.Video;
import com.cxy.website.model.sys.SysUser;
import com.cxy.website.service.PictureService;
import com.cxy.website.service.SysUtilService;
import com.cxy.website.service.VideoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.cxy.website.common.CommonStatus.resourcesAddrs;

/**
 * @program: website
 * @description: 工具
 * @author: CuiXiangYu
 * @create: 2021-12-16 16:42
 **/
@RestController
@RequestMapping("/sysUtil")
public class SysUtilController {

    @Autowired
    SysUtilService sysUtilService;
    @Autowired
    VideoService videoService;
    @Autowired
    PictureService pictureService;

    /**
     * 重新下载封面
     *
     * @return
     */
    @RequestMapping("refreshCover")
    public JsonData refreshCover() {
        sysUtilService.refreshCover();
        return JsonData.buildSuccess();
    }

    @PostMapping("refreshIp")
    public JsonData refreshIp(HttpServletRequest request, @RequestBody String commonData) {
        JSONObject jsonObject = JSONObject.parseObject(commonData);
        CommonStatus.COVER_URL_PREFIX = jsonObject.getString("COVER_URL_PREFIX");
        CommonStatus.resourcesAddrs = JSONArray.parseArray(jsonObject.getString("resourcesAddrs"), String.class);
        String ipAddress = request.getRemoteAddr();
        CommonStatus.BASE_SYS_OUT_URL =ipAddress;
        System.out.println(ipAddress);
        String oldUrl = jsonObject.getString("FILE_URL_PREFIX");
        String newUrl = "http://" + ipAddress + ":5003/";
        CommonStatus.BASE_SYSTEM_URL = "http://" + ipAddress + ":5004/website";
        CommonStatus.FILE_URL_PREFIX = newUrl;
        String newCoverUrl = CommonStatus.COVER_URL_PREFIX.replaceAll(oldUrl, newUrl);
        CommonStatus.COVER_URL_PREFIX = newCoverUrl;
        CommonStatus.FILE_COVER_PREFIX = jsonObject.getString("FILE_COVER_PREFIX");

        System.out.println("=======================更新参数=============================");
        System.out.println("FILE_URL_PREFIX==================="+CommonStatus.FILE_URL_PREFIX);
        System.out.println("COVER_URL_PREFIX==================="+CommonStatus.COVER_URL_PREFIX);
        System.out.println("FILE_COVER_PREFIX==================="+CommonStatus.FILE_COVER_PREFIX);
        System.out.println("BASE_SYSTEM_URL==================="+CommonStatus.BASE_SYSTEM_URL);
        System.out.println("BASE_SYS_OUT_URL==================="+CommonStatus.BASE_SYS_OUT_URL);
        System.out.println("resourcesAddrs==================="+JSON.toJSONString(CommonStatus.resourcesAddrs));
        System.out.println("=======================更新结束=============================");
        return JsonData.buildSuccess();
    }

    @GetMapping("/getVideoDetail")
    public JsonData getVideoDetail(Integer id) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

        Video videos = videoService.findByid(id);
        List<String> address = videoService.getAddress(videos);

        return JsonData.buildSuccess(JSON.toJSONString(address));
    }

    @GetMapping("/getPictureDetail")
    public JsonData getPictureDetail(Integer id) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

        Picture picture = pictureService.findById(id);
        List<String> address = pictureService.getAddress(picture);

        return JsonData.buildSuccess(JSON.toJSONString(address));
    }

    @PostMapping("/getPictureCoverList")
    public JsonData getPictureCoverList(@RequestBody JSONArray idList) {
        JSONObject result = new JSONObject();
        for( Object o : idList) {
            Picture picture = pictureService.findById((Integer) o);
            String cover = pictureService.getCover(picture);
            result.put(o.toString(),cover);
        }
        return JsonData.buildSuccess(result);
    }

    @GetMapping("/checkExit")
    public JsonData checkExit(String url){
        if(url==null||url==""){
            return JsonData.buildError();
        }
        String name = url.substring(url.lastIndexOf("/")+1, url.length());
        List<Video> videos = videoService.checkExist(name);
        if(videos!=null&&videos.size()>0){
            return JsonData.buildSuccess(true);
        }
        return JsonData.buildSuccess(false);
    }
}
