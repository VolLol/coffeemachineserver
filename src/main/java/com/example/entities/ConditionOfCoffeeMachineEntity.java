package com.example.entities;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@DynamicInsert
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "condition_of_coffee_machine")
public class ConditionOfCoffeeMachineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "amount_of_water")
    @Getter
    @Setter
    private Double amountOfWater;

    @Column(name = "amount_of_milk")
    @Getter
    @Setter
    private Double amountOfMilk;

    @Column(name = "count_of_grains")
    @Getter
    @Setter
    private Double countOfGrains;

    @Column(name = "count_of_cups")
    @ColumnDefault(value = "0")
    @Getter
    @Setter
    private Integer countOfCups;

    @Column(name = "is_need_cleaning", columnDefinition = "boolean default false")
    @Getter
    @Setter
    private Boolean isNeedCleaning;

    @Column(name = "last_modified")
    @Getter
    @Setter
    private LocalDateTime lastModified;
}
