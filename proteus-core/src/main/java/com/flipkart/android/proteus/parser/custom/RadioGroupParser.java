package com.flipkart.android.proteus.parser.custom;

import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flipkart.android.proteus.ProteusContext;
import com.flipkart.android.proteus.ProteusView;
import com.flipkart.android.proteus.parser.ViewParser;
import com.flipkart.android.proteus.value.Layout;
import com.flipkart.android.proteus.value.ObjectValue;
import com.flipkart.android.proteus.view.ProteusRadioGroup;

/**
 * Created by nky on 2022/09/30.
 */
public class RadioGroupParser<T extends RadioGroup> extends ViewParser<T> {

    @NonNull
    @Override
    public String getType() {
        return "RadioGroup";
    }

    @Nullable
    @Override
    public String getParentType() {
        return "LinearLayout";
    }

    @NonNull
    @Override
    public ProteusView createView(@NonNull ProteusContext context, @NonNull Layout layout, @NonNull ObjectValue data,
                                  @Nullable ViewGroup parent, int dataIndex) {
        return new ProteusRadioGroup(context);
    }


}
