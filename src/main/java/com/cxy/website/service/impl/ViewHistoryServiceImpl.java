package com.cxy.website.service.impl;

import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.dao.ViewHistoryMapper;
import com.cxy.website.model.Picture;
import com.cxy.website.model.Video;
import com.cxy.website.model.ViewHistory;
import com.cxy.website.model.sys.SysUser;
import com.cxy.website.service.PictureService;
import com.cxy.website.service.VideoService;
import com.cxy.website.service.ViewHistoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2020-02-01 10:06
 **/
@Service
public class ViewHistoryServiceImpl implements ViewHistoryService {

    @Autowired
    ViewHistoryMapper viewHistoryMapper;

    @Autowired
    VideoService videoService;

    @Autowired
    PictureService pictureService;
    /**
     * 添加浏览历史
     *
     * @param viewHistory 浏览历史
     */
    @Override
    public void addHistory(ViewHistory viewHistory) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        viewHistory.setUserId(user.getId());
        viewHistoryMapper.insert(viewHistory);
    }

    /**
     * 查找浏览历史
     *
     * @param type      类型
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 浏览历史
     */
    @Override
    public JsonData getHistory(Integer pageNum, Integer pageSize , Integer type, String startTime, String endTime) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(pageNum, pageSize);
        List<ViewHistory> viewHistoryList = new ArrayList<>();
        if(type==null){
            viewHistoryList = viewHistoryMapper.getVideoHistory(user.getId(),startTime,endTime);
        }else{
            viewHistoryList = viewHistoryMapper.getHistory(user.getId(),type,startTime,endTime);
        }

        for (ViewHistory viewHistory : viewHistoryList) {
            if(viewHistory.getType()==5){
                Picture picture = pictureService.findById(viewHistory.getVideoId());
                if(picture==null){
                    continue;
                }
                picture = pictureService.getPicture(picture, picture.getId(), null);
                viewHistory.setPicture(picture);
            }else{
                Video video = videoService.findByid(viewHistory.getVideoId());
                if(video==null){
                    continue;
                }
                video = videoService.getVideo(video, video.getId(), null);
                viewHistory.setVideo(video);
            }
        }
        PageInfo<ViewHistory> page = new PageInfo<ViewHistory>(viewHistoryList);
        return JsonData.buildSuccess(page);
    }

}
