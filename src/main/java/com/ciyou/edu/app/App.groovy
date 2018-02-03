package com.ciyou.edu.app

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

/**
 * @Author C.
 * @Date 2018-02-02 11:16
 */

@SpringBootApplication
@ComponentScan(basePackages = ["com.ciyou.edu.controller","com.ciyou.edu.aop","com.ciyou.edu.service","com.ciyou.edu.config"])
@MapperScan(basePackages = "com.ciyou.edu.mapper")
class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args)
    }
}
