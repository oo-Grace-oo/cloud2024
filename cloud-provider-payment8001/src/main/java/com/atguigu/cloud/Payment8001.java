package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * <p>
 *
 * </p>
 *
 * @author ShaoHuaYuGong
 * @date 2024/05/30
 */
@SpringBootApplication
@MapperScan("com.atguigu.cloud.mapper")
public class Payment8001 {
    public static void main(String[] args) {
        SpringApplication.run(Payment8001.class, args);
    }
}