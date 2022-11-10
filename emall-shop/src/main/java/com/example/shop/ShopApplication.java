package com.example.shop;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yuefei
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.example.shop.mapper")
@EnableFeignClients
@EnableDiscoveryClient
public class ShopApplication {
    public static void main(String[] args) {
        log.info("项目启动");
        SpringApplication.run(ShopApplication.class, args);
    }

}
