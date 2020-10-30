package com.example.utils.coffee.fabric.types;

public interface ICoffee {

    Double reduceTheAmountOfGrains(Double initialAmountOfGrain);

    Double reduceTheAmountOfWater(Double initialAmountOfWater);

    Double reduceTheAmountOfMilk(Double initialAmountOfMilk);

    String getType();
}
