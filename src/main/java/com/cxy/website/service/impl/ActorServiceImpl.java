package com.cxy.website.service.impl;

import com.cxy.website.dao.ActorMapper;
import com.cxy.website.dao.ActorTypeMapper;
import com.cxy.website.dao.VideoActorMapper;
import com.cxy.website.model.Actor;
import com.cxy.website.model.Video;
import com.cxy.website.service.ActorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2019-10-16 13:48
 **/
@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorMapper actorMapper;

    @Autowired
    VideoActorMapper videoActorMapper;

    /**
     * 添加
     *
     * @param actor
     * @return
     */
    @Override
    public int add(Actor actor) {
        int count = actorMapper.insert(actor);
        return count;
    }

    /**
     * 修改
     *
     * @param actor
     * @return
     */
    @Override
    public int update(Actor actor) {
        int count = actorMapper.updateByPrimaryKey(actor);
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
        int count = actorMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public Actor findByid(int id) {
        Actor actor = actorMapper.selectByPrimaryKey(id);
        return actor;
    }

    /**
     * 根据名子查找
     *
     * @param name
     * @return
     */
    @Override
    public Actor findByName(String name) {
        Actor actor = actorMapper.selectByName(name);
        return actor;
    }

    /**
     * 根据中文名查找
     *
     * @param chineseName
     * @return
     */
    @Override
    public Actor findByChineseName(String chineseName) {
        Actor actor = actorMapper.selectByChineseName(chineseName);
        return actor;
    }

    /**
     * 根据类型查找
     *
     * @param pageNum
     * @param pageSize
     * @param type
     * @return
     */
    @Override
    public PageInfo<Actor> findByType(int pageNum, int pageSize, int type) {
        PageHelper.startPage(pageNum, pageSize);
        List<Actor> actors = actorMapper.selectByType(type);
        PageInfo<Actor> page = new PageInfo<Actor>(actors);
        return page;
    }

    /**
     * 根据国家查找
     *
     * @param pageNum
     * @param pageSize
     * @param country
     * @return
     */
    @Override
    public PageInfo<Actor> findByCountry(int pageNum, int pageSize, String country) {
        PageHelper.startPage(pageNum, pageSize);
        List<Actor> actors = actorMapper.selectByCountry(country);
        PageInfo<Actor> page = new PageInfo<Actor>(actors);
        return page;
    }

    /**
     * 根据级别查找
     *
     * @param pageNum
     * @param pageSize
     * @param level
     * @return
     */
    @Override
    public PageInfo<Actor> findByLevel(int pageNum, int pageSize, int level) {
        PageHelper.startPage(pageNum, pageSize);
        List<Actor> actors = actorMapper.selectByLevel(level);
        PageInfo<Actor> page = new PageInfo<Actor>(actors);
        return page;
    }

    /**
     * 根据漫画id查询作者
     *
     * @param id
     * @return
     */
    @Override
    public List<Actor> findByPictureid(Integer id) {
        List<Actor> actors = actorMapper.selectByPictureid(id);
        return actors;
    }

    @Override
    public List<Actor> findByVideoid(Integer id) {
        List<Actor> actors = actorMapper.selectByVideoid(id);
        return actors;
    }

    @Override
    public PageInfo<Actor> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Actor> actors = actorMapper.selectAll();
        PageInfo<Actor> page = new PageInfo<Actor>(actors);
        return page;
    }

    @Override
    public List<Actor> findByType(Integer type) {
        List<Actor> actors = actorMapper.selectByType(type);
        return actors;
    }

    @Override
    public void updateVideoActor(Integer id, List<String> artists) {
        videoActorMapper.updateVideoActor(id,artists);
    }
}
