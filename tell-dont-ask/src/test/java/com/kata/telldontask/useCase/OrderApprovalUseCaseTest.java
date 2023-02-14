package com.kata.telldontask.useCase;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.order.OrderStatus;
import com.kata.telldontask.domain.order.exception.ApprovedOrderCannotBeRejected;
import com.kata.telldontask.domain.order.exception.RejectedOrderCannotBeApproved;
import com.kata.telldontask.domain.order.exception.ShippedOrdersCannotBeChanged;
import com.kata.telldontask.doubles.TestOrderRepository;
import com.kata.telldontask.useCase.orderApproval.OrderApprovalRequest;
import com.kata.telldontask.useCase.orderApproval.OrderApprovalUseCase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderApprovalUseCaseTest {
    private final TestOrderRepository orderRepository = new TestOrderRepository();
    private final OrderApprovalUseCase useCase = new OrderApprovalUseCase(orderRepository);

    @Test
    void approvedExistingOrder() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setStatus(OrderStatus.CREATED);
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(true);

        useCase.run(request);

        final Order savedOrder = orderRepository.getSavedOrder();
        assertThat(savedOrder.getStatus())
                .isEqualTo(OrderStatus.APPROVED);
    }

    @Test
    void rejectedExistingOrder() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setStatus(OrderStatus.CREATED);
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(false);

        useCase.run(request);

        final Order savedOrder = orderRepository.getSavedOrder();
        assertThat(savedOrder.getStatus())
                .isEqualTo(OrderStatus.REJECTED);
    }

    @Test
    void cannotApproveRejectedOrder() {
        Order initialOrder = new Order();
        initialOrder.setStatus(OrderStatus.REJECTED);
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(true);

        assertThatThrownBy(() -> useCase.run(request))
                .isInstanceOf(RejectedOrderCannotBeApproved.class);

        assertThat(orderRepository.getSavedOrder())
                .isNull();
    }

    @Test
    void cannotRejectApprovedOrder() {
        Order initialOrder = new Order();
        initialOrder.setStatus(OrderStatus.APPROVED);
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(false);

        assertThatThrownBy(() -> useCase.run(request))
                .isInstanceOf(ApprovedOrderCannotBeRejected.class);

        assertThat(orderRepository.getSavedOrder())
                .isNull();
    }

    @Test
    void shippedOrdersCannotBeApproved() {
        Order initialOrder = new Order();
        initialOrder.setStatus(OrderStatus.SHIPPED);
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(true);

        assertThatThrownBy(() -> useCase.run(request))
                .isInstanceOf(ShippedOrdersCannotBeChanged.class);

        assertThat(orderRepository.getSavedOrder())
                .isNull();
    }

    @Test
    void shippedOrdersCannotBeRejected() {
        Order initialOrder = new Order();
        initialOrder.setStatus(OrderStatus.SHIPPED);
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(false);

        assertThatThrownBy(() -> useCase.run(request))
                .isInstanceOf(ShippedOrdersCannotBeChanged.class);

        assertThat(orderRepository.getSavedOrder())
                .isNull();
    }
}
