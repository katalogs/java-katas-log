package com.kata.telldontask.useCase;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.repository.OrderRepository;

public class OrderApprovalUseCase {

  private final OrderRepository orderRepository;

  public OrderApprovalUseCase(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public void run(OrderApprovalRequest request) {
    final Order order = orderRepository.getById(request.getOrderId());

    order.updateStatus(request);

    orderRepository.save(order);
  }

}
