package com.kata.telldontask.domain.order.status;

import com.kata.telldontask.domain.order.OrderStatus;
import com.kata.telldontask.domain.order.exception.OrderCannotBeShipped;
import com.kata.telldontask.domain.order.exception.RejectedOrderCannotBeApproved;

public class OrderRejected extends OrderStatus {

  @Override
  public OrderStatus approve() {
    throw new RejectedOrderCannotBeApproved();
  }

  @Override
  public OrderStatus reject() {
    return new OrderRejected();
  }

  @Override
  public OrderStatus ship() {
    throw new OrderCannotBeShipped();
  }
}
