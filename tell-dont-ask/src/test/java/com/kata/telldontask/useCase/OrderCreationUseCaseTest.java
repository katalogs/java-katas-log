package com.kata.telldontask.useCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kata.telldontask.domain.Order;
import com.kata.telldontask.domain.Product;
import com.kata.telldontask.domain.common.Amount;
import com.kata.telldontask.domain.order.exception.UnknownProduct;
import com.kata.telldontask.domain.order.status.OrderCreated;
import com.kata.telldontask.domain.product.Category;
import com.kata.telldontask.domain.repository.ProductCatalog;
import com.kata.telldontask.doubles.InMemoryProductCatalog;
import com.kata.telldontask.doubles.TestOrderRepository;
import com.kata.telldontask.useCase.orderCreation.OrderCreationUseCase;
import com.kata.telldontask.useCase.orderCreation.SellItemRequest;
import com.kata.telldontask.useCase.orderCreation.SellItemsRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class OrderCreationUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private Category food = new Category() {{
    setName("food");
    setTaxPercentage(new BigDecimal("10"));
  }};

  private final ProductCatalog productCatalog = new InMemoryProductCatalog(
      Arrays.<Product>asList(
          new Product() {{
            setName("salad");
            setPrice(new BigDecimal("3.56"));
            setCategory(food);
          }},
          new Product() {{
            setName("tomato");
            setPrice(new BigDecimal("4.65"));
            setCategory(food);
          }}
      )
  );
  private final OrderCreationUseCase useCase = new OrderCreationUseCase(orderRepository, productCatalog);

  @Test
  void sellMultipleItems() throws Exception {
    SellItemRequest saladRequest = new SellItemRequest();
    saladRequest.setProductName("salad");
    saladRequest.setQuantity(2);

    SellItemRequest tomatoRequest = new SellItemRequest();
    tomatoRequest.setProductName("tomato");
    tomatoRequest.setQuantity(3);

    final SellItemsRequest request = new SellItemsRequest();
    request.setRequests(new ArrayList<>());
    request.getRequests().add(saladRequest);
    request.getRequests().add(tomatoRequest);

    useCase.run(request);

    final Order insertedOrder = orderRepository.getSavedOrder();

    assertThat(insertedOrder.getStatus()).isEqualTo(new OrderCreated());
    assertThat(insertedOrder.getTotal()).isEqualTo(new Amount("23.20"));
    assertThat(insertedOrder.getTax()).isEqualTo(new Amount("2.13"));
    assertThat(insertedOrder.getCurrency()).isEqualTo("EUR");
    assertThat(insertedOrder.getItems()).hasSize(2);
    assertThat(insertedOrder.getItems().get(0).getProduct().getName()).isEqualTo("salad");
    assertThat(insertedOrder.getItems().get(0).getProduct().getPrice()).isEqualTo(new Amount("3.56"));
    assertThat(insertedOrder.getItems().get(0).getQuantity()).isEqualTo(2);
    assertThat(insertedOrder.getItems().get(0).getTaxedAmount()).isEqualTo(new Amount("7.84"));
    assertThat(insertedOrder.getItems().get(0).getTax()).isEqualTo(new Amount("0.72"));
    assertThat(insertedOrder.getItems().get(1).getProduct().getName()).isEqualTo("tomato");
    assertThat(insertedOrder.getItems().get(1).getProduct().getPrice()).isEqualTo(new Amount("4.65"));
    assertThat(insertedOrder.getItems().get(1).getQuantity()).isEqualTo(3);
    assertThat(insertedOrder.getItems().get(1).getTaxedAmount()).isEqualTo(new Amount("15.36"));
    assertThat(insertedOrder.getItems().get(1).getTax()).isEqualTo(new Amount("1.41"));
  }

  @Test
  void unknownProduct() {
    SellItemsRequest request = new SellItemsRequest();
    request.setRequests(new ArrayList<>());
    SellItemRequest unknownProductRequest = new SellItemRequest();
    unknownProductRequest.setProductName("unknown product");
    request.getRequests().add(unknownProductRequest);

    assertThatThrownBy(() -> useCase.run(request))
        .isInstanceOf(UnknownProduct.class);
  }

}
