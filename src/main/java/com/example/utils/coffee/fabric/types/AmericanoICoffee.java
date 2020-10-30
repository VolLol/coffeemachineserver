package com.example.utils.coffee.fabric.types;


import com.example.utils.coffee.fabric.CoffeeTypeEnum;

public class AmericanoICoffee implements ICoffee {
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
        return initialAmountOfMilk;
    }

    @Override
    public String getType() {
        return CoffeeTypeEnum.AMERICANO.toString();
    }
}
