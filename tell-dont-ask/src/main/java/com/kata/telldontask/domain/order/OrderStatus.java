package com.kata.telldontask.domain.order;

import java.util.Objects;

public abstract class OrderStatus {

  public abstract OrderStatus approve();

  public abstract OrderStatus reject();

  public abstract OrderStatus ship();

  @Override
  public int hashCode() {
    return Objects.hash(getClass());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    return o != null && getClass() == o.getClass();
  }
}
