package com.cxy.website.service;

import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.Level;
import com.cxy.website.model.Picture;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PictureService {
    /**
     * 添加
     * @param picture
     * @return
     */
    int add(Picture picture);

    /**
     * 修改
     * @param picture
     * @return
     */
    int update(Picture picture);

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
    Picture findById(int id);

    /**
     * 根据名字查找
     * @param name
     * @return
     */
    Picture findByName(String name);

    /**
     * 根据作者查找
     * @param id
     * @return
     */
    PageInfo<Picture> findByArtist(int pageNum, int pageSize,int id);

    /**
     * 根据类型查找
     * @param type
     * @return
     */
    PageInfo<Picture> findByType(int pageNum, int pageSize,int type);

    /**
     * 根据语言查找
     * @param language
     * @return
     */
    PageInfo<Picture> findByLanguage(int pageNum, int pageSize,String language);

    /**
     * 条件查询图片
     * @param pageNum   页码
     * @param pageSize  但也数量
     * @param actorName 作者名
     * @param pictureName   图片名
     * @param types 类型
     * @return  jsondata
     */
    JsonData findPageList(Integer pageNum, Integer pageSize, String actorName, String pictureName,String language, List<List<Object>> types);

    public Picture getPicture(Picture picture, Integer id);
}
