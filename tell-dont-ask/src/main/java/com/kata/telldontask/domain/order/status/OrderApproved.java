package com.kata.telldontask.domain.order.status;

import com.kata.telldontask.domain.order.OrderStatus;
import com.kata.telldontask.domain.order.OrderStatusEnum;

public class OrderApproved extends OrderStatus {

  @Override
  public OrderStatusEnum getStatus() {
    return OrderStatusEnum.APPROVED;
  }
}
