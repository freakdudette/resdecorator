/*
 * Copyright 2018 bakehousedigital
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

import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

class ResourceUtils {
    private static final String SCHEMA = "http://schemas.android.com/apk/res/android";
    private static final String TAG_TEXT = "text";
    private static final String TAG_HINT = "hint";
    private static final int DEFAULT_RES_ID = -1;

    static void decorate(View view, AttributeSet attrs) {
        if (view instanceof TextView) {
            setText((TextView) view, attrs);
            setHint((TextView) view, attrs);
        }
    }

    private static void setText(TextView textView, AttributeSet attrs) {
        int textResId = attrs.getAttributeResourceValue(
                SCHEMA, TAG_TEXT, DEFAULT_RES_ID);
        if (textResId != DEFAULT_RES_ID) {
            textView.setText(textResId);
        }
    }

    private static void setHint(TextView textView, AttributeSet attrs) {
        int textResId = attrs.getAttributeResourceValue(
                SCHEMA, TAG_HINT, DEFAULT_RES_ID);
        if (textResId != DEFAULT_RES_ID) {
            textView.setHint(textResId);
        }
    }
}
