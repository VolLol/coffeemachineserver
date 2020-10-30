package com.example.usecases;

import com.example.DTO.CleaningDTO;
import com.example.entities.ConditionOfCoffeeMachineEntity;
import com.example.repositories.ConditionOfCoffeeMachineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CleanCoffeeMachineUsecase {

    private final ConditionOfCoffeeMachineRepository coffeeMachineRepository;

    public CleanCoffeeMachineUsecase(ConditionOfCoffeeMachineRepository coffeeMachineRepository) {
        this.coffeeMachineRepository = coffeeMachineRepository;
    }

    public CleaningDTO execute() {
        ConditionOfCoffeeMachineEntity entity = coffeeMachineRepository.findFirstByOrderByIdDesc();
        entity.setLastModified(LocalDateTime.now());
        entity.setIsNeedCleaning(false);
        entity.setId(null);
        coffeeMachineRepository.save(entity);
        return new CleaningDTO("Machine is clean");
    }
}
