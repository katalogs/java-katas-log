package com.kata.mars;

public class Sequence {

  private String commands;
  private boolean error;

  public Sequence(String command) {
    commands = command;
  }

  public boolean hasNext() {
    return !commands.isEmpty();
  }

  public String next() {
    String substring = commands.substring(0, 1);
    this.commands =  commands.substring(1);
    return substring;
  }

  public void stop() {
    this.error = true;
    commands = "";
  }

  public boolean hasError() {
    return error;
  }
}
