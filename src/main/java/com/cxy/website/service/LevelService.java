package com.cxy.website.service;

import com.cxy.website.model.Actor;
import com.cxy.website.model.Level;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface LevelService {
    /**
     * 添加
     * @param level
     * @return
     */
    int add(Level level);

    /**
     * 修改
     * @param level
     * @return
     */
    int update(Level level);

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
    Level findByid(int id);

    /**
     * 根据作品id查找
     * @param productionId
     * @return
     */
    List<Level> findByProductionIdandType(int productionId,int productiontype);

    /**
     * 根据级别查找
     * @param level
     * @return
     */
    List<Level> findByLevel(int level);

    Level findByProductionIdAndUserId(Integer id, Integer userId, Integer type);
}
