package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 * 订单模块
 * </p>
 *
 * @author ShaoHuaYuGong
 * @date 2024/06/11
 */
@SpringBootApplication
@ComponentScan("com.atguigu.cloud")
@EnableDiscoveryClient
public class Order80 {
    public static void main(String[] args) {
        SpringApplication.run(Order80.class, args);
    }
}