package com.kata.mars;

public class RoverBuilder {

  private WorldMap worldMap;
  private String position;

  public static RoverBuilder aRover() {
    return new RoverBuilder();
  }

  public RoverBuilder withWorldMap(WorldMap worldMap) {
    this.worldMap = worldMap;
    return this;
  }

  public RoverBuilder landedAt(String position) {
    this.position = position;
    return this;
  }

  public Rover build() {
    return new Rover(position);
  }
}
