package com.lzy.okgo.model;

public class Response<T> {
    private T body;
    private Throwable exception;

    public T body() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
