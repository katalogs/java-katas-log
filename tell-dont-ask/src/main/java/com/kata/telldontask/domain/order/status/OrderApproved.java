package com.kata.telldontask.domain.order.status;

import com.kata.telldontask.domain.order.IOrderStatus;
import com.kata.telldontask.domain.order.OrderStatus;
import com.kata.telldontask.domain.order.exception.ApprovedOrderCannotBeRejected;

public class OrderApproved extends OrderStatus {

  // TODO: Approve already approved ?
  @Override
  public IOrderStatus approve() {
    return new OrderApproved();
  }

  @Override
  public IOrderStatus reject() {
    throw new ApprovedOrderCannotBeRejected();
  }

  @Override
  public IOrderStatus ship() {
    return new OrderShipped();
  }
}
