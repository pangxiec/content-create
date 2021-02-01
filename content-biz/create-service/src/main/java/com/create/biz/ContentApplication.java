package com.create.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xmy
 * @date 2021/2/1 14:50
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.create"})
@MapperScan("com.create.mapper")
public class ContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class,args);
    }

}
