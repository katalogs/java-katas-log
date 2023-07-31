package com.kata.mars;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RoverTests {

    @ParameterizedTest
    @CsvSource({
            "15:20:N,L,15:20:W",
            "15:20:N,LL,15:20:S",
            "15:20:N,LLL,15:20:E",
            "15:20:N,LLLL,15:20:N",
    })
    void should_turn_left(String initialPosition, String command, String finalPosition){
        Rover rover = new Rover(initialPosition);
        String newPosition = rover.execute(command);
        assertThat(newPosition).isEqualTo(finalPosition);
    }

    @ParameterizedTest
    @CsvSource({
            "1:2:N,R,1:2:E",
            "1:2:N,RR,1:2:S",
            "1:2:N,RRR,1:2:W",
            "1:2:N,RRRR,1:2:N",
    })
    void should_turn_right(String initialPosition, String command, String finalPosition){
        Rover rover = new Rover(initialPosition);
        String newPosition = rover.execute(command);
        assertThat(newPosition).isEqualTo(finalPosition);
    }

    @Test
    void should_turn_left_and_right(){
        String position = "1:5:W";
        Rover rover = new Rover(position);
        String newPosition = rover.execute("LR");
        assertThat(newPosition).isEqualTo(position);
    }
}