package com.andre_luiz_dev.demo_h2_flway_kafka.exceptions;

public class TokenServiceException extends CustomizedException {
    public TokenServiceException(String message) {
        super(getFormattedMessage(message));
    }
}
