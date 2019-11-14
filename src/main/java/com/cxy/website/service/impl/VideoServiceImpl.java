package com.cxy.website.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.cxy.website.common.CommonStatus;
import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.dao.VideoMapper;
import com.cxy.website.model.Actor;
import com.cxy.website.model.Picture;
import com.cxy.website.model.Type;
import com.cxy.website.model.Video;
import com.cxy.website.service.ActorService;
import com.cxy.website.service.TypeService;
import com.cxy.website.service.VideoService;
import com.cxy.website.service.WebSiteToolsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2019-10-16 12:00
 **/
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    TypeService typeService;

    @Autowired
    ActorService actorService;

    @Autowired
    WebSiteToolsService webSiteToolsService;
    /**
     * 添加
     *
     * @param video
     * @return
     */
    @Override
    public int add(Video video) {
        int count = videoMapper.insert(video);
        return count;
    }

    /**
     * 修改
     *
     * @param video
     * @return
     */
    @Override
    public int update(Video video) {
        int count = videoMapper.updateByPrimaryKey(video);
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
        int count = videoMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public Video findByid(int id) {
        Video video = videoMapper.selectByPrimaryKey(id);
        return video;
    }

    /**
     * 根据名字查询
     *
     * @param name
     * @return
     */
    @Override
    public Video findByName(String name) {
        Video video =videoMapper.selectByName(name);
        return video;
    }

    /**
     * 根据演员查询
     *
     * @param id
     * @return
     */
    @Override
    public PageInfo<Video> findByActor(int pageNum, int pageSize,int id) {
        PageHelper.startPage(pageNum, pageSize);
        List<Video> video = videoMapper.selectByActor(id);
        PageInfo<Video> page = new PageInfo<Video>(video);
        return page;
    }

    /**
     * 根据类型查询
     *
     * @param type
     * @return
     */
    @Override
    public PageInfo<Video> findByType(int pageNum, int pageSize,int type) {
        PageHelper.startPage(pageNum, pageSize);
        List<Video> video = videoMapper.selectByType(type);
        PageInfo<Video> page = new PageInfo<Video>(video);
        return page;
    }

    /**
     * 条件查询视频
     *
     * @param pageNum     页码
     * @param pageSize    但也数量
     * @param actorName   作者名
     * @param videoName 图片名
     * @param types       类型
     * @return jsondata
     */
    @Override
    public JsonData findPageList(Integer pageNum, Integer pageSize, String actorName, String videoName, String language, List<List<Object>> types) {
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

        List<Video> videos = videoMapper.selectPageList(actorName, videoName, language, type);
        for (Video video : videos) {
            video = getVideo(video, video.getId());
        }
        PageInfo<Video> page = new PageInfo<Video>(videos);
        List<Type> alltypes = typeService.findByType(CommonStatus.TYPE_TYPE_JAPAN);
        List<Actor> actors = actorService.findByType(CommonStatus.ACTOR_TYPE_JAPAN);
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
    public Video getVideo(Video video, Integer id) {

        List<Actor> actors = actorService.findByVideoid(id);
        List<Type> types = typeService.findByVideoId(id);
        video.setActors(actors);
        video.setTypes(types);
        video.setCoverUrl(CommonStatus.FILE_URL_PREFIX+video.getCoverUrl());
        String address = CommonStatus.FILE_ADDRESS_PREFIX+video.getVideoUrl();
        File root = new File(address);
        List<String> piclist = new ArrayList<>();
        if(root.isFile()){
            piclist.add(CommonStatus.FILE_URL_PREFIX+video.getVideoUrl());
        }else if(root.isDirectory()){
            File[] files = root.listFiles();

            for (File picfile : files) {
                piclist.add(CommonStatus.FILE_URL_PREFIX+video.getVideoUrl()+File.separator+picfile.getName());
            }
        }

        video.setAddress(piclist);
        return video;
    }

    /**
     * 将本地文件信息保存到数据库
     * @param localAddress 文件地址
     */
    @Override
    public void updateVideoFromLocal(String localAddress){
        File file = new File(localAddress);
        if(file.exists()&&file.isFile()){
            Video video = new Video();
            String filename = file.getName().substring(0,file.getName().indexOf("."));

            //从网页获取视频基本信息
            Map<String, Object> videoinfo = webSiteToolsService.getvideoinfo(filename);
            if(videoinfo==null||videoinfo.get("title")==null||videoinfo.get("title")==""){
                return;
            }
            List<String> artists = (List)videoinfo.get("artists");

            if(artists!=null&&artists.size()>0){
                for (String artist : artists) {
                    Actor actor = actorService.findByName(artist);
                    if(actor==null){
                        Actor actor1 = new Actor();
                        actor1.setName(artist);
                        actor1.setChineseName(artist);
                        actor1.setType(CommonStatus.ACTOR_TYPE_JAPAN);
                        actor1.setCreatTime(new Date());
                        actorService.add(actor1);
                    }
                }
            }
            //移动视频文件到对应演员的文件夹
            if(artists.size()==1){
                String targetPath = CommonStatus.FILE_ADDRESS_PREFIX +File.separator+"japanvideo"+File.separator+
                        artists.get(0)+File.separator+file.getName();
                webSiteToolsService.moveFiles(localAddress,targetPath);
                video.setVideoUrl(File.separator+"japanvideo"+File.separator+
                        artists.get(0)+File.separator+file.getName());
            }else{
                String targetPath = CommonStatus.FILE_ADDRESS_PREFIX +File.separator+"japanvideo"+File.separator+
                        "多作者"+File.separator+file.getName();
                webSiteToolsService.moveFiles(localAddress,targetPath);
                video.setVideoUrl(File.separator+"japanvideo"+File.separator+
                        "多作者"+File.separator+file.getName());
            }

            //下载视频封面
            if(videoinfo.get("picurl")!=null){
                webSiteToolsService.downloadPics(videoinfo.get("picurl").toString(), CommonStatus.FILE_COVER_PREFIX + File.separator +
                        "japanVideoCover", filename);
                video.setCoverUrl(File.separator +
                        "japanVideoCover"+File.separator+filename + ".jpg");
            }

            video.setType(CommonStatus.VIDEO_TYPE_JAPAN);
            video.setExist(CommonStatus.VIDEO_EXIST_EXIST);
            video.setName(videoinfo.get("title")==null?filename:videoinfo.get("title").toString());
            video.setCreatTime(new Date());
            add(video);
            Video video1 = findByName(video.getName());
            if(videoinfo.get("category")!=null&&((List) videoinfo.get("category")).size()>0){
                typeService.updateVideoType(video1.getId(),(List<String>)videoinfo.get("category"));
            }
            if(videoinfo.get("artists")!=null&&((List) videoinfo.get("artists")).size()>0){
                actorService.updateVideoActor(video1.getId(),(List<String>)videoinfo.get("artists"));
            }
        }else if(file.exists()&&file.isDirectory()){
            File[] listFiles = file.listFiles();
            for (File listFile : listFiles) {
                updateVideoFromLocal(listFile.getPath());
            }
        }
    }

    @Override
    public void saveNotDownloadInfo(String title, String picurl, String id, String arrayurl) {
        try {
            Video video = new Video();
            JSONArray json = JSONArray.parseArray(arrayurl); // 首先把字符串转成 JSONArray  对象
            List<String> name = new ArrayList<>();
            List<String> category = new ArrayList<>();

            if (json.size() > 0) {
                for (int i = 0; i < json.size(); i++) {

                    String html = json.get(i).toString();// 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    if (html.indexOf("star") > 0) {
                        name.add(html.substring(html.indexOf(",\"") + 2, html.length() - 2)) ;
                    } else if (html.indexOf("genre") > 0) {
                        category.add(html.substring(html.indexOf("genre") + 6, html.indexOf("\",")));
                    }
                    System.out.println();  // 得到 每个对象中的属性值
                }
            }
            if(name!=null&&name.size()>0){
                for (String artist : name) {
                    Actor actor = actorService.findByName(artist);
                    if(actor==null){
                        Actor actor1 = new Actor();
                        actor1.setName(artist);
                        actor1.setChineseName(artist);
                        actor1.setType(CommonStatus.ACTOR_TYPE_JAPAN);
                        actor1.setCreatTime(new Date());
                        actorService.add(actor1);
                    }
                }
            }
            //下载视频封面
            if (picurl != "") {
                webSiteToolsService.downloadPics(picurl, CommonStatus.FILE_COVER_PREFIX + File.separator +
                        "japanVideoCover", id);
                video.setCoverUrl(File.separator +
                        "japanVideoCover" + File.separator + id + ".jpg");
            }

            video.setType(CommonStatus.VIDEO_TYPE_JAPAN);
            video.setExist(CommonStatus.VIDEO_EXIST_NOTINDATABASE);
            video.setName(title);
            video.setCreatTime(new Date());
            add(video);
            Video video1 = findByName(video.getName());
            if (category.size()>0) {
                typeService.updateVideoType(video1.getId(), category);
            }
            if (name.size()>0) {
                actorService.updateVideoActor(video1.getId(),name);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
