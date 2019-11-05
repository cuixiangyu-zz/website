package com.cxy.website;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.cxy.website.dao")
public class WebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);
    }

}
