package com.cxy.website.common.config.rabbitMq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: website
 * @description: rabbitMq配置文件
 * @author: CuiXiangYu
 * @create: 2020-08-28 16:22
 **/
@Configuration
public class RabbitMqConfig {
    //队列名
    /*public static final String FANOUT_QUEUE_NAME = "test_fanout_queue";
    public static final String FANOUT_QUEUE_NAME1 = "test_fanout_queue1";
    public static final String TEST_FANOUT_EXCHANGE = "testFanoutExchange";*/

    //队列名
    public static final String VIDEO_NAME_INFO_QUEUE_NAME = "video_name_info_queue";
    public static final String MOVE_VIDEO_QUEUE_NAME = "move_video_queue";
    public static final String ARTIST_NAME_QUEUE_NAME = "artist_name_queue";
    public static final String COVER_URL_QUEUE_NAME = "cover_url_queue";
    public static final String CREAT_VIDEO_COVER_QUEUE_NAME = "video_cover_queue";
    public static final String REFRESH_COVER_QUEUE_NAME = "refresh_cover_queue";

    //交换机名
    public static final String DIRECT_EXCHANGE = "DirectExchange";


    /*public static final String TOPIC_QUEUE_NAME = "test_topic_queue";
    public static final String TEST_TOPIC_EXCHANGE = "testTopicExchange";
    public static final String TOPIC_ROUTINGKEY = "test.*";*/

    //routing_key名
    public static final String VIDEO_NAME_DIRECT_ROUTING_KEY = "video_name";
    public static final String ARTIST_NAME_DIRECT_ROUTING_KEY = "artist_name";
    public static final String COVER_URL_DIRECT_ROUTING_KEY = "cover_url";
    public static final String MOVE_VIDEO_DIRECT_ROUTING_KEY = "move_video";
    public static final String CREAT_VIDEO_COVER_ROUTING_KEY = "video_cover";
    public static final String REFRESH_COVER_ROUTING_KEY = "refresh_cover";

    //创建队列
    @Bean
    public Queue createVideoNameInfoQueue() {
        return new Queue(VIDEO_NAME_INFO_QUEUE_NAME);
    }

    @Bean
    public Queue createMoveVideoQueue() {
        return new Queue(MOVE_VIDEO_QUEUE_NAME);
    }

    @Bean
    public Queue createArtistNameQueue() {
        return new Queue(ARTIST_NAME_QUEUE_NAME);
    }

    @Bean
    public Queue createCoverUrlQueue() {
        return new Queue(COVER_URL_QUEUE_NAME);
    }

    @Bean
    public Queue createVideoCoverQueue() {
        return new Queue(CREAT_VIDEO_COVER_QUEUE_NAME);
    }

    @Bean
    public Queue createRefreshCoverQueue() {
        return new Queue(REFRESH_COVER_QUEUE_NAME);
    }

    //创建交换机
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    //队列绑定交换机和routing_key
    @Bean
    Binding bindingVideoNameInfoDirect() {
        return BindingBuilder.bind(createVideoNameInfoQueue()).
                to(directExchange()).
                with(VIDEO_NAME_DIRECT_ROUTING_KEY);
    }

    @Bean
    Binding bindingMoveVideoDirect() {
        return BindingBuilder.bind(createMoveVideoQueue()).
                to(directExchange()).
                with(MOVE_VIDEO_DIRECT_ROUTING_KEY);
    }

    @Bean
    Binding bindingArtistNameDirect() {
        return BindingBuilder.bind(createArtistNameQueue()).
                to(directExchange()).
                with(ARTIST_NAME_DIRECT_ROUTING_KEY);
    }

    @Bean
    Binding bindingCoverUrlDirect() {
        return BindingBuilder.bind(createCoverUrlQueue()).
                to(directExchange()).
                with(COVER_URL_DIRECT_ROUTING_KEY);
    }

    @Bean
    Binding bindingVideoCoverDirect() {
        return BindingBuilder.bind(createVideoCoverQueue()).
                to(directExchange()).
                with(CREAT_VIDEO_COVER_ROUTING_KEY);
    }

    @Bean
    Binding bindingRefreshCoverDirect() {
        return BindingBuilder.bind(createRefreshCoverQueue()).
                to(directExchange()).
                with(REFRESH_COVER_ROUTING_KEY);
    }

    /*//创建队列
    @Bean
    public Queue createFanoutQueue() {
        return new Queue(FANOUT_QUEUE_NAME);
    }

    //创建队列
    @Bean
    public Queue createFanoutQueue1() {
        return new Queue(FANOUT_QUEUE_NAME1);
    }*/

    //创建队列


    /*//创建队列
    @Bean
    public Queue createTopicQueue() {
        return new Queue(TOPIC_QUEUE_NAME);
    }

    //创建交换机
    @Bean
    public FanoutExchange defFanoutExchange() {
        return new FanoutExchange(TEST_FANOUT_EXCHANGE);
    }

    //队列与交换机进行绑定
    @Bean
    Binding bindingFanout() {
        return BindingBuilder.bind(createFanoutQueue()).
                to(defFanoutExchange());
    }

    //队列与交换机进行绑定
    @Bean
    Binding bindingFanout1() {
        return BindingBuilder.bind(createFanoutQueue1()).
                to(defFanoutExchange());
    }*/



    /*@Bean
    TopicExchange defTopicExchange(){
        return new TopicExchange(TEST_TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingTopic() {
        return BindingBuilder.bind(createTopicQueue()).
                to(defTopicExchange()).
                with(TOPIC_ROUTINGKEY);
    }*/
}
