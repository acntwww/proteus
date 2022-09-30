package com.flipkart.android.proteus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;

import com.flipkart.android.proteus.ProteusView;


/**
 * Created by nky on 2022/09/30.
 */
public class ProteusRadioButton extends AppCompatRadioButton implements ProteusView {

    private Manager viewManager;

    public ProteusRadioButton(Context context) {
        super(context);
    }

    public ProteusRadioButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProteusRadioButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
