package com.mathematicalfunk.daggertest;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class MainActivityTest extends BaseInjectedRobolectricTest {

    @Mock private CoffeeMaker coffeeMaker;

    private MainActivity testObject;
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