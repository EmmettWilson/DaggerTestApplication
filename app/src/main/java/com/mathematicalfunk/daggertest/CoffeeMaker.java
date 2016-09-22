package com.mathematicalfunk.daggertest;

import java.util.Random;

public class CoffeeMaker {

    private String coffee = "productionCoffee " + Math.abs(new Random().nextInt(10000));

    public CoffeeMaker(){}

    public String makeCoffee(){
        return coffee;
    }
}
