package com.mathematicalfunk.daggertest;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP, application = DaggerApp.class)
public class MainActivityTest {

    @Test
    public void testProductionStringIsSet() throws Exception {
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        MainActivity testObject = controller.create().start().resume().get();

        TextView textView = (TextView) testObject.findViewById(R.id.tea_textview);

        assertEquals("productionString", textView.getText().toString());
    }


}