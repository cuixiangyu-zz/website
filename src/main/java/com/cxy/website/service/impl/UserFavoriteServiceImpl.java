package com.cxy.website.service.impl;

import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.dao.UserFavoriteMapper;
import com.cxy.website.model.Picture;
import com.cxy.website.model.UserFavorite;
import com.cxy.website.model.Video;
import com.cxy.website.model.sys.SysUser;
import com.cxy.website.service.PictureService;
import com.cxy.website.service.UserFavoriteService;
import com.cxy.website.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2020-02-01 10:31
 **/
@Service
public class UserFavoriteServiceImpl implements UserFavoriteService {

    @Autowired
    UserFavoriteMapper userFavoriteMapper;

    @Autowired
    VideoService videoService;

    @Autowired
    PictureService pictureService;

    /**
     * 添加收藏
     *
     * @param userFavorite 实体类
     */
    @Override
    public UserFavorite addFavorite(UserFavorite userFavorite) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        UserFavorite favorite = this.findByUserIdAndVideoId(user.getId(),userFavorite.getType(),userFavorite.getVideoId());
        if(favorite!=null){
            return favorite;
        }
        userFavorite.setUserId(user.getId());
        userFavorite.setCreatTime(new Date());
        userFavoriteMapper.insert(userFavorite);
        return userFavorite;
    }

    /**
     * 删除收藏
     *
     * @param id id
     */
    @Override
    public void deleteFavorite(int id) {
        userFavoriteMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取收藏列表
     *
     * @param pageNum  开始页码
     * @param pageSize 单页数量
     * @param type     类型
     * @return 列表
     */
    @Override
    public JsonData getList(Integer pageNum, Integer pageSize, Integer type,Integer level) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(pageNum,pageSize);
        List<UserFavorite> userFavoriteList = userFavoriteMapper.getList(user.getId(),type,level);
        for (UserFavorite userFavorite : userFavoriteList) {
            if(userFavorite.getType()==5){
                Picture picture = pictureService.findById(userFavorite.getVideoId());
                if(picture==null){
                    continue;
                }
                picture = pictureService.getPicture(picture, picture.getId());
                userFavorite.setPicture(picture);
            }else{
                Video video = videoService.findByid(userFavorite.getVideoId());
                if(video==null){
                    continue;
                }
                video = videoService.getVideo(video, video.getId());
                userFavorite.setVideo(video);
            }
        }
        PageInfo<UserFavorite> page = new PageInfo<>(userFavoriteList);
        return JsonData.buildSuccess(page);
    }

    /**
     * 根据用户id和视频id查找记录
     *
     * @param userId  用户id
     * @param videoId 视频id
     * @return 记录
     */
    @Override
    public UserFavorite findByUserIdAndVideoId(Integer userId,Integer type, Integer videoId) {
        UserFavorite userFavorite = userFavoriteMapper.selectByUserIdAndVideoId(userId,type,videoId);
        return userFavorite;
    }
}
