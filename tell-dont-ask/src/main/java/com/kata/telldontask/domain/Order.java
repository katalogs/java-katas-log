package com.kata.telldontask.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

  private BigDecimal total;
  private String currency;
  private List<OrderItem> items;
  private Amount tax;
  private OrderStatus status;
  private int id;

  public static Order create(String currency) {
    Order order = new Order();
    order.setStatus(OrderStatus.CREATED);
    order.setItems(new ArrayList<>());
    order.setCurrency(currency);
    order.setTotal(new BigDecimal("0.00"));
    order.setTax(new Amount(0.00));
    return order;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
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

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
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
}
