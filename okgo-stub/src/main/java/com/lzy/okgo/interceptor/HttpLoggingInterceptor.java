package com.lzy.okgo.interceptor;

import java.io.IOException;
import java.util.logging.Level;

import okhttp3.Interceptor;
import okhttp3.Response;

public class HttpLoggingInterceptor implements Interceptor {
    public enum Level {
        NONE, BASIC, HEADERS, BODY
    }

    public HttpLoggingInterceptor(String tag) {
    }

    public void setPrintLevel(Level level) {
    }

    public void setColorLevel(java.util.logging.Level level) {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
