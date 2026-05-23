package com.lzy.okgo.callback;

import com.lzy.okgo.model.Response;

public abstract class AbsCallback<T> {
    public void onStart() {}
    public void onSuccess(Response<T> response) {}
    public void onError(Response<T> response) {}
    public void onFinish() {}
    public abstract T convertResponse(okhttp3.Response response) throws Throwable;
}
