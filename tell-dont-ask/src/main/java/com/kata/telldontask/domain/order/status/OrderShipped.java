package com.kata.telldontask.domain.order.status;

import com.kata.telldontask.domain.order.IOrderStatus;
import com.kata.telldontask.domain.order.OrderStatus;
import com.kata.telldontask.domain.order.exception.OrderCannotBeShippedTwice;
import com.kata.telldontask.domain.order.exception.ShippedOrdersCannotBeChanged;

public class OrderShipped extends OrderStatus {

  @Override
  public IOrderStatus approve() {
    throw new ShippedOrdersCannotBeChanged();
  }

  @Override
  public IOrderStatus reject() {
    throw new ShippedOrdersCannotBeChanged();
  }

  @Override
  public IOrderStatus ship() {
    throw new OrderCannotBeShippedTwice();
  }
}
