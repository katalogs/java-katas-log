package com.kata.telldontask.useCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.order.OrderStatusEnum;
import com.kata.telldontask.domain.order.exception.OrderCannotBeShipped;
import com.kata.telldontask.domain.order.exception.OrderCannotBeShippedTwice;
import com.kata.telldontask.domain.order.status.OrderShipped;
import com.kata.telldontask.doubles.TestOrderRepository;
import com.kata.telldontask.doubles.TestOrderShipment;
import com.kata.telldontask.useCase.orderShipment.OrderShipmentRequest;
import com.kata.telldontask.useCase.orderShipment.OrderShipmentUseCase;
import org.junit.jupiter.api.Test;

class OrderShipmentUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private final TestOrderShipment shipmentService = new TestOrderShipment(orderRepository);
  private final OrderShipmentUseCase useCase = new OrderShipmentUseCase(orderRepository, shipmentService);

  @Test
  void shipApprovedOrder() {
    Order initialOrder = new Order();
    initialOrder.setId(1);
    initialOrder.setStatus(OrderStatusEnum.APPROVED);
    orderRepository.addOrder(initialOrder);

    OrderShipmentRequest request = new OrderShipmentRequest();
    request.setOrderId(1);

    useCase.run(request);

    assertThat(orderRepository.getSavedOrder().getStatus()).isEqualTo(new OrderShipped());
    assertThat(shipmentService.getShippedOrder()).isEqualTo(initialOrder);
  }

  @Test
  void createdOrdersCannotBeShipped() {
    Order initialOrder = new Order();
    initialOrder.setId(1);
    initialOrder.setStatus(OrderStatusEnum.CREATED);
    orderRepository.addOrder(initialOrder);

    OrderShipmentRequest request = new OrderShipmentRequest();
    request.setOrderId(1);

    assertThatThrownBy(() -> useCase.run(request))
        .isInstanceOf(OrderCannotBeShipped.class);

    assertThat(orderRepository.getSavedOrder()).isNull();
    assertThat(shipmentService.getShippedOrder()).isNull();
  }

  @Test
  void rejectedOrdersCannotBeShipped() {
    Order initialOrder = new Order();
    initialOrder.setId(1);
    initialOrder.setStatus(OrderStatusEnum.REJECTED);
    orderRepository.addOrder(initialOrder);

    OrderShipmentRequest request = new OrderShipmentRequest();
    request.setOrderId(1);

    assertThatThrownBy(() -> useCase.run(request))
        .isInstanceOf(OrderCannotBeShipped.class);

    assertThat(orderRepository.getSavedOrder()).isNull();
    assertThat(shipmentService.getShippedOrder()).isNull();
  }

  @Test
  public void shippedOrdersCannotBeShippedAgain() {
    Order initialOrder = new Order();
    initialOrder.setId(1);
    initialOrder.setStatus(OrderStatusEnum.SHIPPED);
    orderRepository.addOrder(initialOrder);

    OrderShipmentRequest request = new OrderShipmentRequest();
    request.setOrderId(1);

    assertThatThrownBy(() -> useCase.run(request))
        .isInstanceOf(OrderCannotBeShippedTwice.class);

    assertThat(orderRepository.getSavedOrder()).isNull();
    assertThat(shipmentService.getShippedOrder()).isNull();
  }
}
