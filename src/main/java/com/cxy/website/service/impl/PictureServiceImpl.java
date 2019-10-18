package com.cxy.website.service.impl;

import com.cxy.website.dao.PictureMapper;
import com.cxy.website.dao.PictureTypeMapper;
import com.cxy.website.model.Picture;
import com.cxy.website.model.PictureType;
import com.cxy.website.model.Video;
import com.cxy.website.service.PictureService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2019-10-16 13:38
 **/
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    PictureTypeMapper pictureTypeMapper;

    /**
     * 添加
     *
     * @param picture
     * @return
     */
    @Override
    public int add(Picture picture) {
        int count = pictureMapper.insert(picture);
        return count;
    }

    /**
     * 修改
     *
     * @param picture
     * @return
     */
    @Override
    public int update(Picture picture) {
        int count = pictureMapper.updateByPrimaryKey(picture);
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
        int count = pictureMapper.deleteByPrimaryKey(id);
        int piccount =pictureTypeMapper.deleteByPictureId(id);
        return count;
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public Picture findById(int id) {
        Picture picture = pictureMapper.selectByPrimaryKey(id);
        return picture;
    }

    /**
     * 根据名字查找
     *
     * @param name
     * @return
     */
    @Override
    public Picture findByName(String name) {
        Picture picture = pictureMapper.selectByName(name);
        return picture;
    }

    /**
     * 根据作者查找
     *
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
    @Override
    public PageInfo<Picture> findByArtist(int pageNum, int pageSize, int id) {
        PageHelper.startPage(pageNum, pageSize);
        List<Picture> pictures = pictureMapper.selectByArtist(id);
        PageInfo<Picture> page = new PageInfo<Picture>(pictures);
        return page;
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
    public PageInfo<Picture> findByType(int pageNum, int pageSize, int type) {
        PageHelper.startPage(pageNum, pageSize);
        List<Picture> pictures = pictureMapper.selectByType(type);
        PageInfo<Picture> page = new PageInfo<Picture>(pictures);
        return page;
    }

    /**
     * 根据语言查找
     *
     * @param pageNum
     * @param pageSize
     * @param language
     * @return
     */
    @Override
    public PageInfo<Picture> findByLanguage(int pageNum, int pageSize, String language) {
        PageHelper.startPage(pageNum, pageSize);
        List<Picture> pictures = pictureMapper.selectByLanguage(language);
        PageInfo<Picture> page = new PageInfo<Picture>(pictures);
        return page;
    }
}
