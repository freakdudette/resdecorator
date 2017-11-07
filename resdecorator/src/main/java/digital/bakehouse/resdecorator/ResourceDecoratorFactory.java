/*
 * Copyright 2017 bakehousedigital
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package digital.bakehouse.resdecorator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

class ResourceDecoratorFactory {

    View onViewCreated(View view, String name, View parent, Context context, AttributeSet attrs) {
        return onViewCreated(view, context, attrs);
    }

    View onViewCreated(View view, Context context, AttributeSet attrs) {
        if (view == null) {
            return null;
        }
        decorate(view, attrs);
        return view;
    }

    private void decorate(View view, AttributeSet attrs) {
        if (view instanceof TextView) {
            setText((TextView) view, attrs);
            setHint((TextView) view, attrs);
        }
    }

    private void setText(TextView textView, AttributeSet attrs) {
        int textResId = attrs.getAttributeResourceValue(
                "http://schemas.android.com/apk/res/android",
                "text",
                -1);
        if (textResId != -1) {
            textView.setText(textResId);
        }
    }

    private void setHint(TextView textView, AttributeSet attrs) {
        int textResId = attrs.getAttributeResourceValue(
                "http://schemas.android.com/apk/res/android",
                "hint",
                -1);
        if (textResId != -1) {
            textView.setHint(textResId);
        }
    }

}