package com.mathematicalfunk.daggertest;


import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    StringProvider provideStringProvider1(){
        return new StringProvider();
    }
}
