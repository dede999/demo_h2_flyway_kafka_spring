package com.andre_luiz_dev.demo_h2_flway_kafka.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvLoader {
    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure().load();
    }
}
