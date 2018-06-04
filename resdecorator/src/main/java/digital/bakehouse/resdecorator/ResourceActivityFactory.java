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

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Awesome implementation from guys @Calligraphy
 *
 * @author https://github.com/chrisjenx/Calligraphy
 */
interface ResourceActivityFactory {

    /**
     * Used to Wrap the Activity onCreateView method.
     * <p>
     * You implement this method like so in you base activity.
     * <pre>
     * {@code
     * public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
     *   return DecorContextWrapper.get(getBaseContext()).onActivityCreateView(super.onCreateView(parent, name, context, attrs), attrs);
     * }
     * }
     * </pre>
     *
     * @param parent  parent view, can be null.
     * @param view    result of {@code super.onCreateView(parent, name, context, attrs)}, this might be null, which is fine.
     * @param name    Name of View we are trying to inflate
     * @param context current context (normally the Activity's)
     * @param attrs   see {@link android.view.LayoutInflater.Factory2#onCreateView(android.view.View, String, android.content.Context, android.util.AttributeSet)}  @return the result from the activities {@code onCreateView()}
     * @return The view passed in, or null if nothing was passed in.
     * @see android.view.LayoutInflater.Factory2
     */
    View onActivityCreateView(View parent, View view, String name, Context context, AttributeSet attrs);
}


