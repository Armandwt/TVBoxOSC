package com.owen.tvrecyclerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class TvRecyclerView extends RecyclerView {

    public interface OnItemListener {
        void onItemPreSelected(TvRecyclerView parent, View itemView, int position);
        void onItemSelected(TvRecyclerView parent, View itemView, int position);
        void onItemClick(TvRecyclerView parent, View itemView, int position);
    }

    public interface OnInBorderKeyEventListener {
        boolean onInBorderKeyEvent(int direction, View focused);
    }

    public TvRecyclerView(Context context) {
        super(context);
    }

    public TvRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TvRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setSpacingWithMargins(int horizontalSpacing, int verticalSpacing) {
    }

    public void setOnItemListener(OnItemListener listener) {
    }

    public void setOnInBorderKeyEventListener(OnInBorderKeyEventListener listener) {
    }

    public void setSelection(int position) {
        scrollToPosition(position);
    }

    public void setInterceptFocus(boolean intercept) {
    }

    public boolean isScrolling() {
        return getScrollState() != SCROLL_STATE_IDLE;
    }

    public void setSelectedPosition(int position) {
        scrollToPosition(position);
    }
}
