package com.mathematicalfunk.daggertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject TeaPot teaPot; //Application scoped, but not singleton new one every time
    private ActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.tea_textview);
        textView.setText(teaPot.pourTea());
    }

    @Override
    public ActivityComponent onRetainCustomNonConfigurationInstance() {
        return component;
    }

    @Override
    public ActivityComponent getLastCustomNonConfigurationInstance() {
        return (ActivityComponent)super.getLastCustomNonConfigurationInstance();
    }

    private void inject() {
        component = getLastCustomNonConfigurationInstance();
        if (component == null) {
            component =  ((DaggerApp) getApplication()).getApplicationComponent().plus(new ActivityModule());
        }
        component.inject(this);
    }
}
