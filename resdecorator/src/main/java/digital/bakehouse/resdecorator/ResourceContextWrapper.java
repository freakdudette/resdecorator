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

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import digital.bakehouse.resdecorator.viewdecor.ViewDecorator;
import digital.bakehouse.resdecorator.viewdecor.ViewDecoratorInstaller;

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
    private LayoutInflater inflater;

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

    @Override
    public Object getSystemService(String name) {
        if (LAYOUT_INFLATER_SERVICE.equals(name)) {
            if (inflater == null) {
                inflater = new ResourceLayoutInflater(
                        LayoutInflater.from(getBaseContext()), this, false);
            }
            return inflater;
        }
        return super.getSystemService(name);
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
        return new ResourceContextWrapper(context, resDecorator);
    }

    /**
     * Initializes decorated layout inflation needed ONLY in case {@link ResourceContextWrapper}
     * is used in conjunction with another {@link ContextWrapper} which might overwrite
     * the default logic used by this class.
     * Invocation of this method is not needed otherwise.
     * Should be called in {@link Activity} or {@link AppCompatActivity} onCreate
     * method before the call to super.onCreate
     * <p>
     * <pre>
     * {@code
     * public class MainActivity extends AppCompatActivity {
     *
     *      @Override
     *      protected void onCreate(Bundle savedInstanceState) {
     *              ResourceContextWrapper.initialize(this);
     *              super.onCreate(savedInstanceState);
     *              setContentView(R.layout.activity_main);
     *      }
     * }
     * </pre>
     *
     * @param activity
     */
    public static void initialize(Activity activity) {
        ViewDecorator decorator = new ViewDecorator() {
            @Override
            public void decorate(View parent, View view,
                                 Context context, AttributeSet attrs) {
                ResourceUtils.decorate(view, attrs);
            }
        };
        ViewDecoratorInstaller installer = new ViewDecoratorInstaller(decorator);
        if (activity instanceof AppCompatActivity) {
            installer.install((AppCompatActivity) activity);
        } else {
            installer.install(activity);
        }
    }
}
