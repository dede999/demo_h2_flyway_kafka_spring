package com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.records;

public record EmailSendingDto(
    String message,
    String email,
    String subject
) {
    public String compressedToString() {
        return String.format("%s\t%s\t%s", message, email, subject);
    }

    public static EmailSendingDto fromString(String str) {
        String[] split = str.split("\t");
        return new EmailSendingDto(split[0], split[1], split[2]);
    }
}
