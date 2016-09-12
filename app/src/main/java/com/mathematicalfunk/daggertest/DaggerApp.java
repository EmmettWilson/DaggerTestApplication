package com.mathematicalfunk.daggertest;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

public class DaggerApp extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .viewModule(new ViewModule())
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @VisibleForTesting
    public void setComponent(ApplicationComponent component) {
        this.applicationComponent = component;
    }
}