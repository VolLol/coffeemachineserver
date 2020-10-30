package com.example.repositories;

import com.example.entities.ConditionOfCoffeeMachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ConditionOfCoffeeMachineRepository extends JpaRepository<ConditionOfCoffeeMachineEntity, Long> {


    ConditionOfCoffeeMachineEntity findFirstByOrderByIdDesc();
}
