package com.andre_luiz_dev.demo_h2_flway_kafka.exceptions;

import java.util.List;

public class CustomizedException extends RuntimeException {
  public CustomizedException(String message) {
    super(message);
  }

  public static List<String> getErrorContext() {
    String className = CustomizedException.class.getSimpleName(); // Get simple class name (e.g., "ServiceNameException")
      className = className.substring(0, className.length() - "Exception".length()); // Remove "Exception"
      return List.of(className.split("(?=[A-Z])"));

  }

  public  static String getFormattedMessage(String message) {
    final String ERROR_MESSAGE_TEMPLATE = "Message > %s\n Error Context > %s\n";
    return String.format(ERROR_MESSAGE_TEMPLATE, message, getErrorContext());
  }
}
