package com.mathematicalfunk.daggertest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ViewModule.class})
public interface ApplicationComponent {
    ActivityComponent plus(ActivityModule module);
}
