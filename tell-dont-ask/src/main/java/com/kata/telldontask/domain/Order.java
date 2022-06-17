package com.kata.telldontask.domain;

import static com.kata.telldontask.domain.OrderStatus.CREATED;
import static com.kata.telldontask.domain.OrderStatus.REJECTED;
import static com.kata.telldontask.domain.OrderStatus.SHIPPED;

import com.kata.telldontask.useCase.SellItemRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Order {

  private BigDecimal total;
  private String currency;
  private List<OrderItem> items;
  private BigDecimal tax;
  private OrderStatus status;
  private int id;

  public Order() {
    this.id = 1;
    this.total = BigDecimal.ZERO;
    this.currency = "EUR";
    this.items = new ArrayList<>();
    this.tax = BigDecimal.ZERO;
    this.status = OrderStatus.CREATED;
  }

  public BigDecimal getTotal() {
    return total;
  }

  private void setTotal(BigDecimal total) {
    this.total = total;
  }

  public String getCurrency() {
    return currency;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public BigDecimal getTax() {
    return tax;
  }

  private void setTax(BigDecimal tax) {
    this.tax = tax;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void addItem(Product product, SellItemRequest itemRequest) {
    final OrderItem orderItem = new OrderItem(product, itemRequest);
    this.getItems().add(orderItem);
    this.setTotal(this.getTotal().add(orderItem.getTaxedAmount()));
    this.setTax(this.getTax().add(orderItem.getTax()));
  }

  public void approve(boolean isApproved) {
    if (status == OrderStatus.SHIPPED) {
      throw new ShippedOrdersCannotBeChangedException();
    }

    if (isApproved && status == OrderStatus.REJECTED) {
      throw new RejectedOrderCannotBeApprovedException();
    }

    if (!isApproved && status == OrderStatus.APPROVED) {
      throw new ApprovedOrderCannotBeRejectedException();
    }
    setStatus(isApproved ? OrderStatus.APPROVED : OrderStatus.REJECTED);
  }

  public void ship(Consumer<Order> consumer) {
    if (getStatus().equals(CREATED) || getStatus().equals(REJECTED)) {
      throw new OrderCannotBeShippedException();
    }

    if (getStatus().equals(SHIPPED)) {
      throw new OrderCannotBeShippedTwiceException();
    }

    consumer.accept(this);

    setStatus(OrderStatus.SHIPPED);
  }
}
