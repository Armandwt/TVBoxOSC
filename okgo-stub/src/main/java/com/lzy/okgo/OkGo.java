package com.lzy.okgo;

import android.os.Handler;
import android.os.Looper;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkGo {
    private static OkGo instance;
    private OkHttpClient client;
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());

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
        if (client == null) {
            client = new OkHttpClient();
        }
        return client;
    }

    public static <T> OkGoRequest<T, ? extends OkGoRequest> get(String url) {
        return new OkGoRequest<>(url, "GET");
    }

    public static <T> OkGoRequest<T, ? extends OkGoRequest> post(String url) {
        return new OkGoRequest<>(url, "POST");
    }

    public void cancelTag(Object tag) {
        if (client != null) {
            for (Call call : client.dispatcher().queuedCalls()) {
                if (tag.equals(call.request().tag())) {
                    call.cancel();
                }
            }
            for (Call call : client.dispatcher().runningCalls()) {
                if (tag.equals(call.request().tag())) {
                    call.cancel();
                }
            }
        }
    }

    public static class OkGoRequest<T, R extends OkGoRequest> {
        private String url;
        private String method;
        private Object tag;
        private Map<String, String> headers = new HashMap<>();
        private Map<String, String> params = new HashMap<>();
        private String upBody;

        public OkGoRequest(String url, String method) {
            this.url = url;
            this.method = method;
        }

        public R tag(Object tag) {
            this.tag = tag;
            return (R) this;
        }

        public R headers(String key, String value) {
            headers.put(key, value);
            return (R) this;
        }

        public R headers(HttpHeaders h) {
            if (h != null && h.headersMap != null) {
                headers.putAll(h.headersMap);
            }
            return (R) this;
        }

        public R params(String key, String value) {
            params.put(key, value);
            return (R) this;
        }

        public R params(String key, int value) {
            params.put(key, String.valueOf(value));
            return (R) this;
        }

        public R params(String key, long value) {
            params.put(key, String.valueOf(value));
            return (R) this;
        }

        public R upString(String content) {
            this.upBody = content;
            return (R) this;
        }

        public void execute(AbsCallback<T> callback) {
            OkHttpClient httpClient = OkGo.getInstance().getOkHttpClient();

            Request.Builder builder = new Request.Builder();

            if ("GET".equals(method)) {
                HttpUrl parsed = HttpUrl.parse(url);
                if (parsed == null) {
                    Response<T> resp = new Response<>();
                    resp.setException(new IllegalArgumentException("Invalid URL: " + url));
                    mainHandler.post(() -> {
                        callback.onError(resp);
                        callback.onFinish();
                    });
                    return;
                }
                HttpUrl.Builder urlBuilder = parsed.newBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
                }
                builder.url(urlBuilder.build());
                builder.get();
            } else {
                builder.url(url);
                if (upBody != null) {
                    builder.post(okhttp3.RequestBody.create(okhttp3.MediaType.parse("text/plain;charset=utf-8"), upBody));
                } else {
                    FormBody.Builder formBuilder = new FormBody.Builder();
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        formBuilder.add(entry.getKey(), entry.getValue());
                    }
                    builder.post(formBuilder.build());
                }
            }

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }

            if (tag != null) {
                builder.tag(tag);
            }

            callback.onStart();

            httpClient.newCall(builder.build()).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Response<T> resp = new Response<>();
                    resp.setException(e);
                    mainHandler.post(() -> {
                        callback.onError(resp);
                        callback.onFinish();
                    });
                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) {
                    try {
                        T body = callback.convertResponse(response);
                        Response<T> resp = new Response<>();
                        resp.setBody(body);
                        mainHandler.post(() -> {
                            callback.onSuccess(resp);
                            callback.onFinish();
                        });
                    } catch (Throwable e) {
                        Response<T> resp = new Response<>();
                        resp.setException(e);
                        mainHandler.post(() -> {
                            callback.onError(resp);
                            callback.onFinish();
                        });
                    }
                }
            });
        }
    }
}
