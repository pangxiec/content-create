package com.create.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author xmy
 * @date 2021/2/2 23:26
 */
@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@ComponentScan({"com.create"})
@MapperScan("com.create.mapper")
public class UCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UCenterApplication.class,args);
    }

}
