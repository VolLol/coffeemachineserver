package com.example.utils.coffee.fabric.types;

import com.example.utils.coffee.fabric.CoffeeTypeEnum;

public class LatteCoffee implements ICoffee {
    @Override
    public Double reduceTheAmountOfGrains(Double initialAmountOfGrain) {
        Double finalAmountOfGrains = initialAmountOfGrain - 10.0;
        return finalAmountOfGrains;
    }

    @Override
    public Double reduceTheAmountOfWater(Double initialAmountOfWater) {
        Double finalAmountOfWater = initialAmountOfWater - 100.0;
        return finalAmountOfWater;
    }

    @Override
    public Double reduceTheAmountOfMilk(Double initialAmountOfMilk) {
        Double finalAmountOfMilk = initialAmountOfMilk - 300.0;
        return finalAmountOfMilk;
    }

    @Override
    public String getType() {
        return CoffeeTypeEnum.LATTE.toString();
    }
}
