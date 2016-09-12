package com.mathematicalfunk.daggertest;

import java.util.Random;

public class TeaPot {

    private String tea = "productionTea " + Math.abs(new Random().nextInt(10000));

    public String pourTea(){
        return tea;
    }
}
