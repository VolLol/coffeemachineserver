package com.example.repositories;

import com.example.entities.CoffeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<CoffeeEntity, Long> {
}
