package com.kata.telldontask.domain.order.status;

import com.kata.telldontask.domain.order.OrderStatus;
import com.kata.telldontask.domain.order.exception.OrderCannotBeShippedTwice;
import com.kata.telldontask.domain.order.exception.ShippedOrdersCannotBeChanged;

public class OrderShipped extends OrderStatus {

  @Override
  public OrderStatus approve() {
    throw new ShippedOrdersCannotBeChanged();
  }

  @Override
  public OrderStatus reject() {
    throw new ShippedOrdersCannotBeChanged();
  }

  @Override
  public OrderStatus ship() {
    throw new OrderCannotBeShippedTwice();
  }
}
