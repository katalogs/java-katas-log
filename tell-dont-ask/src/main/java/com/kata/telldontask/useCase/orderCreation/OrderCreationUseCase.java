package com.kata.telldontask.useCase.orderCreation;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.Product;
import com.kata.telldontask.repository.OrderRepository;
import com.kata.telldontask.repository.ProductCatalog;

public class OrderCreationUseCase {

  private final OrderRepository orderRepository;
  private final ProductCatalog productCatalog;

  public OrderCreationUseCase(OrderRepository orderRepository, ProductCatalog productCatalog) {
    this.orderRepository = orderRepository;
    this.productCatalog = productCatalog;
  }

  public void run(SellItemsRequest request) {
    Order order = Order.create("EUR");

    for (SellItemRequest itemRequest : request.getRequests()) {
      Product product = productCatalog.getByName(itemRequest.getProductName());
      order.addItem(product, itemRequest.getQuantity());
    }

    orderRepository.save(order);
  }
}
