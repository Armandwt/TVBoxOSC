package org.xwalk.core;

import android.webkit.WebSettings;

public class XWalkSettings {
    public void setAllowContentAccess(boolean allow) {}
    public void setAllowFileAccess(boolean allow) {}
    public void setAllowUniversalAccessFromFileURLs(boolean allow) {}
    public void setAllowFileAccessFromFileURLs(boolean allow) {}
    public void setDatabaseEnabled(boolean flag) {}
    public void setDomStorageEnabled(boolean flag) {}
    public void setJavaScriptEnabled(boolean flag) {}
    public void setBlockNetworkImage(boolean flag) {}
    public void setMediaPlaybackRequiresUserGesture(boolean require) {}
    public void setUseWideViewPort(boolean use) {}
    public void setJavaScriptCanOpenWindowsAutomatically(boolean flag) {}
    public void setSupportMultipleWindows(boolean support) {}
    public void setLoadWithOverviewMode(boolean overview) {}
    public void setBuiltInZoomControls(boolean enabled) {}
    public void setSupportZoom(boolean support) {}
    public void setCacheMode(int mode) {}
    public void setUserAgentString(String ua) {}
    public String getUserAgentString() { return ""; }
}
