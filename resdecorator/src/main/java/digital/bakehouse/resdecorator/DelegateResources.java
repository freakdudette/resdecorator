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

@SuppressWarnings("NullableProblems")
class DelegateResources extends Resources {

    private Resources delegate;
    private ResourceDecorator resourceDecorator;

    @SuppressWarnings("deprecation")
    DelegateResources(Resources delegate,
                      ResourceDecorator resourceDecorator) {
        super(delegate.getAssets(),
                delegate.getDisplayMetrics(),
                delegate.getConfiguration());
        this.delegate = delegate;
        this.resourceDecorator = resourceDecorator;
    }

    @Override
    public CharSequence getText(int id) throws NotFoundException {
        CharSequence result = resourceDecorator.getCharSequence(delegate, id);
        if (result != null) {
            return result;
        }
        return super.getText(id);
    }

    @Override
    public String getString(int id) throws NotFoundException {
        String result = resourceDecorator.getString(delegate, id);
        if (result != null) {
            return result;
        }
        return super.getString(id);
    }

    @Override
    public String getString(int id, Object... formatArgs) throws NotFoundException {
        String result = resourceDecorator.getString(delegate,
                id, formatArgs);
        if (result != null) {
            return result;
        }
        return super.getString(id, formatArgs);
    }


}
