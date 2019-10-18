package com.cxy.website.service.impl;

import com.cxy.website.dao.VideoMapper;
import com.cxy.website.model.Video;
import com.cxy.website.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2019-10-16 12:00
 **/
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;

    /**
     * 添加
     *
     * @param video
     * @return
     */
    @Override
    public int add(Video video) {
        int count = videoMapper.insert(video);
        return count;
    }

    /**
     * 修改
     *
     * @param video
     * @return
     */
    @Override
    public int update(Video video) {
        int count = videoMapper.updateByPrimaryKey(video);
        return count;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public int delete(int id) {
        int count = videoMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public Video findByid(int id) {
        Video video = videoMapper.selectByPrimaryKey(id);
        return video;
    }

    /**
     * 根据名字查询
     *
     * @param name
     * @return
     */
    @Override
    public Video findByName(String name) {
        Video video =videoMapper.selectByName(name);
        return video;
    }

    /**
     * 根据演员查询
     *
     * @param id
     * @return
     */
    @Override
    public PageInfo<Video> findByActor(int pageNum, int pageSize,int id) {
        PageHelper.startPage(pageNum, pageSize);
        List<Video> video = videoMapper.selectByActor(id);
        PageInfo<Video> page = new PageInfo<Video>(video);
        return page;
    }

    /**
     * 根据类型查询
     *
     * @param type
     * @return
     */
    @Override
    public PageInfo<Video> findByType(int pageNum, int pageSize,int type) {
        PageHelper.startPage(pageNum, pageSize);
        List<Video> video = videoMapper.selectByType(type);
        PageInfo<Video> page = new PageInfo<Video>(video);
        return page;
    }
}
