package com.kata.telldontask.useCase;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.OrderItem;
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
    Order order = new Order();

    for (SellItemRequest itemRequest : request.getRequests()) {
      Product product = productCatalog.getByName(itemRequest.getProductName());

      if (product == null) {
        throw new UnknownProductException();
      } else {

        final OrderItem orderItem = new OrderItem(product, itemRequest);
        order.getItems().add(orderItem);
        order.setTotal(order.getTotal().add(orderItem.getTaxedAmount()));
        order.setTax(order.getTax().add(orderItem.getTax()));
      }
    }

    orderRepository.save(order);
  }
}
