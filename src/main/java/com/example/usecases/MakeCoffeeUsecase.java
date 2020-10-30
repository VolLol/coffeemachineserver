package com.example.usecases;

import com.example.DTO.CoffeeDTO;
import com.example.entities.CoffeeEntity;
import com.example.entities.ConditionOfCoffeeMachineEntity;
import com.example.exceptions.MachineNeedCleaningException;
import com.example.exceptions.NotEnoughResourceException;
import com.example.repositories.CoffeeRepository;
import com.example.utils.coffee.fabric.types.ICoffee;
import com.example.utils.coffee.fabric.CoffeeFactory;
import com.example.utils.coffee.fabric.CoffeeTypeEnum;
import com.example.repositories.ConditionOfCoffeeMachineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MakeCoffeeUsecase {

    private final ConditionOfCoffeeMachineRepository coffeeMachineRepository;
    private final CoffeeRepository coffeeRepository;

    public MakeCoffeeUsecase(ConditionOfCoffeeMachineRepository coffeeMachineRepository, CoffeeRepository coffeeRepository) {
        this.coffeeMachineRepository = coffeeMachineRepository;
        this.coffeeRepository = coffeeRepository;
    }

    public CoffeeDTO execute(String coffeeType) throws NotEnoughResourceException, IllegalArgumentException, MachineNeedCleaningException {
        CoffeeFactory coffeeFactory = new CoffeeFactory();
        ICoffee cupOfCoffee = coffeeFactory.getCoffeeType(CoffeeTypeEnum.valueOf(coffeeType.toUpperCase()));


        ConditionOfCoffeeMachineEntity machineEntity = coffeeMachineRepository.findFirstByOrderByIdDesc();
        checkingResourceLevel(machineEntity, cupOfCoffee);
        checkingTheCleanlinessOfTheMachine(machineEntity);

        ConditionOfCoffeeMachineEntity newConditionOfCoffeeMachineEntity = ConditionOfCoffeeMachineEntity.builder()
                .lastModified(LocalDateTime.now())
                .amountOfWater(cupOfCoffee.reduceTheAmountOfWater(machineEntity.getAmountOfWater()))
                .amountOfMilk(cupOfCoffee.reduceTheAmountOfMilk(machineEntity.getAmountOfMilk()))
                .isNeedCleaning(cleannessCheck(machineEntity.getCountOfCups()))
                .countOfGrains(cupOfCoffee.reduceTheAmountOfGrains(machineEntity.getCountOfGrains()))
                .countOfCups(machineEntity.getCountOfCups() + 1)
                .build();

        coffeeMachineRepository.save(newConditionOfCoffeeMachineEntity);

        CoffeeEntity coffeeEntity = CoffeeEntity.builder()
                .type(cupOfCoffee.getType())
                .cookedTime(LocalDateTime.now())
                .build();
        coffeeRepository.save(coffeeEntity);

        return new CoffeeDTO("Take you " + cupOfCoffee.getType().toLowerCase());


    }

    //Метод для проверки достаточного колличества ресурсов в кофеварке
    private void checkingResourceLevel(ConditionOfCoffeeMachineEntity conditionOfCoffeeMachineEntity, ICoffee coffee) throws NotEnoughResourceException {

        checkingNotNullState(conditionOfCoffeeMachineEntity);

        if (coffee.reduceTheAmountOfWater(conditionOfCoffeeMachineEntity.getAmountOfWater()) < 0) {
            throw new NotEnoughResourceException("Not enough resource:", "water");
        }

        if (coffee.reduceTheAmountOfMilk(conditionOfCoffeeMachineEntity.getAmountOfMilk()) < 0) {
            throw new NotEnoughResourceException("Not enough resource:", "milk");
        }

        if (coffee.reduceTheAmountOfGrains(conditionOfCoffeeMachineEntity.getCountOfGrains()) < 0) {
            throw new NotEnoughResourceException("Not enough resource:", "grains");
        }

    }

    private void checkingNotNullState(ConditionOfCoffeeMachineEntity conditionOfCoffeeMachineEntity) throws NotEnoughResourceException {
        if (conditionOfCoffeeMachineEntity == null) {
            throw new NotEnoughResourceException("Not enough resourse:", "all");
        }
        if (conditionOfCoffeeMachineEntity.getAmountOfWater() == null) {
            throw new NotEnoughResourceException("Not enough resource:", "water");
        }

        if (conditionOfCoffeeMachineEntity.getAmountOfMilk() == null) {
            throw new NotEnoughResourceException("Not enough resource:", "milk");
        }

        if (conditionOfCoffeeMachineEntity.getCountOfGrains() == null) {
            throw new NotEnoughResourceException("Not enough resource:", "grains");
        }
    }

    private void checkingTheCleanlinessOfTheMachine(ConditionOfCoffeeMachineEntity conditionOfCoffeeMachineEntity) throws MachineNeedCleaningException {
        if (conditionOfCoffeeMachineEntity.getIsNeedCleaning()) {
            throw new MachineNeedCleaningException("Сoffee machine needs cleaning");
        }

    }

    private boolean cleannessCheck(Integer countOfCups) {
        if (countOfCups == 0) {
            return false;
        }
        if (countOfCups % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
