package com.create.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xmy
 * @date 2021/2/3 21:53
 */
@SpringBootApplication
@ComponentScan({"com.create"})
@MapperScan("com.create.mapper")
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class,args);
    }
}
