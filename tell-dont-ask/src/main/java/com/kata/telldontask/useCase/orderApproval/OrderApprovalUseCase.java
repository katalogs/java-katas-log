package com.kata.telldontask.useCase.orderApproval;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.OrderStatus;
import com.kata.telldontask.repository.OrderRepository;
import com.kata.telldontask.domain.exception.ApprovedOrderCannotBeRejected;
import com.kata.telldontask.domain.exception.RejectedOrderCannotBeApproved;
import com.kata.telldontask.domain.exception.ShippedOrdersCannotBeChanged;

public class OrderApprovalUseCase {
    private final OrderRepository orderRepository;

    public OrderApprovalUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void run(OrderApprovalRequest request) {
        final Order order = orderRepository.getById(request.getOrderId());

        if (order.getStatus().equals(OrderStatus.SHIPPED)) {
            throw new ShippedOrdersCannotBeChanged();
        }

        if (request.isApproved() && order.getStatus().equals(OrderStatus.REJECTED)) {
            throw new RejectedOrderCannotBeApproved();
        }

        if (!request.isApproved() && order.getStatus().equals(OrderStatus.APPROVED)) {
            throw new ApprovedOrderCannotBeRejected();
        }

        order.setStatus(request.isApproved() ? OrderStatus.APPROVED : OrderStatus.REJECTED);
        orderRepository.save(order);
    }
}
