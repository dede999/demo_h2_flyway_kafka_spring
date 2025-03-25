package com.andre_luiz_dev.demo_h2_flway_kafka.services.auth.dto;

public record UserLoginDto(
        String email,
        String password
) {}
