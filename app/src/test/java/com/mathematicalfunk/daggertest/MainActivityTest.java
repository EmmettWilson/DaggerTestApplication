package com.mathematicalfunk.daggertest;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP, application = TestApp.class)
public class MainActivityTest {

    @Rule public DaggerMockRule<ApplicationComponent> daggerMockRule = new DaggerMockRule<>(ApplicationComponent.class, new ApplicationModule(((TestApp) RuntimeEnvironment.application)))
            .set(new DaggerMockRule.ComponentSetter<ApplicationComponent>() {
                @Override
                public void setComponent(ApplicationComponent applicationComponent) {
                    ((TestApp) RuntimeEnvironment.application).setComponent(applicationComponent);
                }
            });

    @Mock private CoffeeMaker coffeeMaker;

    @InjectMocks MainActivity testObject;

    private ActivityController<MainActivity> controller;

    @Before
    public void setUp() throws Exception {
        when(coffeeMaker.makeCoffee()).thenReturn("mockCoffee");
        controller = Robolectric.buildActivity(MainActivity.class);
        testObject = controller.create().start().resume().get();
    }

    @Test
    public void productionTeaIsSet() throws Exception {

        TextView textView = (TextView) testObject.findViewById(R.id.tea_textview);

        assertTrue(textView.getText().toString().contains("productionTea"));
    }

    @Test
    public void mockCoffeeIsUsed() throws Exception {

        TextView textView = (TextView) testObject.findViewById(R.id.coffee_textview);

        assertEquals("mockCoffee", textView.getText().toString());
    }
}