package cn.cqut.yygh.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CuriT
 * @Date 2022-7-21 10:59
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cn.cqut"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.cqut"})
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}

