package com.mathematicalfunk.daggertest;


import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    @Provides
    @PerActivity
    public CoffeeMaker provideCoffeeMaker(){
        return new CoffeeMaker();
    }
}
