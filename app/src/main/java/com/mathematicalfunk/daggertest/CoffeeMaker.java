package com.mathematicalfunk.daggertest;

import java.util.Random;

import javax.inject.Inject;

@PerActivity
public class CoffeeMaker {

    private String coffee = "productionCoffee " + Math.abs(new Random().nextInt(10000));

    @Inject
    public CoffeeMaker(){}

    public String makeCoffee(){
        return coffee;
    }
}
