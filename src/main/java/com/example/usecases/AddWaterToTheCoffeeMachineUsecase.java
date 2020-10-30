package com.example.usecases;

import com.example.DTO.AddResourceDTO;
import com.example.entities.ConditionOfCoffeeMachineEntity;
import com.example.repositories.ConditionOfCoffeeMachineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddWaterToTheCoffeeMachineUsecase {

    private final ConditionOfCoffeeMachineRepository coffeeMachineRepository;

    public AddWaterToTheCoffeeMachineUsecase(ConditionOfCoffeeMachineRepository coffeeMachineRepository) {
        this.coffeeMachineRepository = coffeeMachineRepository;
    }

    public AddResourceDTO execute() {
        ConditionOfCoffeeMachineEntity entity = coffeeMachineRepository.findFirstByOrderByIdDesc();
        ConditionOfCoffeeMachineEntity newConditionOfCoffeeMachineEntity;

        if (entity == null) {
            newConditionOfCoffeeMachineEntity = ConditionOfCoffeeMachineEntity.builder()
                    .isNeedCleaning(false)
                    .amountOfWater(1000.0)
                    .lastModified(LocalDateTime.now())
                    .build();
        } else {
            newConditionOfCoffeeMachineEntity = ConditionOfCoffeeMachineEntity.builder()
                    .isNeedCleaning(false)
                    .lastModified(LocalDateTime.now())
                    .amountOfWater(1000.0)
                    .amountOfMilk(entity.getAmountOfMilk())
                    .countOfGrains(entity.getCountOfGrains())
                    .countOfCups(entity.getCountOfCups())
                    .id(null)
                    .build();
        }
        coffeeMachineRepository.save(newConditionOfCoffeeMachineEntity);
        return AddResourceDTO.builder()
                .message("You add water to the coffee machine")
                .build();
    }
}
