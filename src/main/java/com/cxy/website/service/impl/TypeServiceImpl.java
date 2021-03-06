package com.cxy.website.service.impl;

import com.cxy.website.dao.TypeMapper;
import com.cxy.website.dao.VideoTypeMapper;
import com.cxy.website.model.Actor;
import com.cxy.website.model.Type;
import com.cxy.website.service.TypeService;
import com.cxy.website.service.WebSiteToolsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2019-10-16 13:34
 **/
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeMapper typeMapper;

    @Autowired
    VideoTypeMapper videoTypeMapper;

    @Autowired
    WebSiteToolsService webSiteToolsService;

    /**
     * 添加
     *
     * @param type
     * @return
     */
    @Override
    public int add(Type type) {
        int count = typeMapper.insert(type);
        return count;
    }

    /**
     * 修改
     *
     * @param type
     * @return
     */
    @Override
    public int update(Type type) {
        int count = typeMapper.updateByPrimaryKey(type);
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
        int count = typeMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public Type findById(int id) {
        Type type = typeMapper.selectByPrimaryKey(id);
        return type;
    }

    /**
     * 根据名字查找
     *
     * @param name
     * @return
     */
    @Override
    public Type findByName(String name,Integer types) {
        Type type = typeMapper.selectByName(name,types);
        return type;
    }

    /**
     * 根据中文名查找
     *
     * @param chinesename
     * @return
     */
    @Override
    public Type findByChineseName(String chinesename) {
        Type type = typeMapper.selectByChineseName(chinesename);
        return type;
    }

    @Override
    public List<Type> findByPictureid(Integer id) {
        List<Type> types = typeMapper.selectByPictureid(id);
        return types;
    }

    @Override
    public List<Type> findByVideoId(Integer id) {
        List<Type> types = typeMapper.selectByVideoId(id);
        return types;
    }

    @Override
    public PageInfo<Type> findByType(int pageNum, int pageSize,int type) {
        PageHelper.startPage(pageNum, pageSize);
        List<Type> typeList = typeMapper.selectByType(type);
        PageInfo<Type> page = new PageInfo<Type>(typeList);
        return page;
    }

    @Override
    public List<Type> findByType(Integer type) {
        List<Type> typeList = typeMapper.selectByType(type);
        return typeList;
    }

    @Override
    public void updateVideoType(int videoId, List<String> typeName) {
        videoTypeMapper.updateVideoType(videoId,typeName);
    }

    @Override
    public void updatePicType(int picId, List<String> typeName) {

    }

    /**
     * 从网页上查找分类并添加到库中
     *
     * @param url  网页地址
     * @param type 分类类型
     */
    @Override
    public void updateType(String url, Integer type) {
        HashSet<String> htmls = webSiteToolsService.getHtmlbyRegex(url, "href=\\\"https://www.javbus.zone/genre/[A-Za-z0-9-]*\\\">.*</a>");
        for (String html : htmls) {
            String chineseName = html.substring(html.indexOf("genre/")+6,html.indexOf("\">"));
            String typeName = html.substring(html.indexOf("\">")+2,html.indexOf("</a>"));
            Type types = new Type();
            types.setChineseName(chineseName);
            types.setTypeName(typeName);
            types.setType(type);
            Type type1 = this.findByName(typeName, type);
            if(type1!=null){
                continue;
            }
            this.add(types);
        }
    }
}
