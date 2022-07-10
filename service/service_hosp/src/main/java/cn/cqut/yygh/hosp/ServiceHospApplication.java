package cn.cqut.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CuriT
 * @Date 2022-7-8 17:05
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.cqut")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}

