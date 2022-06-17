package com.kata.telldontask.useCase;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.repository.OrderRepository;
import com.kata.telldontask.service.ShipmentService;

public class OrderShipmentUseCase {

  private final OrderRepository orderRepository;
  private final ShipmentService shipmentService;

  public OrderShipmentUseCase(OrderRepository orderRepository, ShipmentService shipmentService) {
    this.orderRepository = orderRepository;
    this.shipmentService = shipmentService;
  }

  public void run(OrderShipmentRequest request) {
    final Order order = orderRepository.getById(request.getOrderId());

    order.ship(shipmentService::ship);
    orderRepository.save(order);
  }

}
