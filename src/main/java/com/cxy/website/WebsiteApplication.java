package com.cxy.website;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.annotation.Resource;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.cxy.website.dao")
public class WebsiteApplication  extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        System.setProperty("org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH", "true");
        ApplicationContext context = SpringApplication.run(WebsiteApplication.class, args);
        RabbitMQStart rabbitMQRun = context.getBean(RabbitMQStart.class);
        rabbitMQRun.start();
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setUrlDecode(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

    @Bean
    public RabbitMQStart rabbitMQRun() {
        return new RabbitMQStart();
    }

    private static class RabbitMQStart {
        //为了在main中的static方法中使用@value注解只能用这种办法
        @Value("${rabbitmq.start}")
        private Boolean rabbitmqStart;

        @Resource
        RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;
        public void start() {
            if(rabbitmqStart)
                rabbitListenerEndpointRegistry.start();
            else
                rabbitListenerEndpointRegistry.stop();
            System.out.println("=================== Rabbitmq:"+rabbitmqStart+"===================");
        }
    }
}
