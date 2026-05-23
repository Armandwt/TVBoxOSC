package com.lzy.okgo.model;

public class Response<T> {
    private T body;
    private Throwable exception;

    public T body() {
        return body;
    }

    public Throwable getException() {
        return exception;
    }
}
