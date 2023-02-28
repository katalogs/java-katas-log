package com.kata.telldontask.useCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.order.OrderStatusEnum;
import com.kata.telldontask.domain.order.exception.ApprovedOrderCannotBeRejected;
import com.kata.telldontask.domain.order.exception.RejectedOrderCannotBeApproved;
import com.kata.telldontask.domain.order.exception.ShippedOrdersCannotBeChanged;
import com.kata.telldontask.doubles.TestOrderRepository;
import com.kata.telldontask.useCase.orderApproval.OrderApprovalRequest;
import com.kata.telldontask.useCase.orderApproval.OrderApprovalUseCase;
import org.junit.jupiter.api.Test;

class OrderApprovalUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private final OrderApprovalUseCase useCase = new OrderApprovalUseCase(orderRepository);

  @Test
  void approvedExistingOrder() throws Exception {
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatusEnum.CREATED);
    initialOrder.setId(1);
    orderRepository.addOrder(initialOrder);

    OrderApprovalRequest request = new OrderApprovalRequest();
    request.setOrderId(1);
    request.setApproved(true);

    useCase.run(request);

    final Order savedOrder = orderRepository.getSavedOrder();
    assertThat(savedOrder.getOrderStatus())
        .isEqualTo(OrderStatusEnum.APPROVED);
  }

  @Test
  void rejectedExistingOrder() throws Exception {
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatusEnum.CREATED);
    initialOrder.setId(1);
    orderRepository.addOrder(initialOrder);

    OrderApprovalRequest request = new OrderApprovalRequest();
    request.setOrderId(1);
    request.setApproved(false);

    useCase.run(request);

    final Order savedOrder = orderRepository.getSavedOrder();
    assertThat(savedOrder.getOrderStatus())
        .isEqualTo(OrderStatusEnum.REJECTED);
  }

  @Test
  void cannotApproveRejectedOrder() {
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatusEnum.REJECTED);
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
    initialOrder.setStatus(OrderStatusEnum.APPROVED);
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
    initialOrder.setStatus(OrderStatusEnum.SHIPPED);
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
    initialOrder.setStatus(OrderStatusEnum.SHIPPED);
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
