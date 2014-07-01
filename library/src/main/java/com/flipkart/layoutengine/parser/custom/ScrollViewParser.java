package com.flipkart.layoutengine.parser.custom;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.flipkart.layoutengine.parser.ViewParser;

/**
 * Created by kiran.kumar on 12/05/14.
 */
public class ScrollViewParser extends ViewParser<ScrollView> {
    public ScrollViewParser(Class<View> viewClass) {
        super(viewClass);
    }

    public ScrollViewParser() {
        super(ScrollView.class);
    }


}