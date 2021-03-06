package com.mathematicalfunk.daggertest;


import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private DaggerApp daggerApp;

    public ApplicationModule(DaggerApp daggerApp) {
        this.daggerApp = daggerApp;
    }

    @Provides
    public DaggerApp provideApplication() {
        return daggerApp;
    }

    @Provides
    @Named("application_context")
    public Context provideApplicationContext() {
        return daggerApp.getApplicationContext();
    }
}
