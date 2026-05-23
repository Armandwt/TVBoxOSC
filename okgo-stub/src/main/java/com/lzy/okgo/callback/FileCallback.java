package com.lzy.okgo.callback;

import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public abstract class FileCallback extends AbsCallback<File> {
    private String destFileDir;
    private String destFileName;

    public FileCallback(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
    }

    @Override
    public File convertResponse(okhttp3.Response response) throws Throwable {
        File dir = new File(destFileDir);
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir, destFileName);
        InputStream is = response.body().byteStream();
        long totalLen = response.body().contentLength();
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buf = new byte[4096];
        long downloaded = 0;
        int len;
        while ((len = is.read(buf)) != -1) {
            fos.write(buf, 0, len);
            downloaded += len;
            if (totalLen > 0) {
                Progress progress = new Progress();
                progress.fraction = (float) downloaded / totalLen;
                progress.currentSize = downloaded;
                progress.totalSize = totalLen;
                downloadProgress(progress);
            }
        }
        fos.flush();
        fos.close();
        is.close();
        return file;
    }

    public void downloadProgress(Progress progress) {
    }
}
