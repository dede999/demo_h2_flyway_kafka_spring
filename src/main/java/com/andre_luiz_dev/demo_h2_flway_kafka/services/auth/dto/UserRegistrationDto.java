package com.andre_luiz_dev.demo_h2_flway_kafka.services.auth.dto;

import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.enums.UserRole;

public record UserRegistrationDto(
        String email,
        String password,
        UserRole role
) {}
