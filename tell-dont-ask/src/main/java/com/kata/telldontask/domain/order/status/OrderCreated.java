package com.kata.telldontask.domain.order.status;

import com.kata.telldontask.domain.order.IOrderStatus;
import com.kata.telldontask.domain.order.OrderStatus;
import com.kata.telldontask.domain.order.exception.OrderCannotBeShipped;

public class OrderCreated extends OrderStatus {

  @Override
  public IOrderStatus approve() {
    return new OrderApproved();
  }

  @Override
  public IOrderStatus reject() {
    return new OrderRejected();
  }

  @Override
  public IOrderStatus ship() {
    throw new OrderCannotBeShipped();
  }
}
