package com.cxy.website.service;

import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.UpdateFileName;
import com.cxy.website.model.Video;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface VideoService {
    /**
     * 添加
     *
     * @param video
     * @return
     */
    int add(Video video);

    /**
     * 修改
     *
     * @param video
     * @return
     */
    int update(Video video);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Video findByid(int id);

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    Video findByName(String name);

    /**
     * 根据演员查询
     * @param id
     * @return
     */
    PageInfo<Video> findByActor(int pageNum, int pageSize,int id);

    /**
     * 根据类型查询
     * @param type
     * @return
     */
    PageInfo<Video> findByType(int pageNum, int pageSize,int type);

    JsonData findPageList(Integer pageNum, Integer pageSize, String actorName, String pictureName, String language, List<List<Object>> types, Integer videoType);

    Video getVideo(Video video, Integer id, String type);

    /**
     * 将本地文件信息保存到数据库
     * @param source
     * @param target 文件地址
     * @param filemap
     * @param type
     */
    void updateVideoFromLocal(String source, String target, List<UpdateFileName> filemap, String type);

    /**
     * 保存未下载影片信息
     * @param title 标题
     * @param picurl    图片url
     * @param id    id
     * @param arrayurl  分类和作者
     */
    void saveNotDownloadInfo(String title,String picurl,String id,String arrayurl);

    List<UpdateFileName> selectFileForJapan(String filepath);

    void changeLevel(String id, String level, Integer typeTypeComic);

    public List<UpdateFileName> selectFileForAmerican(String filepath);

    public List<UpdateFileName> selectFileForAnimate(String filepath);

    void saveViewHistory(Integer type, Integer id, Integer startData, Integer watchTime);

    JsonData getWatchList(List<Integer> idList);

    JsonData suggestVideo(String id);

    List<UpdateFileName> selectFileForPornHub(String source);

    public List<String> getAddress(Video video);

    List<Video> checkExist(String name);

    Video findNextByid(Integer id,String type);

    void moveFile(String source, String target);

    void reName(String source, String target, List<UpdateFileName> filemap, String type);
}
