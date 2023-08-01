package com.kata.mars;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoverAcceptanceTests {

    @Test
    void test(){
        Rover rover = new Rover("15:20:N");

        String position = rover.execute("LFFFRFLLFFRRFRFFFL");

        assertThat(position).isEqualTo("15:20:N");

    }


}