package com.andre_luiz_dev.demo_h2_flway_kafka.configuration;

import com.google.gson.Gson;

public class JsonUtil {
    private static final Gson gson = new Gson();

    public static String convertToJson(Object object) {
        return gson.toJson(object);
    }
}
