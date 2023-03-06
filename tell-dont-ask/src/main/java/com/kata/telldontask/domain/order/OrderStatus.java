package com.kata.telldontask.domain.order;

import com.kata.telldontask.domain.order.status.OrderApproved;
import com.kata.telldontask.domain.order.status.OrderCreated;
import com.kata.telldontask.domain.order.status.OrderRejected;
import com.kata.telldontask.domain.order.status.OrderShipped;
import java.util.Objects;

public abstract class OrderStatus {

  public abstract OrderStatusEnum getStatus();

  public static OrderStatus create(OrderStatusEnum orderStatus) {
    switch (orderStatus) {
      case APPROVED:
        return new OrderApproved();
      case REJECTED:
        return new OrderRejected();
      case SHIPPED:
        return new OrderShipped();
      default:
        return new OrderCreated();
    }
  }

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
