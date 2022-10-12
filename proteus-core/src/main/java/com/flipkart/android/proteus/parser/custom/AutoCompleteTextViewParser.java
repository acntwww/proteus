package com.flipkart.android.proteus.parser.custom;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import com.flipkart.android.proteus.ProteusContext;
import com.flipkart.android.proteus.ProteusView;
import com.flipkart.android.proteus.ViewTypeParser;
import com.flipkart.android.proteus.value.Layout;
import com.flipkart.android.proteus.value.ObjectValue;
import com.flipkart.android.proteus.view.ProteusAutoCompleteTextView;

public class AutoCompleteTextViewParser<T extends AppCompatAutoCompleteTextView> extends ViewTypeParser<T> {
    @NonNull
    @Override
    public String getType() {
        return "AutoCompleteTextView";
    }

    @Nullable
    @Override
    public String getParentType() {
        return "EditText";
    }

    @NonNull
    @Override
    public ProteusView createView(@NonNull ProteusContext context, @NonNull Layout layout, @NonNull ObjectValue data, @Nullable ViewGroup parent, int dataIndex) {
        return new ProteusAutoCompleteTextView(context);
    }

    @Override
    protected void addAttributeProcessors() {

    }
}
