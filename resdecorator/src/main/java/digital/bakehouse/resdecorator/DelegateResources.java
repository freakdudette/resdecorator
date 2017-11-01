package digital.bakehouse.resdecorator;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

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

    @NonNull
    @Override
    public CharSequence getText(@StringRes int id) throws NotFoundException {
        CharSequence result = resourceDecorator.getString(delegate, id);
        if (result != null) {
            return result;
        }
        return super.getText(id);
    }

    @NonNull
    @Override
    public String getString(@StringRes int id) throws NotFoundException {
        String result = resourceDecorator.getString(delegate, id);
        if (result != null) {
            return result;
        }
        return super.getString(id);
    }

    @NonNull
    @Override
    public String getString(@StringRes int id, Object... formatArgs) throws NotFoundException {
        String result = resourceDecorator.getString(delegate,
                id, formatArgs);
        if (result != null) {
            return result;
        }
        return super.getString(id, formatArgs);
    }


}
