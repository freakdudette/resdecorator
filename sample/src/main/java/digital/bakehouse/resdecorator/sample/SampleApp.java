package digital.bakehouse.resdecorator.sample;

import android.app.Application;

import digital.bakehouse.resdecorator.ResourceContextWrapper;

public class SampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ResourceContextWrapper.initialize();
    }
}
