package com.lzy.okgo;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpHeaders;

import okhttp3.OkHttpClient;

public class OkGo {
    private static OkGo instance;
    private OkHttpClient client;

    public static synchronized OkGo getInstance() {
        if (instance == null) {
            instance = new OkGo();
        }
        return instance;
    }

    public OkGo setOkHttpClient(OkHttpClient client) {
        this.client = client;
        return this;
    }

    public OkHttpClient getOkHttpClient() {
        return client;
    }

    public static <T> Request<T, ? extends Request> get(String url) {
        return new Request<>(url);
    }

    public static <T> Request<T, ? extends Request> post(String url) {
        return new Request<>(url);
    }

    public void cancelTag(Object tag) {
    }

    public static class Request<T, R extends Request> {
        private String url;
        private Object tag;

        public Request(String url) {
            this.url = url;
        }

        public R tag(Object tag) {
            this.tag = tag;
            return (R) this;
        }

        public R headers(String key, String value) {
            return (R) this;
        }

        public R headers(HttpHeaders headers) {
            return (R) this;
        }

        public R params(String key, String value) {
            return (R) this;
        }

        public R params(String key, int value) {
            return (R) this;
        }

        public R params(String key, long value) {
            return (R) this;
        }

        public R upString(String content) {
            return (R) this;
        }

        public void execute(AbsCallback<T> callback) {
        }
    }
}
