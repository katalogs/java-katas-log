package com.kata.telldontask.domain.order;

import com.kata.telldontask.domain.order.exception.ApprovedOrderCannotBeRejected;
import com.kata.telldontask.domain.order.exception.RejectedOrderCannotBeApproved;
import com.kata.telldontask.domain.order.exception.ShippedOrdersCannotBeChanged;
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

  public OrderStatus approve() {
    if (this instanceof OrderShipped) {
      throw new ShippedOrdersCannotBeChanged();
    }
    if (this instanceof OrderRejected) {
      throw new RejectedOrderCannotBeApproved();
    }
    return new OrderApproved();
  }


  public OrderStatus reject() {
    if (this instanceof OrderShipped) {
      throw new ShippedOrdersCannotBeChanged();
    }
    if (this instanceof OrderApproved) {
      throw new ApprovedOrderCannotBeRejected();
    }
    return new OrderRejected();
  }
}
