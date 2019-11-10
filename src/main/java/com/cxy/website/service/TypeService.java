package com.cxy.website.service;

import com.cxy.website.model.Picture;
import com.cxy.website.model.Type;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TypeService {
    /**
     * 添加
     * @param type
     * @return
     */
    int add(Type type);

    /**
     * 修改
     * @param type
     * @return
     */
    int update(Type type);

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
    Type findById(int id);

    /**
     * 根据名字查找
     * @param name
     * @return
     */
    Type findByName(String name);

    /**
     * 根据中文名查找
     * @param chinesename
     * @return
     */
    Type findByChineseName(String chinesename);

    List<Type> findByPictureid(Integer id);

    List<Type> findByVideoId(Integer id);

    PageInfo<Type> findByType(int pageNum, int pageSize,int type);

    List<Type> findByType(Integer type);
}
