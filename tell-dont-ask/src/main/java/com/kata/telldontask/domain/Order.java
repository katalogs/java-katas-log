package com.kata.telldontask.domain;

import com.kata.telldontask.domain.common.Amount;
import com.kata.telldontask.domain.order.OrderAssertion;
import com.kata.telldontask.domain.order.OrderItem;
import com.kata.telldontask.domain.order.OrderStatus;
import com.kata.telldontask.domain.order.OrderStatusEnum;
import java.util.ArrayList;
import java.util.List;

public class Order {

  private Amount total;
  private String currency;
  private List<OrderItem> items;
  private Amount tax;
  private OrderStatus status;
  private int id;

  public static Order create(String currency) {
    Order order = new Order();
    order.setStatus(OrderStatusEnum.CREATED);
    order.setItems(new ArrayList<>());
    order.setCurrency(currency);
    order.setTotal(new Amount(0.00));
    order.setTax(new Amount(0.00));
    return order;
  }

  public Amount getTotal() {
    return total;
  }

  public void setTotal(Amount total) {
    this.total = total;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public void setItems(List<OrderItem> items) {
    this.items = items;
  }

  public Amount getTax() {
    return tax;
  }

  public void setTax(Amount tax) {
    this.tax = tax;
  }

  public OrderStatusEnum getStatus() {
    return status.getStatus();
  }

  public OrderStatusEnum getOrderStatus() {
    return status.getStatus();
  }

  public void setStatus(OrderStatusEnum status) {
    this.status = OrderStatus.create(status);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void addItem(Product product, int quantity) {
    final OrderItem orderItem = new OrderItem(product, quantity);

    items.add(orderItem);
    total = total.add(orderItem.getTaxedAmount());
    tax = tax.add(orderItem.getTax());
  }

  public void approve() {
    this.status = this.status.approve();
  }

  public void reject() {
    this.status = this.status.reject();
  }
  
  public void ship() {
    OrderAssertion.assertNotCreatedOrRejected(this);
    OrderAssertion.assertNotAlreadyShipped(this);
    this.status = OrderStatus.create(OrderStatusEnum.SHIPPED);
  }
}
