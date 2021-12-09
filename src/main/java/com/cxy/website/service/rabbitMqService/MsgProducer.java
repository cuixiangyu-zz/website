package com.cxy.website.service.rabbitMqService;

import com.alibaba.fastjson.JSONObject;
import com.cxy.website.common.config.rabbitMq.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: website
 * @description: 生产者
 * @author: CuiXiangYu
 * @create: 2020-08-28 16:35
 **/
@Component
public class MsgProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 影片名队列
     * @param jsonObject    影片名
     */
    public void sendToVideoName(JSONObject jsonObject){
        rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE,RabbitMqConfig.VIDEO_NAME_DIRECT_ROUTING_KEY,jsonObject.toJSONString());
    }

    /**
     * 作者名队列
     * @param jsonObject    作者名
     */
    public void sendToArtistName(JSONObject jsonObject){
        rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE,RabbitMqConfig.ARTIST_NAME_DIRECT_ROUTING_KEY,jsonObject.toJSONString());
    }

    /**
     * 封面url队列
     * @param jsonObject    封面url
     */
    public void sendToCoverUrl(JSONObject jsonObject){
        rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE,RabbitMqConfig.COVER_URL_DIRECT_ROUTING_KEY,jsonObject.toJSONString());
    }

    /**
     * 移动影片队列
     * @param jsonObject
     */
    public void sendToMoveVideo(JSONObject jsonObject){
        rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE,RabbitMqConfig.MOVE_VIDEO_DIRECT_ROUTING_KEY,jsonObject.toJSONString());
    }
}
