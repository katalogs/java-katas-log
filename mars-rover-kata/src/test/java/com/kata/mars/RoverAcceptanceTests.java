package com.kata.mars;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoverAcceptanceTests {

    @Test
    void command_first_short_drive_after_landing_should_return_to_initial_position(){
        Rover rover = new Rover("15:20:N");

        String position = rover.execute("LFFFRFLLFFRRFRFFFL");

        assertThat(position).isEqualTo("15:20:N");

    }

    @Test
    void rover_received_an_invalid_command_and_stop_and_new_command_should_work() {
        String position = "1:2:N";
        Rover rover = new Rover(position);
        rover.execute("FLXF");
        String newPosition = rover.execute("F");
        assertThat(newPosition).isEqualTo("0:3:W");
    }


    @Test
    void rover_should_move_arround_the_world(){
        String position = "0:0:N";
        Rover rover = new Rover(position);
        String newPosition = rover.execute("FFFFF");
        assertThat(newPosition).isEqualTo("0:0:N");
        newPosition = rover.execute("LFFFFF");
        assertThat(newPosition).isEqualTo("0:0:W");
        newPosition = rover.execute("LFFFFF");
        assertThat(newPosition).isEqualTo("0:0:S");
        newPosition = rover.execute("LFFFFF");
        assertThat(newPosition).isEqualTo("0:0:E");
    }


}