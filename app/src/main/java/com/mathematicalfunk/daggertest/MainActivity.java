package com.mathematicalfunk.daggertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject StringProvider stringProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((DaggerApp) getApplication()).getApplicationComponent().inject(this);

        TextView textView = (TextView) findViewById(R.id.hello_textview);
        textView.setText(stringProvider.getString());
    }
}
