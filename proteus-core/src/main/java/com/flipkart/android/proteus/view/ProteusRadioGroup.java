package com.flipkart.android.proteus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import com.flipkart.android.proteus.ProteusView;

/**
 * Created by nky on 2022/09/30.
 */
public class ProteusRadioGroup extends RadioGroup implements ProteusView {
    private Manager viewManager;

    public ProteusRadioGroup(Context context) {
        super(context);
    }

    public ProteusRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
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
