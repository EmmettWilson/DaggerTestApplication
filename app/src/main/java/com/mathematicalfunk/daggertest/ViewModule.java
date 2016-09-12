package com.mathematicalfunk.daggertest;


import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    TeaPot provideStringProvider(){
        return new TeaPot();
    }
}
