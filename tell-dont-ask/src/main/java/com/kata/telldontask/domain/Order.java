package com.kata.telldontask.domain;

import com.kata.telldontask.domain.common.Amount;
import com.kata.telldontask.domain.order.IOrderStatus;
import com.kata.telldontask.domain.order.OrderItem;
import com.kata.telldontask.domain.order.status.OrderCreated;
import java.util.ArrayList;
import java.util.List;

public class Order implements IOrderStatus {

  private Amount total;
  private String currency;
  private List<OrderItem> items;
  private Amount tax;
  private IOrderStatus status;
  private int id;

  public static Order create(String currency) {
    return new Order(currency);
  }

  public Order() {
    this("EUR");
  }

  private Order(String currency) {
    status = new OrderCreated();
    setItems(new ArrayList<>());
    setCurrency(currency);
    setTotal(new Amount(0.00));
    setTax(new Amount(0.00));
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

  public IOrderStatus getStatus() {
    return status;
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

  public IOrderStatus approve() {
    this.status = this.status.approve();
    return this;
  }

  public IOrderStatus reject() {
    this.status = this.status.reject();
    return this;
  }

  public IOrderStatus ship() {
    this.status = this.status.ship();
    return this;
  }
}
