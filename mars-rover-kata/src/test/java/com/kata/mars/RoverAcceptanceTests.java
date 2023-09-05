package com.kata.mars;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoverAcceptanceTests {

  @Test
  void command_first_short_drive_after_landing_should_return_to_initial_position() {
    Rover rover = RoverBuilder.aRover()
        .withWorldMap(new WorldMap(new Position(-180, -180), new Position(180, 180)))
        .landedAt("15:20:N")
        .build();

    String position = rover.execute("LFFFRFLLFFRRFRFFFL");

    assertThat(position).isEqualTo("15:20:N");
  }

  @Test
  void rover_received_an_invalid_command_and_stop_and_new_command_should_work() {
    String position = "1:2:N";
    Rover rover = RoverBuilder.aRover()
        .landedAt(position)
        .build();

    String newPosition = rover.execute("FLXF");
    assertThat(newPosition).isEqualTo("E:1:3:W");

    newPosition = rover.execute("F");
    assertThat(newPosition).isEqualTo("0:3:W");
  }

  @Test
  void rover_should_move_around_the_world() {
    String position = "0:0:N";
    Rover rover = RoverBuilder.aRover()
        .landedAt(position)
        .build();

    String newPosition = rover.execute("FFFFF");
    assertThat(newPosition).isEqualTo("0:0:N");

    newPosition = rover.execute("LFFFFF");
    assertThat(newPosition).isEqualTo("0:0:W");

    newPosition = rover.execute("LFFFFF");
    assertThat(newPosition).isEqualTo("0:0:S");

    newPosition = rover.execute("LFFFFF");
    assertThat(newPosition).isEqualTo("0:0:E");
  }

  @Test
  void rover_should_detect_obstacle_on_his_way() {
    WorldMap wolrdMap = new WorldMap(new Position(-180, -180), new Position(180, 180), List.of(new Obstacle(0, 2)));

    String position = "0:0:N";
    Rover rover = RoverBuilder.aRover()
        .withWorldMap(wolrdMap)
        .landedAt(position)
        .build();

    String newPosition = rover.execute("FFFFF");

    assertThat(newPosition).isEqualTo("O:0:1:N");
  }

}