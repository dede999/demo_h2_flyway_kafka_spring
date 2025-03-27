package com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    BASE_USER("USER"),
    TIER_ONE("TIER_ONE"),
    TIER_TWO("TIER_TWO"),
    ADMIN("ADMIN");

    private final String role;
}
