package com.example.controllers;

import com.example.DTO.AddResourceDTO;
import com.example.exceptions.MachineNeedCleaningException;
import com.example.requests.AddResourceRequest;
import com.example.DTO.CleaningDTO;
import com.example.DTO.CoffeeDTO;
import com.example.exceptions.NotEnoughResourceException;
import com.example.requests.CoffeeRequest;
import com.example.usecases.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConditionOfCoffeeMachineController {

    private final AddGrainsToTheCoffeeMachineUsecase addGrainsToTheCoffeeMachineUsecase;
    private final AddMilkToTheCoffeeMachineUsecase addMilkToTheCoffeeMachineUsecase;
    private final AddWaterToTheCoffeeMachineUsecase addWaterToTheCoffeeMachineUsecase;
    private final CleanCoffeeMachineUsecase cleanCoffeeMachineUsecase;
    private final MakeCoffeeUsecase makeCoffeeUsecase;


    public ConditionOfCoffeeMachineController(AddGrainsToTheCoffeeMachineUsecase addGrainsToTheCoffeeMachineUsecase, AddMilkToTheCoffeeMachineUsecase addMilkToTheCoffeeMachineUsecase, AddWaterToTheCoffeeMachineUsecase addWaterToTheCoffeeMachineUsecase, CleanCoffeeMachineUsecase cleanCoffeeMachineUsecase, MakeCoffeeUsecase makeCoffeeUsecase) {
        this.addGrainsToTheCoffeeMachineUsecase = addGrainsToTheCoffeeMachineUsecase;
        this.addMilkToTheCoffeeMachineUsecase = addMilkToTheCoffeeMachineUsecase;
        this.addWaterToTheCoffeeMachineUsecase = addWaterToTheCoffeeMachineUsecase;
        this.cleanCoffeeMachineUsecase = cleanCoffeeMachineUsecase;
        this.makeCoffeeUsecase = makeCoffeeUsecase;
    }


    @PutMapping("/coffeemachine/add")
    public ResponseEntity getAddresource(@RequestBody AddResourceRequest addResourceRequest) {

        if (addResourceRequest.getResourceType().toUpperCase().equals("GRAINS")) {
            AddResourceDTO resourceDTO = addGrainsToTheCoffeeMachineUsecase.execute();
            return ResponseEntity.status(HttpStatus.OK).body(resourceDTO);
        }

        if (addResourceRequest.getResourceType().toUpperCase().equals("MILK")) {
            AddResourceDTO resourceDTO = addMilkToTheCoffeeMachineUsecase.execute();
            return ResponseEntity.status(HttpStatus.OK).body(resourceDTO);
        }
        if (addResourceRequest.getResourceType().toUpperCase().equals("WATER")) {
            AddResourceDTO resourceDTO = addWaterToTheCoffeeMachineUsecase.execute();
            return ResponseEntity.status(HttpStatus.OK).body(resourceDTO);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Correct resource");
    }

    @GetMapping("/coffeemachine/clean")
    public ResponseEntity cleanCoffeeMachine() {
        CleaningDTO cleaningDTO = cleanCoffeeMachineUsecase.execute();
        return ResponseEntity.status(HttpStatus.OK).body(cleaningDTO);
    }

    @PutMapping("/coffeemachine/makeCoffee")
    public ResponseEntity getCoffee(@RequestBody CoffeeRequest coffeeRequest) {
        CoffeeDTO coffeeDTO = null;
        try {
            coffeeDTO = makeCoffeeUsecase.execute(coffeeRequest.getType());
        } catch (NotEnoughResourceException e) {
            coffeeDTO = CoffeeDTO.builder()
                    .message(e.getMessage() + " " + e.getResourceType())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(coffeeDTO);
        } catch (IllegalArgumentException e) {
            coffeeDTO = CoffeeDTO.builder()
                    .message("Incorrect drink name. Try another")
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(coffeeDTO);
        } catch (MachineNeedCleaningException e) {
            coffeeDTO = CoffeeDTO.builder()
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(coffeeDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(coffeeDTO);
    }
}
