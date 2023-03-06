package com.kata.telldontask.domain.order.status;

import com.kata.telldontask.domain.order.IOrderStatus;
import com.kata.telldontask.domain.order.OrderStatus;
import com.kata.telldontask.domain.order.exception.OrderCannotBeShipped;
import com.kata.telldontask.domain.order.exception.RejectedOrderCannotBeApproved;

public class OrderRejected extends OrderStatus {

  @Override
  public IOrderStatus approve() {
    throw new RejectedOrderCannotBeApproved();
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
