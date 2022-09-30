package com.flipkart.android.proteus.parser.custom;

import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flipkart.android.proteus.ProteusContext;
import com.flipkart.android.proteus.ProteusView;
import com.flipkart.android.proteus.parser.ViewParser;
import com.flipkart.android.proteus.value.Layout;
import com.flipkart.android.proteus.value.ObjectValue;
import com.flipkart.android.proteus.view.ProteusRadioButton;

public class RadioButtonParser<T extends RadioButton> extends ViewParser<T> {

    @NonNull
    @Override
    public String getType() {
        return "RadioButton";
    }

    @Nullable
    @Override
    public String getParentType() {
        return "RadioGroup";
    }

    @NonNull
    @Override
    public ProteusView createView(@NonNull ProteusContext context, @NonNull Layout layout, @NonNull ObjectValue data, @Nullable ViewGroup parent, int dataIndex) {
       return new ProteusRadioButton(context);
    }
}
