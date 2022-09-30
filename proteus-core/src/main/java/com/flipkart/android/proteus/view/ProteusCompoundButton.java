package com.flipkart.android.proteus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import com.flipkart.android.proteus.ProteusView;

public class ProteusCompoundButton extends CompoundButton implements ProteusView {

    private Manager viewManager;

    public ProteusCompoundButton(Context context) {
        super(context);
    }

    public ProteusCompoundButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProteusCompoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Manager getViewManager() {
        return viewManager;
    }

    @Override
    public void setViewManager(@NonNull Manager manager) {
        this.viewManager = manager;
    }

    @NonNull
    @Override
    public View getAsView() {
        return this;
    }
}
