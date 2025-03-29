package com.andre_luiz_dev.demo_h2_flway_kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class DemoH2FlwayKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoH2FlwayKafkaApplication.class, args);
    }

}
