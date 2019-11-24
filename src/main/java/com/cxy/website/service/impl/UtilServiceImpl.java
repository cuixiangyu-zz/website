package com.cxy.website.service.impl;

import com.cxy.website.dao.UtilMapper;
import com.cxy.website.model.Util;
import com.cxy.website.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2019-10-16 13:31
 **/
@Service
public class UtilServiceImpl implements UtilService {

    @Autowired
    UtilMapper utilMapper;

    /**
     * 添加
     *
     * @param util
     * @return
     */
    @Override
    public int add(Util util) {
        int count = utilMapper.insert(util);
        return count;
    }

    /**
     * 修改
     *
     * @param util
     * @return
     */
    @Override
    public int update(Util util) {
        int count = utilMapper.updateByPrimaryKey(util);
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
        int count = utilMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public Util findByid(int id) {
        Util util = utilMapper.selectByPrimaryKey(id);
        return util;
    }

    /**
     * 根据id查找
     *
     * @param key
     * @return
     */
    @Override
    public Util findBykey(String key) {
        Util util = utilMapper.selectByKey(key);
        return util;
    }

    @Override
    public List<Util> findByType(Integer type) {
        List<Util> utils = utilMapper.selectAll();
        return utils;
    }
}
