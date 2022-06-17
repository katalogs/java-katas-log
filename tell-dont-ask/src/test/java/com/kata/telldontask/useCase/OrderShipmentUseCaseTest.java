package com.kata.telldontask.useCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.OrderStatus;
import com.kata.telldontask.doubles.TestOrderRepository;
import com.kata.telldontask.doubles.TestShipmentService;
import org.junit.jupiter.api.Test;

class OrderShipmentUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private final TestShipmentService shipmentService = new TestShipmentService();
  private final OrderShipmentUseCase useCase = new OrderShipmentUseCase(orderRepository,
      shipmentService);

  @Test
  void shipApprovedOrder() {
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatus.APPROVED);
    orderRepository.addOrder(initialOrder);

    OrderShipmentRequest request = new OrderShipmentRequest();
    request.setOrderId(1);

    useCase.run(request);

    assertThat(orderRepository.getSavedOrder().getStatus()).isEqualTo(OrderStatus.SHIPPED);
    assertThat(shipmentService.getShippedOrder()).isEqualTo(initialOrder);
  }

  @Test
  void createdOrdersCannotBeShipped() {
    Order initialOrder = new Order();

    initialOrder.setStatus(OrderStatus.CREATED);
    orderRepository.addOrder(initialOrder);

    OrderShipmentRequest request = new OrderShipmentRequest();
    request.setOrderId(1);

    assertThatThrownBy(() -> useCase.run(request))
        .isInstanceOf(OrderCannotBeShippedException.class);

    assertThat(orderRepository.getSavedOrder()).isNull();
    assertThat(shipmentService.getShippedOrder()).isNull();
  }

  @Test
  void rejectedOrdersCannotBeShipped() {
    Order initialOrder = new Order();

    initialOrder.setStatus(OrderStatus.REJECTED);
    orderRepository.addOrder(initialOrder);

    OrderShipmentRequest request = new OrderShipmentRequest();
    request.setOrderId(1);

    assertThatThrownBy(() -> useCase.run(request))
        .isInstanceOf(OrderCannotBeShippedException.class);

    assertThat(orderRepository.getSavedOrder()).isNull();
    assertThat(shipmentService.getShippedOrder()).isNull();
  }

  @Test
  public void shippedOrdersCannotBeShippedAgain() {
    Order initialOrder = new Order();

    initialOrder.setStatus(OrderStatus.SHIPPED);
    orderRepository.addOrder(initialOrder);

    OrderShipmentRequest request = new OrderShipmentRequest();
    request.setOrderId(1);

    assertThatThrownBy(() -> useCase.run(request))
        .isInstanceOf(OrderCannotBeShippedTwiceException.class);

    assertThat(orderRepository.getSavedOrder()).isNull();
    assertThat(shipmentService.getShippedOrder()).isNull();
  }
}
