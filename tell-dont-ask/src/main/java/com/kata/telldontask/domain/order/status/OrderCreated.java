package com.kata.telldontask.domain.order.status;

import com.kata.telldontask.domain.order.OrderStatus;
import com.kata.telldontask.domain.order.OrderStatusEnum;
import com.kata.telldontask.domain.order.exception.OrderCannotBeShipped;

public class OrderCreated extends OrderStatus {

  @Override
  public OrderStatusEnum getStatus() {
    return OrderStatusEnum.CREATED;
  }

  @Override
  public OrderStatus approve() {
    return new OrderApproved();
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
