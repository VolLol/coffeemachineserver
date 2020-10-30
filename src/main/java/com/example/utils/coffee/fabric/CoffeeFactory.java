package com.example.utils.coffee.fabric;

import com.example.utils.coffee.fabric.types.*;

public class CoffeeFactory {

    public ICoffee getCoffeeType(CoffeeTypeEnum coffeeTypeEnum) throws IllegalArgumentException {
        ICoffee returnType = null;
        switch (coffeeTypeEnum) {
            case ESPRESSO:
                returnType = new EspressoICoffee();
                break;
            case AMERICANO:
                returnType = new AmericanoICoffee();
                break;
            case CAPPUCCINO:
                returnType = new СappuccinoCoffee();
                break;
            case LATTE:
                returnType = new LatteCoffee();
                break;
            default:
                throw new IllegalArgumentException("Wrong coffee type:" + coffeeTypeEnum);
        }
        return returnType;
    }

}
