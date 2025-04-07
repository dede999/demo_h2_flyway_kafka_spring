package com.andre_luiz_dev.demo_h2_flway_kafka.configuration;

import com.andre_luiz_dev.demo_h2_flway_kafka.exceptions.JsonParsingException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonUtil {
    private static final Gson gson = new Gson();

    public static String convertToJson(Object object) {
        return gson.toJson(object);
    }

    // Converts a JSON string to an object of the specified class type
    public static <T> T fromJson(String json, Class<T> clazz) throws JsonParsingException {
        try {
            return gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            // Wrap the exception in a custom exception with a meaningful message
            throw new JsonParsingException("Invalid JSON format or unable to parse to " + clazz.getName(), e);
        } catch (Exception e) {
            throw new JsonParsingException("An unexpected error occurred while parsing JSON", e);
        }
    }

}
