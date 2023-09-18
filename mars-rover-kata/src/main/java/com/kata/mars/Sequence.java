package com.kata.mars;

public class Sequence {

  private String commands;
  private RoverError error;

  public Sequence(String command) {
    commands = command;
  }

  public boolean hasNext() {
    return !commands.isEmpty();
  }

  public String next() {
    String substring = commands.substring(0, 1);
    this.commands = commands.substring(1);
    return substring;
  }

  public void stop() {
    stop(new CommandError());
  }

  public void stop(RoverError roverError) {
    this.error = roverError;
    commands = "";
  }

  public boolean hasError() {
    return this.error != null;
  }

  public RoverError getError() {
    return error;
  }
}
