package org.xwalk.core;

import android.content.Context;
import android.widget.FrameLayout;

public class XWalkView extends FrameLayout {
    public XWalkView(Context context) {
        super(context);
    }

    public XWalkSettings getSettings() {
        return new XWalkSettings();
    }

    public void setUIClient(XWalkUIClient client) {
    }

    public void setResourceClient(XWalkResourceClient client) {
    }

    public void loadUrl(String url) {
    }

    public void stopLoading() {
    }

    public void onDestroy() {
    }

    public void evaluateJavascript(String script, Object callback) {
    }

    public void clearCache(boolean includeDiskFiles) {
    }
}
