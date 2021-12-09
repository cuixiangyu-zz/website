package com.cxy.website.service.rabbitMqService;

import com.alibaba.fastjson.JSONObject;
import com.cxy.website.common.CommonStatus;
import com.cxy.website.common.config.rabbitMq.RabbitMqConfig;
import com.cxy.website.model.Actor;
import com.cxy.website.model.Level;
import com.cxy.website.model.UpdateFileName;
import com.cxy.website.model.Video;
import com.cxy.website.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: website
 * @description: 消费者
 * @author: CuiXiangYu
 * @create: 2020-08-28 16:39
 **/
@Component
@Slf4j
public class MsgConsumer {

    @Autowired
    private WebSiteToolsService webSiteToolsService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private LevelService levelService;

    @Autowired
    private MsgProducer msgProducer;

    /**
     * 根据名字从网站爬取作品名，演员等信息队列
     * @param massage   名字
     */
    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitMqConfig.VIDEO_NAME_INFO_QUEUE_NAME, durable = "true"),
                                    exchange = @Exchange(value = RabbitMqConfig.DIRECT_EXCHANGE),
                                    key = RabbitMqConfig.VIDEO_NAME_DIRECT_ROUTING_KEY)
                    })
    @RabbitHandler
    public void processVideoNameInfoMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        System.out.println("processVideoNameMsg1-----------------------"+msg);

        JSONObject videoNameQueue = (JSONObject) JSONObject.parse(msg);
        String source = videoNameQueue.getString("source");
        JSONObject updateFileNameJson = videoNameQueue.getJSONObject("updateFileName");
        UpdateFileName updateFileName = updateFileNameJson.toJavaObject(UpdateFileName.class);
        String fileaddress = source + File.separator + updateFileName.getFilename();
        String suggestname = updateFileName.getSuggestname();

        File file = new File(fileaddress);
        Video video = new Video();
        String filename = suggestname.substring(0, suggestname.indexOf("."));

        //从网页获取视频基本信息
        Map<String, Object> videoinfo = webSiteToolsService.getvideoinfo(filename);

        if (videoinfo == null || videoinfo.get("title") == null || videoinfo.get("title") == "") {
            return;
        }

        videoNameQueue.put("videoinfo",videoinfo);
        msgProducer.sendToArtistName(videoNameQueue);
    }

    /**
     * 移动影片到指定文件夹
     * @param massage   来源文件夹和目标文件夹
     */
    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitMqConfig.MOVE_VIDEO_QUEUE_NAME, durable = "true"),
                                    exchange = @Exchange(value = RabbitMqConfig.DIRECT_EXCHANGE),
                                    key = RabbitMqConfig.MOVE_VIDEO_DIRECT_ROUTING_KEY)
                    })
    @RabbitHandler
    public void processMoveVideoMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        System.out.println("processVideoNameMsg2-----------------------"+msg);

        JSONObject moveVideoJson = (JSONObject) JSONObject.parse(msg);
        String fileAddress = moveVideoJson.getString("fileAddress");
        String targetPath = moveVideoJson.getString("targetPath");
        webSiteToolsService.moveFiles(fileAddress, targetPath);
    }

    /**
     * 将作品名，文件路径，封
     * 面路径等保存到数据库
     * @param massage   品名，文件路径，封面路径
     */
    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitMqConfig.ARTIST_NAME_QUEUE_NAME, durable = "true"),
                                    exchange = @Exchange(value = RabbitMqConfig.DIRECT_EXCHANGE),
                                    key = RabbitMqConfig.ARTIST_NAME_DIRECT_ROUTING_KEY)
                    })
    @RabbitHandler
    public void processArtistNameMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        System.out.println("processVideoNameMsg3-----------------------"+msg);

        JSONObject videoNameQueue = (JSONObject) JSONObject.parse(msg);
        String source = videoNameQueue.getString("source");
        String type = videoNameQueue.getString("type");
        String target = videoNameQueue.getString("target");
        JSONObject updateFileNameJson = videoNameQueue.getJSONObject("updateFileName");
        UpdateFileName updateFileName = updateFileNameJson.toJavaObject(UpdateFileName.class);
        Map<String, Object> videoinfo = (Map<String, Object>) videoNameQueue.get("videoinfo");

        String fileAddress = source + File.separator + updateFileName.getFilename();
        String suggestname = updateFileName.getSuggestname();
        File file = new File(fileAddress);
        Video video = new Video();
        String filename = suggestname.substring(0, suggestname.indexOf("."));

        Video oldvideo = videoService.findByName(videoinfo.get("title").toString());
        if (oldvideo != null && oldvideo.getExist() == 3) {
            return;
        } else if (oldvideo != null && oldvideo.getExist() == 1) {
            return;
        }
        List<String> artists = (List) videoinfo.get("artists");

        if (artists != null && artists.size() > 0) {
            for (String artist : artists) {
                Actor actor = actorService.findByName(artist);
                if (actor == null) {
                    Actor actor1 = new Actor();
                    actor1.setName(artist);
                    actor1.setChineseName(artist);
                    if (type.equals("english")) {
                        actor1.setType(CommonStatus.ACTOR_TYPE_AMERICAN);
                    } else if (type.equals("japanvideo")) {
                        actor1.setType(CommonStatus.ACTOR_TYPE_JAPAN);
                    }

                    actor1.setCreatTime(new Date());
                    actorService.add(actor1);
                    Actor actor2 = actorService.findByName(artist);
                    Level level = new Level();
                    level.setProductionId(actor2.getId());
                    level.setLevel("0");
                    level.setUserId(0);
                    level.setProductionType(5);
                    levelService.add(level);
                }
            }
        }
        //移动视频文件到对应演员的文件夹
        if (artists.size() == 1) {
            String targetPath = target + File.separator + type + File.separator +
                    artists.get(0) + File.separator + suggestname;

            JSONObject moveVideoJson = new JSONObject();
            moveVideoJson.put("fileAddress",fileAddress);
            moveVideoJson.put("targetPath",targetPath);
            msgProducer.sendToMoveVideo(moveVideoJson);

            video.setVideoUrl(File.separator + type + File.separator +
                    artists.get(0) + File.separator + suggestname);
        } else {
            String targetPath = target + File.separator + type + File.separator +
                    "多作者" + File.separator + file.getName();

            JSONObject moveVideoJson = new JSONObject();
            moveVideoJson.put("fileAddress",fileAddress);
            moveVideoJson.put("targetPath",targetPath);
            msgProducer.sendToMoveVideo(moveVideoJson);

            video.setVideoUrl(File.separator + type + File.separator +
                    "多作者" + File.separator + suggestname);
        }

        //下载视频封面
        if (videoinfo.get("picurl") != null) {

            String localAddress = CommonStatus.FILE_COVER_PREFIX + File.separator +"japanVideoCover";
            JSONObject picUrlJson = new JSONObject();
            picUrlJson.put("picUrl",videoinfo.get("picurl").toString());
            picUrlJson.put("localAddress",localAddress);
            picUrlJson.put("filename",filename);

            msgProducer.sendToCoverUrl(picUrlJson);

            video.setCoverUrl(File.separator +
                    "japanVideoCover" + File.separator + filename + ".jpg");
        }
        if (type.equals("english")) {
            video.setType(CommonStatus.VIDEO_TYPE_AMERICAN);
        } else if (type.equals("japanvideo")) {
            video.setType(CommonStatus.VIDEO_TYPE_JAPAN);
        }

        video.setExist(CommonStatus.VIDEO_EXIST_EXIST);
        video.setName(videoinfo.get("title") == null ? filename : videoinfo.get("title").toString());
        video.setCreatTime(new Date());
        videoService.add(video);
        Video video1 = videoService.findByName(video.getName());
        if (videoinfo.get("category") != null && ((List) videoinfo.get("category")).size() > 0) {
            typeService.updateVideoType(video1.getId(), (List<String>) videoinfo.get("category"));
        }
        if (videoinfo.get("artists") != null && ((List) videoinfo.get("artists")).size() > 0) {
            actorService.updateVideoActor(video1.getId(), (List<String>) videoinfo.get("artists"));
        }
    }

    /**
     * 下载封面到指定路径
     * @param massage   封面url
     */
    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitMqConfig.COVER_URL_QUEUE_NAME, durable = "true"),
                                    exchange = @Exchange(value = RabbitMqConfig.DIRECT_EXCHANGE),
                                    key = RabbitMqConfig.COVER_URL_DIRECT_ROUTING_KEY)
                    })
    @RabbitHandler
    public void processCoverUrlMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        System.out.println("processVideoNameMsg4-----------------------"+msg);

        JSONObject picUrlJson = JSONObject.parseObject(msg);

        String picUrl = picUrlJson.getString("picUrl");
        String localAddress = picUrlJson.getString("localAddress");
        String filename = picUrlJson.getString("filename");
        if(StringUtils.isEmpty(picUrl)){
            return;
        }
        webSiteToolsService.downloadPics(picUrl, localAddress, filename);
    }
}
