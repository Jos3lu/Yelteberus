package com.hiberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VideoProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoProducerApplication.class, args);
    }
}
