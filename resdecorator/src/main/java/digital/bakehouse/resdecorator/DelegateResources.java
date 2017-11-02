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
        CharSequence result = resourceDecorator.getString(delegate, id);
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
