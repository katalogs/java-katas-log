package com.kata.vavr.exercises;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Alexandre Grison (eqk83)
 */
@Data
@AllArgsConstructor
public class Pet {
    private final PetType type;
    private final String name;
    private final int age;
}
