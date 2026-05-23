package com.lzy.okgo.callback;

import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import java.io.File;

public abstract class FileCallback extends AbsCallback<File> {
    public FileCallback(String destFileDir, String destFileName) {
    }

    @Override
    public File convertResponse(okhttp3.Response response) throws Throwable {
        return null;
    }

    public void downloadProgress(Progress progress) {
    }
}
