package com.andre_luiz_dev.demo_h2_flway_kafka.exceptions;

public class RegisteredUserMailException extends CustomizedException {
  public RegisteredUserMailException(String message) {
    super(getFormattedMessage(message, RegisteredUserMailException.class.getName()), null);
  }
}
