package digital.bakehouse.resdecorator;

import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

class ResourceUtils {
    private static final String SCHEMA = "http://schemas.android.com/apk/res/android";
    private static final String TAG_TEXT = "text";
    private static final String TAG_HINT = "hint";
    private static final int DEFAULT_RES_ID = -1;

    static void decorate(View view, AttributeSet attrs) {
        if (view instanceof TextView) {
            setText((TextView) view, attrs);
            setHint((TextView) view, attrs);
        }
    }

    private static void setText(TextView textView, AttributeSet attrs) {
        int textResId = attrs.getAttributeResourceValue(
                SCHEMA, TAG_TEXT, DEFAULT_RES_ID);
        if (textResId != DEFAULT_RES_ID) {
            textView.setText(textResId);
        }
    }

    private static void setHint(TextView textView, AttributeSet attrs) {
        int textResId = attrs.getAttributeResourceValue(
                SCHEMA, TAG_HINT, DEFAULT_RES_ID);
        if (textResId != DEFAULT_RES_ID) {
            textView.setHint(textResId);
        }
    }
}
