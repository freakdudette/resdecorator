package digital.bakehouse.resdecorator;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.view.LayoutInflater;

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

    private Resources resources;
    private ResourceDecorator resourceDecorator;
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
}
