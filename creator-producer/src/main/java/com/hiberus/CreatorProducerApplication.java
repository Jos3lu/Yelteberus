package com.hiberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CreatorProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreatorProducerApplication.class, args);
    }
}
