package com.kata.telldontask.doubles;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.repository.OrderRepository;
import com.kata.telldontask.service.OrderShipment;

public class TestOrderShipment implements OrderShipment {

    private final OrderRepository orderRepository;

    private Order shippedOrder = null;

    public TestOrderShipment(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getShippedOrder() {
        return shippedOrder;
    }

    @Override
    public void ship(Order order) {

        order.ship();
        orderRepository.save(order);

        this.shippedOrder = order;
    }
}
