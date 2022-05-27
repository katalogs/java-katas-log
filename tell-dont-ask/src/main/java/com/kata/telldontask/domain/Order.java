package com.kata.telldontask.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.kata.telldontask.useCase.SellItemRequest;

public class Order {

  private BigDecimal total;
  private String currency;
  private List<OrderItem> items;
  private BigDecimal tax;
  private OrderStatus status;
  private int id;

  public Order() {
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

  public void setId(int id) {
    this.id = id;
  }

public void addItem(Product product, SellItemRequest itemRequest) {
	final OrderItem orderItem = new OrderItem(product, itemRequest);
	this.getItems().add(orderItem);
    this.setTotal(this.getTotal().add(orderItem.getTaxedAmount()));
    this.setTax(this.getTax().add(orderItem.getTax()));
}


}
