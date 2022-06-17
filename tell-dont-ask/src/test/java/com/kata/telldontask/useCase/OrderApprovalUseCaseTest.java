package com.kata.telldontask.useCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kata.telldontask.domain.ApprovedOrderCannotBeRejectedException;
import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.OrderStatus;
import com.kata.telldontask.domain.RejectedOrderCannotBeApprovedException;
import com.kata.telldontask.domain.ShippedOrdersCannotBeChangedException;
import com.kata.telldontask.doubles.TestOrderRepository;
import org.junit.jupiter.api.Test;

class OrderApprovalUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private final OrderApprovalUseCase useCase = new OrderApprovalUseCase(orderRepository);

  @Test
  void approvedExistingOrder() {
    Order initialOrder = new Order();

    orderRepository.addOrder(initialOrder);

    OrderApprovalRequest request = new OrderApprovalRequest(1, true);

    useCase.run(request);

    final Order savedOrder = orderRepository.getSavedOrder();
    assertThat(savedOrder.getStatus())
        .isEqualTo(OrderStatus.APPROVED);
  }

  @Test
  void rejectedExistingOrder() {
    Order initialOrder = new Order();

    orderRepository.addOrder(initialOrder);

    OrderApprovalRequest request = new OrderApprovalRequest(1, false);

    useCase.run(request);

    final Order savedOrder = orderRepository.getSavedOrder();
    assertThat(savedOrder.getStatus())
        .isEqualTo(OrderStatus.REJECTED);
  }

  @Test
  void cannotApproveRejectedOrder() {
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatus.REJECTED);

    orderRepository.addOrder(initialOrder);

    OrderApprovalRequest request = new OrderApprovalRequest(1, true);

    assertThatThrownBy(() -> useCase.run(request))
        .isInstanceOf(RejectedOrderCannotBeApprovedException.class);

    assertThat(orderRepository.getSavedOrder())
        .isNull();
  }

  @Test
  void cannotRejectApprovedOrder() {
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatus.APPROVED);

    orderRepository.addOrder(initialOrder);

    OrderApprovalRequest request = new OrderApprovalRequest(1, false);

    assertThatThrownBy(() -> useCase.run(request))
        .isInstanceOf(ApprovedOrderCannotBeRejectedException.class);

    assertThat(orderRepository.getSavedOrder())
        .isNull();
  }

  @Test
  void shippedOrdersCannotBeApproved() {
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatus.SHIPPED);

    orderRepository.addOrder(initialOrder);

    OrderApprovalRequest request = new OrderApprovalRequest(1, true);

    assertThatThrownBy(() -> useCase.run(request))
        .isInstanceOf(ShippedOrdersCannotBeChangedException.class);

    assertThat(orderRepository.getSavedOrder())
        .isNull();
  }

  @Test
  void shippedOrdersCannotBeRejected() {
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatus.SHIPPED);

    orderRepository.addOrder(initialOrder);

    OrderApprovalRequest request = new OrderApprovalRequest(1, false);

    assertThatThrownBy(() -> useCase.run(request))
        .isInstanceOf(ShippedOrdersCannotBeChangedException.class);

    assertThat(orderRepository.getSavedOrder())
        .isNull();
  }
}
