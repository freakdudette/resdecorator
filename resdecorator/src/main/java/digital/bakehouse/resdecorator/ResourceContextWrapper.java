package digital.bakehouse.resdecorator;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.view.LayoutInflater;

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

    public static ResourceContextWrapper wrap(Context context, ResourceDecorator resDecorator) {
        return new ResourceContextWrapper(context, resDecorator);
    }
}
