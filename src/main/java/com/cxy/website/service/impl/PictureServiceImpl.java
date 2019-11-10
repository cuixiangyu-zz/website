package com.cxy.website.service.impl;

import com.cxy.website.common.CommonStatus;
import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.dao.PictureMapper;
import com.cxy.website.dao.PictureTypeMapper;
import com.cxy.website.model.*;
import com.cxy.website.service.ActorService;
import com.cxy.website.service.PictureService;
import com.cxy.website.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    TypeService typeService;

    @Autowired
    ActorService actorService;

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
        int piccount = pictureTypeMapper.deleteByPictureId(id);
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

    /**
     * 条件查询图片
     *
     * @param pageNum     页码
     * @param pageSize    但也数量
     * @param actorName   作者名
     * @param pictureName 图片名
     * @param types       类型
     * @return jsondata
     */
    @Override
    public JsonData findPageList(Integer pageNum, Integer pageSize, String actorName, String pictureName, String language, List<List<Object>> types) {
        PageHelper.startPage(pageNum, pageSize);
        List<String> type = null;
        if(types!=null&&types.size()>0){
            type = new ArrayList<>();
            for (List<Object> typelist : types) {

                if(typelist.get(1)!=null){
                    type.add(typelist.get(1).toString());
                }
            }
        }

        List<Picture> pictures = pictureMapper.selectPageList(actorName, pictureName, language, type);
        for (Picture picture : pictures) {
            picture = getPicture(picture, picture.getId());
        }
        PageInfo<Picture> page = new PageInfo<Picture>(pictures);
        List<Type> alltypes = typeService.findByType(CommonStatus.TYPE_TYPE_PICTURE);
        List<Actor> actors = actorService.findByType(CommonStatus.ACTOR_TYPE_PICTURE);
        Map<String, Object> jsondata = getTypeList(alltypes, type);
        jsondata.put("PageInfo",page);
        jsondata.put("actors",actors);
        return JsonData.buildSuccess(jsondata);
    }

    public Map<String, Object> getTypeList(List<Type> allTypes, List<String> selectTypes) {
        if (allTypes == null) {
            return null;
        }
        List<Map> childList = new ArrayList<>();
        Map<String, Object> typeMap = new HashMap<>();
        typeMap.put("value", "allTypes");
        typeMap.put("label", "类型");
        typeMap.put("multiple", "true");
        List<Map<String, Object>> typeList = new ArrayList<>();

        for (Type type : allTypes) {
            Map<String, Object> info = new HashMap<>();
            info.put("value", type.getId());
            info.put("label", type.getChineseName());
            info.put("multiple", "true");
            if (selectTypes!=null&&selectTypes.contains(type.getChineseName())) {
                info.put("checked", true);
            } else {
                info.put("checked", false);
            }
            typeList.add(info);
        }
        typeMap.put("children", typeList);
        List<Map<String, Object>> typelist = new ArrayList<>();
        typelist.add(typeMap);
        Map<String, Object> jsondata = new HashMap<>();
        jsondata.put("typeMap", typelist);
        return jsondata;
    }

    @Override
    public Picture getPicture(Picture picture, Integer id) {

        List<Actor> actors = actorService.findByPictureid(id);
        List<Type> types = typeService.findByPictureid(id);
        picture.setActors(actors);
        picture.setTypes(types);
        picture.setCoverUrl(CommonStatus.FILE_URL_PREFIX+picture.getCoverUrl());
        String address = CommonStatus.FILE_ADDRESS_PREFIX+picture.getPictureUrl();
        File root = new File(address);
        File[] files = root.listFiles();
        List<String> piclist = new ArrayList<>();
        for (File picfile : files) {
            piclist.add(CommonStatus.FILE_URL_PREFIX+picture.getPictureUrl()+File.separator+picfile.getName());
        }
        picture.setAddress(piclist);
        return picture;
    }
}
