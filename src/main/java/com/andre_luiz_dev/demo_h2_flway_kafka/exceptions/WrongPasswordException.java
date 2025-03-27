package com.andre_luiz_dev.demo_h2_flway_kafka.exceptions;

public class WrongPasswordException extends CustomizedException {
    public WrongPasswordException(String message) {
        super(getFormattedMessage(message));
    }
}
