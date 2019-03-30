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

import android.content.res.Resources;

/**
 * Decorates Android {@link Resources} with the target of adding customization on top of it.
 * Used in conjunction with {@link ResourceContextWrapper} it ca be used as a hook to be executed
 * when a default resource is getting retrieved.
 * <p>
 * An example could be to add localization in your app at some point.
 * Normally you would have to either:
 * -> extend the {@link android.widget.TextView} classes, and define a custom xml tag or similar
 * -> or traverse the view tree
 * -> or to actually call {@link android.widget.TextView#setText} for all of the app's labels
 * Using this class you can just override {@link #getString(Resources, int, Object...)} and
 * map the id or the resource name to a different string.
 */
public interface ResourceDecorator {

    /**
     * Decorates the retrieval of strings from {@link Resources}.
     * This method will be called each time the resource id of the text or hint is being resolved:
     * -> {@link android.content.Context#getResources} getString or getText methods are called
     * -> a layout is inflated with classes that are or extend {@link android.widget.TextView}
     * and a text resource id is set for text and hint
     * -> {@link android.widget.TextView#setText(int)} is called
     *
     * @param resources Raw resources
     * @param id        Id of the resource to resolve
     * @param params    Arguments to be used while formatting the resolved id
     * @return If returns null -> the loaded text will be retrieved using the default method.
     * Example: getResources().getString(id) will return the default resolved string.
     * Otherwise returns customized string resource.
     * <p>
     * <pre>
     * {@code
     *      ResourceContextWrapper.wrap(context, new ResourceDecorator() {
     *          public String getString(Resources resources, int id, Object... params) {
     *              if(id == R.id.app_name) {
     *                  return resources.getString(id).toUpperCase();
     *              }
     *          }
     *      }
     * }
     * </pre>
     */
    String getString(Resources resources, int id, Object... params);

    String getCharSequence(Resources resources, int id, Object... params);
}
