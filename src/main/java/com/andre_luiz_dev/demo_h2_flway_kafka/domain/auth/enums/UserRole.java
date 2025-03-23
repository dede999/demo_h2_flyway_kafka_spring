package com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    BASE_USER("ROLE_USER"),
    TIER_ONE("ROLE_TIER_ONE"),
    TIER_TWO("ROLE_TIER_TWO"),
    ADMIN("ROLE_ADMIN");

    private final String role;
}
