package org.xwalk.core;

public class XWalkUIClient {
    public enum ConsoleMessageType {
        DEBUG, ERROR, INFO, LOG, WARNING
    }

    public XWalkUIClient(XWalkView view) {
    }

    public boolean onConsoleMessage(XWalkView view, String message, int lineNumber, String sourceId, ConsoleMessageType messageType) {
        return false;
    }

    public boolean onJsAlert(XWalkView view, String url, String message, XWalkJavascriptResult result) {
        return false;
    }

    public boolean onJsConfirm(XWalkView view, String url, String message, XWalkJavascriptResult result) {
        return false;
    }

    public boolean onJsPrompt(XWalkView view, String url, String message, String defaultValue, XWalkJavascriptResult result) {
        return false;
    }
}
