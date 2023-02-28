package com.kata.telldontask.domain.order;

import com.kata.telldontask.domain.order.status.OrderApproved;
import com.kata.telldontask.domain.order.status.OrderCreated;
import com.kata.telldontask.domain.order.status.OrderRejected;
import com.kata.telldontask.domain.order.status.OrderShipped;

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
}
