package com.kata.telldontask.domain.order;

import static com.kata.telldontask.domain.order.OrderStatusEnum.CREATED;
import static com.kata.telldontask.domain.order.OrderStatusEnum.REJECTED;
import static com.kata.telldontask.domain.order.OrderStatusEnum.SHIPPED;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.order.exception.ApprovedOrderCannotBeRejected;
import com.kata.telldontask.domain.order.exception.OrderCannotBeShipped;
import com.kata.telldontask.domain.order.exception.OrderCannotBeShippedTwice;
import com.kata.telldontask.domain.order.exception.RejectedOrderCannotBeApproved;
import com.kata.telldontask.domain.order.exception.ShippedOrdersCannotBeChanged;

public class OrderAssertion {


  public static void assertNotRejected(Order order) {
    if (order.getOrderStatus().equals(OrderStatusEnum.REJECTED)) {
      throw new RejectedOrderCannotBeApproved();
    }
  }

  public static void assertNotShipped(Order order) {
    if (order.getOrderStatus().equals(OrderStatusEnum.SHIPPED)) {
      throw new ShippedOrdersCannotBeChanged();
    }
  }


  public static void assertNotApproved(Order order) {
    if (order.getOrderStatus().equals(OrderStatusEnum.APPROVED)) {
      throw new ApprovedOrderCannotBeRejected();
    }
  }

  public static void assertNotCreatedOrRejected(Order order) {
    if (order.getOrderStatus().equals(CREATED) || order.getOrderStatus().equals(REJECTED)) {
      throw new OrderCannotBeShipped();
    }
  }

  public static void assertNotAlreadyShipped(Order order) {
    if (order.getOrderStatus().equals(SHIPPED)) {
      throw new OrderCannotBeShippedTwice();
    }
  }
}
