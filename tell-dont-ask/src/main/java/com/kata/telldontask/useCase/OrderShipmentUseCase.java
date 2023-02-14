package com.kata.telldontask.useCase;

import com.kata.telldontask.domain.exception.OrderCannotBeShipped;
import com.kata.telldontask.domain.exception.OrderCannotBeShippedTwice;
import com.kata.telldontask.repository.OrderRepository;
import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.OrderStatus;
import com.kata.telldontask.service.ShipmentService;

import static com.kata.telldontask.domain.OrderStatus.CREATED;
import static com.kata.telldontask.domain.OrderStatus.REJECTED;
import static com.kata.telldontask.domain.OrderStatus.SHIPPED;

public class OrderShipmentUseCase {
    private final OrderRepository orderRepository;
    private final ShipmentService shipmentService;

    public OrderShipmentUseCase(OrderRepository orderRepository, ShipmentService shipmentService) {
        this.orderRepository = orderRepository;
        this.shipmentService = shipmentService;
    }

    public void run(OrderShipmentRequest request) {
        final Order order = orderRepository.getById(request.getOrderId());

        if (order.getStatus().equals(CREATED) || order.getStatus().equals(REJECTED)) {
            throw new OrderCannotBeShipped();
        }

        if (order.getStatus().equals(SHIPPED)) {
            throw new OrderCannotBeShippedTwice();
        }

        shipmentService.ship(order);

        order.setStatus(OrderStatus.SHIPPED);
        orderRepository.save(order);
    }
}
