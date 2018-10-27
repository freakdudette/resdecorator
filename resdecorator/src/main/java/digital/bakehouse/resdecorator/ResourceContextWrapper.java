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
import android.content.ContextWrapper;
import android.content.res.Resources;

import io.github.inflationx.viewpump.InflateResult;
import io.github.inflationx.viewpump.Interceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * Wraps the {@link Context} with the sole purpose of customizing the way the app
 * {@link Resources} are retrieved.
 * Usage:
 * -> {@link #wrap(Context, ResourceDecorator)} -> to instantiate.
 * -> return the instance from {@link android.app.Activity#attachBaseContext} -> to enable;
 *
 * @see ResourceDecorator for the complete description of decoration mechanism
 */
public class ResourceContextWrapper extends ContextWrapper {

    private final ResourceDecorator resourceDecorator;
    private Resources resources;

    private ResourceContextWrapper(Context base,
                                   ResourceDecorator resourceDecorator) {
        super(base);
        this.resourceDecorator = resourceDecorator;
    }

    @Override
    public Resources getResources() {
        if (resources == null) {
            resources = new DelegateResources(super.getResources(),
                    resourceDecorator);
        }
        return resources;
    }

    /**
     * Wraps the context with customized resource retrieval
     *
     * @param context      Context to wrap
     * @param resDecorator Implementation of {@link ResourceDecorator} that will be used
     *                     each time a resource is accessed by its id
     * @return The context wrapper that enables customized resource retrieval
     * @see ResourceDecorator for more details
     */
    public static ResourceContextWrapper wrap(Context context, ResourceDecorator resDecorator) {
        context = ViewPumpContextWrapper.wrap(context);
        return new ResourceContextWrapper(context, resDecorator);
    }

    public static void initialize() {
        initialize(ViewPump.builder());
    }

    public static void initialize(ViewPump.Builder viewPumpBuilder) {
        ViewPump.init(viewPumpBuilder
                .addInterceptor(new Interceptor() {
                    @Override
                    public InflateResult intercept(Chain chain) {
                        InflateResult result = chain.proceed(chain.request());
                        ResourceUtils.decorate(result.view(), result.attrs());
                        return result;
                    }
                })
                .build());
    }
}
