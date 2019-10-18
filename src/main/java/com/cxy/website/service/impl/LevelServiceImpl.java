package com.cxy.website.service.impl;

import com.cxy.website.dao.LevelMapper;
import com.cxy.website.model.Level;
import com.cxy.website.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2019-10-16 13:44
 **/
@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    LevelMapper levelMapper;
    /**
     * 添加
     *
     * @param level
     * @return
     */
    @Override
    public int add(Level level) {
        int count = levelMapper.insert(level);
        return count;
    }

    /**
     * 修改
     *
     * @param level
     * @return
     */
    @Override
    public int update(Level level) {
        int count = levelMapper.updateByPrimaryKey(level);
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
        int count = levelMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public Level findByid(int id) {
        Level level = levelMapper.selectByPrimaryKey(id);
        return level;
    }

    /**
     * 根据作品id查找
     *
     * @param productionId
     * @return
     */
    @Override
    public List<Level> findByProductionId(int productionId) {
        List<Level> levels = levelMapper.selectByProductionId(productionId);
        return levels;
    }

    /**
     * 根据级别查找
     *
     * @param level
     * @return
     */
    @Override
    public List<Level> findByLevel(int level) {
        List<Level> levels = levelMapper.selectByLevel(level);
        return levels;
    }
}
