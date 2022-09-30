package com.flipkart.android.proteus.parser.custom;

import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flipkart.android.proteus.ProteusContext;
import com.flipkart.android.proteus.ProteusView;
import com.flipkart.android.proteus.parser.ViewParser;
import com.flipkart.android.proteus.value.Layout;
import com.flipkart.android.proteus.value.ObjectValue;
import com.flipkart.android.proteus.view.ProteusCompoundButton;

public class CompoundButtonParser<V extends CompoundButton> extends ViewParser<V> {

    @Override
    public String getType() {
        return "CompoundButton";
    }

    @Override
    public String getParentType() {
        return "Button";
    }

    @NonNull
    @Override
    public ProteusView createView(@NonNull ProteusContext context, @NonNull Layout layout, @NonNull ObjectValue data, @Nullable ViewGroup parent, int dataIndex) {
        return new ProteusCompoundButton(context);
    }
}
