package com.cxy.website.service;

import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.UserFavorite;

public interface UserFavoriteService {

    /**
     * 添加收藏
     * @param userFavorite  实体类
     */
    UserFavorite addFavorite(UserFavorite userFavorite);

    /**
     * 删除收藏
     * @param id    id
     */
    void deleteFavorite(int id);

    /**
     * 获取收藏列表
     * @param pageNum   开始页码
     * @param pageSize  单页数量
     * @param type  类型
     * @return  列表
     */
    JsonData getList(Integer pageNum, Integer pageSize,Integer type,Integer level);

    /**
     * 根据用户id和视频id查找记录
     * @param userId    用户id
     * @param videoId   视频id
     * @return  记录
     */
    UserFavorite findByUserIdAndVideoId(Integer userId,Integer type, Integer videoId);
}
