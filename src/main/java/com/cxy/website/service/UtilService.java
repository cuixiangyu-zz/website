package com.cxy.website.service;

import com.cxy.website.model.Util;

import java.util.List;

public interface UtilService {
    /**
     * 添加
     * @param util
     * @return
     */
    int add(Util util);

    /**
     * 修改
     * @param util
     * @return
     */
    int update(Util util);

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
    Util findByid(int id);

    /**
     * 根据id查找
     * @param key
     * @return
     */
    Util findBykey(String key);

    List<Util> findByType(Integer type);
}
