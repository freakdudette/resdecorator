package digital.bakehouse.resdecorator;

import android.content.res.Resources;

public interface ResourceDecorator {
    String getString(Resources resources, int id, Object... params);
}
