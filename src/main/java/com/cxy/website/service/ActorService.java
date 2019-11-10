package com.cxy.website.service;

import com.cxy.website.model.Actor;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ActorService {

    /**
     * 添加
     * @param actor
     * @return
     */
    int add(Actor actor);

    /**
     * 修改
     * @param actor
     * @return
     */
    int update(Actor actor);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    Actor findByid(int id);

    /**
     * 根据名子查找
     * @param name
     * @return
     */
    Actor findByName(String name);

    /**
     * 根据中文名查找
     * @param chineseName
     * @return
     */
    Actor findByChineseName(String chineseName);

    /**
     * 根据类型查找
     * @param type
     * @return
     */
    PageInfo<Actor> findByType(int pageNum, int pageSize,int type);

    /**
     * 根据国家查找
     * @param country
     * @return
     */
    PageInfo<Actor> findByCountry(int pageNum, int pageSize,String country);

    /**
     * 根据级别查找
     * @param level
     * @return
     */
    PageInfo<Actor> findByLevel(int pageNum, int pageSize,int level);

    /**
     * 根据漫画id查询作者
     * @param id
     * @return
     */
    List<Actor> findByPictureid(Integer id);

    List<Actor> findByVideoid(Integer id);

    PageInfo<Actor> findAll(int pageNum, int pageSize);

    List<Actor> findByType(Integer type);
}
