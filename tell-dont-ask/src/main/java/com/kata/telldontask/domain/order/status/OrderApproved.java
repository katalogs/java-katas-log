package com.kata.telldontask.domain.order.status;

import com.kata.telldontask.domain.order.OrderStatus;
import com.kata.telldontask.domain.order.exception.ApprovedOrderCannotBeRejected;

public class OrderApproved extends OrderStatus {

  // TODO: Approve already approved ?
  @Override
  public OrderStatus approve() {
    return new OrderApproved();
  }

  @Override
  public OrderStatus reject() {
    throw new ApprovedOrderCannotBeRejected();
  }

  @Override
  public OrderStatus ship() {
    return new OrderShipped();
  }
}
