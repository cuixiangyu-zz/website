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
    Type findByName(String name,Integer type);

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

    void updateVideoType(int videoId , List<String> typeName);

    void updatePicType(int picId , List<String> typeName);

    /**
     * 从网页上查找分类并添加到库中
     * @param url   网页地址
     * @param type  分类类型
     */
    void updateType(String url, Integer type);
}
