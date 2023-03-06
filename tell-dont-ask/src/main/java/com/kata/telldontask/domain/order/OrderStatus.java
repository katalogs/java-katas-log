package com.kata.telldontask.domain.order;

import java.util.Objects;

public abstract class OrderStatus implements IOrderStatus {

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
