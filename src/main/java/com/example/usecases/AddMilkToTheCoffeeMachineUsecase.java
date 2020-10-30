package com.example.usecases;

import com.example.DTO.AddResourceDTO;
import com.example.entities.ConditionOfCoffeeMachineEntity;
import com.example.repositories.ConditionOfCoffeeMachineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddMilkToTheCoffeeMachineUsecase {

    private final ConditionOfCoffeeMachineRepository coffeeMachineRepository;

    public AddMilkToTheCoffeeMachineUsecase(ConditionOfCoffeeMachineRepository coffeeMachineRepository) {
        this.coffeeMachineRepository = coffeeMachineRepository;
    }

    public AddResourceDTO execute() {
        ConditionOfCoffeeMachineEntity entity = coffeeMachineRepository.findFirstByOrderByIdDesc();
        ConditionOfCoffeeMachineEntity newConditionOfCoffeeMachineEntity;
        if (entity == null) {
            newConditionOfCoffeeMachineEntity = ConditionOfCoffeeMachineEntity.builder()
                    .lastModified(LocalDateTime.now())
                    .amountOfMilk(1000.0)
                    .isNeedCleaning(false)
                    .build();
        } else {
            newConditionOfCoffeeMachineEntity = ConditionOfCoffeeMachineEntity.builder()
                    .lastModified(LocalDateTime.now())
                    .amountOfMilk(1000.0)
                    .isNeedCleaning(false)
                    .amountOfWater(entity.getAmountOfWater())
                    .countOfGrains(entity.getCountOfGrains())
                    .countOfCups(entity.getCountOfCups())
                    .build();
        }
        coffeeMachineRepository.save(newConditionOfCoffeeMachineEntity);
        return AddResourceDTO.builder()
                .message("You add milk to the coffee machine")
                .build();
    }

}
