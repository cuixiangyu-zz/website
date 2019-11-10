package com.cxy.website.service;

import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.Util;
import com.cxy.website.model.Video;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface VideoService {
    /**
     * 添加
     *
     * @param video
     * @return
     */
    int add(Video video);

    /**
     * 修改
     *
     * @param video
     * @return
     */
    int update(Video video);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Video findByid(int id);

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    Video findByName(String name);

    /**
     * 根据演员查询
     * @param id
     * @return
     */
    PageInfo<Video> findByActor(int pageNum, int pageSize,int id);

    /**
     * 根据类型查询
     * @param type
     * @return
     */
    PageInfo<Video> findByType(int pageNum, int pageSize,int type);

    JsonData findPageList(Integer pageNum, Integer pageSize, String actorName, String pictureName, String language, List<List<Object>> types);
}
