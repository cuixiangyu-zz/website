package com.cxy.website.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cxy.website.common.CommonStatus;
import com.cxy.website.common.util.RandomUtils;
import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.dao.VideoMapper;
import com.cxy.website.model.*;
import com.cxy.website.model.sys.SysUser;
import com.cxy.website.service.*;
import com.cxy.website.service.rabbitMqService.MsgProducer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Autowired
    UtilService utilService;

    @Autowired
    LevelService levelService;

    @Autowired
    UserFavoriteService userFavoriteService;

    @Autowired
    ArrangeVideos arrangeVideos;

    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    RestTemplate restTemplate;


    private static final Pattern VIDEO_AMERICAN_PATTERN = Pattern.compile("[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}");
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

    @Override
    public List<Video> checkExist(String name) {
        List<Video> videos = videoMapper.checkExist(name);
        return videos;
    }

    @Override
    public Video findNextByid(Integer id,String type) {
        Video video = videoMapper.selectByPrimaryKey(id);
        Video nextVideo = new Video();
        if(video.getType().equals(CommonStatus.ACTOR_TYPE_JAPAN)){
            nextVideo = videoMapper.findNextJapanByid(id,type);
            if(nextVideo==null||nextVideo.getId()==null){
                nextVideo = videoMapper.findFirstJapan(id,type);
            }
        }else{
            nextVideo = videoMapper.findNextByid(id,video.getType(),type);
            if(nextVideo==null||nextVideo.getId()==null){
                nextVideo = videoMapper.findFirst(id,type);
            }
        }
        return nextVideo;
    }

    @Override
    public void moveFile(String source, String target) {

        File sourceFile = new File(source);
        if(!sourceFile.exists()||sourceFile.isFile()){
            return;
        }
        for (File file : sourceFile.listFiles()) {
            String fileName = file.getName().substring(0,file.getName().length()-4);
            Video video = videoMapper.selectLikeName(fileName);
            if(video!=null){
                String videoUrl = video.getVideoUrl().substring(0,video.getVideoUrl().lastIndexOf("/")+1);
                String filePath = target+videoUrl;
                File videoFile = new File(filePath);
                if(!videoFile.exists()){
                    videoFile.mkdirs();
                }
                File targetFile = new File(filePath+file.getName());
                if(targetFile.exists()){
                    continue;
                }
                file.renameTo(targetFile);
                video.setVideoUrl(videoUrl+file.getName());
                update(video);
            }
        }
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
     * @param videoType
     * @return jsondata
     */
    @Override
    public JsonData findPageList(Integer pageNum, Integer pageSize, String actorName, String videoName, String language, List<List<Object>> types, Integer videoType) {
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

        List<Video> videos = videoMapper.selectPageList(actorName, videoName, language, type ,videoType);
        for (Video video : videos) {
            video = getVideo(video, video.getId(), null);
        }
        PageInfo<Video> page = new PageInfo<Video>(videos);

        List<Actor> actors = actorService.findByType(CommonStatus.ACTOR_TYPE_JAPAN);
        Map<String, Object> jsondata = new HashMap<>();
        jsondata.put("PageInfo",page);
        jsondata.put("actors",actors);
        return JsonData.buildSuccess(jsondata);
    }

    /**
     * 根据类型列表和所选类型创建前端所用数据类型
     * @param allTypes  所有类型
     * @param selectTypes   所选类型
     * @return  前端所用数据类型
     */
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

    /**
     * 组装前端需求视频实体类，主要更新封面地址，视频地址，演员和分类
     * @param video 视频实体类
     * @param id    视频id
     * @param type
     * @return 需求视频实体类
     */
    @Override
    public Video getVideo(Video video, Integer id, String type) {

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        Level oldlevel =  levelService.findByProductionIdAndUserId(id,user.getId(),CommonStatus.TYPE_TYPE_JAPAN);
        if(oldlevel!=null){
            video.setLevel(oldlevel.getLevel());
        }

        List<Actor> actors = actorService.findByVideoid(id);
        List<Type> types = typeService.findByVideoId(id);
        Level level = levelService.findByProductionIdAndUserId(video.getId(), user.getId(), video.getType());
        if(level!=null){
            video.setLevel(level.getLevel());
        }

        UserFavorite userFavorite = userFavoriteService.findByUserIdAndVideoId(user.getId(),video.getType(),id);
        if(userFavorite!=null){
            video.setUserFavorite(userFavorite);
        }

        video.setActors(actors);
        video.setTypes(types);
        video.setCoverUrl(CommonStatus.COVER_URL_PREFIX+video.getCoverUrl());
        if(type!=null&&type.equals("detail")){
            List<String> piclist = new ArrayList<>();
            if(CommonStatus.SYS_TYPE.equals("base")){
                piclist= getAddress(video);
            }else{
                JsonData forObject = restTemplate.getForObject(CommonStatus.BASE_SYSTEM_URL + "/sysUtil/getVideoDetail?id=" + id, JsonData.class);
                if(forObject==null||forObject.getCode()!=0){
                    return video;
                }
                List<String> addrList = JSONObject.parseArray((String) forObject.getData()).toJavaObject(List.class);

                for (String address : addrList) {
                    System.out.println("oldAddr========"+address);
                    System.out.println("BASE_SYS_INNER_URL========"+CommonStatus.BASE_SYS_INNER_URL);
                    System.out.println("BASE_SYS_OUT_URL========"+CommonStatus.BASE_SYS_OUT_URL);
                    String s = address.replace(CommonStatus.BASE_SYS_INNER_URL, CommonStatus.BASE_SYS_OUT_URL);
                    piclist.add(s);
                }
            }
            video.setAddress(piclist);
        }
        return video;
    }

    @Override
    public List<String> getAddress(Video video) {
        String address;
        List<String> piclist = new ArrayList<>();
        if(System.getProperty("os.name").toLowerCase().contains("linux")){
            for (String resourcesAddr : CommonStatus.resourcesAddrs) {
                File root = new File(resourcesAddr + video.getVideoUrl());
                if(!root.exists()){
                    if(video.getVideoUrl().contains("多作者")){
                        root = new File(resourcesAddr + video.getName()+ video.getVideoUrl().substring(video.getVideoUrl().lastIndexOf(".")));
                        if(!root.exists()){
                            continue;
                        }
                    }
                    continue;
                }

                if(root.isFile()){
                    String path = root.getPath();
                    path = path.substring(5,path.length());
                    piclist.add(CommonStatus.FILE_URL_PREFIX+path);
                }else if(root.isDirectory()){
                    File[] files = root.listFiles();

                    for (File picfile : files) {
                        String path = picfile.getPath();
                        path = path.substring(5,path.length());
                        piclist.add(CommonStatus.FILE_URL_PREFIX+path);
                    }
                }
            }
        }else if(System.getProperty("os.name").toLowerCase().contains("windows")){
            for (String addr : CommonStatus.WINDOWS_ADDRS) {
                address = addr + video.getVideoUrl();
                if (new File(address).exists()) {
                    break;
                }
            }
        }
        return piclist;
    }


    /**
     * 将本地文件信息保存到数据库
     * @param source    来源文件夹
     * @param target 文件地址
     * @param filemap   文件名
     * @param type  文件类型
     */
    @Override
    public void updateVideoFromLocal(String source, String target, List<UpdateFileName> filemap, String type){
        System.out.println(System.currentTimeMillis());

        if (filemap != null && filemap.size() > 0) {
            if(type.equals("animate")){
                saveAnimate(source,target,filemap);
            }else if(type.equals("pornHub")){
                savePornVideo(source,target,filemap);
            }else if(type.equals("movie")){
                saveMovie(source,target,filemap);
            }else if(type.equals("korean")){
                saveKorean(source,target,filemap);
            }else if(type.equals("american")){
                saveAmerican(source,target,filemap);
            }else{
                for (UpdateFileName updateFileName : filemap) {
                    Video video = videoMapper.valudate(updateFileName.getSuggestname().substring(0,updateFileName.getSuggestname().indexOf(".")));
                    if(video!=null){
                        continue;
                    }
                    arrangeVideos.update(source,target,type,updateFileName);
                }
            }
        }
        actorService.updateLevel(null);
    }


    @Override
    public void reName(String source, String target, List<UpdateFileName> filemap, String type){
        System.out.println(System.currentTimeMillis());
        for (UpdateFileName updateFileName : filemap) {
            File oldFile = new File(source+File.separator+updateFileName.getFilename());
            if(!oldFile.exists()){
                continue;
            }
            File newFile = new File(target+File.separator+updateFileName.getSuggestname());
            if(newFile.exists()){
                continue;
            }
            oldFile.renameTo(newFile);
        }

    }

    private void saveAnimate(String source, String target, List<UpdateFileName> filemap) {
        Video video = new Video();
        video.setType(CommonStatus.VIDEO_TYPE_ANIMATE);
        video.setExist(CommonStatus.VIDEO_EXIST_EXIST);

        for (UpdateFileName updateFileName : filemap) {
            String filename = updateFileName.getFilename();
            String suggestname = updateFileName.getSuggestname();
            String oldAddr = source+File.separator+filename;
            File oldFile = new File(oldAddr);
            if(!oldFile.exists()||oldFile.isDirectory()){
                continue;
            }
            video.setName(suggestname);
            video.setCreatTime(new Date());

            //文件名
            String newName = getFileName();
            String suffix = filename.substring(filename.lastIndexOf("."),filename.length());
            String targetAddr = target+File.separator + "animate"+File.separator+newName+suffix;

            String coverAddr = CommonStatus.FILE_COVER_PREFIX + File.separator +
                    "animateCover" + File.separator + newName + ".jpg";
            //VideoUtil.creatPic(oldAddr,coverAddr);

            video.setVideoUrl(targetAddr);
            video.setCoverUrl(File.separator +
                    "animateCover" + File.separator + newName + ".jpg");
            video.setVideoUrl(File.separator + "animate" + File.separator + newName+suffix);

            JSONObject moveVideoJson = new JSONObject();
            moveVideoJson.put("oldAddr",oldAddr);
            moveVideoJson.put("coverAddr",coverAddr);
            moveVideoJson.put("targetAddr",targetAddr);
            msgProducer.sendToVideoCover(moveVideoJson);

            //webSiteToolsService.moveFiles(oldAddr,targetAddr);
            add(video);

        }

    }

    private void saveMovie(String source, String target, List<UpdateFileName> filemap) {
        Video video = new Video();
        video.setType(CommonStatus.VIDEO_TYPE_MOVIE);
        video.setExist(CommonStatus.VIDEO_EXIST_EXIST);

        for (UpdateFileName updateFileName : filemap) {
            String filename = updateFileName.getFilename();
            String suggestname = updateFileName.getSuggestname();
            String oldAddr = source+File.separator+filename;
            File oldFile = new File(oldAddr);
            if(!oldFile.exists()||oldFile.isDirectory()){
                continue;
            }
            video.setName(suggestname);
            video.setCreatTime(new Date());

            //文件名
            String newName = getFileName();
            String suffix = filename.substring(filename.lastIndexOf("."),filename.length());
            String targetAddr = target+File.separator + "movie"+File.separator+newName+suffix;

            String coverAddr = CommonStatus.FILE_COVER_PREFIX + File.separator +
                    "movieCover" + File.separator + newName + ".jpg";
            //VideoUtil.creatPic(oldAddr,coverAddr);

            video.setVideoUrl(targetAddr);
            video.setCoverUrl(File.separator +
                    "movieCover" + File.separator + newName + ".jpg");
            video.setVideoUrl(File.separator + "movie" + File.separator + newName+suffix);

            JSONObject moveVideoJson = new JSONObject();
            moveVideoJson.put("oldAddr",oldAddr);
            moveVideoJson.put("coverAddr",coverAddr);
            moveVideoJson.put("targetAddr",targetAddr);
            msgProducer.sendToVideoCover(moveVideoJson);

            //webSiteToolsService.moveFiles(oldAddr,targetAddr);
            add(video);

        }

    }

    private void saveKorean(String source, String target, List<UpdateFileName> filemap) {
        Video video = new Video();
        video.setType(CommonStatus.VIDEO_TYPE_KOREAN);
        video.setExist(CommonStatus.VIDEO_EXIST_EXIST);

        for (UpdateFileName updateFileName : filemap) {
            String filename = updateFileName.getFilename();
            String suggestname = updateFileName.getSuggestname();
            String oldAddr = source+File.separator+filename;
            File oldFile = new File(oldAddr);
            if(!oldFile.exists()||oldFile.isDirectory()){
                continue;
            }
            video.setName(suggestname);
            video.setCreatTime(new Date());

            //文件名
            String newName = getFileName();
            String suffix = filename.substring(filename.lastIndexOf("."),filename.length());
            String targetAddr = target+File.separator + "korean"+File.separator+newName+suffix;

            String coverAddr = CommonStatus.FILE_COVER_PREFIX + File.separator +
                    "koreanCover" + File.separator + newName + ".jpg";
            //VideoUtil.creatPic(oldAddr,coverAddr);

            video.setVideoUrl(targetAddr);
            video.setCoverUrl(File.separator +
                    "koreanCover" + File.separator + newName + ".jpg");
            video.setVideoUrl(File.separator + "korean" + File.separator + newName+suffix);

            JSONObject moveVideoJson = new JSONObject();
            moveVideoJson.put("oldAddr",oldAddr);
            moveVideoJson.put("coverAddr",coverAddr);
            moveVideoJson.put("targetAddr",targetAddr);
            msgProducer.sendToVideoCover(moveVideoJson);

            //webSiteToolsService.moveFiles(oldAddr,targetAddr);
            add(video);

        }

    }

    private void saveAmerican(String source, String target, List<UpdateFileName> filemap) {
        Video video = new Video();
        video.setType(CommonStatus.VIDEO_TYPE_AMERICAN);
        video.setExist(CommonStatus.VIDEO_EXIST_EXIST);

        for (UpdateFileName updateFileName : filemap) {
            String filename = updateFileName.getFilename();
            String suggestname = updateFileName.getSuggestname();
            String oldAddr = source+File.separator+filename;
            File oldFile = new File(oldAddr);
            if(!oldFile.exists()||oldFile.isDirectory()){
                continue;
            }
            video.setName(suggestname);
            video.setCreatTime(new Date());

            //文件名
            String newName = getFileName();
            String suffix = filename.substring(filename.lastIndexOf("."),filename.length());
            String targetAddr = target+File.separator + "american"+File.separator+newName+suffix;

            String coverAddr = CommonStatus.FILE_COVER_PREFIX + File.separator +
                    "americanCover" + File.separator + newName + ".jpg";
            //VideoUtil.creatPic(oldAddr,coverAddr);

            video.setVideoUrl(targetAddr);
            video.setCoverUrl(File.separator +
                    "americanCover" + File.separator + newName + ".jpg");
            video.setVideoUrl(File.separator + "american" + File.separator + newName+suffix);

            JSONObject moveVideoJson = new JSONObject();
            moveVideoJson.put("oldAddr",oldAddr);
            moveVideoJson.put("coverAddr",coverAddr);
            moveVideoJson.put("targetAddr",targetAddr);
            msgProducer.sendToVideoCover(moveVideoJson);

            //webSiteToolsService.moveFiles(oldAddr,targetAddr);
            add(video);

        }

    }

    private void savePornVideo(String source, String target, List<UpdateFileName> filemap) {
        Video video = new Video();
        video.setType(CommonStatus.VIDEO_TYPE_PORN);
        video.setExist(CommonStatus.VIDEO_EXIST_EXIST);

        for (UpdateFileName updateFileName : filemap) {
            String filename = updateFileName.getFilename();
            String suggestname = updateFileName.getSuggestname();
            String oldAddr = source+File.separator+filename;
            File oldFile = new File(oldAddr);
            if(!oldFile.exists()||oldFile.isDirectory()){
                continue;
            }
            video.setName(suggestname);
            video.setCreatTime(new Date());

            //文件名
            String newName = getFileName();
            String suffix = filename.substring(filename.lastIndexOf("."),filename.length());
            String targetAddr = target+File.separator + "pornHub"+File.separator+newName+suffix;

            String picDir = CommonStatus.PORN_IMG_ADDR+filename.substring(filename.lastIndexOf("_")+1,filename.indexOf("."))+".png";
            File picFile = new File(picDir);

            String coverAddr = CommonStatus.FILE_COVER_PREFIX + File.separator +
                    "pornHub" + File.separator + newName + ".jpg";
            //VideoUtil.creatPic(oldAddr,coverAddr);

            video.setVideoUrl(targetAddr);
            video.setCoverUrl(File.separator +
                    "pornHub" + File.separator + newName + ".jpg");
            video.setVideoUrl(File.separator + "pornHub" + File.separator + newName+suffix);

            JSONObject moveVideoJson = new JSONObject();
            moveVideoJson.put("oldAddr",oldAddr);
            moveVideoJson.put("coverAddr",coverAddr);
            moveVideoJson.put("targetAddr",targetAddr);

            if(picFile.exists()){
                webSiteToolsService.moveFiles(picDir,coverAddr);
                webSiteToolsService.moveFiles(oldAddr,targetAddr);
            }else{
                msgProducer.sendToVideoCover(moveVideoJson);
            }
            //webSiteToolsService.moveFiles(oldAddr,targetAddr);
            add(video);

        }

    }

    private String getFileName(){
        String prefix = String.valueOf(System.currentTimeMillis());
        String s = RandomUtils.generateString(5);
        return prefix+s;
    }

    /**
     * 保存未入库视频信息
     * @param title 标题
     * @param picurl    图片url
     * @param id    id
     * @param arrayurl  分类和作者
     */
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

    /**
     * 查询文件夹下所有视频，并提供建议文件名
     * @param filepath  文件夹路径
     * @return 建议文件名
     */
    @Override
    public List<UpdateFileName> selectFileForJapan(String filepath) {
        actorService.updateLevel(null);
        File file = new File(filepath);
        List<UpdateFileName> filelist = new ArrayList<UpdateFileName>();
        List<Util> utils = utilService.findByType(1);
        if(file.exists()&&file.isDirectory()){
            File[] listFiles = file.listFiles();
            for (File listFile : listFiles) {
                String filename = listFile.getName();
                String index = filename.substring(filename.lastIndexOf("."),filename.length());
                String name = filename.substring(0,filename.lastIndexOf("."));

                for (Util util : utils) {
                    if(name.indexOf(util.getKey())>=0){
                        name=name.replace(util.getKey(),util.getValue()==null?"":util.getValue());
                    }
                }
                name.trim();
                if(name.substring(name.length()-1,name.length()).equals("C")){
                    name = name.substring(0,name.length()-1);
                }
                if(name.indexOf("-")<0){
                    name=name.substring(0, name.length()-3)+"-"+name.substring(name.length()-3, name.length());
                }
                name=name.toUpperCase();
                UpdateFileName updateFileName = new UpdateFileName();
                updateFileName.setFilename(filename);
                updateFileName.setSuggestname(name+index);
                filelist.add(updateFileName);
            }
        }
        return filelist;
    }

    /**
     * 查询文件夹下所有视频，并提供建议文件名
     * @param filepath  文件夹路径
     * @return 建议文件名
     */
    @Override
    public List<UpdateFileName> selectFileForAmerican(String filepath) {
        File file = new File(filepath);
        List<UpdateFileName> filelist = new ArrayList<UpdateFileName>();
        List<Util> utils = utilService.findByType(2);
        if(file.exists()&&file.isDirectory()){
            File[] listFiles = file.listFiles();
            for (File listFile : listFiles) {
                String filename = listFile.getName();
                String index = filename.substring(filename.lastIndexOf("."),filename.length());
                String name = filename.substring(0,filename.lastIndexOf("."));
                String search = "";
                String time = "";
                for (Util util : utils) {
                    if(name.startsWith(util.getKey())){
                        search=util.getValue();
                    }
                }
                Matcher matcher = null;
                matcher = VIDEO_AMERICAN_PATTERN.matcher(name);
                while (matcher.find()) {
                    time = matcher.group(0).trim();
                }
                time = time.replaceAll("\\.","-");
                UpdateFileName updateFileName = new UpdateFileName();
                updateFileName.setFilename(filename);
                updateFileName.setSuggestname(search+"-"+time+index);
                filelist.add(updateFileName);
            }
        }
        return filelist;
    }

    /**
     * 查询文件夹下所有视频，并提供建议文件名
     * @param filepath  文件夹路径
     * @return 建议文件名
     */
    @Override
    public List<UpdateFileName> selectFileForAnimate(String filepath) {
        File file = new File(filepath);
        List<UpdateFileName> filelist = new ArrayList<UpdateFileName>();
        List<Util> utils = utilService.findByType(2);
        if(file.exists()&&file.isDirectory()){
            File[] listFiles = file.listFiles();
            for (File listFile : listFiles) {
                String filename = listFile.getName();

                UpdateFileName updateFileName = new UpdateFileName();
                updateFileName.setFilename(filename);
                updateFileName.setSuggestname(filename);
                filelist.add(updateFileName);
            }
        }
        return filelist;
    }

    /**
     * 查询文件夹下所有视频，并提供建议文件名
     * @param filepath  文件夹路径
     * @return 建议文件名
     */
    @Override
    public List<UpdateFileName> selectFileForPornHub(String filepath) {
        File file = new File(filepath);
        List<UpdateFileName> filelist = new ArrayList<UpdateFileName>();
        List<Util> utils = utilService.findByType(2);
        if(file.exists()&&file.isDirectory()){
            File[] listFiles = file.listFiles();
            for (File listFile : listFiles) {
                String filename = listFile.getName();

                UpdateFileName updateFileName = new UpdateFileName();
                updateFileName.setFilename(filename);
                updateFileName.setSuggestname(filename);
                filelist.add(updateFileName);
            }
        }
        return filelist;
    }

    @Override
    public void saveViewHistory(Integer type, Integer id, Integer startData, Integer watchTime) {

    }

    @Override
    public JsonData getWatchList(List<Integer> idList) {
        List<Video> videoList = videoMapper.selectByIdList(idList);
        for (Video video : videoList) {
            video = getVideo(video, video.getId(), null);
        }
        return JsonData.buildSuccess(videoList);
    }

    @Override
    public JsonData suggestVideo(String id) {
        List<Video> videoList = videoMapper.getSuggestVideo(id);
        for (Video video : videoList) {
            video = getVideo(video, video.getId(), null);
        }
        return JsonData.buildSuccess(videoList);
    }

    /**
     * 更新作品分数
     * @param id    作品id
     * @param level 作品分数
     * @param typeTypeComic 作品分类
     */
    @Override
    public void changeLevel(String id, String level, Integer typeTypeComic) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        Level oldlevel =  levelService.findByProductionIdAndUserId(Integer.parseInt(id),user.getId(),typeTypeComic);
        if(oldlevel!=null){
            oldlevel.setLevel(level);
            levelService.update(oldlevel);
        }else{
            Level levels = new Level();
            levels.setLevel(level);
            levels.setProductionId(Integer.parseInt(id));
            levels.setProductionType(typeTypeComic);
            levels.setUserId(user.getId());
            levelService.add(levels);
        }
        actorService.updateLevel(Integer.parseInt(id));
    }
}
