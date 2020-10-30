package com.example.usecases;

import com.example.DTO.AddResourceDTO;
import com.example.entities.ConditionOfCoffeeMachineEntity;
import com.example.repositories.ConditionOfCoffeeMachineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddGrainsToTheCoffeeMachineUsecase {

    private final ConditionOfCoffeeMachineRepository coffeeMachineRepository;

    public AddGrainsToTheCoffeeMachineUsecase(ConditionOfCoffeeMachineRepository coffeeMachineRepository) {
        this.coffeeMachineRepository = coffeeMachineRepository;
    }

    public AddResourceDTO execute() {

        ConditionOfCoffeeMachineEntity entity = coffeeMachineRepository.findFirstByOrderByIdDesc();
        ConditionOfCoffeeMachineEntity newConditionOfCoffeeMachineEntity;
        if (entity == null) {
            newConditionOfCoffeeMachineEntity = ConditionOfCoffeeMachineEntity.builder()
                    .countOfGrains(300.0)
                    .lastModified(LocalDateTime.now())
                    .isNeedCleaning(false)
                    .build();
        } else {
            newConditionOfCoffeeMachineEntity = ConditionOfCoffeeMachineEntity.builder()
                    .lastModified(LocalDateTime.now())
                    .countOfGrains(300.0)
                    .isNeedCleaning(false)
                    .amountOfMilk(entity.getAmountOfMilk())
                    .amountOfWater(entity.getAmountOfWater())
                    .countOfCups(entity.getCountOfCups())
                    .build();
        }
        coffeeMachineRepository.save(newConditionOfCoffeeMachineEntity);
        return AddResourceDTO.builder()
                .message("You add grains to the coffee machine")
                .build();

    }
}
