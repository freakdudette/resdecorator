package digital.bakehouse.resdecorator.sample;

import android.app.Application;

import digital.bakehouse.resdecorator.ResourceContextWrapper;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class SampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Default usage
        ResourceContextWrapper.initialize();

        //Using with calligraphy
//        initializeWithCalligraphy();
    }

    private void initializeWithCalligraphy() {
        ResourceContextWrapper.initialize(
                ViewPump.builder()
                        .addInterceptor(new CalligraphyInterceptor(
                                new CalligraphyConfig.Builder()
                                        .setDefaultFontPath("fonts/ArialMT.ttf")
                                        .setFontAttrId(R.attr.fontPath)
                                        .build())
                        ));
    }
}
