package digital.bakehouse.resdecorator.sample;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import digital.bakehouse.resdecorator.ResourceContextWrapper;
import digital.bakehouse.resdecorator.ResourceDecorator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ResourceContextWrapper.wrap(newBase, getResourceDecorator()));
    }

    private ResourceDecorator getResourceDecorator() {
        //This can be anything actually. For this sample let's just
        //imagine that we have to make every text retrieved from strings.xml
        // uppercase with underscores instead of spaces
        return new ResourceDecorator() {
            @Override
            public String getString(Resources resources, int id, Object... params) {
                return resources.getString(id).toUpperCase().replaceAll(" ", "_");
            }
        };
    }
}
