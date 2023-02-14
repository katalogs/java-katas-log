package com.kata.telldontask.useCase.orderShipment;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.repository.OrderRepository;
import com.kata.telldontask.service.OrderShipment;

public class OrderShipmentUseCase {
    private final OrderRepository orderRepository;
    private final OrderShipment orderShipment;

    public OrderShipmentUseCase(OrderRepository orderRepository, OrderShipment shipmentService) {
        this.orderRepository = orderRepository;
        this.orderShipment = shipmentService;
    }

    public void run(OrderShipmentRequest request) {
        final Order order = orderRepository.getById(request.getOrderId());
        orderShipment.ship(order);
    }
}
