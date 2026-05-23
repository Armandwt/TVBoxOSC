package com.lzy.okgo.model;

import java.util.LinkedHashMap;

public class HttpHeaders {
    public LinkedHashMap<String, String> headersMap = new LinkedHashMap<>();

    public static void setUserAgent(String ua) {
    }

    public void put(String key, String value) {
        headersMap.put(key, value);
    }

    public String get(String key) {
        return headersMap.get(key);
    }
}
