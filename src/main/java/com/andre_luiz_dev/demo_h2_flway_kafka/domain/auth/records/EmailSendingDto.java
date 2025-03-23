package com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.records;

public record EmailSendingDto(
    String message,
    String email,
    String subject
) {}
