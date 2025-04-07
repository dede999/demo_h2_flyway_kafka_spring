package com.andre_luiz_dev.demo_h2_flway_kafka.exceptions;

public class UserNotFoundException extends CustomizedException {
    public UserNotFoundException(String userEmail, Throwable cause) {
        super(getFormattedMessage(
            "No user found with email: " + userEmail,
            UserNotFoundException.class.getName()), cause);
    }
    public UserNotFoundException(String userEmail) {
        super(getFormattedMessage(
                "No user found with email: " + userEmail,
                UserNotFoundException.class.getName()), null);
    }
}
