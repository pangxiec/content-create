package com.create.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xmy
 * @date 2021/2/2 23:26
 */
@SpringBootApplication
@ComponentScan({"com.create"})
@MapperScan("com.create.mapper")
public class UCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UCenterApplication.class,args);
    }
}
